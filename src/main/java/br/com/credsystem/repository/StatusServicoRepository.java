package br.com.credsystem.repository;

import br.com.credsystem.model.StatusServico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusServicoRepository extends CrudRepository<StatusServico, Integer> {
    List<StatusServico> findAllByRotina(int rotina);
    StatusServico findStatusServicoByRotinaAndSistema(int rotina, int Sistema);
}
