package br.com.credsystem.exception;

import br.com.credsystem.common.enumeration.CodigoErro;

public class ErroException extends Exception{
    private CodigoErro codigoErro;

    public ErroException(Exception e) {
        super(e);
    }

    public ErroException() {

    }

    public ErroException(CodigoErro codigoErro) {
        this.codigoErro = codigoErro;
    }

    public CodigoErro getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(CodigoErro codigoErro) {
        this.codigoErro = codigoErro;
    }
}
