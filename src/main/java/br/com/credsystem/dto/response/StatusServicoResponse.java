package br.com.credsystem.dto.response;

import br.com.credsystem.common.enumeration.Sistema;
import br.com.credsystem.common.enumeration.StatusRotina;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusServicoResponse {
    private Sistema sistema;
    private StatusRotina status;
}
