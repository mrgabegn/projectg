package br.com.credsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "STATUS_SERVICO")
public class StatusServico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_STATUS_SERVICO", nullable = false)
    private int id;
    @Column(name = "ID_SISTEMA")
    private int sistema;
    @Column(name = "ID_ROTINA")
    private int rotina;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DT_ATUALIZAC")
    private Date atualizac;

}
