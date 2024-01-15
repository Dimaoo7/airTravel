package com.gridnine.testing;

import java.util.List;

public class Filter {
    private final List<Flight> flights = FlightBuilder.createFlights();

    //Вылет до текущего момента времени.
    public void findDepartureTillCurrentMoment() {


    }

    //Вылет до текущего момента времени.
    public void findSegmentsWithDateBeforeFlightDate() {
        System.out.println("findSegmentsWithArrDateBeforeFlightDate");
    }

    //Перелеты, где общее время, проведённое на земле, превышает два часа
    public void findTransferTimeMoreTwoHours() {
        System.out.println("findTransferTimeTillMoment");
    }
}
