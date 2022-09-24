package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.City;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.CityRepository;
import com.booking.recruitment.hotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class DefaultCityService implements CityService {

  private final CityRepository cityRepository;
  @Autowired
  DefaultCityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @Autowired
  DefaultHotelService hotelService;

  @Override
  public City getCityById(Long id) {
    return cityRepository
        .findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Could not find city with ID provided"));
  }

  @Override
  public List<City> getAllCities() {
    return cityRepository.findAll();
  }

  @Override
  public City createCity(City city) {
    if (city.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new City");
    }
    return cityRepository.save(city);
  }

    /**
     * This method searches for nearby hotels based on given city id within 5km.
     * @param cityId as input parameter.
     * @return List<Hotel> as list of nearby available hotels.
     */
  @Override
  public List<Hotel> getNearByHotels(Long cityId){
  Optional<City> city= cityRepository.findById(cityId);
  List<Hotel> listHotels= hotelService.getAvailableHotelsByCity(cityId);
    List<Hotel> hotelList =new ArrayList<>();
    double distance= 0.d;
    for(Hotel hotel: listHotels){
       distance = distance(city.get().getCityCentreLatitude(),city.get().getCityCentreLongitude(),
              hotel.getLatitude(),hotel.getLongitude());
      if(distance<=5.0){
        hotelList.add(hotel);
      }
    }
    return hotelList;
  }

    /**
     * This method calculates distance in km from city center to all available hotels.
     * @param cityCentreLatitude  city center latitude
     * @param cityCentreLongitude city center longitude
     * @param hotelLatitude hotel latitude
     * @param hotelLongitude hotel longitude
     * @return distance in KM from city center to hotel.
     */
   private double distance( double cityCentreLatitude, double cityCentreLongitude, double hotelLatitude, double hotelLongitude){
      final int EARTH_RADIUS = 6371;

      double dLat = Math.toRadians((hotelLatitude - cityCentreLatitude));
      double dLong = Math.toRadians((hotelLongitude - cityCentreLongitude));

       cityCentreLatitude = Math.toRadians(cityCentreLatitude);
      hotelLatitude = Math.toRadians(hotelLatitude);

      double a = haversin(dLat) + Math.cos(cityCentreLatitude) * Math.cos(hotelLatitude) * haversin(dLong);
      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

      return EARTH_RADIUS * c;
    }


    private static double haversin(double val) {
      return Math.pow(Math.sin(val / 2), 2);
    }


}
