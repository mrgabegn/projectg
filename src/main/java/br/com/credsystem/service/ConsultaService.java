package br.com.credsystem.service;

import br.com.credsystem.common.enumeration.TipoRetorno;
import br.com.credsystem.dto.response.BureauxRegistroResponseDTO;

public interface ConsultaService {
    BureauxRegistroResponseDTO consultaSerasaPorId(Integer id, TipoRetorno tipoRetorno) throws Exception ;

    BureauxRegistroResponseDTO consultaSerasa(String cpf, String produto, String rotina, String tipo, String layoutFile, int httpConnectTentativas, int httpConnectTimeoutSecs, TipoRetorno tipoRetorno, String proposta);

    BureauxRegistroResponseDTO consultaSerasaLocal(String cpf, String produto, String rotina, String tipo, String layoutFile, int httpConnectTentativas, int httpConnectTimeoutSecs, TipoRetorno tipoRetorno, String proposta);

}
