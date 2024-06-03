package com.realestate.MockProject_052024.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PropertyID;
    private String PropertyType;
    private String Location;
    private String ImageURL;
    private int PriceRange;
    private int SquareFootage;
    private int NumbersOfBedRooms;
    private int NumbersOfBathRooms;
    private String StatusOfProperty;
    private int BuildInTime;
    private String Description;

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
    public int getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(int propertyID) {
        PropertyID = propertyID;
    }

    public String getPropertyType() {
        return PropertyType;
    }

    public void setPropertyType(String propertyType) {
        PropertyType = propertyType;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getPriceRange() {
        return PriceRange;
    }

    public void setPriceRange(int priceRange) {
        PriceRange = priceRange;
    }

    public int getSquareFootage() {
        return SquareFootage;
    }

    public void setSquareFootage(int squareFootage) {
        SquareFootage = squareFootage;
    }

    public int getNumbersOfBedRooms() {
        return NumbersOfBedRooms;
    }

    public void setNumbersOfBedRooms(int numbersOfBedRooms) {
        NumbersOfBedRooms = numbersOfBedRooms;
    }

    public int getNumbersOfBathRooms() {
        return NumbersOfBathRooms;
    }

    public void setNumbersOfBathRooms(int numbersOfBathRooms) {
        NumbersOfBathRooms = numbersOfBathRooms;
    }

    public String getStatusOfProperty() {
        return StatusOfProperty;
    }

    public void setStatusOfProperty(String statusOfProperty) {
        StatusOfProperty = statusOfProperty;
    }

    public int getBuildInTime() {
        return BuildInTime;
    }

    public void setBuildInTime(int buildInTime) {
        BuildInTime = buildInTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
