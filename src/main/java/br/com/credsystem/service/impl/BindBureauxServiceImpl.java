package br.com.credsystem.service.impl;

import br.com.credsystem.common.enumeration.TipoRetorno;
import br.com.credsystem.dto.BureauxRegistro;
import br.com.credsystem.dto.BureauxRegistroDTO;
import br.com.credsystem.dto.SerasaStatusDTO;
import br.com.credsystem.dto.response.BureauxRegistroResponseDTO;
import br.com.credsystem.integration.api.SerasaAPI;
import br.com.credsystem.model.LogSerasaRetorno;
import br.com.credsystem.service.BindBureauxService;
import br.com.credsystem.service.SerasaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class BindBureauxServiceImpl implements BindBureauxService {

    SerasaService serasaService;

    @Override
    public SerasaStatusDTO bindBureauxRegistro(LogSerasaRetorno logSerasaRetorno, BureauxRegistroResponseDTO retorno, TipoRetorno tipoRetorno) throws Exception {
        retorno.setId(Integer.parseInt(logSerasaRetorno.getLogSerasaEnvio().getId().toString()));
        String retornoConsulta;
        retornoConsulta = logSerasaRetorno.getRetorno();

        SerasaStatusDTO status = new SerasaStatusDTO();
        if (tipoRetorno.equals(TipoRetorno.ANALITICO)) {
            Map<String, Object> registroList = serasaService.getSerasaRetornoMapped(retornoConsulta);
            for (Map.Entry<String, Object> entry : registroList.entrySet()) {
                Properties properties = (Properties) entry.getValue();
                retorno.add(new BureauxRegistroDTO(entry.getKey() + "", entry.getValue().toString(), properties));
            }
        }

        String negativado = retornoConsulta.substring(193, 194);

        status.setScore(Integer.parseInt(logSerasaRetorno.getScore()));
        if ("S".equals(negativado)) {
            logSerasaRetorno.setNegativado("S");
            status.setCentralCreditoProblematico(true);
        } else {
            status.setCentralCreditoProblematico(logSerasaRetorno.getNegativado() != null && logSerasaRetorno.getNegativado().equals("S"));
        }
        status.setScore(Integer.parseInt(logSerasaRetorno.getScore()));
        retorno.setRetornoOriginal(retornoConsulta);

        retorno.setStatus(status);
        return status;
    }
}
