package br.com.credsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "SERASASENHA")
public class SerasaSenha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ROTINA", nullable = false)
    private String rotina;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "SENHA")
    private String senha;
    @Column(name = "NOVA_SENHA")
    private String novaSenha;
    @Column(name = "DIAS_RETROATIVOS")
    private Integer diasRetroativos;
    @Column(name = "UTILIZA_PRODUTO")
    private Integer utilizaProduto;
}
