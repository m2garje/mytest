package com.booking.recruitment.hotel.service;

import com.booking.recruitment.hotel.model.City;
import com.booking.recruitment.hotel.model.Hotel;

import java.util.List;

public interface CityService {
  List<City> getAllCities();

  City getCityById(Long id);

  City createCity(City city);


  List<Hotel> getNearByHotels(Long cityId);


}
