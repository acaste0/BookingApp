package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.StayListingBinding;
import me.inc.bookingapp.model.entity.BookStay;
import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import me.inc.bookingapp.model.service.StayListingServiceModel;
import me.inc.bookingapp.model.view.AccountViewModel;
import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.service.BookingService;
import me.inc.bookingapp.service.StayListingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/listing/stay")
public class StayController {

    private final StayListingService stayListingService;
    private final BookingService bookingService;
    private final ModelMapper modelMapper;


    public StayController(StayListingService stayListingService, BookingService bookingService, ModelMapper modelMapper) {
        this.stayListingService = stayListingService;
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("allCountries")
    public List<String> allCountries() {
        return Arrays.stream(Locale.getISOCountries())
                .map(c -> new Locale("", c)
                        .getDisplayCountry()).collect(Collectors.toList());
    }

    @ModelAttribute("stayTypes")
    public List<StayType> stayTypes() {
        return new ArrayList<>(List.of(StayType.values()));
    }

    @GetMapping("/create")
    public ModelAndView createListing(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (!model.containsAttribute("stayBinding")) {
            StayListingBinding binding = new StayListingBinding().setListingType(ListingType.STAY)
                    .setListingTitle((String) model.getAttribute("title"))
                    .setStayProperties(new StayProperties());
            modelAndView.addObject("stayBinding", binding);
        }

        modelAndView.setViewName("stay/stay-create");
        return modelAndView;
    }

    @GetMapping("/bookings/{id}")
    public ModelAndView viewBookings(@PathVariable String id,
                                    Principal principal) {
        ModelAndView mav = new ModelAndView();
        StayListingView stay = stayListingService.getByViewId(id);
        List<BookStay> accountsBooked = bookingService.getAllAccountsBookedStayListing(id);
        if (!stay.getAddedFrom().equals(principal.getName())) {
            mav.setViewName("redirect:/account/listings");
            return mav;
        }

        mav.addObject("listing", stay);
        mav.addObject("reservations", accountsBooked);
        mav.setViewName("stay/who-booked");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editListing(@PathVariable String id,
                                    Principal principal) {
        ModelAndView mav = new ModelAndView();
        StayListingView stay = stayListingService.getByViewId(id);

        if (!stay.getAddedFrom().equals(principal.getName())) {
            mav.setViewName("redirect:/account/listings");
            return mav;
        }

        mav.addObject("listing", stay);
        mav.setViewName("stay/edit");
        return mav;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editListingConfirm(StayListingBinding listing,
                                           BindingResult bindingResult,
                                           RedirectAttributes redirectAttributes,
                                           Principal principal, @PathVariable String id) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("listing", listing);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.listing", listing);

            mav.setViewName("redirect:/account/listings");
            return mav;
        }

        stayListingService.editListing(listing, id, principal.getName());
        redirectAttributes.addFlashAttribute("success", true);
        mav.setViewName("redirect:/account/listings");
        return mav;
    }




    @PostMapping("/create")
    public ModelAndView createListingConfirm(@Valid StayListingBinding stayBinding,
                                             BindingResult bindingResult,
                                             RedirectAttributes redirectAttributes,
                                             Principal principal) throws IOException {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("stayBinding", stayBinding);
            redirectAttributes.
                    addFlashAttribute("org.springframework.validation.BindingResult.stayBinding",
                            bindingResult);

            mav.setViewName("redirect:/listing/stay/create");
            return mav;
        }

        if (!this.stayListingService.titleAvailability(stayBinding.getListingTitle())) {
            redirectAttributes.addFlashAttribute("stayBinding", stayBinding);
            redirectAttributes.addFlashAttribute("titleExist", true);

            mav.setViewName("redirect:/listing/stay/create");
            return mav;
        }

        stayListingService.createListing(this.modelMapper.map(stayBinding, StayListingServiceModel.class),
                principal.getName());
        redirectAttributes.addFlashAttribute("successListingAdd", true);
        mav.setViewName("redirect:/account/listings");
        return mav;
    }
}
