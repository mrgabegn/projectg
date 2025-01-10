package br.com.credsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BureauxRegistro {
    private String tipo;
    private String registro;
    private Properties registroProperties;
}
