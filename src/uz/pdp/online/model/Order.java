package uz.pdp.online.model;

import java.util.Objects;

public class Order {

    static int count=1;
    {
        count++;
    }

    private int id;
    private User user;
    private Ticket ticket;
    private int quantityTicket;
    private int sum;

    public Order() {
    }

    public Order(int id, User user, Ticket ticket, int quantityTicket, int sum) {
        this.id = id;
        this.user = user;
        this.ticket = ticket;
        this.quantityTicket = quantityTicket;
        this.sum = sum;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(int quantityTicket) {
        this.quantityTicket = quantityTicket;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && quantityTicket == order.quantityTicket && sum == order.sum && Objects.equals(user, order.user) && Objects.equals(ticket, order.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, ticket, quantityTicket, sum);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", ticket=" + ticket +
                ", quantityTicket=" + quantityTicket +
                ", sum=" + sum +
                '}';
    }
}
