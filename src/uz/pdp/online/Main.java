package uz.pdp.online;

import uz.pdp.online.model.Bus;
import uz.pdp.online.model.Role;
import uz.pdp.online.model.User;

public class Main {

    public static void main(String[] args) {

        User admin = new User(User.getCount(), "admin", "admin", "admin", Role.ADMIN, 0);
        Storage.getStorage().usersList.add(admin);

        Bus bus = new Bus(Bus.getCount(), "Countryman", 91, 24);
        Storage.getStorage().busList.add(bus);

        Application.getApplication().startApplications();

    }

}
