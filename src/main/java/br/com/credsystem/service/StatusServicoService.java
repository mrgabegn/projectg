package br.com.credsystem.service;

import br.com.credsystem.common.enumeration.Sistema;
import br.com.credsystem.common.enumeration.StatusRotina;
import br.com.credsystem.dto.response.StatusServicoResponse;

import java.util.List;

public interface StatusServicoService {
    List<StatusServicoResponse> listarRotina();

    boolean atualizarStatusRotina(Sistema sistema, StatusRotina status);
}
