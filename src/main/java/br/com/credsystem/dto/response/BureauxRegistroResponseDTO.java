package br.com.credsystem.dto.response;

import br.com.credsystem.dto.BureauxRegistro;
import br.com.credsystem.dto.BureauxRegistroDTO;
import br.com.credsystem.dto.SerasaStatusDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BureauxRegistroResponseDTO {
    private boolean origemBuffer = false;
    private String cpf;
    private String retornoOriginal;
    private Integer id;
    private SerasaStatusDTO status;
    private Integer tempoBufferUtilizado;
    private Integer tempoBufferProcesso;
    private String usuarioConsulta;
    private Date dataConsulta;

    private List<BureauxRegistroDTO> bureauxRegistroList;

    public BureauxRegistroResponseDTO(String cpf) {
    }

    public void add(BureauxRegistroDTO registro) {
        this.bureauxRegistroList.add(registro);
    }
}
