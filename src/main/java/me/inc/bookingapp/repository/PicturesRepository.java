package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.StayListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicturesRepository extends JpaRepository<Picture, String> {

    List<Picture> findAllByListing(StayListing stayListing);
}
