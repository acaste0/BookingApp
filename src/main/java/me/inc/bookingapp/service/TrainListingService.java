package me.inc.bookingapp.service;

import me.inc.bookingapp.model.entity.Train;
import me.inc.bookingapp.model.service.TrainListingServiceModel;
import me.inc.bookingapp.model.view.TrainView;

import java.math.BigDecimal;
import java.util.List;

public interface TrainListingService {

    void createTrainListing(TrainListingServiceModel trainListingServiceModel);
    void payTrainTicket(String trainId, String accountUsername);
    List<TrainView> getAllTrainsWithStartingPoint(String startingPoint);
    List<TrainView> getAllTrainsWithEndPoint(String endPoint);
    List<TrainView> getAllTrains();
    List<TrainView> getAllTrainsWithTicketPriceAbove(BigDecimal ticketPrice);
    List<TrainView> getAllTrainsWithTicketPriceBelow(BigDecimal ticketPrice);

    TrainView getTrainViewById(String id);

    Train getById(String listingId);
}
