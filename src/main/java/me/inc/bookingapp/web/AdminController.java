package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.AccountRoleEditBinding;
import me.inc.bookingapp.model.service.StayListingServiceModel;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.AdminService;
import me.inc.bookingapp.service.RoleService;
import me.inc.bookingapp.service.StayListingService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AccountService accountService;
    private final RoleService roleService;
    private final StayListingService stayListingService;


    public AdminController(AdminService adminService, AccountService accountService, AccountService accountService1, RoleService roleService, ModelMapper modelMapper, StayListingService stayListingService) {
        this.adminService = adminService;
        this.accountService = accountService1;
        this.roleService = roleService;
        this.stayListingService = stayListingService;
    }


    @ModelAttribute("roleBinding")
    public AccountRoleEditBinding roleBinding() {
        return new AccountRoleEditBinding();
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
