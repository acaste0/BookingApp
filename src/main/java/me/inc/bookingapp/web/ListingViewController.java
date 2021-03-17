package me.inc.bookingapp.web;

import me.inc.bookingapp.service.StayListingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class ListingViewController {

    private final StayListingService stayListingService;

    public ListingViewController(StayListingService stayListingService) {
        this.stayListingService = stayListingService;
    }

    @GetMapping("/stay")
    public ModelAndView viewAllStays(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("all-stays");
        mav.addObject("stayListings",stayListingService.getAllListings());
        return mav;
    }

}
