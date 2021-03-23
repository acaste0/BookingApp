package me.inc.bookingapp.web;

import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.service.StayListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stay")
public class StayRestController {

    private final StayListingService stayListingService;

    public StayRestController(StayListingService stayListingService) {
        this.stayListingService = stayListingService;
    }

    @GetMapping("/api")
    public ResponseEntity<List<StayListingView>> finaAll() {
        return ResponseEntity.ok().body(stayListingService.getAll());
    }

}
