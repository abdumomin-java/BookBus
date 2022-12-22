package uz.pdp.online;

import uz.pdp.online.model.Role;
import uz.pdp.online.model.User;
import uz.pdp.online.service.AppOperation;

import java.util.Scanner;

public class Application implements AppOperation {

    static Application application;

    public static Application getApplication() {
        if (application == null){
            application = new Application();
        }
        return application;
    }

    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    @Override
    public void startApplications() {
        System.out.println();
        System.out.println(" @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @");
        System.out.println(" @ @ @                  WELCOME TO ONLINE BUS BOOKING                    @ @ @");
        System.out.println(" @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @");
        label:
        while (true){
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
            System.out.println(" 1-> Sign In; 2-> Sign Up; 0-> Go To Back; ");
            String com = scannerText.nextLine();
            switch (com) {
                case "1":
                    User user = signIn();
                    if (user == null) {
                        System.out.println(" This User is not found, Try again! ");
                    } else {
                        if (user.getRole().equals(Role.ADMIN)) {
                            AdminConsole.getAdminConsole().startAdmin(user);
                        } else if (user.getRole().equals(Role.DRIVER)) {
                            DriverConsole.getDriverConsole().startDriver(user);
                        } else {
                            UserConsole.getUserConsole().startUser(user);
                        }
                    }
                    break;
                case "2":
                    signUp();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println(" Wrong command! ");
                    break;
            }
        }
    }

    private void signUp() {
        while (true) {
            System.out.println("\n Let's start SignUP, Be carefully! ");
            System.out.println(" 1-> Continue; 2-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            System.out.print(" Enter your name: ");
            String conName = scannerText.nextLine();
            System.out.print(" Enter your login: ");
            String conLogin = scannerText.nextLine();
            System.out.print(" Enter your password: ");
            String conPassword = scannerText.nextLine();
            System.out.print(" Enter your Balance: ");
            int conBalance = scannerInt.nextInt();
            if (isHere(conLogin)){
                System.out.println(" This login is already exists, Try again! ");
            } else {
                User user = new User(User.getCount(), conName, conLogin, conPassword, Role.USER, conBalance);
                Storage.getStorage().usersList.add(user);
                System.out.println(" Successfully added User, Thank you ! " );
                UserConsole.getUserConsole().startUser(user);
            }

        }
    }

    boolean isHere(String login){
        User user1 = Storage.getStorage().usersList.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst().orElse(null);
        return user1 != null;
    }

    private User signIn() {
        System.out.print(" Enter your login: ");
        String conLogin = scannerText.nextLine();
        System.out.print(" Enter your password: ");
        String conPassword = scannerText.nextLine();

        return Storage.getStorage().usersList.stream()
                .filter(user -> user.getLogin().equals(conLogin) && user.getPassword().equals(conPassword))
                .findFirst().orElse(null);
    }


}
