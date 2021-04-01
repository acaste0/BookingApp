package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.AccountEditBinding;
import me.inc.bookingapp.model.binding.AccountRegistrationBinding;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.model.service.AccountServiceModel;
import me.inc.bookingapp.model.view.AccountViewModel;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final BookingService bookingService;
    private final ModelMapper modelMapper;

    public AccountController(AccountService accountService, BookingService bookingService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }




    @GetMapping("/listings")
    public ModelAndView accountListings(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("stayListings",
                accountService.getAllAccountListings(principal.getName()));
        mav.setViewName("/account/account-listings");
        return mav;
    }

    @GetMapping("/bookings/stay")
    public ModelAndView accountStayBookings(Principal principal) {
        ModelAndView mav = new ModelAndView("/account/stay-bookings");
        mav.addObject("bookings", bookingService.getAllStayBookingsByUsername(principal.getName()));

        return mav;
    }

    @GetMapping("/bookings/trains")
    public ModelAndView accountTrainBookings(Principal principal) {
        ModelAndView mav = new ModelAndView("/account/train-bookings");
        mav.addObject("bookings", bookingService.getAllTrainBookingsByUsername(principal.getName()));

        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView accountEdit(Model model, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accountView", accountView(principal.getName()));
        if (!model.containsAttribute("accountEditForm"))
            modelAndView.addObject("accountEditForm", accountEditBinding(principal.getName()));
        modelAndView.setViewName("account/account-edit");

        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView accountEditConfirm(@Valid AccountEditBinding accountEditForm,
                                           BindingResult bindingResult,
                                           RedirectAttributes redirectAttributes,
                                           Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("accountEditForm", accountEditForm);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.accountEditForm",
                            bindingResult);


            modelAndView.setViewName("redirect:/account/edit");
            return modelAndView;
        }

        accountService.editAccount(principal.getName(), accountEditForm);
        redirectAttributes.addFlashAttribute("successAccountEdit", true);
        modelAndView.setViewName("redirect:/account/view");
        return modelAndView;
    }

    @GetMapping("/login")
    public String accountLogin(Model model) {
        if (!model.containsAttribute("bad_credentials"))
            model.addAttribute("bad_credentials", false);
        return "login";
    }

    @GetMapping("/view")
    public ModelAndView accountView(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        AccountViewModel view = accountService.getView(principal.getName());

        modelAndView.addObject("accountView", view);
        modelAndView.setViewName("/account/profile");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registerView(Model model) {
        ModelAndView mav = new ModelAndView("/account/register");
        if (!model.containsAttribute("accountRegistration")) {
            mav.addObject("accountRegistration", new AccountRegistrationBinding());
        }

        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView accountRegister(@Valid AccountRegistrationBinding accountRegistration,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("accountRegistration", accountRegistration);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.accountRegistration",
                            bindingResult);

            mav.setViewName("redirect:/account/registration");
            return mav;
        }

        if (!this.accountService.usernameAvailability(accountRegistration.getUsername())) {
            redirectAttributes.addFlashAttribute("usernameExist", true);

            if (!this.accountService.emailAvailability(accountRegistration.getEmail())) {
                redirectAttributes.addFlashAttribute("emailExist", true);
            }

            redirectAttributes.addFlashAttribute("account", accountRegistration);
            mav.setViewName("redirect:/account/registration");
            return mav;

        }

        this.accountService.registerAccount(modelMapper.map(accountRegistration, AccountServiceModel.class));
        redirectAttributes.addFlashAttribute("successfulRegistration", true);
        mav.setViewName("redirect:/");
        return mav;
    }


    @GetMapping("/logout")
    public String accountLogout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }


    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute("email") String email, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("email", email);

        modelAndView.setViewName("redirect:/account/login");
        return modelAndView;
    }


    @ModelAttribute("types")
    public List<AccountType> accTypes() {
        return List.of(AccountType.PERSONAL, AccountType.BUSINESS);
    }

    public AccountEditBinding accountEditBinding(String username) {
        return modelMapper.map(accountService.getAccountByUsername(username), AccountEditBinding.class);
    }

    public AccountViewModel accountView(String username) {
        return modelMapper.map(accountService.getAccountByUsername(username), AccountViewModel.class);
    }

}


