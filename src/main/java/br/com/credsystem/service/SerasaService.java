package br.com.credsystem.service;

import br.com.credsystem.dto.BureauxRegistroDTO;
import br.com.credsystem.model.LogSerasaRetorno;

import java.util.List;
import java.util.Map;

public interface SerasaService {
    void salvarBufferUnico(LogSerasaRetorno logSerasaRetorno, String modelo, String origemConsulta, String rotina, String reaproveita, String tipoConsulta, String proposta);

    Map<String, Object> getSerasaRetornoMapped(String retornoConsulta) throws Exception;
}
