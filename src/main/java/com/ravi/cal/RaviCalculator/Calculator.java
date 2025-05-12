package com.ravi.cal.RaviCalculator;

public class Calculator {

    private long first;
    private long second;

    public Calculator(long first, long second) {
        this.first = first;
        this.second = second;
    }

    public long getFirst() {
        return first;
    }

    public long getSecond() {
        return second;
    }

    public long addFucn(long first, long second) {
        return first + second;
    }

    public long subFucn(long first, long second) {
        return second - first;
    }

    public long mulFucn(long first, long second) {
        return first * second;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide two numeric arguments. Example: java -jar app.jar 5 3");
            return;
        }

        try {
            long first = Long.parseLong(args[0]);
            long second = Long.parseLong(args[1]);

            Calculator cal = new Calculator(first, second);
            String output = String.format(
                "\n*** Your Results ***\n\nFirst: %d\nSecond: %d\n\nSum : %d\nDifference : %d\nProduct : %d\n\n",
                cal.first, cal.second, cal.addFucn(first, second), cal.subFucn(first, second), cal.mulFucn(first, second)
            );

            System.out.println(output);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide two integers.");
        }
    }
}

