package com.booking.clinic.enumm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("Kişi"),
    FEMALE("Qadın");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Gender fromLabel(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.label.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Gender tapılmadı: " + value);
    }
}
