package com.example.kusithms_hdmedi_project.global.converter;

import com.example.kusithms_hdmedi_project.domain.hospital.dto.SearchType;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;

public class SearchTypeConverter implements Converter<String, SearchType> {
    @Override
    public SearchType convert(String source) {
        return Arrays.stream(SearchType.values())
                .filter(t -> t.getHospitalTableValue().equals(source))
                .findAny()
                .orElse(SearchType.AVERAGE_RATING);
    }
}
