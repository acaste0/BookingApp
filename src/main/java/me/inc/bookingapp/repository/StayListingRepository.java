package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  StayListingRepository extends JpaRepository<StayListing, String> {
    Optional<StayListing> findByListingTitle(String title);
}
