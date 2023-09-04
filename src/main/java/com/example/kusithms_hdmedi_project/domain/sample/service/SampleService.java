package com.example.kusithms_hdmedi_project.domain.sample.service;

import com.example.kusithms_hdmedi_project.domain.sample.dto.request.SampleRequestDto;
import com.example.kusithms_hdmedi_project.domain.sample.dto.response.SampleResponseDto;
import com.example.kusithms_hdmedi_project.domain.sample.entity.Sample;
import com.example.kusithms_hdmedi_project.domain.sample.repository.SampleRepository;
import com.example.kusithms_hdmedi_project.global.error.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class SampleService {

    private final SampleRepository sampleRepository;

    public SampleResponseDto sampleCreate(SampleRequestDto sampleRequestDto){
        validateMessageDuplicate(sampleRequestDto.getMessage());
        Sample sample = new Sample(sampleRequestDto.getMessage());
        Sample savedSample = sampleRepository.save(sample);
        return SampleResponseDto.of(savedSample.getId(), savedSample.getMessage());
    }

    private void validateMessageDuplicate(String message){
        List<Sample> sampleList = sampleRepository.findAllByMessage(message);
        if(!sampleList.isEmpty()){
            throw new ConflictException();
        }
    }
}
