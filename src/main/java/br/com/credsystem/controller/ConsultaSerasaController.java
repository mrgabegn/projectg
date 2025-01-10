package br.com.credsystem.controller;

import br.com.credsystem.common.enumeration.TipoRetorno;
import br.com.credsystem.dto.request.consultaSerasaRequestDTO;
import br.com.credsystem.dto.response.BureauxRegistroResponseDTO;
import br.com.credsystem.service.ConsultaService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consulta")
@Setter(onMethod_ = @Autowired)
public class ConsultaSerasaController {

    ConsultaService service;

    public ConsultaSerasaController(ConsultaService service) {
        this.service = service;
    }

    @GetMapping("/consultaSerasaPorId/{id}/{tipoRetorno}")
    @ResponseStatus(HttpStatus.OK)
    public BureauxRegistroResponseDTO consultaSerasaPorId(@PathVariable("id") Integer id,
                                            @PathVariable("tipoRetorno") String tipoRetorno) throws Exception {
        return service.consultaSerasaPorId(id,TipoRetorno.getByDescription(tipoRetorno));
    }

    @PostMapping("/consultaSerasa")
    @ResponseStatus(HttpStatus.OK)
    public BureauxRegistroResponseDTO consultaSerasa(@RequestBody consultaSerasaRequestDTO request) throws Exception {
        return service.consultaSerasa(request);
    }

    @GetMapping("/consultaSerasa/local")
    @ResponseStatus(HttpStatus.OK)
    public BureauxRegistroResponseDTO consultaSerasaLocal(@RequestBody consultaSerasaRequestDTO request) throws Exception {
        return service.consultaSerasaLocal(request);
    }
    
}
