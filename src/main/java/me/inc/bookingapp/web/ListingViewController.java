package me.inc.bookingapp.web;

import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.service.StayListingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        mav.setViewName("/stay/all-stays");
        mav.addObject("stayListings", stayListingService.getAllListings());
        return mav;
    }

    @GetMapping("/stay/view/{id}")
    public ModelAndView viewAllStays(@PathVariable String id){
        ModelAndView mav = new ModelAndView("/stay/view");
        StayListingView sl = stayListingService.getById(id);
        if (sl == null) {
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
        mav.addObject("stayListing", sl);
        return mav;
    }

}
