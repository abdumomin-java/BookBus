package uz.pdp.online.model;

import java.util.Objects;

public class Bus {

    static int count=1;
    {
        count++;
    }

    private int id;
    private String name;
    private int number;
    private int theNumberOfSeat;

    public Bus() {
    }

    public Bus(int id, String name, int number, int theNumberOfSeat) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.theNumberOfSeat = theNumberOfSeat;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Bus.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTheNumberOfSeat() {
        return theNumberOfSeat;
    }

    public void setTheNumberOfSeat(int theNumberOfSeat) {
        this.theNumberOfSeat = theNumberOfSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return id == bus.id && number == bus.number && theNumberOfSeat == bus.theNumberOfSeat && Objects.equals(name, bus.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, theNumberOfSeat);
    }

    @Override
    public String toString() {
        return " ID: " + id + "| Name:" + name + "| Number: " + number + "| TheNumberOfSeat: " + theNumberOfSeat ;
    }
}

