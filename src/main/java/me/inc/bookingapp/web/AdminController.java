package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.AccountRoleEditBinding;
import me.inc.bookingapp.model.binding.TrainCreateBinding;
import me.inc.bookingapp.model.service.TrainListingServiceModel;
import me.inc.bookingapp.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AccountService accountService;
    private final RoleService roleService;
    private final TrainListingService trainListingService;
    private final StayListingService stayListingService;
    private final ModelMapper modelMapper;


    public AdminController(AdminService adminService, AccountService accountService, AccountService accountService1, RoleService roleService, ModelMapper modelMapper, TrainListingService trainListingService, StayListingService stayListingService, ModelMapper modelMapper1) {
        this.adminService = adminService;
        this.accountService = accountService1;
        this.roleService = roleService;
        this.trainListingService = trainListingService;
        this.stayListingService = stayListingService;
        this.modelMapper = modelMapper1;
    }

    @ModelAttribute("allCountries")
    public List<String> allCountries() {
        return Arrays.stream(Locale.getISOCountries())
                .map(c -> new Locale("", c)
                        .getDisplayCountry()).collect(Collectors.toList());
    }

    @ModelAttribute("roleBinding")
    public AccountRoleEditBinding roleBinding() {
        return new AccountRoleEditBinding();
    }

    @GetMapping("/train/add")
    public ModelAndView addNewTrainListing(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (!model.containsAttribute("trainBinding"))
            modelAndView.addObject("trainBinding", new TrainCreateBinding());
        modelAndView.setViewName("/admin/add-train");
        return modelAndView;
    }

    @PostMapping("/train/add")
    public ModelAndView addNewTrainListingConfirm(@Valid TrainCreateBinding trainBinding,
                                                  BindingResult bindingResult,
                                                  RedirectAttributes redirectAttributes,
                                                  Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("trainBinding", trainBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.trainBinding",
                    bindingResult);

            modelAndView.setViewName("redirect:/admin/train/add");
            return modelAndView;
        }

        trainListingService.createTrainListing(modelMapper.map(trainBinding, TrainListingServiceModel.class));
        redirectAttributes.addFlashAttribute("success", true);
        modelAndView.setViewName("redirect:/admin/train/add");
        return modelAndView;
    }

    @GetMapping("/listing/stays/delete")
    public ModelAndView deleteStayListingView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listings", stayListingService.getAll());
        modelAndView.setViewName("/admin/delete-stay-listing");
        return modelAndView;
    }

    @PostMapping("/listing/stays/delete")
    public String deleteStayListingConfirm(String id, RedirectAttributes redirectAttributes) {
        stayListingService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/listing/stays/delete";
    }

    @GetMapping("/role/remove")
    public ModelAndView removeRoleView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roleService.getAllRoles());
        modelAndView.setViewName("/admin/remove-role");
        return modelAndView;
    }

    @GetMapping("/account/edit/{id}")
    public ModelAndView editAccountView(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", accountService.getAccount(id));
        modelAndView.setViewName("/admin/account-edit");
        return modelAndView;
    }

    @GetMapping("/role/add")
    public ModelAndView addRoleView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roleService.getAllRoles());
        modelAndView.setViewName("/admin/add-role");
        return modelAndView;
    }

    @PostMapping("/role/add")
    public String addRole(AccountRoleEditBinding accountRoleEditBinding, RedirectAttributes redirectAttributes) {

        try {
            adminService.addRole(accountRoleEditBinding);
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/admin/role/add";

        } catch (UnsupportedOperationException a) {
            redirectAttributes.addFlashAttribute("alreadyInThisRole", true);
            return "redirect:/admin/role/add";

        } catch (UsernameNotFoundException e) {
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:/admin/role/add";
        }

    }


    @PostMapping("/role/remove")
    public String removeRole(AccountRoleEditBinding accountRoleEditBinding,
                             RedirectAttributes redirectAttributes) {

        try {
            boolean b = adminService.removeRole(accountRoleEditBinding);
            if (b) {
                redirectAttributes.addFlashAttribute("success", true);
            } else {
                redirectAttributes.addFlashAttribute("noRole", true);
            }

            return "redirect:/admin/role/remove";

        } catch (UsernameNotFoundException e) {
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:/admin/role/remove";
        }

    }


    @GetMapping("/logs")
    public ModelAndView logsView() {
        return new ModelAndView("/admin/logs");
    }

}
