package me.inc.bookingapp.web;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import me.inc.bookingapp.repository.AccountRepository;
import me.inc.bookingapp.repository.PicturesRepository;
import me.inc.bookingapp.repository.StayListingRepository;
import me.inc.bookingapp.repository.StayPropertiesRepository;
import me.inc.bookingapp.service.StayListingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ContextConfiguration(classes = StayController.class)
public class StayController {

    private static final String PREFIX = "/listing/stay";

    private String testStayId;
    private String propertiesId;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    StayListingRepository stayListingRepository;

    @Autowired
    PicturesRepository picturesRepository;

    @Autowired
    StayPropertiesRepository stayPropertiesRepository;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void setup() {
        this.init();
        System.out.println("asd");
    }

    @Test
    @WithMockUser(value = "ivan", roles = {"USER", "ADMIN"})
    void testStayView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/stay/view/{id}", testStayId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("/stay/view")).
                andExpect(model().attributeExists("stayListing"));
    }

//    @Test
//    @WithMockUser(value = "ivan", roles = {"USER", "ADMIN"})
//    void testShould() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(PREFIX + "/create")
//                .param("listingTitle", "test").
//                        param("listingType", ListingType.STAY.name()).
//                        param("stayType", StayType.HOTEL.name()).
//                        param("availabilityLeft", "10").
//                        param("country", "Bulgaria").
//                        param("city", "Sofia").
//                        param("address", "boris 3").
//                        param("description", "asdasda").
//                        param("pricePerNight", "10").
//                        with(csrf())).
//                andExpect(status().is3xxRedirection());
//
//        Assertions.assertEquals(2, stayListingRepository.count());
//        //todo: may verify the created album properties
//
//    }


    private void init() {
        Account account = new Account().setAccountType(AccountType.BUSINESS)
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setPassword("12345")
                .setUsername("ivan")
                .setEmail("ivan@abv.bg")
                .setCity("Sofia");

        account = accountRepository.save(account);

        StayProperties stayProperties = new StayProperties();
        stayProperties.setHasWifi(true);
        stayProperties.setHasTV(true);

        StayListing stayListing = new StayListing()
                .setListingType(ListingType.STAY)
                .setStayProperties(stayProperties)
                .setAddress("Adress 12")
                .setCity("Sofia")
                .setStayType(StayType.HOTEL)
                .setCountry("Bulgaria")
                .setPictures(List.of(new Picture().setPictureUrl("asd")))
                .setPricePerNight(BigDecimal.TEN)
                .setDescription("description")
                .setAvailabilityLeft(10);

        stayListing.setListingTitle("Hotel Via");
        stayListing.setAddedFrom(account);
        stayListing.setStayProperties(stayProperties);

        stayListing = stayListingRepository.save(stayListing);

        stayProperties.setListing(stayListing);

        testStayId = stayListing.getId();

    }
}
