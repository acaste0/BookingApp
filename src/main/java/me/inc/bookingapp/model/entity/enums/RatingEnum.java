package me.inc.bookingapp.model.entity.enums;

public enum RatingEnum {
    HIGHLY_DISSATISFIED(1), DISSATISFIED(2), NEUTRAL(3), SATISFIED(4), HIGHLY_SATISFIED(5);

    RatingEnum(double value) {
        this.value = value;
    }

    private double value;

    public double getValue() {
        return value;
    }
}
