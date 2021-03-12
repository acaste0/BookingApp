package me.inc.bookingapp.web;

import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.AdminService;
import me.inc.bookingapp.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final AccountService accountService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;


    public AdminController(AdminService adminService, AccountService accountService, RoleService roleService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.accountService = accountService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/edit/role")
    public ModelAndView editRoleView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roleService.getAllRoles());
        modelAndView.setViewName("/admin/change-role");
        return modelAndView;
    }

    @PostMapping("/edit/role")
    public String editRoleView(String username, AccountRole accountRole, RedirectAttributes redirectAttributes) {

        if (!accountService.usernameAvailability(username)) {
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:/account/edit/role";
        }

        if (!adminService.changeRole(username, accountRole)) {
            redirectAttributes.addFlashAttribute("alreadyInThisRole", true);

            return "redirect:/account/edit/role";
        }

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/account/edit/role";
        ;
    }

}
