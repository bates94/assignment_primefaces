package com.example.demo.web;

import com.example.demo.models.Country;
import com.example.demo.models.User;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Named(value = "country")
@SessionScoped
public class CountryController implements Serializable {

    private String city;
    private String district;
    private List<String> cityNames;
    private List<String> districtNames;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        cityNames = countryRepository.findAllCities();
        districtNames = countryRepository.findAllDistricts(city);
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

    public List<String> getCityNames() {
        return cityNames;
    }

    public void setCityNames(List<String> cityNames) {
        this.cityNames = cityNames;
    }

    public List<String> getDistrictNames() {
        return districtNames;
    }

    public void setDistrictNames(List<String> districtNames) {
        this.districtNames = districtNames;
    }

    public void showDistricts(String city) {
        this.city = city;
        districtNames = countryRepository.findAllDistricts(city);
    }

    public void save() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        User user = userRepository.findByUserName(username);
        user.setCity(this.city);
        user.setDistrict(this.district);
        userRepository.save(user);
        Messages.addMessage("User has been reorganized!");
    }

    public void add() {

    }
}
