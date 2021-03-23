package me.inc.bookingapp.config.aop;
import me.inc.bookingapp.model.binding.AccountRoleEditBinding;
import me.inc.bookingapp.model.binding.StayListingBinding;
import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* me.inc.bookingapp.web.StayController.createListingConfirm(..))")
    public void createListingPointcut(){};


    @Pointcut("execution(* me.inc.bookingapp.web.AdminController.addRole(..))")
    public void addRolePointcut(){};

    @After("addRolePointcut()")
    public void createRoleChangeLog(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        AccountRoleEditBinding roleBinding = (AccountRoleEditBinding) args[0];
        String accountUsername = roleBinding.getUsername();
        String action = joinPoint.getSignature().getName();

        logService.createRoleChangeLog(action, accountUsername);
    }

    @After("createListingPointcut()")
    public void createListingLog(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        StayListingBinding l = (StayListingBinding) args[0];
        String listingTitle = l.getListingTitle();
        String action = joinPoint.getSignature().getName();

        logService.createStayListingLog(action, listingTitle);
    }
}
