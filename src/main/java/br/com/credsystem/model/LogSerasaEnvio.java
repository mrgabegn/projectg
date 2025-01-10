package br.com.credsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "LOG_SERASA_ENVIO")
public class LogSerasaEnvio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(allocationSize=1, name = "ID_LOG_SERASA_ENVSEQ", sequenceName = "SQ_LOG_SERASA_ENVIO_SERASA_ENV")
    @GeneratedValue(generator = "ID_LOG_SERASA_ENVSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_LOG_SERASA_ENV")
    private BigInteger id;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "DATA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "PRODUTO")
    private String produto;

    @Column(name = "REQUISICAO")
    private String requisicao;

    @JoinColumn(name = "ROTINA", referencedColumnName = "ROTINA")
    @ManyToOne
    private SerasaSenha serasaSenha;

    @Column(name = "TIPO")
    private Character tipo = 'F';
}
