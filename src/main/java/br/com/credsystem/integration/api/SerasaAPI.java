package br.com.credsystem.integration.api;

import br.com.credsystem.dto.BureauxRegistroDTO;
import br.com.credsystem.model.LogSerasaRetorno;
import br.com.credsystem.model.SerasaSenha;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface SerasaAPI {

    @RequestLine("GET /retorno")
    List<BureauxRegistroDTO>  getSerasaRetornoMapped(String retornoConsulta);

    @RequestLine("GET /consulta")
    LogSerasaRetorno getResultado(@Param("cpf") String cpf,
                                  @Param("produto") String produto,
                                  @Param("c") char c,
                                  @Param("serasaSenha") SerasaSenha serasaSenha,
                                  @Param("layoutFile")String layoutFile,
                                  @Param("httpConnectTentativas")int httpConnectTentativas,
                                  @Param("httpConnectTimeoutSecs")int httpConnectTimeoutSecs);

}
