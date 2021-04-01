package me.inc.bookingapp.web;

import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.model.view.TrainView;
import me.inc.bookingapp.service.StayListingService;
import me.inc.bookingapp.service.TrainListingService;
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
    private final TrainListingService trainListingService;

    public ListingViewController(StayListingService stayListingService, TrainListingService trainListingService) {
        this.stayListingService = stayListingService;
        this.trainListingService = trainListingService;
    }

    @GetMapping("/stay")
    public ModelAndView viewAllStays(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/stay/all-stays");
        mav.addObject("stayListings", stayListingService.getAllListings());
        return mav;
    }

    @GetMapping("/stay/view/{id}")
    public ModelAndView viewStayInfo(@PathVariable String id){
        ModelAndView mav = new ModelAndView("/stay/view");
        StayListingView sl = stayListingService.getByViewId(id);
        if (sl == null) {
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
        mav.addObject("stayListing", sl);
        return mav;
    }

    @GetMapping("/train")
    public ModelAndView modelAndView() {
        ModelAndView mav = new ModelAndView("train/all-trains");
        mav.addObject("trains", trainListingService.getAllTrains());
        return mav;
    }

    @GetMapping("/train/view/{id}")
    public ModelAndView viewTrainInfo(@PathVariable String id){
        ModelAndView mav = new ModelAndView("/train/view");
        TrainView trainView = trainListingService.getTrainViewById(id);
        if (trainView == null) {
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
        mav.addObject("trainListing", trainView);
        return mav;
    }

}
