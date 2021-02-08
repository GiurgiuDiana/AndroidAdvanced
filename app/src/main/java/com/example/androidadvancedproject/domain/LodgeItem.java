package com.example.androidadvancedproject.domain;

import java.sql.Timestamp;

public class LodgeItem {
    private final int lodgeId;
    private final String lodgeName;
    private final String lodgeOwner;
    private final Timestamp lodgeAvailability;
    private final String lodgeNumber;
    private final String lodgeLocation;



    public LodgeItem(int lodgeId, String lodgeName, String lodgeOwner, Timestamp lodgeAvailability, String lodgeNumber, String lodgeLocation) {
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

    public Timestamp getLodgeAvailability() {
        return lodgeAvailability;
    }

    public String getLodgeNumber() {
        return lodgeNumber;
    }

    public String getLodgeLocation() {
        return lodgeLocation;
    }
}

