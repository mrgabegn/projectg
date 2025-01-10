package br.com.credsystem.service.impl;

import br.com.credsystem.model.SerasaSenha;
import br.com.credsystem.repository.SerasaSenhaRepository;
import br.com.credsystem.service.SerasaSenhaService;
import org.springframework.stereotype.Service;

@Service
public class SerasaSenhaServiceImpl implements SerasaSenhaService {

    SerasaSenhaRepository repository;

    @Override
    public SerasaSenha buscarPorId(String rotina) {
        return repository.findById(rotina).orElseThrow(RuntimeException::new);
    }
}
