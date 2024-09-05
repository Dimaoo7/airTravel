package com.gridnine.testing;

import com.gridnine.testing.filters.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filters.DepartureBeforeNowFilter;
import com.gridnine.testing.filters.GroundTimeMoreThanTwoHoursFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private List<Flight> flights;

    @BeforeEach
    public void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    public void filterByDepartureToTheCurrentMomentInTime() {
        FlightFilter flightFilter = new DepartureBeforeNowFilter();
        List<Flight> filtered = flightFilter.filterFlights(flights);

        assertEquals(5, filtered.size());
        assertTrue(filtered.get(0).getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now()));
    }

    @Test
    public void filterByArrivalBeforeDeparture() {
        FlightFilter flightFilter = new ArrivalBeforeDepartureFilter();
        List<Flight> filtered = flightFilter.filterFlights(flights);

        assertEquals(5, filtered.size());
        assertTrue(filtered.get(0).getSegments().get(0).getArrivalDate()
                .isAfter(filtered.get(0).getSegments().get(0).getDepartureDate()));
    }

    @Test
    public void filterByGroundTimeMoreThanTwoHours() {
        FlightFilter flightFilter = new GroundTimeMoreThanTwoHoursFilter();
        List<Flight> filtered = flightFilter.filterFlights(flights);

        assertEquals(4, filtered.size());
        assertTrue(hasByLongGroundTime(filtered.get(0)));
        assertTrue(hasByLongGroundTime(filtered.get(1)));
    }

    private boolean hasByLongGroundTime(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long groundTime = 0;

        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime currentArrival = segments.get(i).getArrivalDate();
            LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();
            groundTime += currentArrival.until(nextDeparture, ChronoUnit.HOURS);
        }
        return groundTime < 2;
    }
}