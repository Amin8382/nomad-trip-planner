package com.nomad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTripRequest {

    @NotBlank
    private String name;

    @NotNull
    private String vibe;

    @NotNull @Positive
    private BigDecimal totalBudget;

    private String currency;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Long originCityId;

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
    public Long getOriginCityId() { return originCityId; }
    public void setOriginCityId(Long originCityId) { this.originCityId = originCityId; }
}
