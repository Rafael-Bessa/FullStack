package bessa.morangon.rafael.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> List<T> obterDadosLista(String json, Class<T> classe) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, classe);
            return mapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}