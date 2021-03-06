package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.TrainBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainBookRepository extends JpaRepository<TrainBook, String> {
}
