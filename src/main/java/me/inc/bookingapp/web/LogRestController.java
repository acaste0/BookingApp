package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.ServiceResponse;
import me.inc.bookingapp.model.view.AdminLogView;
import me.inc.bookingapp.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogRestController {

    private final LogService logService;

    public LogRestController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping("logs/roleChange")
    public ResponseEntity<Object> getAllRoleChangeLogs() {
        ServiceResponse<List<AdminLogView>> response = new ServiceResponse<>("success", logService.findAllAdminLogs());
        return new ResponseEntity<>(response, HttpStatus.OK);
     }



}
