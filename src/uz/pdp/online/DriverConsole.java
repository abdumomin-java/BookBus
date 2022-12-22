package uz.pdp.online;

import uz.pdp.online.model.User;
import uz.pdp.online.service.DriverOperation;

public class DriverConsole implements DriverOperation {

    static DriverConsole driverConsole;

    public static DriverConsole getDriverConsole() {
        if (driverConsole == null) {
            driverConsole = new DriverConsole();
        }
        return driverConsole;
    }

    @Override
    public void startDriver(User user) {
        System.out.println(user.getName() + " Driver, Assalomu alaykum! ");
        System.out.println("         List of Driver's Travel       ");
        Storage.getStorage().travelsList
                .forEach(travel -> {
                    if (travel.getUser().equals(user)) {
                        System.out.println(travel.toString());
                        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
                    }
                });
    }
}
