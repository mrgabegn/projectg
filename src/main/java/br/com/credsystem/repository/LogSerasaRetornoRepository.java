package br.com.credsystem.repository;

import br.com.credsystem.model.LogSerasaRetorno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;

@Repository
public interface LogSerasaRetornoRepository extends CrudRepository<LogSerasaRetorno, BigInteger> {

    @Query("SELECT retorno " +
            "FROM LogSerasaRetorno retorno , LogSerasaEnvio envio " +
            "WHERE retorno.reaproveita = '1' " +
            "AND envio.cpf = :cpf " +
            "AND envio.produto = :produto " +
            "AND envio.tipo = :tipo " +
            "AND envio.data >= :dataApartirDe " +
            "AND envio.id = retorno.logSerasaEnvio.id " +
            "ORDER BY envio.data DESC")
    LogSerasaRetorno findTpConsultaApartirDe(@Param("cpf")String cpf,
                                             @Param("produto")String produto,
                                             @Param("tipo")char tipo,
                                             @Param("dataApartirDe")Date dataApartirDe);

    @Query("SELECT retorno " +
            "FROM LogSerasaRetorno retorno , LogSerasaEnvio envio " +
            "WHERE retorno.reaproveita = '1' " +
            "AND envio.cpf = :cpf " +
            "AND envio.tipo = :tipo " +
            "AND envio.data >= :dataApartirDe " +
            "AND envio.id = retorno.logSerasaEnvio.id " +
            "ORDER BY envio.data DESC")
    LogSerasaRetorno findTpConsultaApartirDe(@Param("cpf")String cpf,
                                             @Param("tipo")char tipo,
                                             @Param("dataApartirDe")Date dataApartirDe);
}
