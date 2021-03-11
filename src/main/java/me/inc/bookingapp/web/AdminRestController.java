package me.inc.bookingapp.web;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.view.AccountViewModel;
import me.inc.bookingapp.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all-users")
    @ResponseBody
    public List<Account> allUsers() {
        return this.adminService.getAllUsers();
    }

}
