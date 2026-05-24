package com.nomad.dto;

import java.math.BigDecimal;

public class BundleDto {
    private Long id;
    private String name;
    private String description;
    private Long flightId;
    private String flightAirline;
    private Long accommodationId;
    private String accommodationName;
    private Long coworkingSpaceId;
    private String coworkingSpaceName;
    private Long cityId;
    private String cityName;
    private BigDecimal totalPrice;
    private BigDecimal discount;
    private String currency;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getFlightAirline() { return flightAirline; }
    public void setFlightAirline(String flightAirline) { this.flightAirline = flightAirline; }
    public Long getAccommodationId() { return accommodationId; }
    public void setAccommodationId(Long accommodationId) { this.accommodationId = accommodationId; }
    public String getAccommodationName() { return accommodationName; }
    public void setAccommodationName(String accommodationName) { this.accommodationName = accommodationName; }
    public Long getCoworkingSpaceId() { return coworkingSpaceId; }
    public void setCoworkingSpaceId(Long coworkingSpaceId) { this.coworkingSpaceId = coworkingSpaceId; }
    public String getCoworkingSpaceName() { return coworkingSpaceName; }
    public void setCoworkingSpaceName(String coworkingSpaceName) { this.coworkingSpaceName = coworkingSpaceName; }
    public Long getCityId() { return cityId; }
    public void setCityId(Long cityId) { this.cityId = cityId; }
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
