package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.ListingStartBinding;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/listing")
public class ListingController {

    private final StayController stayController;
    private final ModelMapper modelMapper;

    public ListingController(StayController stayController, ModelMapper modelMapper) {
        this.stayController = stayController;
        this.modelMapper = modelMapper;
    }



    @ModelAttribute("listingSetup")
    public ListingStartBinding listingSetup() {
        return new ListingStartBinding();
    }

    @PostMapping("/type/submit")
    public ModelAndView listingStartSubmit(ListingStartBinding listingSetup,
                                           BindingResult bindingResult,
                                           RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("titleTooShort", true);
            mav.setViewName("/");
            return mav;
        }

        switch (listingSetup.getListingType()) {
            case STAY:
                redirectAttributes.addFlashAttribute("title", listingSetup.getListingTitle());
                mav.setViewName("redirect:/listing/stay/create");
                return mav;
            case TRAIN:
                mav.addObject("title", listingSetup.getListingTitle());
                mav.setViewName("redirect:/listing/train/create");
                return mav;
            case CAR_RENT:
                mav.addObject("title", listingSetup.getListingTitle());
                mav.setViewName("redirect:/listing/car-rent/create");
                return mav;
        }

        mav.setViewName("redirect:/");
        return mav;
    }

}
