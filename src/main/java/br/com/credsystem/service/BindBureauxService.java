package br.com.credsystem.service;

import br.com.credsystem.common.enumeration.TipoRetorno;
import br.com.credsystem.dto.SerasaStatusDTO;
import br.com.credsystem.dto.response.BureauxRegistroResponseDTO;
import br.com.credsystem.model.LogSerasaRetorno;

public interface BindBureauxService {
    SerasaStatusDTO bindBureauxRegistro(LogSerasaRetorno logSerasaRetorno, BureauxRegistroResponseDTO retorno, TipoRetorno tipoRetorno) throws Exception;

}
