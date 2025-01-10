package br.com.credsystem.dto;

import br.com.credsystem.model.LogSerasaEnvio;
import br.com.credsystem.model.LogSerasaRetorno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BufferUnicoCargaDTO {

    private String cpf;
    private Integer idEnvio;
    private Date dataEnvio;
    private Integer score;
    private String negativado;
    private String modelo;
    private String segmento;

    public LogSerasaRetorno trataRetorno(BufferUnicoCargaDTO carga){
        BigInteger idEnvio = new BigInteger(carga.getIdEnvio().toString());
        LogSerasaRetorno retorno = new LogSerasaRetorno();
        LogSerasaEnvio envio = new LogSerasaEnvio();
        retorno.setNegativado(carga.getNegativado());
        retorno.setScore(carga.getScore().toString());
        envio.setCpf(carga.getCpf());
        envio.setData(carga.getDataEnvio());
        envio.setProduto(carga.getSegmento());
        envio.setId(idEnvio);
        retorno.setLogSerasaEnvio(envio);
        return retorno;
    }
}
