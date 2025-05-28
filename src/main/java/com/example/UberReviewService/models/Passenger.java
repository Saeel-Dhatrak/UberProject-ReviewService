package com.example.UberReviewService.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends BaseModel {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "booking")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<Review> reviews = new ArrayList<>();
}
