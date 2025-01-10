package br.com.credsystem.service.impl;

import br.com.credsystem.common.enumeration.TipoRetorno;
import br.com.credsystem.common.util.DateUtils;
import br.com.credsystem.dto.BufferUnicoCargaDTO;
import br.com.credsystem.dto.ConsultaBureauxDTO;
import br.com.credsystem.dto.request.consultaSerasaRequestDTO;
import br.com.credsystem.dto.response.BureauxRegistroResponseDTO;
import br.com.credsystem.integration.api.SerasaAPI;
import br.com.credsystem.model.LogSerasaRetorno;
import br.com.credsystem.model.SerasaSenha;
import br.com.credsystem.service.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;

@Service
@Setter(onMethod_ = @Autowired)
public class ConsultaServiceImpl implements ConsultaService {

    LogSerasaService logSerasaService;
    BindBureauxService bindBureauxService;
    SerasaService serasaService;
    SerasaSenhaService serasaSenhaService;
    BufferUnicoCargaService bufferUnicoCargaService;
    SerasaAPI serasaAPI;

    public ConsultaServiceImpl(LogSerasaService logSerasaService, BindBureauxService bindBureauxService, SerasaService serasaService, SerasaSenhaService serasaSenhaService, BufferUnicoCargaService bufferUnicoCargaService, SerasaAPI serasaAPI) {
        this.logSerasaService = logSerasaService;
        this.bindBureauxService = bindBureauxService;
        this.serasaService = serasaService;
        this.serasaSenhaService = serasaSenhaService;
        this.bufferUnicoCargaService = bufferUnicoCargaService;
        this.serasaAPI = serasaAPI;
    }

    @Override
    public BureauxRegistroResponseDTO consultaSerasaPorId(Integer id, TipoRetorno tipoRetorno) throws Exception {
        LogSerasaRetorno logSerasaRetorno = logSerasaService.findByIdEnvio(BigInteger.valueOf(id));
        BureauxRegistroResponseDTO retorno = BureauxRegistroResponseDTO.builder()
                .origemBuffer(true)
                .cpf(logSerasaRetorno.getLogSerasaEnvio().getCpf())
                .dataConsulta(logSerasaRetorno.getLogSerasaEnvio().getData())
                .build();
        bindBureauxService.bindBureauxRegistro(logSerasaRetorno, retorno, tipoRetorno);
        return retorno;
    }

    @Override
    public BureauxRegistroResponseDTO consultaSerasa(consultaSerasaRequestDTO request) throws Exception {
        BureauxRegistroResponseDTO retorno = BureauxRegistroResponseDTO.builder()
                .cpf(request.cpf())
                .build();

        ConsultaBureauxDTO dto = buildDto(request);
        SerasaSenha serasaSenha = serasaSenhaService.buscarPorId(request.rotina());
        LogSerasaRetorno logRetorno = logSerasaService.findTpConsultaApartirDe(dto, request, serasaSenha);
        dto.setLogSerasaRetorno(logRetorno);
        if (TipoRetorno.getByDescription(request.tipo()) == TipoRetorno.RESUMIDO) {
            getByDescriptionResumido(request, dto, serasaSenha);
        }
        if (dto.getLogSerasaRetorno() != null && dto.getLogSerasaRetorno().getLogSerasaEnvio() != null && dto.getLogSerasaRetorno().getLogSerasaEnvio().getCpf() != null) {
            retorno.setOrigemBuffer(true);
            dto.setReaproveita("S");
        } else {
            dto.setLogSerasaRetorno(serasaAPI.getResultado(request.cpf(), dto.getProduto(), request.tipo().charAt(0), serasaSenha, request.layoutFile(), request.httpConnectTentativas(), request.httpConnectTimeoutSecs()));
        }

        bindBureauxService.bindBureauxRegistro(dto.getLogSerasaRetorno(), retorno, TipoRetorno.getByDescription(request.tipoRetorno()));
        String origemConsulta = "GESTAO";
        if (request.rotina().equals("CREDLINE") || request.rotina().equals("VIPAGEM")) {
            origemConsulta = "INICIACAO";
        }
        if (dto.getCarga() == null) {
            int posicaoScore = 0;
            if (dto.getLogSerasaRetorno().getRetorno().indexOf("CB280") > 0) {
                posicaoScore = dto.getLogSerasaRetorno().getRetorno().indexOf("CB280");
            } else if (dto.getLogSerasaRetorno().getRetorno().indexOf(" B280") > 0) {
                posicaoScore = dto.getLogSerasaRetorno().getRetorno().indexOf(" B280");
            }
            if (posicaoScore > 0) {
                dto.setModelo(dto.getLogSerasaRetorno().getRetorno().substring(posicaoScore + 5, posicaoScore + 9));
            }
        } else {
            dto.setModelo(dto.getCarga().getModelo());
        }

        serasaService.salvarBufferUnico(dto.getLogSerasaRetorno(),
                                        dto.getModelo(), origemConsulta, request.rotina(),
                                        dto.getReaproveita(), dto.getTipoConsulta(),
                                        request.proposta());

        retorno.setTempoBufferUtilizado(serasaSenha.getDiasRetroativos());
        retorno.setTempoBufferProcesso(DateUtils.iDiffDays(dto.getLogSerasaRetorno().getLogSerasaEnvio().getData(), new Date()));
        retorno.setUsuarioConsulta(dto.getLogSerasaRetorno().getLogSerasaEnvio().getId().toString());
        retorno.setDataConsulta(dto.getLogSerasaRetorno().getLogSerasaEnvio().getData());

        return retorno;
    }

    private void getByDescriptionResumido(consultaSerasaRequestDTO request, ConsultaBureauxDTO dto, SerasaSenha serasaSenha) {
        dto.setCarga(bufferUnicoCargaService.validaConsultaBureau(request.cpf(), serasaSenha.getDiasRetroativos()));
        if ((dto.getCarga() != null
                && dto.getCarga().getCpf() != null
                && dto.getLogSerasaRetorno() != null
                && dto.getLogSerasaRetorno().getLogSerasaEnvio() != null
                && dto.getLogSerasaRetorno().getLogSerasaEnvio().getCpf() != null
                && dto.getCarga().getDataEnvio().compareTo(dto.getLogSerasaRetorno().getLogSerasaEnvio().getData()) > 0)
                || (dto.getCarga() != null && dto.getCarga().getCpf() != null && dto.getLogSerasaRetorno() == null)) {

            dto.setLogSerasaRetorno(dto.getCarga().trataRetorno(dto.getCarga()));
            dto.setReaproveita("S");
            dto.setTipoConsulta("BATCH");
        }
    }

    private static ConsultaBureauxDTO buildDto(consultaSerasaRequestDTO request) {
        return ConsultaBureauxDTO.builder()
                .modelo("")
                .reaproveita("N")
                .tipoConsulta("ONLINE")
                .carga(null)
                .logSerasaRetorno(null)
                .produto(request.produto() != null ? request.produto().toUpperCase() : null)
                .build();
    }

    @Override
    public BureauxRegistroResponseDTO consultaSerasaLocal(consultaSerasaRequestDTO request) throws Exception {
        BureauxRegistroResponseDTO retorno = new BureauxRegistroResponseDTO(request.cpf());

        String produto = request.produto();

        if (null != produto) {
            produto = produto.toUpperCase();
        }

        SerasaSenha serasaSenha = serasaSenhaService.buscarPorId(request.rotina());

        BufferUnicoCargaDTO carga = null;
        LogSerasaRetorno logSerasaRetorno = null;

        logSerasaRetorno = logSerasaService.findTpConsultaApartirDe(request.cpf(), produto,
                                                                    request.tipo().charAt(0),
                                                                    serasaSenha.getDiasRetroativos(),
                                                                    serasaSenha.getUtilizaProduto());

        if (TipoRetorno.getByDescription(request.tipoRetorno()) == TipoRetorno.RESUMIDO) {

            carga = bufferUnicoCargaService.validaConsultaBureau(request.cpf(), serasaSenha.getDiasRetroativos());

            if ((carga != null
                    && carga.getCpf() != null
                    && logSerasaRetorno != null
                    && logSerasaRetorno.getLogSerasaEnvio() != null
                    && logSerasaRetorno.getLogSerasaEnvio().getCpf() != null
                    && carga.getDataEnvio().compareTo(logSerasaRetorno.getLogSerasaEnvio().getData()) > 0)
                    || (carga != null && carga.getCpf() != null && logSerasaRetorno == null)) {

                logSerasaRetorno = carga.trataRetorno(carga);
            }
        }

        if (logSerasaRetorno != null && logSerasaRetorno.getLogSerasaEnvio() != null && logSerasaRetorno.getLogSerasaEnvio().getCpf() != null) {
            retorno.setOrigemBuffer(true);
        } else {
            return null;
        }

        bindBureauxService.bindBureauxRegistro(logSerasaRetorno, retorno, TipoRetorno.getByDescription(request.tipoRetorno()));

        retorno.setTempoBufferUtilizado(serasaSenha.getDiasRetroativos());
        retorno.setTempoBufferProcesso(DateUtils.iDiffDays(logSerasaRetorno.getLogSerasaEnvio().getData(), new Date()));
        retorno.setUsuarioConsulta(logSerasaRetorno.getLogSerasaEnvio().getId().toString());
        retorno.setDataConsulta(logSerasaRetorno.getLogSerasaEnvio().getData());
        return retorno;

    }


}
