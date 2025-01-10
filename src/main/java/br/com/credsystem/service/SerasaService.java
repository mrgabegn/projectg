package br.com.credsystem.service;

import br.com.credsystem.model.LogSerasaRetorno;

public interface SerasaService {
    void salvarBufferUnico(LogSerasaRetorno logSerasaRetorno, String modelo, String origemConsulta, String rotina, String reaproveita, String tipoConsulta, String proposta);

}
