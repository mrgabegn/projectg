package br.com.credsystem.common.enumeration;

public enum StatusRotina {
    Ativa("A"),
    Inativa("I");

    private String id_status;

    private StatusRotina(String id_status) {
        this.id_status = id_status;
    }

    public String getValue() {
        return this.id_status;
    }

    public static StatusRotina getValue(String value) {
        for (StatusRotina e : StatusRotina.values()) {
            if (e.id_status.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
