package br.com.credsystem.service.impl;

import br.com.credsystem.common.util.DateUtils;
import br.com.credsystem.dto.ConsultaBureauxDTO;
import br.com.credsystem.dto.request.consultaSerasaRequestDTO;
import br.com.credsystem.model.LogSerasaRetorno;
import br.com.credsystem.model.SerasaSenha;
import br.com.credsystem.repository.LogSerasaEnvioRepository;
import br.com.credsystem.repository.LogSerasaRetornoRepository;
import br.com.credsystem.service.LogSerasaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

@Service
public class LogSerasaServiceImpl implements LogSerasaService {

    LogSerasaRetornoRepository repository;
    LogSerasaEnvioRepository envioRepository;

    @Override
    public LogSerasaRetorno findByIdEnvio(BigInteger id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public LogSerasaRetorno findTpConsultaApartirDe(String cpf, String produto, char tipo, Integer diasAtras, Integer utilizaProduto) {
        Calendar hoje = Calendar.getInstance();
        Date dataApartirDe = DateUtils.somaDias(hoje.getTime(), -diasAtras).getTime();
        if (utilizaProduto == 1) {
            return repository.findTpConsultaApartirDe(cpf, produto, tipo, dataApartirDe);
        } else {
            return repository.findTpConsultaApartirDe(cpf, tipo, dataApartirDe);
        }
    }

    @Override
    public LogSerasaRetorno findTpConsultaApartirDe(ConsultaBureauxDTO dto, consultaSerasaRequestDTO request, SerasaSenha serasaSenha) {
        Calendar hoje = Calendar.getInstance();
        Date dataApartirDe = DateUtils.somaDias(hoje.getTime(), - serasaSenha.getDiasRetroativos()).getTime();
        if (serasaSenha.getUtilizaProduto() == 1) {
            return repository.findTpConsultaApartirDe(request.cpf(), dto.getProduto() , request.tipo().charAt(0), dataApartirDe);
        } else {
            return repository.findTpConsultaApartirDe(request.cpf(), request.tipo().charAt(0), dataApartirDe);
        }

    }
}
