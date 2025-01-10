package br.com.credsystem.service;

import br.com.credsystem.common.enumeration.TipoRetorno;
import br.com.credsystem.dto.request.consultaSerasaRequestDTO;
import br.com.credsystem.dto.response.BureauxRegistroResponseDTO;

public interface ConsultaService {
    BureauxRegistroResponseDTO consultaSerasaPorId(Integer id, TipoRetorno tipoRetorno) throws Exception ;

    BureauxRegistroResponseDTO consultaSerasa(consultaSerasaRequestDTO request) throws Exception;

    BureauxRegistroResponseDTO consultaSerasaLocal(consultaSerasaRequestDTO request) throws Exception;
}
