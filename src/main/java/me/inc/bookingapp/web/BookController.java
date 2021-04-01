package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.BookStayBinding;
import me.inc.bookingapp.model.entity.BookStay;
import me.inc.bookingapp.model.entity.TrainBook;
import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.service.BookingService;
import me.inc.bookingapp.service.StayListingService;
import me.inc.bookingapp.service.TrainListingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/account/book")
public class BookController {

    private final BookingService bookingService;
    private final StayListingService stayListingService;
    private final TrainListingService trainListingService;
    private final ModelMapper modelMapper;

    public BookController(BookingService bookingService, StayListingService stayListingService, TrainListingService trainListingService, ModelMapper modelMapper) {
        this.bookingService = bookingService;
        this.stayListingService = stayListingService;
        this.trainListingService = trainListingService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/stay/{id}")
    public ModelAndView bookStay(@PathVariable String id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        StayListingView stay = stayListingService.getByViewId(id);

        modelAndView.addObject("stayListing", stay);
        if (!model.containsAttribute("book"))
            modelAndView.addObject("book", new BookStayBinding());

        modelAndView.setViewName("stay/book");

        return modelAndView;
    }

    @PostMapping("/stay/{id}")
    public ModelAndView bookStayConfirm(@PathVariable String id, @Valid BookStayBinding book, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("book", book);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.book",
                            bindingResult);

            modelAndView.setViewName("redirect:/account/book/stay/" + id);
            return modelAndView;
        }

        bookingService.bookStay(modelMapper.map(book, BookStay.class), id, principal.getName());
        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("name", stayListingService.getById(id).getListingTitle());
        modelAndView.setViewName("redirect:/account/bookings/stay");

        return modelAndView;
    }

    @PostMapping("/train/{id}")
    public ModelAndView trainBook(@PathVariable String id, RedirectAttributes redirectAttributes, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        bookingService.bookTrain(modelMapper.map(trainListingService.getById(id), TrainBook.class), id, principal.getName());
        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("name", trainListingService.getTrainViewById(id).getListingTitle());
        modelAndView.setViewName("redirect:/account/bookings/trains");

        return modelAndView;
    }

}
