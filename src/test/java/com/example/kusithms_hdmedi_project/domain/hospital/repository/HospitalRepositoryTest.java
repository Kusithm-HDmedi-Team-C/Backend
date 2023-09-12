package com.example.kusithms_hdmedi_project.domain.hospital.repository;

import com.example.kusithms_hdmedi_project.domain.hospital.dto.SearchType;
import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.global.error.exception.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

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

    @Nested
    class findAllWithSearchType_메서드는 {

        private List<Hospital> expected1;
        private List<Hospital> expected2;

        @BeforeEach
        void init() {
            Hospital hospital1 = Hospital.builder()
                    .name("병원1")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(2)
                    .totalRating(10)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            Hospital hospital2 = Hospital.builder()
                    .name("병원1")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(4)
                    .totalRating(8)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            Hospital hospital3 = Hospital.builder()
                    .name("병원1")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(10)
                    .totalRating(79)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            em.persist(hospital1);
            em.persist(hospital2);
            em.persist(hospital3);

            expected1 = List.of(hospital3, hospital1, hospital2);
            expected2 = List.of(hospital3, hospital2, hospital1);
        }

        @Nested
        class AVERAGE_RATING로_조회하면 {

            @Test
            void 만족도_순으로_병원정보를_제공한다() {
                Sort sort = Sort.by(SearchType.AVERAGE_RATING.getHospitalTableValue()).descending()
                        .and(Sort.by("name").ascending());
                PageRequest pageRequest = PageRequest.of(0, 10, sort);
                Page<Hospital> page = hospitalRepository.findAll(pageRequest);
                List<Hospital> actual = page.getContent();

                Assertions.assertThat(actual).isEqualTo(expected1);
            }
        }

        @Nested
        class NUMBER_OF_REVIEW로_조회하면 {

            @Test
            void 리뷰_개수_순으로_병원정보를_제공한다() {
                Sort sort = Sort.by(SearchType.NUMBER_OF_REVIEWS.getHospitalTableValue()).descending()
                        .and(Sort.by("name").ascending());
                PageRequest pageRequest = PageRequest.of(0, 10, sort);
                List<Hospital> actual = hospitalRepository.findAll(pageRequest).getContent();

                Assertions.assertThat(actual).isEqualTo(expected2);
            }
        }
    }

    @Nested
    class findByNameContaining_메서드는 {

        private List<Hospital> expected1;
        private List<Hospital> expected2;

        @BeforeEach
        void init() {
            Hospital hospital1 = Hospital.builder()
                    .name("서울청정신건강의학과의원 강남점")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(2)
                    .totalRating(10)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            Hospital hospital2 = Hospital.builder()
                    .name("서울대학교어린이병원")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(4)
                    .totalRating(8)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            Hospital hospital3 = Hospital.builder()
                    .name("서울정신건강의학과의원")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(10)
                    .totalRating(79)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            Hospital hospital4 = Hospital.builder()
                    .name("국립나주병원")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(10)
                    .totalRating(79)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            Hospital hospital5 = Hospital.builder()
                    .name("창원아이정신건강의학과의원")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(10)
                    .totalRating(79)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            em.persist(hospital1);
            em.persist(hospital2);
            em.persist(hospital3);
            em.persist(hospital4);
            em.persist(hospital5);

            expected1 = List.of(hospital1, hospital2, hospital3);
            expected2 = List.of();
        }

        @Nested
        class 존재하는_이름으로_검색하면 {

            @Test
            void 병원정보를_제공한다() {
                PageRequest pageRequest = PageRequest.of(0, 5);

                Assertions.assertThat(hospitalRepository.findByNameContaining("서울", pageRequest).getContent()).isEqualTo(expected1);
            }
        }

        @Nested
        class 존재하지_않는_이름으로_검색하면 {

            @Test
            void 빈_리스트를_제공한다() {
                PageRequest pageRequest = PageRequest.of(0, 5);

                Assertions.assertThat(hospitalRepository.findByNameContaining("광주", pageRequest).getContent()).isEqualTo(expected2);
            }
        }
    }
}