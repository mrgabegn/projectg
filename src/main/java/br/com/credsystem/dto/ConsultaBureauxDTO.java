package br.com.credsystem.dto;

import br.com.credsystem.model.LogSerasaRetorno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaBureauxDTO {
    private String modelo;
    private String reaproveita;
    private String tipoConsulta;
    private String produto;
    BufferUnicoCargaDTO carga;
    LogSerasaRetorno logSerasaRetorno;

    public void setProduto(String produto) {
        this.produto = produto.toUpperCase();
    }
}
