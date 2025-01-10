package br.com.credsystem.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Layout {
    private String tipo;
    private int tamanho;

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        retorno.append("[" + this.getClass().getSimpleName() + "]")
                .append(" tipo=")
                .append(getTipo())
                .append(" tamanho=")
                .append(getTamanho());
        return retorno.toString();
    }
}
