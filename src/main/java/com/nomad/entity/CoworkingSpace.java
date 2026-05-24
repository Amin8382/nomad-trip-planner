package com.nomad.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "coworking_spaces")
public class CoworkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal pricePerDay;

    private String currency = "USD";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    private String amenities;

    private Double rating;

    private boolean hasHighSpeedInternet;

    private boolean open247;

    public CoworkingSpace() {}

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
    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }
    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public boolean isHasHighSpeedInternet() { return hasHighSpeedInternet; }
    public void setHasHighSpeedInternet(boolean hasHighSpeedInternet) { this.hasHighSpeedInternet = hasHighSpeedInternet; }
    public boolean isOpen247() { return open247; }
    public void setOpen247(boolean open247) { this.open247 = open247; }
}
