package br.com.credsystem.repository;

import br.com.credsystem.model.BufferUnico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BufferUnicoRepository extends CrudRepository<BufferUnico, Long> {
}
