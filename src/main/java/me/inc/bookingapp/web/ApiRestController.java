package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.ServiceResponse;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.view.AdminLogView;
import me.inc.bookingapp.model.view.GeneralLogView;
import me.inc.bookingapp.model.view.ListingLogView;
import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.AdminService;
import me.inc.bookingapp.service.LogService;
import me.inc.bookingapp.service.StayListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiRestController {

    private final LogService logService;
    private final AdminService adminService;
    private final AccountService accountService;
    private final StayListingService stayListingService;

    public ApiRestController(LogService logService, AdminService adminService, AccountService accountService, StayListingService stayListingService) {
        this.logService = logService;
        this.adminService = adminService;
        this.accountService = accountService;
        this.stayListingService = stayListingService;
    }


    @GetMapping("logs/roleChange")
    public ResponseEntity<Object> getAllRoleChangeLogs() {
        ServiceResponse<List<AdminLogView>> response = new ServiceResponse<>("success", logService.findAllAdminLogs());
        return new ResponseEntity<>(response, HttpStatus.OK);
     }

    @GetMapping("logs/listings")
    public ResponseEntity<Object> getAllGeneralLogs() {
        ServiceResponse<List<ListingLogView>> response = new ServiceResponse<>("success",
                logService.findAllListingLogs());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("admin/all-users")
    public List<Account> allUsers() {
        return this.adminService.getAllUsers();
    }

    @GetMapping("stay/api")
    public ResponseEntity<List<StayListingView>> finaAll() {
        return ResponseEntity.ok().body(stayListingService.getAll());
    }

}
