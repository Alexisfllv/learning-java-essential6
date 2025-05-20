package edu.com.javaesencial07salesapi.util;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperUtil {


    private final ApplicationContext applicationContext;


    // lista

    public <S,T> List<T> mapList(List<S> source, Class<T> targetClass, String... mapperQualifier){

        ModelMapper modelMapper = getModelMapper(mapperQualifier);

        return source.stream()
                .map(sourceElement -> modelMapper.map(sourceElement, targetClass))
                .collect(Collectors.toList());
    }

    // un solo elemento

    public <S,T> T map(S source, Class<T> targetClass, String... mapperQualifier){
        ModelMapper modelMapper = getModelMapper(mapperQualifier);
        return modelMapper.map(source, targetClass);
    }

    private ModelMapper getModelMapper(String[] mapperQualifier) {

        if(mapperQualifier.length == 0 || mapperQualifier == null || mapperQualifier[0].isEmpty()){
            return applicationContext.getBean("defaultMapper",ModelMapper.class);
        }else{
            return applicationContext.getBean(mapperQualifier[0],ModelMapper.class);
        }
    }
}
