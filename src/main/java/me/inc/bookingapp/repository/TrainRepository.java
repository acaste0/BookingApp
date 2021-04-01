package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.ListingLog;
import me.inc.bookingapp.model.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {

    List<Train> findAllByLeaveBefore(LocalDateTime before);

}
