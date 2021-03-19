package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.AccountRoleEditBinding;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.AdminService;
import me.inc.bookingapp.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final RoleService roleService;


    public AdminController(AdminService adminService, AccountService accountService, RoleService roleService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @ModelAttribute("roleBinding")
    public AccountRoleEditBinding roleBinding() {
        return new AccountRoleEditBinding();
    }

    @GetMapping("/role/remove")
    public ModelAndView removeRoleView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roleService.getAllRoles());
        modelAndView.setViewName("/admin/remove-role");
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

}
