package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.ListingStartBinding;
import me.inc.bookingapp.model.binding.StayListingBinding;
import me.inc.bookingapp.model.binding.StayPropertiesBinding;
import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import me.inc.bookingapp.model.service.StayListingServiceModel;
import me.inc.bookingapp.service.StayListingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    private final ModelMapper modelMapper;


    public StayController(StayListingService stayListingService, ModelMapper modelMapper) {
        this.stayListingService = stayListingService;
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


    @GetMapping("/all")
    public String allStays() {

        return "all-stays";
    }

    @GetMapping("/create")
    public ModelAndView createListing(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (!model.containsAttribute("stayBinding")) {
            StayListingBinding binding = new StayListingBinding().setListingType(ListingType.STAY)
                    .setListingTitle((String) model.getAttribute("title"))
                    .setStayProperties(new StayProperties());
            for (int i = 0; i < 3; i++) {
                binding.addPicture(new Picture());
            }

            modelAndView.addObject("stayBinding", binding);
        }
        modelAndView.setViewName("listing/stay-create");
        return modelAndView;
    }


    @PostMapping("/create")
    public ModelAndView createListing(@Valid StayListingBinding stayBinding,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      Principal principal) {
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
