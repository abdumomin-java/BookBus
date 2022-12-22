package uz.pdp.online.model;

import java.util.Objects;

public class Travel {

    static int count = 1;
    {
        count++;
    }

    private int id;
    private String name;
    private String from;
    private String to;
    private String date;
    private Bus bus;
    private User user;
    private int priceForPerSeat;

    public Travel() {
    }

    public Travel(int id, String name, String from, String to, String date, Bus bus, User user, int priceForPerSeat) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
        this.bus = bus;
        this.user = user;
        this.priceForPerSeat = priceForPerSeat;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Travel.count = count;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPriceForPerSeat() {
        return priceForPerSeat;
    }

    public void setPriceForPerSeat(int priceForPerSeat) {
        this.priceForPerSeat = priceForPerSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return id == travel.id && priceForPerSeat == travel.priceForPerSeat && Objects.equals(name, travel.name) && Objects.equals(from, travel.from) && Objects.equals(to, travel.to) && Objects.equals(date, travel.date) && Objects.equals(bus, travel.bus) && Objects.equals(user, travel.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, from, to, date, bus, user, priceForPerSeat);
    }

    @Override
    public String toString() {
        return " ID: " + id + "| Name: "  + name + "| From: " + from + "| To: " + to + "| Date: " + date + " PriceForPerSeat: " + priceForPerSeat
                + "\n Bus: " + bus +
                "\n User: " + user ;
    }
}
