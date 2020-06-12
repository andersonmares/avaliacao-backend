package br.com.avaliacao.repository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CepRepository {

    private static final String URL_BASE = "http://viacep.com.br/ws/%s/json/";
    private static final RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
    }

    public CepVO consultar(String cep) {
        CepVO consulta = null;
        try {
            consulta = restTemplate.getForObject(String.format(URL_BASE, cep), CepVO.class);
            if ("true".equals(consulta.getErro())){
                return null;
            }
        } catch (Exception e) {
            log.error("CEP desconhecido ou comportamento inesperado do servi\u00E7o externo de consulta.", e);
        }
        return consulta;
    }

    @Data
    static class CepVO {
        private Integer id;
        private String cep;
        private String logradouro;
        private String bairro;
        private String localidade;
        private String uf;
        private String ibge;
        private String complemento;
        private String erro;
        private String unidade;
        private String gia;
    }

}
