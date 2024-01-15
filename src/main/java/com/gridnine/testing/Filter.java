package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Filter {

    //Сегмент вылет прилет
    private final List<Flight> flights = FlightBuilder.createFlights();

    //Вылет до текущего момента времени.
    public void findDepartureTillCurrentMoment() {
        System.out.println("Вылет до текущего момента времени");
        LocalDateTime now = LocalDateTime.now();
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(now)) {
                    System.out.println(segment);
                }
            }
        }
    }

    //Перелеты с датой прилёта раньше даты вылета.
    public void findSegmentsWithArrivalDateBeforeFlightDate() {
        System.out.println("Перелеты с датой прилёта раньше даты вылета");

            System.out.print("Напишите через сколько дней вы вылетаете: ");

            Scanner scanner = new Scanner(System.in);
            int days = scanner.nextInt();
            scanner.close();

            LocalDateTime departureDate = LocalDateTime.now().plusDays(days);

            List<Segment> resultSegments = flights.stream()
                    .flatMap(flight -> flight.getSegments().stream())
                    .filter(segment -> segment.getArrivalDate().isBefore(departureDate))
                    .collect(Collectors.toList());

            System.out.println("Перелеты с датой прилёта раньше даты вылета: " + resultSegments);
        }

    //Перелеты, где общее время, проведённое на земле, превышает два часа
    public void findTransferTimeMoreTwoHours() {

        List<Flight> resultFlights = new ArrayList<>();

        for (Flight flight : flights) {
            long transferTime = 0;

            for (int j = 0; j < flight.getSegments().size() - 1; j++) {

                LocalDateTime arrival = flight.getSegments().get(j).getArrivalDate();
                LocalDateTime departure = flight.getSegments().get(j + 1).getDepartureDate();
                long limitTransferHours = 2;
                transferTime = transferTime + arrival.until(departure, ChronoUnit.HOURS);

                if (transferTime >= limitTransferHours) {
                    resultFlights.add(flight);
                }
            }
        }
        System.out.println("Перелеты где проведённое на земле время превышает два часа: " + resultFlights);
    }
}
