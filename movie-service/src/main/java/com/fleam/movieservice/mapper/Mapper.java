package com.fleam.movieservice.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public <T> T objectToDTO(Object object, Class<T> targetClass){
        return modelMapper.map(object, targetClass);
    }

    public <S, T> List<T> objectsToDTOs(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

}
