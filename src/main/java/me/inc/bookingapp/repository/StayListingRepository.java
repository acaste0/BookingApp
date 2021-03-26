package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import me.inc.bookingapp.model.view.StayListingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;

@Repository
public interface  StayListingRepository extends JpaRepository<StayListing, String> {
    Optional<StayListing> findByListingTitle(String title);

    @Query(nativeQuery = true, value = "select * from stay_listings ORDER BY created_on DESC LIMIT 3")
    List<StayListing> getLastThreeRecentlyAdded();
}
