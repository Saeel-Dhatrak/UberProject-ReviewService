package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query(nativeQuery = true, value = "SELECT * from Driver where id = :id AND license_number = :license")
    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String license);
    // Raw mysql query, error is thrown at runtime
    @Query("SELECT d from Driver as d from Driver where d.id = :id AND d.licenseNumber = :ln")
    Optional<Driver> hibernateFindByIdAndLicense(Long id, String ln);
    // Hibernate query, error thrown at compile time only

}
