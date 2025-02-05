//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "properties"
)
public class Property {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String title;
    @Column(
            nullable = false
    )
    private String description;
    @Column(
            nullable = false
    )
    private String address;
    @Column(
            nullable = false
    )
    private double price;
    private LocalDate availableDate;

    @Column
    private Integer bhks;


    @Column
    private String amenities; // Storing as JSON String
    public Property() {
    }


public Integer getBhks() {
    return this.bhks;
}

    public void setBhks(Integer bhks) {
        this.bhks = bhks;
    }

    public LocalDate getAvailableDate() {
        return this.availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    // Getter for amenities as List<String>
    public List<String> getAmenitiesList() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(this.amenities, List.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Setter for amenities as List<String>
    public void setAmenitiesList(List<String> amenities) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.amenities = objectMapper.writeValueAsString(amenities);
        } catch (Exception e) {
            this.amenities = "[]"; // Default empty JSON array
        }
    }
}
