package me.inc.bookingapp.web;

import me.inc.bookingapp.model.binding.AccountEditBinding;
import me.inc.bookingapp.model.service.AccountServiceModel;
import me.inc.bookingapp.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountRestController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;


    public AccountRestController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ObjectError> handleException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return bindingResult.getAllErrors();
    }

}


