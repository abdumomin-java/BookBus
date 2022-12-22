package uz.pdp.online;

import uz.pdp.online.model.*;
import uz.pdp.online.service.UserOperation;

import java.util.List;
import java.util.Scanner;

public class UserConsole implements UserOperation {

    static UserConsole userConsole;

    public static UserConsole getUserConsole() {
        if (userConsole == null) {
            userConsole = new UserConsole();
        }
        return userConsole;
    }

    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    @Override
    public void startUser(User user) {
        System.out.println(" Assalomu Alaykum " + user.getName());
        label:
        while (true) {
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
            System.out.println(" 1-> Booking travel; 2-> Show my ordered Ticket; 0-> Go To Back; ");
            String com = scannerText.nextLine();
            switch (com) {
                case "1":
                    bookingTravel(user);
                    break;
                case "2":
                    showMyOrders(user);
                    break;
                case "0":
                    break label;
                default:
                    System.out.println(" Wrong command ");
                    break;
            }
        }
    }

    private void showMyOrders(User user) {
        System.out.println(" History Your Orders: ");
        Storage.getStorage().ordersList.forEach(order -> {
            if (order.getUser().equals(user)){
                System.out.println(" Id: " +order.getId() +
                        "| User=" + order.getUser() +
                        "| Ticket=" + order.getTicket() +
                        "| QuantityTicket=" + order.getQuantityTicket() +
                        "| Sum=" + order.getSum());
            }
        });

    }


    private void bookingTravel(User user) {
        while (true) {
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
            System.out.println(" Let's start Booking Travel, Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            if (Storage.getStorage().travelsList.size() == 0) {
                System.out.println(" Travel List is empty ");
            } else {
                Travel travel = chosenTravelForUser();
                if (travel == null) {
                    System.out.println(" This Travel is not found ");
                } else {
                    if (Storage.getStorage().busList.size() == 0) {
                        System.out.println(" Bus List is empty ");
                    } else {

                        List<Travel> travelsChosenForUser = Storage.getStorage().travelsList.stream()
                                .filter(travel1 -> travel1.equals(travel)).toList();

                        Travel travel1 = chosenBusForUser(travelsChosenForUser);
                        if (travel1 == null) {
                            System.out.println(" This Id is not found ");
                        } else {
                            Bus bus = travel1.getBus();
                            List<Ticket> availableTickets = Storage.getStorage().ticketsList.stream()
                                    .filter(ticket -> ticket.getTravel().getBus().equals(bus) && ticket.getStatus().equals(Status.AVAILABLE))
                                    .toList();
                            if (availableTickets.size() == 0) {
                                System.out.println(" Bu avtobusdagi barcha chipta sotilgan ");
                            } else {
                                System.out.println(" Bu avtobusdagi yaroqli chiptalar soni: " + availableTickets.size());
                                Ticket ticket = availableTickets.get(0);
                                System.out.println(" NEchta chipta sotib olmoqchisiz: ");
                                int quantity = scannerInt.nextInt();
                                int sum =  quantity * travel.getPriceForPerSeat();
                                if (user.getBalance() >= sum){
                                    Order order = new Order(Order.getCount(), user, ticket, quantity, sum );
                                    Storage.getStorage().ordersList.add(order);
                                    System.out.println(" muvaffaqtiyatli qoshildi ");
                                } else {
                                    System.out.println(" sizda mablag` yetarli emas, mablag'ni toldirishni unutmang. ");
                                }

                            }

                        }

                    }
                }
            }
        }
    }


    public Travel chosenBusForUser(List<Travel> travelsChosenForUser) {
        travelsChosenForUser.forEach(travel -> {
            System.out.println(travel.toString());
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        });
        System.out.println(" Enter chosen ID: ");
        int nextInt = scannerInt.nextInt();
        return travelsChosenForUser.stream()
                .filter(travel -> travel.getId() == nextInt)
                .findFirst().orElse(null);
    }

    private Travel chosenTravelForUser() {
        Storage.getStorage().travelsList
                .forEach(travel -> {
                    System.out.println(travel.toString());
                    System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
                });
        System.out.println(" Enter chosen Travel ID: ");
        int nextInt = scannerInt.nextInt();
        return Storage.getStorage().travelsList.stream()
                .filter(travel -> travel.getId() == nextInt)
                .findFirst().orElse(null);
    }
}
