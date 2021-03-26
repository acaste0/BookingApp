package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.GeneralLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralLogRepository extends JpaRepository<GeneralLog, String> {
}
