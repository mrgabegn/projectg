package br.com.credsystem.service.impl;

import br.com.credsystem.model.LogSerasaRetorno;
import br.com.credsystem.repository.LogSerasaRetornoRepository;
import br.com.credsystem.service.LogSerasaService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class LogSerasaServiceImpl implements LogSerasaService {

    LogSerasaRetornoRepository repository;

    @Override
    public LogSerasaRetorno findByIdEnvio(BigInteger id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public LogSerasaRetorno findTpConsultaApartirDe(String cpf, String produto, char c, Integer diasRetroativos, Integer utilizaProduto) {
        return null;
    }
}
