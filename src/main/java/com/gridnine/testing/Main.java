package com.gridnine.testing;

public class Main {

    public static void main(String[] args) {

        Filter filter = new Filter();

        filter.findDepartureTillCurrentMoment();
        System.out.println();
        filter.findSegmentsWithDateBeforeFlightDate();
        System.out.println();
        filter.findTransferTimeMoreTwoHours();

    }
}