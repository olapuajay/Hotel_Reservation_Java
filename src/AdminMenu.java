import api.AdminResource;
import model.*;

import java.util.*;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        while(true) {
            printMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addRoom();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Admin Menu ---");

        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");

        System.out.print("Enter your choice: ");
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();

        if(customers.isEmpty()) {
            System.out.println("No customers found.");
        }

        for(Customer customer: customers) {
            System.out.println(customer);
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();

        for(IRoom room: rooms) {
            System.out.println(room);
        }
    }

    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        try {
            System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine();

            System.out.print("Enter Price (0 for free room): ");
            Double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter room type (1 for SINGLE, 2 for DOUBLE): ");
            int typeChoice = Integer.parseInt(scanner.nextLine());

            RoomType type;

            if(typeChoice == 1) {
                type = RoomType.SINGLE;
            } else if(typeChoice == 2) {
                type = RoomType.DOUBLE;
            } else {
                System.out.println("Invalid! Enter valid room type (1 for SINGLE, 2 for DOUBLE)");
                return;
            }

            IRoom room;

            if (price == 0) {
                room = new FreeRoom(roomNumber, type);
            } else {
                room = new Room(roomNumber, price, type);
            }

            List<IRoom> rooms = new ArrayList<>();
            rooms.add(room);

            adminResource.addRoom(rooms);

            System.out.println("Room added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }
}
