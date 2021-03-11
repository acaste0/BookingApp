package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.StayListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StayListingRepository extends JpaRepository<StayListing, String> {
}
