package br.com.credsystem.service.impl;

import br.com.credsystem.common.enumeration.Sistema;
import br.com.credsystem.common.enumeration.StatusRotina;
import br.com.credsystem.dto.response.StatusServicoResponse;
import br.com.credsystem.model.StatusServico;
import br.com.credsystem.repository.StatusServicoRepository;
import br.com.credsystem.service.StatusServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusServicoServiceImpl implements StatusServicoService {

    @Autowired
    StatusServicoRepository repository;


    @Override
    public List<StatusServicoResponse> listarRotina() {
        List<StatusServicoResponse> retorno = new ArrayList<>();

        List<StatusServico> statusServicoLst = repository.findAllByRotina(29);

        for (StatusServico statusServico : statusServicoLst) {
            StatusServicoResponse retornoStatusServico = new StatusServicoResponse();

            retornoStatusServico.setSistema(Sistema.getValue(statusServico.getSistema()));
            retornoStatusServico.setStatus(StatusRotina.getValue(statusServico.getStatus()));
            retorno.add(retornoStatusServico);
        }

        return retorno;
    }

    @Override
    public boolean atualizarStatusRotina(Sistema sistema, StatusRotina status) {
        StatusServico statusServico = repository.findStatusServicoByRotinaAndSistema(29, sistema.getValue());
        statusServico.setStatus(status.getValue());
        repository.save(statusServico);
        return true;
    }
}
