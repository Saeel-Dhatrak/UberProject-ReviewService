package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("*****************");
        Review r = Review.builder()
                .content("Good Ride")
                .rating(4.0)
                .build();

        reviewRepository.save(r);
        System.out.println(r.getId());

        List<Review> reviews = reviewRepository.findAll();
        for(Review review : reviews){
            System.out.println(r.getContent());
        }

       // reviewRepository.deleteById(2L);
    }
}
