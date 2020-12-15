package com.example.androidadvancedproject.data;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.Date;

public class LodgeItemDTO {
    private final int lodgeId;
    private final String lodgeName;
    private final String lodgeOwner;
    private final String lodgeAvailability;
    private final String lodgeNumber;
    private final String lodgeLocation;

    public LodgeItemDTO(int lodgeId, String lodgeName, String lodgeOwner, String lodgeAvailability, String lodgeNumber, String lodgeLocation) {
        this.lodgeId = lodgeId;
        this.lodgeName = lodgeName;
        this.lodgeOwner = lodgeOwner;
        this.lodgeAvailability = lodgeAvailability;
        this.lodgeNumber = lodgeNumber;
        this.lodgeLocation = lodgeLocation;
    }

    public int getLodgeId() {
        return lodgeId;
    }

    public String getLodgeName() {
        return lodgeName;
    }

    public String getLodgeOwner() {
        return lodgeOwner;
    }

    public String getLodgeAvailability() {
        return lodgeAvailability;
    }

    public String getLodgeNumber() {
        return lodgeNumber;
    }

    public String getLodgeLocation() {
        return lodgeLocation;
    }
}
