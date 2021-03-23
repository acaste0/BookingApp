package me.inc.bookingapp.service;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AdminLog;
import me.inc.bookingapp.model.entity.ListingLog;
import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.view.AdminLogView;
import me.inc.bookingapp.model.view.ListingLogView;
import me.inc.bookingapp.repository.AdminLogRepository;
import me.inc.bookingapp.repository.ListingLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final AccountService accountService;
    private final StayListingService stayListingService;
    private final AdminLogRepository adminLogRepository;
    private final ListingLogRepository listingLogRepository;
    private final ModelMapper modelMapper;

    public LogServiceImpl(AccountService accountService, StayListingService stayListingService, AdminLogRepository adminLogRepository, ListingLogRepository listingLogRepository, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.stayListingService = stayListingService;
        this.adminLogRepository = adminLogRepository;
        this.listingLogRepository = listingLogRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<AdminLogView> findAllAdminLogs() {
        return adminLogRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    AdminLogView adminLogView = modelMapper
                            .map(logEntity, AdminLogView.class);
                    adminLogView.setAdminAccountUsername(logEntity.getAdminAccount().getUsername())
                            .setUserAccountUsername(logEntity.getUserAccount().getUsername());
                    return adminLogView;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void createStayListingLog(String action, String listingTitle) {
        Authentication authentication = getAuthentication();
        StayListing listing = stayListingService.findEntityByTitle(listingTitle);
        Account account = accountService.getAccountEntity(authentication.getName());


        ListingLog listingLog = new ListingLog().
                setStayListing(listing).
                setAccount(account).
                setAction(action);

        listingLogRepository.save(listingLog);
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication();
    }

    @Override
    public void createRoleChangeLog(String action, String userAccountUsername) {
        Authentication authentication = getAuthentication();

        Account adminAccount = accountService.getAccountEntity(authentication.getName());
        Account userAccount = accountService.getAccountEntity(userAccountUsername);

        AdminLog adminLog = new AdminLog().setAdminAccount(adminAccount).setUserAccount(userAccount).setAction(action);
        adminLogRepository.save(adminLog);

    }

    @Override
    public List<ListingLogView> findAllListingLogs() {
        return listingLogRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    ListingLogView listingLogView = modelMapper
                            .map(logEntity, ListingLogView.class);
                    listingLogView.setUsername(logEntity.getAccount().getUsername());
                    return listingLogView;
                })
                .collect(Collectors.toList());
    }
}
