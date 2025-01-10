package br.com.credsystem.controller;

import br.com.credsystem.common.enumeration.TipoRetorno;
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
    public BureauxRegistroResponseDTO consultaSerasa() {
        return service.consultaSerasa(
                cpf,
                produto,
                rotina,
                tipo,
                layoutFile,
                httpConnectTentativas,
                httpConnectTimeoutSecs,
                tipoRetorno,
                proposta
        );
    }

    @GetMapping("/consultaSerasa/local")
    @ResponseStatus(HttpStatus.OK)
    public BureauxRegistroResponseDTO consultaSerasaLocal(@RequestParam(name = "cpf") String cpf,
                                                          @RequestParam(name = "produto") String produto,
                                                          @RequestParam(name = "rotina") String rotina,
                                                          @RequestParam(name = "tipo") String tipo,
                                                          @RequestParam(name = "layoutFile") String layoutFile,
                                                          @RequestParam(name = "httpConnectTentativas") int httpConnectTentativas,
                                                          @RequestParam(name = "httpConnectTimeoutSecs") int httpConnectTimeoutSecs,
                                                          @RequestParam(name = "tipo_retorno") TipoRetorno tipoRetorno,
                                                          @RequestParam(name = "proposta") String proposta) {
        return service.consultaSerasaLocal(
                cpf,
                produto,
                rotina,
                tipo,
                layoutFile,
                httpConnectTentativas,
                httpConnectTimeoutSecs,
                tipoRetorno,
                proposta
        );
    }
    
}
