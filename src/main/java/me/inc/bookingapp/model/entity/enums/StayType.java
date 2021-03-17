package me.inc.bookingapp.model.entity.enums;

public enum StayType {
    HOTEL("Hotel"), ROOM("Room"), APARTMENT("Apartment"), GUEST_HOUSE("Guest House");

    public final String name;

    StayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
