package br.com.credsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BureauxRegistroDTO {
    private String tipo;
    private String registro;
    private Properties registroProperties;
}
