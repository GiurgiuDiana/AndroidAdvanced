package com.example.androidadvancedproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class LodgeItemDTO {
    private final int lodgeId;
    private final String lodgeName;
    private final String lodgeOwner;
    private final Date lodgeAvailability;
    private final String lodgeNumber;
    private final String lodgeLocation;

    public LodgeItemDTO(int lodgeId, String lodgeName, String lodgeOwner, Date lodgeAvailability, String lodgeNumber, String lodgeLocation) {
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

    public Date getLodgeAvailability() {
        return lodgeAvailability;
    }

    public String getLodgeNumber() {
        return lodgeNumber;
    }

    public String getLodgeLocation() {
        return lodgeLocation;
    }
}
