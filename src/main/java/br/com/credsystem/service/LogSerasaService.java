package br.com.credsystem.service;

import br.com.credsystem.model.LogSerasaRetorno;

import java.math.BigInteger;

public interface LogSerasaService {
    LogSerasaRetorno findByIdEnvio(BigInteger bigInteger);

    LogSerasaRetorno findTpConsultaApartirDe(String cpf, String produto, char c, Integer diasRetroativos, Integer utilizaProduto);

}
