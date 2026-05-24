package com.nomad.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TripDto {
    private Long id;
    private String name;
    private String vibe;
    private BigDecimal totalBudget;
    private String currency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long userId;
    private List<ItineraryDayDto> itineraryDays;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getVibe() { return vibe; }
    public void setVibe(String vibe) { this.vibe = vibe; }
    public BigDecimal getTotalBudget() { return totalBudget; }
    public void setTotalBudget(BigDecimal totalBudget) { this.totalBudget = totalBudget; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<ItineraryDayDto> getItineraryDays() { return itineraryDays; }
    public void setItineraryDays(List<ItineraryDayDto> itineraryDays) { this.itineraryDays = itineraryDays; }
}
