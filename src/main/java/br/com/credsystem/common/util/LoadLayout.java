package br.com.credsystem.common.util;

import br.com.credsystem.common.enumeration.CodigoErro;
import br.com.credsystem.exception.ErroException;
import lombok.Getter;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public abstract class LoadLayout {
    protected Logger logger;

    private StringBuilder linhaEncoded = new StringBuilder();
    @Getter
    protected StringBuilder linha = new StringBuilder();
    @Getter
    protected int tamanhoLinha = 0;
    Properties props;
    @Getter
    protected Properties resposta;

    public LoadLayout() {
        logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    public StringBuilder getLinhaEncoded() throws ErroException {
        try {
            linhaEncoded.append(URLEncoder.encode(linha.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new ErroException(CodigoErro.LAYOUT_URL_ENCODER_ERROR);
        }
        return linhaEncoded;
    }

    protected abstract void parseLine(String line, String layoutPath);
}
