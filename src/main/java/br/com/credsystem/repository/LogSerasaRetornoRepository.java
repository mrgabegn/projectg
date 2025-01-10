package br.com.credsystem.repository;

import br.com.credsystem.model.LogSerasaRetorno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface LogSerasaRetornoRepository extends CrudRepository<LogSerasaRetorno, BigInteger> {
}
