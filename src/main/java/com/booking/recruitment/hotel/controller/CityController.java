package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.City;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
  private final CityService cityService;

  @Autowired
  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping("/cities")
  @ResponseStatus(HttpStatus.OK)
  public List<City> getAllCities() {
    return cityService.getAllCities();
  }

  @PostMapping("/create-city")
  @ResponseStatus(HttpStatus.CREATED)
  public City createCity(@RequestBody City city) {
    return cityService.createCity(city);
  }

  @GetMapping("/search/{cityId}")
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getHotelById(@PathVariable Long cityId) {
    return cityService.getNearByHotels(cityId);

  }

}
