package br.com.credsystem.controller;

import br.com.credsystem.common.enumeration.Sistema;
import br.com.credsystem.common.enumeration.StatusRotina;
import br.com.credsystem.dto.response.StatusServicoResponse;
import br.com.credsystem.service.StatusServicoService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
@Setter(onMethod_ = @Autowired)
public class StatusController {

    StatusServicoService service;

    public StatusController(StatusServicoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<StatusServicoResponse> listarStatus() throws Exception {
        return service.listarRotina();
    }

    @PutMapping("/atualizarStatusRotina")
    @ResponseStatus(HttpStatus.OK)
    public boolean atualizarStatusRotina(
            @RequestParam(name = "sistema") Sistema sistema,
            @RequestParam(name = "status") StatusRotina status) throws Exception {
        return service.atualizarStatusRotina(sistema,status);
    }
}
