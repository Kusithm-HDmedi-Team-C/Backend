package com.example.kusithms_hdmedi_project.domain.hospital.service;

import com.example.kusithms_hdmedi_project.domain.hospital.dto.SearchType;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalPageDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalSearchDto;
import com.example.kusithms_hdmedi_project.domain.hospital.dto.response.HospitalSearchPageDto;
import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HospitalServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    HospitalService hospitalService;

    @Nested
    class getHospitalPage_메서드는 {
        List<HospitalDto> expect1;
        List<HospitalDto> expect2;
        @BeforeEach
        void init() {
            Hospital hospital1 = Hospital.builder()
                    .name("병원1")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(2)
                    .totalRating(10)
                    .averageRating(5)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            Hospital hospital2 = Hospital.builder()
                    .name("병원2")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(4)
                    .totalRating(8)
                    .averageRating(2)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            Hospital hospital3 = Hospital.builder()
                    .name("병원3")
                    .telephone("02-111-1111")
                    .url("www.aaa.com")
                    .mapUrl("www.map.naver.com/aaa")
                    .numberOfReviews(10)
                    .totalRating(79)
                    .averageRating(7.9)
                    .area1("서울")
                    .area2("동작구")
                    .area3("사당로 42")
                    .area("3층")
                    .build();

            em.persist(hospital1);
            em.persist(hospital2);
            em.persist(hospital3);

            expect1 = Stream.of(hospital3, hospital1, hospital2)
                    .map(HospitalDto::of)
                    .collect(Collectors.toList());
            expect2 = Stream.of(hospital3, hospital2, hospital1)
                    .map(HospitalDto::of)
                    .collect(Collectors.toList());
        }

        @Nested
        class pageSize와_pageNumber에_따라 {

            @Nested
            class AVERAGE_RATING로_조회하면 {

                @Test
                void 만족도_순으로_병원정보를_제공한다() {

                    HospitalPageDto page = hospitalService.getHospitalPage(SearchType.AVERAGE_RATING, 0, 10);
                    List<HospitalDto> hospitals = page.getHospitals();

                    Assertions.assertThat(hospitals).extracting(
                            HospitalDto::getName,
                            HospitalDto::getAverageRating,
                            HospitalDto::getNumberOfReviews)
                            .contains(Tuple.tuple(expect1.get(0).getName(), expect1.get(0).getAverageRating(), expect1.get(0).getNumberOfReviews())
                            , Tuple.tuple(expect1.get(1).getName(), expect1.get(1).getAverageRating(), expect1.get(1).getNumberOfReviews()),
                                    Tuple.tuple(expect1.get(2).getName(), expect1.get(2).getAverageRating(), expect1.get(2).getNumberOfReviews()));
                }
            }

            @Nested
            class NUMBER_OF_REVIEW로_조회하면 {

                @Test
                void 리뷰_개수_순으로_병원정보를_제공한다() {
                    HospitalPageDto page = hospitalService.getHospitalPage(SearchType.NUMBER_OF_REVIEWS, 0, 3);
                    List<HospitalDto> hospitals = page.getHospitals();

                    for (HospitalDto hospital : hospitals) {
                        System.out.println(hospital);
                    }

                    for (HospitalDto hospitalDto : expect2) {
                        System.out.println(hospitalDto);
                    }

                    Assertions.assertThat(hospitals).extracting(
                                    HospitalDto::getName,
                                    HospitalDto::getAverageRating,
                                    HospitalDto::getNumberOfReviews)
                            .contains(Tuple.tuple(expect2.get(0).getName(), expect2.get(0).getAverageRating(), expect2.get(0).getNumberOfReviews()),
                                    Tuple.tuple(expect2.get(1).getName(), expect2.get(1).getAverageRating(), expect2.get(1).getNumberOfReviews()),
                                    Tuple.tuple(expect2.get(2).getName(), expect2.get(2).getAverageRating(), expect2.get(2).getNumberOfReviews()));
                }
            }
        }
    }

    @Nested
    class searchHospitalsByName_메서드는 {
        List<HospitalSearchDto> expect1;
        List<HospitalSearchDto> expect2;

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

            expect1 = List.of(HospitalSearchDto.of(hospital1), HospitalSearchDto.of(hospital2), HospitalSearchDto.of(hospital3));
            expect2 = List.of();
        }

        @Nested
        class 존재하는_이름으로_조회하면 {

            @Test
            void 만족도_순으로_병원정보를_제공한다() {

                HospitalSearchPageDto page = hospitalService.searchHospitalsByName("서울");
                List<HospitalSearchDto> hospitals = page.getHospitals();

                Assertions.assertThat(hospitals).extracting(
                                HospitalSearchDto::getName,
                                HospitalSearchDto::getAddress)
                        .contains(Tuple.tuple(expect1.get(0).getName(), expect1.get(0).getAddress())
                                , Tuple.tuple(expect1.get(1).getName(), expect1.get(1).getAddress()));
            }
        }

        @Nested
        class 존재하지않은_이름으로_조회하면 {
            @Test
            void 빈_리스트를_리턴한다() {
                List<HospitalSearchDto> hospitals = hospitalService.searchHospitalsByName("광주").getHospitals();
                Assertions.assertThat(hospitals.size()).isEqualTo(0);
                Assertions.assertThat(expect2).isEqualTo(hospitals);
            }
        }
    }
}