package me.inc.bookingapp.web;

import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.service.StayListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private final StayListingService stayListingService;

    public IndexController(StayListingService stayListingService) {
        this.stayListingService = stayListingService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        List<StayListingView> listing = stayListingService.getRecentlyAdded();
        model.addAttribute("listings", listing);
        return "index";
    }


}
