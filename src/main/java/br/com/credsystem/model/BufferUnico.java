package br.com.credsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BUFFER_UNICO")
public class BufferUnico implements Serializable {

    @Id
    @Column(name = "ID_BUFFER_UNICO")
    @GeneratedValue(generator = "ID_BUFFER_UNICOSEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "ID_BUFFER_UNICOSEQ", sequenceName = "SEQ_ID_BUFFER_UNICO")
    private Long id;
    @Column(name = "CPF")
    private String Cpf;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CONSULTA")
    private Date dataConsulta;
    @Column(name = "ROTINA")
    private String rotina;
    @Column(name = "SCORE")
    private Integer score;
    @Column(name = "NEGATIVADO")
    private String negativado;
    @Column(name = "DATA_INCLUSAO")
    private LocalDateTime dataInclusao;
    @Column(name = "BUREAU_CONSULTADO")
    private String bureauConsultado;
    @Column(name = "ID_ORIGEM")
    private Long idOrigem;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "PRODUTO")
    private String produto;
    @Column(name = "PROCESSO")
    private String processo;
    @Column(name = "REAPROVEITAMENTO")
    private String reaproveitamento;
    @Column(name = "TIPO_CONSULTA")
    private String tipoConsulta;
    @Column(name = "PROPOSTA")
    private String proposta;
}
