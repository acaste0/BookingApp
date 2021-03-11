package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.AccountLoginBinding;
import me.inc.bookingapp.model.binding.AccountRegistrationBinding;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.model.service.AccountServiceModel;
import me.inc.bookingapp.model.view.AccountViewModel;
import me.inc.bookingapp.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("types")
    public List<AccountType> accTypes() {
        return List.of(AccountType.PERSONAL, AccountType.BUSINESS);
    }

    @ModelAttribute("account")
    public AccountRegistrationBinding account() {
        return new AccountRegistrationBinding();
    }


    @GetMapping("/registration")
    public String registerGet(Model model) {
        return "register";
    }

    @GetMapping("/edit")
    public String accountEdit() {
        return "account-edit";
    }


    @GetMapping("/login")
    public String accountLogin() {
        return "login";
    }

    @GetMapping("/view/{id}")
    public ModelAndView accountView(@PathVariable String id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        AccountViewModel view = accountService.getView(id);
        if (view == null || !principal.getName().equals(id)) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.addObject("accountView", view);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @PostMapping("/registration")
    public String accountRegister(AccountRegistrationBinding account,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (!this.accountService.usernameAvailability(account.getUsername())) {
            redirectAttributes.addFlashAttribute("usernameExist", true);

            if (!this.accountService.emailAvailability(account.getEmail())) {
                redirectAttributes.addFlashAttribute("emailExist", true);
            }

            redirectAttributes.addFlashAttribute("account", account);
            return "redirect:/account/registration";

        }


        this.accountService.registerAccount(modelMapper.map(account, AccountServiceModel.class));
        return "redirect:/";
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

}


