package com.example.kusithms_hdmedi_project.domain.hospital.repository;

import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.global.error.exception.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HospitalRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    public void HospitalRepository_조회기능_테스트() throws Exception {
        // given
        Hospital hospital = Hospital.builder()
                .name("연세건강정신병원")
                .telephone("02-822-0345")
                .url("https://blog.naver.com/verydoc")
                .mapUrl("https://map.naver.com/p/search/%EC%97%B0%EC%84%B8%EA%B1%B4%EA%B0%95%EC%A0%95%EC%8B%A0%EB%B3%91%EC%9B%90/place/1123328494?placePath=?entry=pll&from=nx&fromNxList=true&c=15.00,0,0,0,dh")
                .numberOfReviews(0)
                .totalRating(0)
                .area("주유노빌딩 2층")
                .area1("서울")
                .area2("동작구")
                .area3("상도로 264")
                .build();

        em.persist(hospital);
        Long id = hospital.getId();

        // when
        Hospital hospital1 = hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException(ErrorCode.ENTITY_NOT_FOUND.getMessage()));

        // then
        Assertions.assertThat(hospital1.getName()).isEqualTo(hospital.getName());
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                        .isThrownBy(() -> hospitalRepository.findById(id + 1).orElseThrow(() -> new RuntimeException(ErrorCode.ENTITY_NOT_FOUND.getMessage())))
                                .withMessage(ErrorCode.ENTITY_NOT_FOUND.getMessage());
        Assertions.assertThatThrownBy(() -> hospitalRepository.findById(id + 1).orElseThrow(() -> new RuntimeException(ErrorCode.ENTITY_NOT_FOUND.getMessage())));

    }
}