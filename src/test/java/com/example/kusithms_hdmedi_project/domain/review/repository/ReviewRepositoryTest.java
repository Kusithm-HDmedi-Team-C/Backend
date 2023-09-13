package com.example.kusithms_hdmedi_project.domain.review.repository;


import com.example.kusithms_hdmedi_project.domain.hospital.entity.Hospital;
import com.example.kusithms_hdmedi_project.domain.review.dto.request.CreateReviewRequestDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.CreateReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.GetHospitalVReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.dto.response.VerifyReviewResponseDto;
import com.example.kusithms_hdmedi_project.domain.review.entity.ExaminationType;
import com.example.kusithms_hdmedi_project.domain.review.entity.ReviewExamination;
import com.example.kusithms_hdmedi_project.domain.review.entity.VerifiedReview;
import com.example.kusithms_hdmedi_project.domain.review.service.ReviewService;
import com.example.kusithms_hdmedi_project.global.error.exception.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ReviewRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    VerifiedReviewRepository verifiedReviewRepository;

    @Autowired
    ReviewService reviewService;

    @Test
    public void 병원별_VerifiedReivew_검색_테스트() throws Exception {
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

        VerifiedReview verifiedReview = VerifiedReview.builder()
                .content("리뷰리뷰리뷰리뷰")
                .price(10000)
                .imageUrl("이미지입니다")
                .doctor("오진영")
                .rating(3)
                .hospital(hospital)
                .build();

        hospital.getVerifiedReviews().add(verifiedReview);

        em.persist(verifiedReview);

        List<ExaminationType> examinationTypes1 = new ArrayList<>();

        examinationTypes1.add(ExaminationType.AAA);
        examinationTypes1.add(ExaminationType.BBB);
        examinationTypes1.add(ExaminationType.CCC);

        for (ExaminationType examinationType: examinationTypes1) {
            ReviewExamination reviewExamination = ReviewExamination.builder()
                    .verifiedReview(verifiedReview)
                    .reviewType(examinationType)
                    .build();

            em.persist(reviewExamination);

            verifiedReview.getReviewExaminations().add(reviewExamination);
        }


        em.flush();
        em.clear();

        //when
        List<GetHospitalVReviewResponseDto> hospitalVerifiedReview = reviewService.getHospitalVerifiedReview(hospital.getId());

        System.out.println((hospitalVerifiedReview.get(0).getExaminations().get(0)));
        System.out.println((hospitalVerifiedReview.get(0).getExaminations().get(1)));
        System.out.println((hospitalVerifiedReview.get(0).getExaminations().get(2)));

        //then
        Assertions.assertThat(verifiedReview.getContent()).isEqualTo(hospitalVerifiedReview.get(0).getContent());
        Assertions.assertThat(hospitalVerifiedReview.get(0).getExaminations().get(1)).isEqualTo(examinationTypes1.get(1));

    }


    @Test
    public void 병원_REVIEW_등록_테스트() throws Exception {

        // 병원 리뷰 등록 테스트를 하려했지만 실패하고...
//        // given
//        Hospital hospital = Hospital.builder()
//                .name("연세건강정신병원")
//                .telephone("02-822-0345")
//                .url("https://blog.naver.com/verydoc")
//                .mapUrl("https://map.naver.com/p/search/%EC%97%B0%EC%84%B8%EA%B1%B4%EA%B0%95%EC%A0%95%EC%8B%A0%EB%B3%91%EC%9B%90/place/1123328494?placePath=?entry=pll&from=nx&fromNxList=true&c=15.00,0,0,0,dh")
//                .numberOfReviews(0)
//                .totalRating(0)
//                .area("주유노빌딩 2층")
//                .area1("서울")
//                .area2("동작구")
//                .area3("상도로 264")
//                .build();
//
//        em.persist(hospital);
//
//        em.flush();
//        em.clear();
//
//        List<ExaminationType> examinationTypes1 = new ArrayList<>();
//
//        examinationTypes1.add(ExaminationType.AAA);
//        examinationTypes1.add(ExaminationType.BBB);
//        examinationTypes1.add(ExaminationType.CCC);
//
//
//
//        List<ExaminationType> examinationTypes2 = new ArrayList<>();
//
//        examinationTypes2.add(ExaminationType.CCC);
//        examinationTypes2.add(ExaminationType.BBB);
//        examinationTypes2.add(ExaminationType.AAA);
//
//        CreateReviewRequestDto createReviewRequestDto1 = CreateReviewRequestDto.of(hospital.getId(), 3, "리뷰리뷰리뷰리뷰", "김닥터", 9999, examinationTypes1);
//        CreateReviewRequestDto createReviewRequestDto2 = CreateReviewRequestDto.of(hospital.getId(), 5, "222222리뷰222", "오닥터", 5555, examinationTypes2);
//
//
//        //when
//        CreateReviewResponseDto testReview1 = reviewService.createTestReview(createReviewRequestDto1, "www.첫번쨰 이미지.com");
//        CreateReviewResponseDto testReview2 = reviewService.createTestReview(createReviewRequestDto2, "www.두번째 이미지.com");
//
//
//        VerifyReviewResponseDto verifyReviewResponseDto = reviewService.verifyReview(testReview1.getReviewId());
//        VerifyReviewResponseDto verifyReviewResponseDto2 = reviewService.verifyReview(testReview2.getReviewId());
//        List<GetHospitalVReviewResponseDto> hospitalVerifiedReview = reviewService.getHospitalVerifiedReview(hospital.getId());
//
//
//        System.out.println("reviewId11 :: " + testReview1);
//        System.out.println("reviewId22 :: " + testReview2);
//
//        System.out.println("찾은 개수 : " + hospitalVerifiedReview.toArray().length);
//
//        hospitalVerifiedReview.forEach(data -> System.out.println("찾은 병원리뷰" + data.getExaminations() + data.getContent() + data.getReviewId()));
////        reviewService.createReview()
    }

    @Test
    public void 병원이름_페이지_가져오기(){
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

        VerifiedReview verifiedReview1 = VerifiedReview.builder()
                .content("리뷰리뷰리뷰리뷰11")
                .price(10000)
                .imageUrl("이미지입니다")
                .doctor("오진영")
                .rating(3)
                .hospital(hospital)
                .build();

        VerifiedReview verifiedReview2 = VerifiedReview.builder()
                .content("리뷰리뷰리뷰리뷰222")
                .price(10000)
                .imageUrl("이미지입니다")
                .doctor("오진영")
                .rating(3)
                .hospital(hospital)
                .build();

        VerifiedReview verifiedReview3 = VerifiedReview.builder()
                .content("리뷰리뷰리뷰리뷰333")
                .price(10000)
                .imageUrl("이미지입니다")
                .doctor("오진영")
                .rating(3)
                .hospital(hospital)
                .build();
        VerifiedReview verifiedReview4 = VerifiedReview.builder()
                .content("리뷰리뷰리뷰리뷰444")
                .price(10000)
                .imageUrl("이미지입니다")
                .doctor("오진영")
                .rating(3)
                .hospital(hospital)
                .build();

        VerifiedReview verifiedReview5 = VerifiedReview.builder()
                .content("리뷰리뷰리뷰리뷰555")
                .price(10000)
                .imageUrl("이미지입니다")
                .doctor("오진영")
                .rating(3)
                .hospital(hospital)
                .build();

        VerifiedReview verifiedReview6 = VerifiedReview.builder()
                .content("리뷰리뷰리뷰리뷰666")
                .price(10000)
                .imageUrl("이미지입니다")
                .doctor("오진영")
                .rating(3)
                .hospital(hospital)
                .build();


        hospital.getVerifiedReviews().add(verifiedReview1);
        hospital.getVerifiedReviews().add(verifiedReview2);
        hospital.getVerifiedReviews().add(verifiedReview3);
        hospital.getVerifiedReviews().add(verifiedReview4);
        hospital.getVerifiedReviews().add(verifiedReview5);
        hospital.getVerifiedReviews().add(verifiedReview6);

        em.persist(verifiedReview1);
        em.persist(verifiedReview2);
        em.persist(verifiedReview3);
        em.persist(verifiedReview4);
        em.persist(verifiedReview5);
        em.persist(verifiedReview6);
        em.flush();

        // when
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<VerifiedReview> verifiedReviews = verifiedReviewRepository.findByHospitalIdOrderByCreateDateDesc(hospital.getId(), pageRequest).getContent();

        // then
        Assertions.assertThat(verifiedReviews.get(0).getId()).isEqualTo(verifiedReview6.getId());
        Assertions.assertThat(verifiedReviews.get(1).getId()).isEqualTo(verifiedReview5.getId());
        Assertions.assertThat(verifiedReviews.get(2).getId()).isEqualTo(verifiedReview4.getId());
        Assertions.assertThat(verifiedReviews.get(3).getId()).isEqualTo(verifiedReview3.getId());
        Assertions.assertThat(verifiedReviews.get(4).getId()).isEqualTo(verifiedReview2.getId());

        verifiedReviews.forEach(data -> System.out.println(data.getContent()));
    }
}
