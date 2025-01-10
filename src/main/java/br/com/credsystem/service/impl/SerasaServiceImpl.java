package br.com.credsystem.service.impl;

import br.com.credsystem.model.BufferUnico;
import br.com.credsystem.model.LogSerasaRetorno;
import br.com.credsystem.repository.BufferUnicoRepository;
import br.com.credsystem.service.SerasaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SerasaServiceImpl implements SerasaService {

    BufferUnicoRepository bufferUnicoRepository;

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void salvarBufferUnico(LogSerasaRetorno logSerasaRetorno, String modelo, String origemConsulta, String rotina, String reaproveita, String tipoConsulta, String proposta) {
        BufferUnico bufferUnico = new BufferUnico();
        bufferUnico.setBureauConsultado("SERASA");
        bufferUnico.setCpf(logSerasaRetorno.getLogSerasaEnvio().getCpf());
        bufferUnico.setDataConsulta(logSerasaRetorno.getLogSerasaEnvio().getData());
        bufferUnico.setDataInclusao(LocalDateTime.now());
        bufferUnico.setIdOrigem(Long.parseLong(logSerasaRetorno.getLogSerasaEnvio().getId().toString()));
        bufferUnico.setModelo(modelo);
        bufferUnico.setNegativado(logSerasaRetorno.getNegativado());
        bufferUnico.setProcesso(origemConsulta);
        bufferUnico.setProduto(logSerasaRetorno.getLogSerasaEnvio().getProduto());
        bufferUnico.setRotina(rotina);
        Integer score = logSerasaRetorno.getScore() == null ? 0 : Integer.parseInt(logSerasaRetorno.getScore());
        bufferUnico.setScore(score);
        bufferUnico.setReaproveitamento(reaproveita);
        bufferUnico.setTipoConsulta(tipoConsulta);
        bufferUnico.setProposta(proposta);
        bufferUnicoRepository.save(bufferUnico);
    }
}
