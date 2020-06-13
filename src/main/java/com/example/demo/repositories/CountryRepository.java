package com.example.demo.repositories;

import com.example.demo.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "select distinct city from Country")
    List<String> findAllCities();

    @Query(value = "select district from Country where city = :city")
    List<String> findAllDistricts(String city);

}
