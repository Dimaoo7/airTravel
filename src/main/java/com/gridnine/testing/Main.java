package com.gridnine.testing;

import com.gridnine.testing.filters.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filters.DepartureBeforeNowFilter;
import com.gridnine.testing.filters.GroundTimeMoreThanTwoHoursFilter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter departureBeforeNowFilter = new DepartureBeforeNowFilter();
        List<Flight> filteredFlightsByDepartureBeforeNow= departureBeforeNowFilter.filterFlights(flights);
        System.out.println("Включен фильтр по вылету до текущего момента времени: "
                + filteredFlightsByDepartureBeforeNow);

        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        List<Flight> filteredFlightsByArrivalBeforeDeparture = arrivalBeforeDepartureFilter.filterFlights(flights);
        System.out.println("Включен фильтр по дате прилета раньше даты вылета: "
                + filteredFlightsByArrivalBeforeDeparture);

        FlightFilter groundTimeMoreThanTwoHoursFilter = new GroundTimeMoreThanTwoHoursFilter();
        List<Flight> filteredFlightsByGroundTimeMoreThanTwoHours = groundTimeMoreThanTwoHoursFilter.filterFlights(flights);
        System.out.println("Включен фильтр по общему времени на земле более двух часов: "
                + filteredFlightsByGroundTimeMoreThanTwoHours);

        System.out.println("Исходные тестовые данные без фильтров:" + flights);
    }
}


