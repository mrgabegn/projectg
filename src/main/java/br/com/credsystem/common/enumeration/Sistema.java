package br.com.credsystem.common.enumeration;

public enum Sistema {
    WEBSYSTEM(1),
    CREDLINE(2),
    APP(10),
    DESBLOQUEIO_ONLINE(34),
    EMISSAO_VOUCHER(39),
    PEDIDO_CARTAO_MAIS(50),
    PEDIDO_CARTAO_PRIVATE(51);

    private int id_sistema;

    private Sistema(int id_sistema) {
        this.id_sistema = id_sistema;
    }

    public int getValue() {
        return this.id_sistema;
    }

    public static Sistema getValue(int value) {
        for (Sistema e : Sistema.values()) {
            if (e.id_sistema == value) {
                return e;
            }
        }
        return null;
    }
}
