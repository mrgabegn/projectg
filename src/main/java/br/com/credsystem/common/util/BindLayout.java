package br.com.credsystem.common.util;

import br.com.credsystem.common.enumeration.CodigoErro;
import br.com.credsystem.exception.ErroException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BindLayout extends LoadLayout{

    private String retorno;
    private HashMap<String, Object> respostas = new HashMap<>();
    private short count = 1;
    private String tamanho_texto = "";

    @Override
    protected void parseLine(String line, String layoutPath) {
        if (line.contains(":")) {
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter("\\s*:\\s*");

            String campo = lineScanner.next();
            int tamanho = Integer.parseInt(lineScanner.next());
            int fim = tamanhoLinha + tamanho;
            String valor = "";
            try {
                valor = retorno.substring(tamanhoLinha, fim);
            } catch (Exception e) {
                if(retorno.length()>tamanhoLinha) {
                    valor = retorno.substring(tamanhoLinha, retorno.length());
                }
            }
            if (campo.startsWith("tamanho_texto")) {
                tamanho_texto = valor;
            } else {
                if (campo.startsWith("texto")) {
                    // Sub-layout do retorno
                    if (tamanho_texto.trim().length() > 0) {
                        int limite = Integer.parseInt(tamanho_texto.trim());
                        int ponteiro = 0;
                        do {
                            int tamanho_resposta = Integer.parseInt(valor.substring(ponteiro, ponteiro + 3));
                            String tipo_resposta = valor.substring(ponteiro + 3, ponteiro + 6);
                            String resposta = "";
                            if(layoutPath.endsWith("CSR60.v.04")) {
                                resposta = valor.substring(ponteiro, ponteiro + 3 + tamanho_resposta);
                            } else {
                                resposta = valor.substring(ponteiro, ponteiro + 3 + tamanho_resposta);
                            }

                            try {
                                BindLayout bindLayout = new BindLayout();
                                bindLayout.setRetorno(resposta);
                                InputStream is = this.getClass().getResourceAsStream(layoutPath + "/RESPOSTA" + tipo_resposta + ".txt");
                                bindLayout.readLayoutFile("/" + layoutPath, is, "", null);
                                respostas.put(tipo_resposta + Short.valueOf(count++), bindLayout.getResposta());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ponteiro += tamanho_resposta + 3;
                        } while (ponteiro < limite);
                    }
                }
                tamanho_texto = "";
            }
            tamanhoLinha += tamanho;
            linha.append(campo + " : " + valor + '\n');
            resposta.put(campo, valor);
        }
    }

    public void readLayoutFile(String layoutPath, InputStream inputStream, String condicao1, Properties props) throws ErroException {
        this.props = props;
        tamanhoLinha = 0;
        resposta = new Properties();
        try {
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter(System.getProperty("line.separator"));
            scanner.useDelimiter("\n");
            //System.out.println("==> " + scanner.);
            if (scanner.hasNext()) {
                scanner.next();
                String line = "";
                while (scanner.hasNext()) {
                    line = scanner.next();
                    if (line.length() > 0) {
                        if (line.toLowerCase().startsWith("if(")) {
                            Scanner lineScanner = new Scanner(line);
                            lineScanner.useDelimiter("\\s*:\\s*");
                            lineScanner.next();
                            String condicao2 = lineScanner.next();
                            line = scanner.next();

                            if (!condicao1.equals(condicao2)) {
                                while (!scanner.next().equals("}")) {
                                }
                                line = scanner.next();
                            }
                        }
                    }
                    parseLine(line, layoutPath);
                    //System.out.println("line: " + line);
                }
            }
            //logger.info("tamanho = " + tamanhoLinha);
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErroException(CodigoErro.LAYOUT_FILE_NOT_FOUND_ERROR);
        }
    }

    public String findStartWith(String texto, String stringStart) {
        String valido = "";
        Scanner scanner = new Scanner(texto);
        scanner.useDelimiter(System.getProperty("line.separator"));
        while (scanner.hasNext()) {
            valido = scanner.next();
            if(valido.startsWith(stringStart)) {
                break;
            }
        }
        return valido;
    }

}

