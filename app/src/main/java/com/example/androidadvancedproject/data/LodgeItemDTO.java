package com.example.androidadvancedproject.data;

import java.util.Date;

public class LodgeItemDTO {
     private final int lodge_id;
     private final String lodge_name;
     private final String lodge_owner;
     private final Date availability;
     private final String lodge_number;
     private final String lodge_location;

    public LodgeItemDTO(int lodge_id, String lodge_name, String lodge_owner, Date availability, String lodge_number, String lodge_location) {
        this.lodge_id = lodge_id;
        this.lodge_name = lodge_name;
        this.lodge_owner = lodge_owner;
        this.availability = availability;
        this.lodge_number = lodge_number;
        this.lodge_location = lodge_location;
    }

    public int getLodge_id() {
        return lodge_id;
    }

    public String getLodge_name() {
        return lodge_name;
    }

    public String getLodge_owner() {
        return lodge_owner;
    }

    public Date getAvailability() {
        return availability;
    }

    public String getLodge_number() {
        return lodge_number;
    }

    public String getLodge_location() {
        return lodge_location;
    }
}
