package uz.pdp.online.model;

import java.util.Objects;

public class Ticket {

    static int count=1;
    {
        count++;
    }

    private int id;
    private Travel travel;
    private int priceTicket;
    private int seatNumber;
    private Status status;

    public Ticket() {
    }

    public Ticket(int id, Travel travel, int priceTicket, int seatNumber, Status status) {
        this.id = id;
        this.travel = travel;
        this.priceTicket = priceTicket;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Ticket.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public int getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(int priceTicket) {
        this.priceTicket = priceTicket;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && priceTicket == ticket.priceTicket && seatNumber == ticket.seatNumber && Objects.equals(travel, ticket.travel) && status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, travel, priceTicket, seatNumber, status);
    }

    @Override
    public String toString() {
        return " Id: " + id + "| Travel: " + travel
                + "\n PriceTicket: " + priceTicket
                + "| seatNumber: " + seatNumber
                + "| Status: " + status;
    }
}
