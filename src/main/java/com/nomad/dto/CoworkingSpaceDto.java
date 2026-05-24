package com.nomad.dto;

import java.math.BigDecimal;

public class CoworkingSpaceDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal pricePerDay;
    private String currency;
    private Long cityId;
    private String cityName;
    private String amenities;
    private Double rating;
    private boolean hasHighSpeedInternet;
    private boolean open247;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(BigDecimal pricePerDay) { this.pricePerDay = pricePerDay; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public Long getCityId() { return cityId; }
    public void setCityId(Long cityId) { this.cityId = cityId; }
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public boolean isHasHighSpeedInternet() { return hasHighSpeedInternet; }
    public void setHasHighSpeedInternet(boolean hasHighSpeedInternet) { this.hasHighSpeedInternet = hasHighSpeedInternet; }
    public boolean isOpen247() { return open247; }
    public void setOpen247(boolean open247) { this.open247 = open247; }
}
