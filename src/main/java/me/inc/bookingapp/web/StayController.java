package me.inc.bookingapp.web;

import me.inc.bookingapp.service.StayListingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stay")
public class StayController {

    private final StayListingService stayListingService;
    private final ModelMapper modelMapper;


    public StayController(StayListingService stayListingService, ModelMapper modelMapper) {
        this.stayListingService = stayListingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String allStays() {

        return "all-stays";
    }

}
