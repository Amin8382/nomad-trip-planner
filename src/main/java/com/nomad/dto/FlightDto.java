package com.nomad.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlightDto {
    private Long id;
    private String airline;
    private Long originCityId;
    private String originCityName;
    private Long destinationCityId;
    private String destinationCityName;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private BigDecimal price;
    private String currency;
    private Integer durationMinutes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public Long getOriginCityId() { return originCityId; }
    public void setOriginCityId(Long originCityId) { this.originCityId = originCityId; }
    public String getOriginCityName() { return originCityName; }
    public void setOriginCityName(String originCityName) { this.originCityName = originCityName; }
    public Long getDestinationCityId() { return destinationCityId; }
    public void setDestinationCityId(Long destinationCityId) { this.destinationCityId = destinationCityId; }
    public String getDestinationCityName() { return destinationCityName; }
    public void setDestinationCityName(String destinationCityName) { this.destinationCityName = destinationCityName; }
    public LocalDateTime getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDateTime departureDate) { this.departureDate = departureDate; }
    public LocalDateTime getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(LocalDateTime arrivalDate) { this.arrivalDate = arrivalDate; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
}
