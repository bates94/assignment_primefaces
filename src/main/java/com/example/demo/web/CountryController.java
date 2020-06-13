package com.example.demo.web;

import com.example.demo.models.Country;
import com.example.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Named(value = "country")
@SessionScoped
public class CountryController implements Serializable {

    private String city;
    private String district;
    private List<Country> country;

    @Autowired
    CountryRepository countryRepository;

    @PostConstruct
    public void init() {
        country = countryRepository.findAll();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public void save() {
        
    }
}
