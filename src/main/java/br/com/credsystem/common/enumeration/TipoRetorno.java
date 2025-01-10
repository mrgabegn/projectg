package br.com.credsystem.common.enumeration;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;

@AllArgsConstructor
public enum TipoRetorno {
    ANALITICO("analitico"),
    RESUMIDO ("resumido");
    public String description;
    public static Map<String, TipoRetorno> map;
    static {
           Arrays.stream(TipoRetorno.values()).forEach(tipoRetorno ->
           map.put(tipoRetorno.description, tipoRetorno));
    }

    public static TipoRetorno getByDescription(String description){
          return map.get(description);
    }
}
