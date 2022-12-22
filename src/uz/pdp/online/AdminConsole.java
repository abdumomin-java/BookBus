package uz.pdp.online;

import uz.pdp.online.model.*;
import uz.pdp.online.service.AdminOperation;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdminConsole implements AdminOperation {

    static AdminConsole adminConsole;

    public static AdminConsole getAdminConsole() {
        if (adminConsole == null) {
            adminConsole = new AdminConsole();
        }
        return adminConsole;
    }

    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    @Override
    public void startAdmin(User user) {
        System.out.println(" Assalomu Alaykum " + user.getName());
        label:
        while (true) {
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
            System.out.println(" 1-> Add Bus; 2-> Add Driver; 3-> Add Travel; 4-> Show More Options; 0-> Go To Back; ");
            String com = scannerText.nextLine();
            switch (com) {
                case "1":
                    addBus();
                    break;
                case "2":
                    addDriver();
                    break;
                case "3":
                    addTravel();
                    break;
                case "4":
                    showMoreOption();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println(" Wrong command ");
                    break;
            }
        }
    }

    private void showMoreOption() {
        while (true){
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
            System.out.println(" Let's start 'Show more options', Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            if (Storage.getStorage().busList.size() == 0 ){
                System.out.println(" Bus List is empty ");
            } else {
                Bus bus1 = addHelpBus();
                if (bus1 == null){
                    System.out.println(" This ID is not found ");
                } else {
                    if (Storage.getStorage().travelsList.size() == 0){
                        System.out.println(" Travel List is empty ");
                    } else {
                        Travel travel1 = chosenTravel(bus1);
                        if (travel1 == null) {
                            System.out.println(" This ID is not found ");
                        } else {
                            if (Storage.getStorage().ticketsList.size() == 0){
                                System.out.println(" Ticket List is empty ");
                            } else {

                                showTickets(travel1);

                            }
                        }
                    }
                }
            }
        }
    }

    private void showTickets(Travel travel1) {
        Storage.getStorage().ticketsList
                .forEach(ticket -> {
                    if (ticket.getTravel().equals(travel1)){

                        System.out.println(" Id: " + ticket.getId() + "| Travel: " + ticket.getTravel()
                                + "\n PriceTicket: " + ticket.getPriceTicket()
                                + "| seatNumber: " + ticket.getSeatNumber()
                                + "| Status: " + ticket.getStatus());

                        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
                    }
                });
    }


    public Travel chosenTravel(Bus bus){
        Storage.getStorage().travelsList
                .forEach(travel -> {
                    if (travel.getBus().equals(bus)){
                        System.out.println(" ID: " + travel.getId() + "| Name: "  + travel.getName() + "| From: " + travel.getFrom() + "| To: " + travel.getTo() + "| Date: " + travel.getDate() + " PriceForPerSeat: " + travel.getPriceForPerSeat()
                                + "\n Bus: " + travel.getBus() +
                                "\n User: " + travel.getUser());
                        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
                    }
                });
        System.out.println(" Enter chosen Travel ID: ");
        int nextInt = scannerInt.nextInt();
        return Storage.getStorage().travelsList.stream()
                .filter(travel -> travel.getId() == nextInt)
                .findFirst().orElse(null);
    }

    private void addTravel() {
        while (true) {
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
            System.out.println(" Let's start add Travel, Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            System.out.print(" Enter Travel's name: ");
            String conName = scannerText.nextLine();
            System.out.print(" Enter FROM: ");
            String conFrom = scannerText.nextLine();
            System.out.print(" Enter TO: ");
            String conTo = scannerText.nextLine();
            System.out.print(" Enter Travel's date: ");
            String conDate = scannerText.nextLine();
            System.out.print(" Enter Price for per Seat: ");
            int priceForPerSeat = scannerInt.nextInt();

            System.out.println(" Then, You must choose Bus -> ");
            if (Storage.getStorage().busList.size() == 0){
                System.out.println(" Bus list is empty ");
            } else {
                Bus bus1 = addHelpBus();
                if (bus1 == null){
                    System.out.println(" Chosen ID is not found ");
                } else {
                    System.out.println(" Then, You must choose Driver -> ");
                    List<User> driversList = Storage.getStorage().usersList.stream()
                            .filter(user -> user.getRole().equals(Role.DRIVER)).toList();
                    if (driversList.size() == 0){
                        System.out.println(" Driver List is empty ");
                    } else {
                        User driver = addHelpDriver(driversList);
                        if (driver == null){
                            System.out.println(" Chosen ID is not found ");
                        } else {

                            if (isChosenBusAndUser(bus1, driver)){
                                System.out.println(" This Bus and This Driver is already exists, Try again! ");
                            } else {
                                Travel travel = new Travel(Travel.getCount(), conName, conFrom, conTo, conDate, bus1, driver, priceForPerSeat);
                                Storage.getStorage().travelsList.add(travel);
                                System.out.println(" Successfully added Travel, Thank you! ");

                                for (int i = 0; i < bus1.getTheNumberOfSeat(); i++) {
                                    Ticket ticket = new Ticket(Ticket.getCount(), travel, priceForPerSeat, (i+1), Status.AVAILABLE);
                                    Storage.getStorage().ticketsList.add(ticket);
                                }
                                System.out.println(" Successfully added " + bus1.getTheNumberOfSeat() +", Thank you! ");
                            }

                        }
                    }
                }
            }
        }
    }

    public boolean isChosenBusAndUser( Bus bus1, User driver){
        Travel travel1 = Storage.getStorage().travelsList.stream()
                .filter(travel -> travel.getBus().equals(bus1) && travel.getUser().equals(driver))
                .findFirst().orElse(null);
        return travel1 != null;
    }

    public Bus addHelpBus(){
        Storage.getStorage().busList.forEach(bus -> {
            System.out.println(bus.toString());
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        });
        System.out.println(" Enter chosen Bus's ID: ");
        int chosenBusID = scannerInt.nextInt();
        return Storage.getStorage().busList.stream()
                .filter(bus -> bus.getId() == chosenBusID)
                .findFirst().orElse(null);
    }

    public User addHelpDriver(List<User> driversList){
        driversList.forEach(user -> {
            System.out.println(user.toString());
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        });
        System.out.println(" Enter your chosen ID: ");
        int nextInt = scannerInt.nextInt();
        return driversList.stream()
                .filter(user -> user.getId() == nextInt)
                .findFirst().orElse(null);
    }

    private void addDriver() {
        while (true) {
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
            System.out.println(" Let's start add Driver, Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            System.out.print(" Enter driver's name: ");
            String conName = scannerText.nextLine();
            System.out.print(" Enter driver's login: ");
            String conLogin = scannerText.nextLine();
            System.out.print(" Enter driver's password: ");
            String conPassword = scannerText.nextLine();

            if (Application.application.isHere(conLogin)) {
                System.out.println(" This login is already exists, Try again! ");
            } else {
                User user = new User(User.getCount(), conName, conLogin, conPassword, Role.DRIVER, 0);
                Storage.getStorage().usersList.add(user);
                System.out.println(" Successfully added Driver, Thank you ! ");
            }
        }
    }

    private void addBus() {
        while (true) {
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
            System.out.println(" Let's start add Bus, Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            System.out.print(" Enter Bus's name: ");
            String conName = scannerText.nextLine();
            System.out.print(" Enter Bus's number: ");
            int conNumber = scannerInt.nextInt();
            System.out.print(" Enter the Number of Seat: ");
            int theNumberOfSeat = scannerInt.nextInt();

            if (isHereName(conName)) {
                System.out.println(" This Name is already exists, Try again! ");
            } else {
                Bus bus = new Bus(Bus.getCount(), conName, conNumber, theNumberOfSeat);
                Storage.getStorage().busList.add(bus);
                System.out.println(" Successfully added Bus, Thank you! ");
            }
        }
    }

    boolean isHereName(String name) {
        Bus bus1 = Storage.getStorage().busList.stream()
                .filter(bus -> bus.getName().equals(name))
                .findFirst().orElse(null);
        return bus1 != null;
    }
}
