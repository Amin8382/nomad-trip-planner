package com.nomad.dto;

import java.time.LocalDate;

public class ItineraryDayDto {
    private Long id;
    private Integer dayNumber;
    private LocalDate date;
    private Long cityId;
    private String cityName;
    private Long accommodationId;
    private String accommodationName;
    private Long coworkingSpaceId;
    private String coworkingSpaceName;
    private String notes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getDayNumber() { return dayNumber; }
    public void setDayNumber(Integer dayNumber) { this.dayNumber = dayNumber; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Long getCityId() { return cityId; }
    public void setCityId(Long cityId) { this.cityId = cityId; }
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public Long getAccommodationId() { return accommodationId; }
    public void setAccommodationId(Long accommodationId) { this.accommodationId = accommodationId; }
    public String getAccommodationName() { return accommodationName; }
    public void setAccommodationName(String accommodationName) { this.accommodationName = accommodationName; }
    public Long getCoworkingSpaceId() { return coworkingSpaceId; }
    public void setCoworkingSpaceId(Long coworkingSpaceId) { this.coworkingSpaceId = coworkingSpaceId; }
    public String getCoworkingSpaceName() { return coworkingSpaceName; }
    public void setCoworkingSpaceName(String coworkingSpaceName) { this.coworkingSpaceName = coworkingSpaceName; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
