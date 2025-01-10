package br.com.credsystem.service.impl;

import br.com.credsystem.dto.BufferUnicoCargaDTO;
import br.com.credsystem.service.BufferUnicoCargaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BufferUnicoCargaServiceImpl implements BufferUnicoCargaService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public BufferUnicoCargaDTO validaConsultaBureau(String cpf, Integer diasReaproveitamento) {
        BufferUnicoCargaDTO carga = null;
        Query query = entityManager.createNativeQuery("SELECT cpf, to_char(data_consulta,'dd/mm/rrrr hh24:mi:ss') data_consulta, id_envio, modelo, negativacao, score, segmento "
                + "FROM TABLE(SFCUSER.fc_consultaBufferSerasaCPF(:cpf, :qtdDias))");
        query.setParameter("cpf", cpf);
        query.setParameter("qtdDias", diasReaproveitamento);

        List l = query.getResultList();
        for (Object l1 : l) {
            try {
                Object[] coluna = (Object[]) l1;
                carga = new BufferUnicoCargaDTO();
                carga.setCpf(String.valueOf(coluna[0]));
                carga.setDataEnvio(formataData(coluna[1].toString()));
                carga.setIdEnvio(Integer.parseInt(coluna[2].toString()));
                carga.setModelo(coluna[3].toString());
                carga.setNegativado("1".equals(coluna[4].toString()) ? "S" : "N");
                carga.setScore(Integer.parseInt(coluna[5].toString()));
                carga.setSegmento(String.valueOf(coluna[6]));
            } catch (Exception ex) {
                Logger.getLogger(BufferUnicoCargaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return carga;
    }

    public java.sql.Date formataData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }
        java.sql.Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            date = new java.sql.Date((formatter.parse(data)).getTime());
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
}
