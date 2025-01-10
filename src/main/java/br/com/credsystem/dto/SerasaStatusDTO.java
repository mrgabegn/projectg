package br.com.credsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SerasaStatusDTO {
    private boolean centralCreditoProblematico = false;
    private boolean ccfProblematico = false;
    private boolean contumaciaProblematico = false;
    private int score;
    private short codigoRetorno = 0;
    private String resposta;
    private Integer id;
}
