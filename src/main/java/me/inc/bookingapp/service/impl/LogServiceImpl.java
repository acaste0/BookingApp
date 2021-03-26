package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.*;
import me.inc.bookingapp.model.view.AdminLogView;
import me.inc.bookingapp.model.view.GeneralLogView;
import me.inc.bookingapp.model.view.ListingLogView;
import me.inc.bookingapp.repository.AdminLogRepository;
import me.inc.bookingapp.repository.GeneralLogRepository;
import me.inc.bookingapp.repository.ListingLogRepository;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.LogService;
import me.inc.bookingapp.service.StayListingService;
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
    private final GeneralLogRepository generalLogRepository;
    private final AdminLogRepository adminLogRepository;
    private final ListingLogRepository listingLogRepository;
    private final ModelMapper modelMapper;

    public LogServiceImpl(AccountService accountService, StayListingService stayListingService, GeneralLogRepository generalLogRepository, AdminLogRepository adminLogRepository, ListingLogRepository listingLogRepository, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.stayListingService = stayListingService;
        this.generalLogRepository = generalLogRepository;
        this.adminLogRepository = adminLogRepository;
        this.listingLogRepository = listingLogRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<AdminLogView> findAllAdminLogs() {
        return adminLogRepository
                .findAll()
                .stream()
                .map(logEntity -> modelMapper
                        .map(logEntity, AdminLogView.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createWebLog(String action) {
        Authentication authentication = getAuthentication();
        String username = authentication.getName().
                equals("anonymousUser") ? "not logged" : authentication.getName();

        GeneralLog generalLog = new GeneralLog().
                setAccount(username).
                setAction(action);

        generalLogRepository.save(generalLog);
    }

    @Override
    public List<GeneralLogView> findAllGeneralLogs() {
        return generalLogRepository
                .findAll()
                .stream()
                .map(logEntity -> modelMapper
                        .map(logEntity, GeneralLogView.class))
                .collect(Collectors.toList());

    }


    @Override
    public void createStayListingLog(String action, String listingTitle) {
        Authentication authentication = getAuthentication();
        StayListing listing = stayListingService.findEntityByTitle(listingTitle);
        Account account = accountService.getAccountEntity(authentication.getName());


        ListingLog listingLog = new ListingLog().
                setStayListingTitle(listing.getListingTitle()).
                setAccount(account.getUsername()).
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

        AdminLog adminLog = new AdminLog().setAdminAccountUsername(authentication.getName())
                .setUserAccountUsername(userAccountUsername)
                .setAction(action);

        adminLogRepository.save(adminLog);

    }

    @Override
    public List<ListingLogView> findAllListingLogs() {
        return listingLogRepository
                .findAll()
                .stream()
                .map(logEntity -> modelMapper
                        .map(logEntity, ListingLogView.class))
                .collect(Collectors.toList());
    }
}
