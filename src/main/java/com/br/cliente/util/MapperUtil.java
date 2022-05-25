package com.br.cliente.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {

    private MapperUtil() {}

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public <D> D convertTo(Object bean, Class<D> target) {
        return objectMapper.convertValue(bean, target);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> convertTo(element, targetClass))
                .collect(Collectors.toList());
    }

    public void copyProperties(Object bean, Object target, String... ignoreProperties) {
        BeanUtils.copyProperties(bean, target, ignoreProperties);
    }

}
