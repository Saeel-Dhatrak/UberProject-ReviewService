package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Driver;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.DriverRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements CommandLineRunner {

    private ReviewRepository reviewRepository;
    private BookingRepository bookingRepository;
    private DriverRepository driverRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository,
    DriverRepository driverRepository){
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("*****************");
//        Review r = Review.builder()
//                        .content("Good Ride")
//                        .rating(4.0)
//                        .build();
//
//        Booking b = Booking.builder()
//                        .review(r)
//                        .endTime(new Date())
//                        .build();
//        //reviewRepository.save(r);
//        bookingRepository.save(b);
//        System.out.println(r);
//
//        System.out.println(r.getId());

//        List<Review> reviews = reviewRepository.findAll();
//        for(Review review : reviews){
//            System.out.println(r.getContent());
//        }

       // reviewRepository.deleteById(2L);

        Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L,"MH34Aj6666");
        if(driver.isPresent()){
            System.out.println(driver.get().getName());
            List<Booking> bookings = driver.get().getBookings();
            for(Booking booking : bookings){
                System.out.println(booking.getId());
            }
        }

    }
}
