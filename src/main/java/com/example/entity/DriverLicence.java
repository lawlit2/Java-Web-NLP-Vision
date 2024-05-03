package com.example.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class DriverLicence {
    String IdCard;
    String date;
    String ApprovedDrivingType;
    String  ValidDate;
    String name;
    String  FirstDate;
}
