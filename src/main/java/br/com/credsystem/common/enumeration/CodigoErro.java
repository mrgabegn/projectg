package br.com.credsystem.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum CodigoErro {
    ERRO_INTERNO("br.com.credsystem.error.interno"),
    LAYOUT_FILE_WHITHOUT_CONTENT_ERROR("br.com.credsystem.error.fileWithoutContent"),
    LAYOUT_FILE_NOT_FOUND_ERROR("br.com.credsystem.error.fileNotFound"),
    LAYOUT_URL_ENCODER_ERROR("br.com.credsystem.error.url.encoder"),
    REGISTRO_NAO_LOCALIZADO("br.com.credsystem.error.registro.nao.localizado");

    private String chave;

    public String getChave() {
        return this.chave;
    }

    CodigoErro(String chave) {
        this.chave = chave;
    }
}
