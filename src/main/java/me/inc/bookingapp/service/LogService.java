package me.inc.bookingapp.service;

import me.inc.bookingapp.model.view.AdminLogView;
import me.inc.bookingapp.model.view.GeneralLogView;
import me.inc.bookingapp.model.view.ListingLogView;

import java.util.List;

public interface LogService {
    void createStayListingLog(String action, String listingId);
    void createRoleChangeLog(String action, String accountUsername);

    List<ListingLogView> findAllListingLogs();
    List<AdminLogView> findAllAdminLogs();

}
