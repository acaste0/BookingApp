package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.Train;
import me.inc.bookingapp.model.service.TrainListingServiceModel;
import me.inc.bookingapp.model.view.TrainView;
import me.inc.bookingapp.repository.TrainRepository;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.TrainListingService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Service
public class TrainListingServiceImpl implements TrainListingService {

    private final TrainRepository trainRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public TrainListingServiceImpl(TrainRepository trainRepository, AccountService accountService, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createTrainListing(TrainListingServiceModel trainListingServiceModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Train train = modelMapper.map(trainListingServiceModel, Train.class);
        train.setAddedFrom(accountService.getAccountEntity(authentication.getName()));
        System.out.println();
        trainRepository.save(train);
    }

    @Scheduled(fixedDelay = 30000)
    private void updateRepeatedTrains() {
        List<Train> allByLeaveBefore = trainRepository.findAllByLeaveBefore(LocalDateTime.now());
        allByLeaveBefore.forEach(e -> {
            e.setArrive(e.getArrive().plusSeconds(86400));
            e.setLeave(e.getLeave().plusSeconds(86400));
            trainRepository.save(e);
        });
    }

    @Override
    public void payTrainTicket(String trainId, String accountUsername) {
    }

    @Override
    public List<TrainView> getAllTrainsWithStartingPoint(String startingPoint) {
        return null;
    }

    @Override
    public List<TrainView> getAllTrainsWithEndPoint(String endPoint) {
        return null;
    }

    @Override
    public List<TrainView> getAllTrains() {
        return this.trainRepository.findAll().stream()
                .map(t -> modelMapper.map(t, TrainView.class)).collect(Collectors.toList());
    }

    @Override
    public List<TrainView> getAllTrainsWithTicketPriceAbove(BigDecimal ticketPrice) {
        return null;
    }

    @Override
    public List<TrainView> getAllTrainsWithTicketPriceBelow(BigDecimal ticketPrice) {
        return null;
    }

    @Override
    public TrainView getTrainViewById(String id) {

        return trainRepository.findById(id).map(t -> modelMapper.map(t, TrainView.class)).orElse(null);
    }

    @Override
    public Train getById(String listingId) {
        return this.trainRepository.getOne(listingId);
    }
}
