package com.example.kusithms_hdmedi_project.domain.hospital.controller;

import com.example.kusithms_hdmedi_project.domain.hospital.dto.SearchType;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalDetailsDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalPageDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalSearchDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalSearchPageDto;
import com.example.kusithms_hdmedi_project.domain.hospital.service.HospitalService;
import com.example.kusithms_hdmedi_project.global.common.BaseResponse;
import com.example.kusithms_hdmedi_project.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<?>> getHospitalsByName(@RequestParam String hospitalName) {
        HospitalSearchPageDto hospitals = hospitalService.searchHospitalsByName(hospitalName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, hospitals));
    }

    @GetMapping("/details/{hospitalId}")
    public ResponseEntity<BaseResponse<?>> getHospitalDetails(@PathVariable Long hospitalId,
                                                              @RequestParam(defaultValue = "0") int reviewPageNum,
                                                              @RequestParam(defaultValue = "10") int pageSize) {
        HospitalDetailsDto hospital = hospitalService.getHospitalDetails(hospitalId, reviewPageNum, pageSize);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, hospital));
    }
}
