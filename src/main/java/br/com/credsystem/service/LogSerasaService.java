package br.com.credsystem.service;

import br.com.credsystem.dto.ConsultaBureauxDTO;
import br.com.credsystem.dto.request.consultaSerasaRequestDTO;
import br.com.credsystem.model.LogSerasaRetorno;
import br.com.credsystem.model.SerasaSenha;

import java.math.BigInteger;

public interface LogSerasaService {
    LogSerasaRetorno findByIdEnvio(BigInteger bigInteger);

    LogSerasaRetorno findTpConsultaApartirDe(String cpf, String produto, char c, Integer diasRetroativos, Integer utilizaProduto);

    LogSerasaRetorno findTpConsultaApartirDe(ConsultaBureauxDTO dto, consultaSerasaRequestDTO request, SerasaSenha serasaSenha);
}
