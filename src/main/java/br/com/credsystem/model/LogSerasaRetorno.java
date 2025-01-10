package br.com.credsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "LOG_SERASA_RETORNO")
public class LogSerasaRetorno implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Basic(optional = false)
    @SequenceGenerator(allocationSize=1, name = "ID_LOG_SERASA_ENVSEQ", sequenceName = "SQ_LOG_SERASA_ENVIO_SERASA_ENV")
    @GeneratedValue(generator = "ID_LOG_SERASA_ENVSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ENVIO")
    private BigInteger id;

    @JoinColumn(name = "ID_ENVIO", referencedColumnName = "ID_LOG_SERASA_ENV")
    @OneToOne
    private LogSerasaEnvio logSerasaEnvio;

    @Column(name = "RETORNO")
    private String retorno;

    @Column(name = "TEMPO_RETORNO")
    private BigInteger tempoRetorno;

    @Column(name = "REAPROVEITA")
    private char reaproveita = '1';

    @Column(name = "LAYOUT_PATH")
    private String layoutPath;

    @Column(name = "SCORE")
    private String score;

    @Column(name = "FLAG_NEGATIVACAO")
    private String negativado;
}