package br.com.credsystem.dto.request;

public record consultaSerasaRequestDTO(
        String cpf,
        String produto,
        String rotina,
        String tipo,
        String layoutFile,
        int httpConnectTentativas,
        int httpConnectTimeoutSecs,
        String tipoRetorno,
        String proposta
) {
}
