package com.example.kusithms_hdmedi_project.domain.hospital.controller;

import com.example.kusithms_hdmedi_project.domain.hospital.dto.SearchType;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalPageDto;
import com.example.kusithms_hdmedi_project.domain.hospital.service.HospitalService;
import com.example.kusithms_hdmedi_project.global.common.BaseResponse;
import com.example.kusithms_hdmedi_project.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    /**
     * pageNum 은 0부터 시작한다.
     */
    @GetMapping
    public ResponseEntity<BaseResponse<?>> getHospitals(@RequestParam(defaultValue = "AVERAGE_RATING") SearchType searchType,
                                                        @RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        HospitalPageDto page = hospitalService.getHospitalPage(searchType, pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, page));
    }
}
