package com.booking.recruitment.hotel.repository;

import com.booking.recruitment.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Transactional
    @Modifying
    @Query("Update Hotel set deleted='true' where id=:id")
    int UpdateHotel(long id);
}
