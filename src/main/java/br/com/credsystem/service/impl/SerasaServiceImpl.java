package br.com.credsystem.service.impl;

import br.com.credsystem.common.util.BindLayout;
import br.com.credsystem.common.util.Layout;
import br.com.credsystem.common.util.StringUtils;
import br.com.credsystem.dto.BureauxRegistroDTO;
import br.com.credsystem.exception.ErroException;
import br.com.credsystem.model.BufferUnico;
import br.com.credsystem.model.LogSerasaRetorno;
import br.com.credsystem.repository.BufferUnicoRepository;
import br.com.credsystem.service.SerasaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class SerasaServiceImpl implements SerasaService {

    BufferUnicoRepository bufferUnicoRepository;
    private static final String layoutPath = "bureaux/serasa/layouts/B49C";


    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void salvarBufferUnico(LogSerasaRetorno logSerasaRetorno, String modelo, String origemConsulta, String rotina, String reaproveita, String tipoConsulta, String proposta) {
        BufferUnico bufferUnico = new BufferUnico();
        bufferUnico.setBureauConsultado("SERASA");
        bufferUnico.setCpf(logSerasaRetorno.getLogSerasaEnvio().getCpf());
        bufferUnico.setDataConsulta(logSerasaRetorno.getLogSerasaEnvio().getData());
        bufferUnico.setDataInclusao(LocalDateTime.now());
        bufferUnico.setIdOrigem(Long.parseLong(logSerasaRetorno.getLogSerasaEnvio().getId().toString()));
        bufferUnico.setModelo(modelo);
        bufferUnico.setNegativado(logSerasaRetorno.getNegativado());
        bufferUnico.setProcesso(origemConsulta);
        bufferUnico.setProduto(logSerasaRetorno.getLogSerasaEnvio().getProduto());
        bufferUnico.setRotina(rotina);
        Integer score = logSerasaRetorno.getScore() == null ? 0 : Integer.parseInt(logSerasaRetorno.getScore());
        bufferUnico.setScore(score);
        bufferUnico.setReaproveitamento(reaproveita);
        bufferUnico.setTipoConsulta(tipoConsulta);
        bufferUnico.setProposta(proposta);
        bufferUnicoRepository.save(bufferUnico);
    }

    @Override
    public Map<String, Object> getSerasaRetornoMapped(String retornoConsulta) throws Exception {
        Map<String, Object> retorno;

        List<Layout> layoutsInfo = getLayoutsInfo();
        Map<String, Object> unsortMap = new HashMap<>();
        for (Layout layout : layoutsInfo) {
            if (!"B49C".equals(layout.getTipo())) {
                int i = 0;
                while ((i = (retornoConsulta.indexOf(layout.getTipo(), i) + 1)) > 0) {
                    int start = i - 1;
                    int end = start + layout.getTamanho();
                    if (end > retornoConsulta.length()) {
                        throw new Exception(layout + " fora de padrao");
                    }
                    String texto = retornoConsulta.substring(start, end);

                    try {
                        BindLayout bindLayout = new BindLayout();
                        bindLayout.setRetorno(texto);

                        InputStream layoutRetorno = this.getClass().getResourceAsStream("/" + layoutPath + "/" + layout.getTipo() + ".txt");

                        bindLayout.readLayoutFile("/" + layoutPath, layoutRetorno, "", null);
                        unsortMap.put("(" + String.format("%05d", start) + ") " + layout.getTipo(), bindLayout.getResposta());
                    } catch (ErroException e) {
                        e.printStackTrace();
                        throw new Exception(layout + " erro ao fazer bind");
                    }
                    retornoConsulta = retornoConsulta.replace(texto, StringUtils.completeZerosLeft("", layout.getTamanho()));
                }
            }
        }
        if (unsortMap.isEmpty()) {
            throw new Exception(retornoConsulta.trim());
        } else {
            retorno = new TreeMap<>(unsortMap);
        }
        return retorno;

    }

    private List<Layout> getLayoutsInfo() {
        List<Layout> retorno = new ArrayList();

        Layout layoutA900 = new Layout("A900", 115);
        Layout layoutB001 = new Layout("B001", 115);
        Layout layoutB002 = new Layout("B002", 115);
        Layout layoutB003 = new Layout("B003", 115);
        Layout layoutB004 = new Layout("B004", 115);
        Layout layoutB005 = new Layout("B005", 115);
        Layout layoutB006 = new Layout("B006", 115);
        Layout layoutB280 = new Layout("B280", 115);
        Layout layoutB352 = new Layout("B352", 115);
        Layout layoutB353 = new Layout("B353", 115);
        Layout layoutB354 = new Layout("B354", 115);
        Layout layoutB355 = new Layout("B355", 115);
        Layout layoutB357 = new Layout("B357", 115);
        Layout layoutB358 = new Layout("B358", 115);
        Layout layoutB359 = new Layout("B359", 115);
        Layout layoutB360 = new Layout("B360", 115);
        Layout layoutB361 = new Layout("B361", 115);
        Layout layoutB362 = new Layout("B362", 115);
        Layout layoutB363 = new Layout("B363", 115);
        Layout layoutB364 = new Layout("B364", 115);
        Layout layoutB365 = new Layout("B365", 115);
        Layout layoutB366 = new Layout("B366", 115);
        Layout layoutB367 = new Layout("B367", 115);
        Layout layoutB368 = new Layout("B368", 105);
        Layout layoutB370 = new Layout("B370", 115);
        Layout layoutB3761 = new Layout("B3761", 114);
        Layout layoutB3762 = new Layout("B3762", 114);
        Layout layoutB900 = new Layout("B900", 109);
        Layout layoutB49C = new Layout("B49C", 654);
        Layout layoutF900BFG0 = new Layout("F900BFG0", 115);
        Layout layoutF900SPC1000 = new Layout("F900SPC1000", 115);
        Layout layoutF900SPC1001 = new Layout("F900SPC1001", 115);
        Layout layoutF900SPC1002 = new Layout("F900SPC1002", 115);
        Layout layoutF900SPC1003 = new Layout("F900SPC1003", 115);
        Layout layoutF900SPC4000 = new Layout("F900SPC4000", 115);
        Layout layoutF900SPC4001 = new Layout("F900SPC4001", 115);
        Layout layoutF900SPC4002 = new Layout("F900SPC4002", 115);
        Layout layoutF900SPC4003 = new Layout("F900SPC4003", 115);
        Layout layoutP002 = new Layout("P002", 115);
        Layout layoutP006 = new Layout("P006", 115);
        Layout layoutP011 = new Layout("P011", 115);
        Layout layoutT999 = new Layout("T999", 115);
        Layout layoutF900TE730 = new Layout("F900TE730", 115);
        Layout layoutF900TE731 = new Layout("F900TE731", 115);
        Layout layoutF900TE732 = new Layout("F900TE732", 115);

        retorno.add(layoutA900);
        retorno.add(layoutB280);
        retorno.add(layoutB352);
        retorno.add(layoutB353);
        retorno.add(layoutB354);
        retorno.add(layoutB355);
        retorno.add(layoutB357);
        retorno.add(layoutB358);
        retorno.add(layoutB359);
        retorno.add(layoutB360);
        retorno.add(layoutB361);
        retorno.add(layoutB362);
        retorno.add(layoutB363);
        retorno.add(layoutB364);
        retorno.add(layoutB365);
        retorno.add(layoutB366);
        retorno.add(layoutB367);
        retorno.add(layoutB368);
        retorno.add(layoutB370);
        retorno.add(layoutB3761);
        retorno.add(layoutB3762);
        retorno.add(layoutB900);
        retorno.add(layoutB49C);
        retorno.add(layoutF900BFG0);
        retorno.add(layoutF900SPC1000);
        retorno.add(layoutF900SPC1001);
        retorno.add(layoutF900SPC1002);
        retorno.add(layoutF900SPC1003);
        retorno.add(layoutF900SPC4000);
        retorno.add(layoutF900SPC4001);
        retorno.add(layoutF900SPC4002);
        retorno.add(layoutF900SPC4003);
        retorno.add(layoutP002);
        retorno.add(layoutP006);
        retorno.add(layoutP011);
        retorno.add(layoutT999);
        retorno.add(layoutB001);
        retorno.add(layoutB002);
        retorno.add(layoutB003);
        retorno.add(layoutB004);
        retorno.add(layoutB005);
        retorno.add(layoutB006);
        retorno.add(layoutF900TE730);
        retorno.add(layoutF900TE731);
        retorno.add(layoutF900TE732);

        return retorno;
    }
}
