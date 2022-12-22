package uz.pdp.online;

import uz.pdp.online.model.*;

import java.util.ArrayList;

public class Storage {

    static Storage storage;

    public static Storage getStorage() {
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public ArrayList<Bus> busList = new ArrayList<>();
    public ArrayList<User> usersList = new ArrayList<>();
    public ArrayList<Travel> travelsList = new ArrayList<>();
    public ArrayList<Ticket> ticketsList = new ArrayList<>();
    public ArrayList<Order> ordersList = new ArrayList<>();


}
