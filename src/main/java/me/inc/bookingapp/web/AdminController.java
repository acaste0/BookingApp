package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.AccountRoleAddBinding;
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
    private final AccountService accountService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;


    public AdminController(AdminService adminService, AccountService accountService, RoleService roleService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.accountService = accountService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("roleAdd")
    public AccountRoleAddBinding addRole() {
        return new AccountRoleAddBinding();
    }

    @GetMapping("/role/add")
    public ModelAndView addRoleView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roleService.getAllRoles());
        modelAndView.setViewName("/admin/add-role");
        return modelAndView;
    }

    @PostMapping("/role/add")
    public String addRole(AccountRoleAddBinding accountRoleAddBinding, RedirectAttributes redirectAttributes) {

        try {
            adminService.addRole( accountRoleAddBinding);
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

}
