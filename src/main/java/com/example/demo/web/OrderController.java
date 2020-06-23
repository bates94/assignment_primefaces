package com.example.demo.web;

import com.example.demo.models.AddressDetails;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.utils.AddressType;
import com.example.demo.utils.Messages;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "order")
@ViewScoped
public class OrderController implements Serializable {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CountryRepository countryRepository;

    private AddressDetails shippingAddressDetails;

    private AddressDetails billingAddressDetails;

    private List<String> countryNames;

    private List<String> cityNames;

    private List<String> districtNames;

    private boolean skip;

    @PostConstruct
    public void init() {
        shippingAddressDetails = new AddressDetails();
        billingAddressDetails = new AddressDetails();
        countryNames = countryRepository.findAllCountries();
    }

    public AddressDetails getShippingAddressDetails() {
        return shippingAddressDetails;
    }

    public void setShippingAddressDetails(AddressDetails shippingAddressDetails) {
        this.shippingAddressDetails = shippingAddressDetails;
    }

    public AddressDetails getBillingAddressDetails() {
        return billingAddressDetails;
    }

    public void setBillingAddressDetails(AddressDetails billingAddressDetails) {
        this.billingAddressDetails = billingAddressDetails;
    }

    public List<String> getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(List<String> countryNames) {
        this.countryNames = countryNames;
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

    public void showCities(String country) {
       cityNames = countryRepository.findAllCities(country);
    }

    public void showDistricts(String city) {
        districtNames = countryRepository.findAllDistricts(city);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public void save() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();

        billingAddressDetails.setAddressType(AddressType.BILLING_ADDRESS);
        billingAddressDetails.setUserName(username);
        orderRepository.save(billingAddressDetails);

        shippingAddressDetails.setAddressType(AddressType.SHIPPING_ADDRESS);
        shippingAddressDetails.setUserName(username);
        orderRepository.save(shippingAddressDetails);

        Messages.addMessage("Order has been given!");
    }


}
