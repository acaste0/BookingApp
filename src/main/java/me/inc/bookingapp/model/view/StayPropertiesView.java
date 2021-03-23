package me.inc.bookingapp.model.view;

public class StayPropertiesView {
    private boolean hasKitchen;
    private boolean hasBedroom;
    private boolean hasBathroom;
    private boolean hasTV;
    private boolean hasWifi;
    private boolean hasParking;
    private boolean hasElevator;
    private boolean hasCityView;
    private boolean hasAirConditioning;
    private boolean hasRestaurant;
    private boolean hasFreeBreakfast;
    private boolean isAllInclusive;

    public boolean isHasKitchen() {
        return hasKitchen;
    }

    public StayPropertiesView setHasKitchen(boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
        return this;
    }

    public boolean isHasBedroom() {
        return hasBedroom;
    }

    public StayPropertiesView setHasBedroom(boolean hasBedroom) {
        this.hasBedroom = hasBedroom;
        return this;
    }

    public boolean isHasBathroom() {
        return hasBathroom;
    }

    public StayPropertiesView setHasBathroom(boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
        return this;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public StayPropertiesView setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
        return this;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public StayPropertiesView setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
        return this;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public StayPropertiesView setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
        return this;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }

    public StayPropertiesView setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
        return this;
    }

    public boolean isHasCityView() {
        return hasCityView;
    }

    public StayPropertiesView setHasCityView(boolean hasCityView) {
        this.hasCityView = hasCityView;
        return this;
    }

    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public StayPropertiesView setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
        return this;
    }

    public boolean isHasRestaurant() {
        return hasRestaurant;
    }

    public StayPropertiesView setHasRestaurant(boolean hasRestaurant) {
        this.hasRestaurant = hasRestaurant;
        return this;
    }

    public boolean isHasFreeBreakfast() {
        return hasFreeBreakfast;
    }

    public StayPropertiesView setHasFreeBreakfast(boolean hasFreeBreakfast) {
        this.hasFreeBreakfast = hasFreeBreakfast;
        return this;
    }

    public boolean isAllInclusive() {
        return isAllInclusive;
    }

    public StayPropertiesView setAllInclusive(boolean allInclusive) {
        isAllInclusive = allInclusive;
        return this;
    }
}
