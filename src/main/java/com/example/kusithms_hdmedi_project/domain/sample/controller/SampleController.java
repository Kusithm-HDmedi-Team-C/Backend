package com.example.kusithms_hdmedi_project.domain.sample.controller;


import com.example.kusithms_hdmedi_project.domain.sample.dto.request.SampleRequestDto;
import com.example.kusithms_hdmedi_project.domain.sample.dto.response.SampleResponseDto;
import com.example.kusithms_hdmedi_project.domain.sample.service.SampleService;
import com.example.kusithms_hdmedi_project.global.common.BaseResponse;
import com.example.kusithms_hdmedi_project.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class SampleController {

    private final SampleService sampleService;

    @PostMapping("/sample")
    public ResponseEntity<BaseResponse<?>> sampleCreate(@RequestBody SampleRequestDto sampleRequestDto) {
        SampleResponseDto sampleResponseDto = sampleService.sampleCreate(sampleRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, sampleResponseDto));
    }
}
