package br.com.credsystem.service.impl;

import br.com.credsystem.common.enumeration.TipoRetorno;
import br.com.credsystem.common.util.DateUtils;
import br.com.credsystem.dto.BufferUnicoCarga;
import br.com.credsystem.dto.BufferUnicoCargaDTO;
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
    @Autowired
    SerasaAPI serasaAPI;

    public ConsultaServiceImpl(LogSerasaService logSerasaService,
                               BindBureauxService bindBureauxService,
                               SerasaService serasaService,
                               SerasaSenhaService serasaSenhaService) {
        this.logSerasaService = logSerasaService;
        this.bindBureauxService = bindBureauxService;
        this.serasaService = serasaService;
        this.serasaSenhaService = serasaSenhaService;
    }

    @Override
    public BureauxRegistroResponseDTO consultaSerasaPorId(Integer id, TipoRetorno tipoRetorno) {
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
    public BureauxRegistroResponseDTO consultaSerasa(String cpf, String produto, String rotina, String tipo, String layoutFile, int httpConnectTentativas, int httpConnectTimeoutSecs, TipoRetorno tipoRetorno, String proposta) {
        BureauxRegistroResponseDTO retorno = BureauxRegistroResponseDTO.builder()
                .cpf(cpf)
                .build();

        String modelo = "";
        String reaproveita = "N";
        String tipoConsulta = "ONLINE";

        if (null != produto) {
            produto = produto.toUpperCase();
        }

        SerasaSenha serasaSenha = serasaSenhaService.buscarPorId(rotina);

        BufferUnicoCargaDTO carga = null;
        LogSerasaRetorno logSerasaRetorno = null;

        logSerasaRetorno = logSerasaService.findTpConsultaApartirDe(cpf, produto, tipo.charAt(0), serasaSenha.getDiasRetroativos(), serasaSenha.getUtilizaProduto());

        if (tipoRetorno == TipoRetorno.RESUMIDO) {

            carga = bufferUnicoCargaService.validaConsultaBureau(cpf, serasaSenha.getDiasRetroativos());

            if ((carga != null
                    && carga.getCpf() != null
                    && logSerasaRetorno != null
                    && logSerasaRetorno.getLogSerasaEnvio() != null
                    && logSerasaRetorno.getLogSerasaEnvio().getCpf() != null
                    && carga.getDataEnvio().compareTo(logSerasaRetorno.getLogSerasaEnvio().getData()) > 0)
                    || (carga != null && carga.getCpf() != null && logSerasaRetorno == null)) {

                logSerasaRetorno = carga.trataRetorno(carga);
                reaproveita = "S";
                tipoConsulta = "BATCH";
            }
        }

        if (logSerasaRetorno != null && logSerasaRetorno.getLogSerasaEnvio() != null && logSerasaRetorno.getLogSerasaEnvio().getCpf() != null) {

            retorno.setOrigemBuffer(true);
            reaproveita = "S";

        } else {

            logSerasaRetorno = serasaAPI.getResultado(cpf, produto, tipo.charAt(0), serasaSenha, layoutFile, httpConnectTentativas, httpConnectTimeoutSecs);
        }

        bindBureauxService.bindBureauxRegistro(logSerasaRetorno, retorno, tipoRetorno);

        String origemConsulta = "GESTAO";

        if (rotina.equals("CREDLINE") || rotina.equals("VIPAGEM")) {
            origemConsulta = "INICIACAO";
        }

        if (carga == null) {
            int posicaoScore = 0;
            if (logSerasaRetorno.getRetorno().indexOf("CB280") > 0) {
                posicaoScore = logSerasaRetorno.getRetorno().indexOf("CB280");
            } else if (logSerasaRetorno.getRetorno().indexOf(" B280") > 0) {
                posicaoScore = logSerasaRetorno.getRetorno().indexOf(" B280");
            }
            if (posicaoScore > 0) {
                modelo = logSerasaRetorno.getRetorno().substring(posicaoScore + 5, posicaoScore + 9);
            }
        } else {
            modelo = carga.getModelo();
        }

        serasaService.salvarBufferUnico(logSerasaRetorno, modelo, origemConsulta, rotina, reaproveita, tipoConsulta, proposta);

        retorno.setTempoBufferUtilizado(serasaSenha.getDiasRetroativos());
        retorno.setTempoBufferProcesso(DateUtils.iDiffDays(logSerasaRetorno.getLogSerasaEnvio().getData(), new Date()));
        retorno.setUsuarioConsulta(logSerasaRetorno.getLogSerasaEnvio().getId().toString());
        retorno.setDataConsulta(logSerasaRetorno.getLogSerasaEnvio().getData());

        return retorno;
    }

    @Override
    public BureauxRegistroResponseDTO consultaSerasaLocal(String cpf, String produto, String rotina, String tipo, String layoutFile, int httpConnectTentativas, int httpConnectTimeoutSecs, TipoRetorno tipoRetorno, String proposta) {
        BureauxRegistroResponseDTO retorno = new BureauxRegistroResponseDTO(cpf);

        if (null != produto) {
            produto = produto.toUpperCase();
        }

        SerasaSenha serasaSenha = serasaSenhaService.buscarPorId(rotina);

        BufferUnicoCargaDTO carga = null;
        LogSerasaRetorno logSerasaRetorno = null;

        //String mensagem = "cpf " + cpf + " a " + serasaSenha.getDiasRetroativos() + " dia(s) atras rotina " + rotina + ".";
        logSerasaRetorno = logSerasaService.findTpConsultaApartirDe(cpf, produto, tipo.charAt(0), serasaSenha.getDiasRetroativos(), serasaSenha.getUtilizaProduto());

        if (tipoRetorno == TipoRetorno.RESUMIDO) {

            carga = bufferUnicoCargaService.validaConsultaBureau(cpf, serasaSenha.getDiasRetroativos());

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

        bindBureauxService.bindBureauxRegistro(logSerasaRetorno, retorno, tipoRetorno);

        retorno.setTempoBufferUtilizado(serasaSenha.getDiasRetroativos());
        retorno.setTempoBufferProcesso(DateUtils.iDiffDays(logSerasaRetorno.getLogSerasaEnvio().getData(), new Date()));
        retorno.setUsuarioConsulta(logSerasaRetorno.getLogSerasaEnvio().getId().toString());
        retorno.setDataConsulta(logSerasaRetorno.getLogSerasaEnvio().getData());
        return retorno;

    }


}
