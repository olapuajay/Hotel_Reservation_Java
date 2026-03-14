import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class MainMenu {
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final AdminResource adminResource = AdminResource.getInstance();

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            printMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    AdminMenu.start();
                    break;
                case "5":
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Hotel Reservation Application ---");

        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");

        System.out.print("Enter your choice: ");
    }

    private static void createAccount() {
        try {
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();

            hotelResource.createACustomer(email, firstName, lastName);

            System.out.println("Account created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    private static void seeMyReservations() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);

        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for(Reservation reservation: reservations) {
                System.out.println(reservation);
            }
        }
    }

    private static void findAndReserveRoom() {
        try {
            System.out.print("Enter check-in date (yyyy-mm-dd): ");
            Date checkIn = java.sql.Date.valueOf(scanner.nextLine());

            System.out.print("Enter check-out date (yyyy-mm-dd): ");
            Date checkOut = java.sql.Date.valueOf(scanner.nextLine());

            Collection<IRoom> rooms = hotelResource.findARoom(checkIn, checkOut);

//            boolean recommended = false;

            if(rooms.isEmpty()) {
                System.out.println("No rooms available.");
                return;
            }

            System.out.println("\nAvailable Rooms:");

            for(IRoom room: rooms) {
                System.out.println(room);
            }

            System.out.print("Enter room number to book: ");
            String roomNumber = scanner.nextLine();

            IRoom room = hotelResource.getRoom(roomNumber);

            if(room == null) {
                System.out.println("Room does not exist.");
                return;
            }

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            Date bookingCheckIn = checkIn;
            Date bookingCheckOut = checkOut;

            Calendar cal = Calendar.getInstance();

            cal.setTime(checkIn);
            cal.add(Calendar.DATE, 7);
            Date recommendedCheckIn = cal.getTime();

            cal.setTime(checkOut);
            cal.add(Calendar.DATE, 7);
            Date recommendedCheckOut = cal.getTime();

            try {
                Reservation reservation = hotelResource.bookARoom(email, room, bookingCheckIn, bookingCheckOut);

                System.out.println("Reservation successful:");
                System.out.println(reservation);
            } catch (IllegalArgumentException e) {
                Reservation reservation = hotelResource.bookARoom(email, room, recommendedCheckIn, recommendedCheckOut);

                System.out.println("Reservation successful with recommended dates:");
                System.out.println(reservation);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
