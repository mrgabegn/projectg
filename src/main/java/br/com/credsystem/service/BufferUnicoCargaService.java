package br.com.credsystem.service;

import br.com.credsystem.dto.BufferUnicoCargaDTO;

public interface BufferUnicoCargaService {
    BufferUnicoCargaDTO validaConsultaBureau(String cpf, Integer diasRetroativos);

}
