package com.booking.recruitment.hotel.service;

import com.booking.recruitment.hotel.model.Hotel;

import java.util.List;

public interface HotelService {
  List<Hotel> getAllHotels();

  List<Hotel> getHotelsByCity(Long cityId);

  List<Hotel> getAvailableHotelsByCity(Long cityId);

  Hotel createNewHotel(Hotel hotel);

  Hotel FindById(long id);

  int UpdateHotel(long id);

}
