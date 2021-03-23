package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.ListingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingLogRepository extends JpaRepository<ListingLog, Long> {
}
