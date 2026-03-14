package service;
import model.*;
import java.util.*;

public class ReservationService {
    private static ReservationService instance;
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final List<Reservation> reservations = new ArrayList<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        if(instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public void addRoom(IRoom room) {
        if(room.getRoomNumber() == null || room.getRoomNumber().isEmpty()) {
            throw new IllegalArgumentException("Room number cannot be empty");
        }

        if(rooms.containsKey(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room already exists");
        }

        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Date today = new Date();
        if(checkInDate.before(today)) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }

        if(!checkOutDate.after(checkInDate)) {
            throw new IllegalArgumentException("Checkout must be after check-in");
        }

        for(Reservation reservation: reservations) {
            if(reservation.getRoom().equals(room)) {
                if(reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate)) {
                    throw new IllegalArgumentException("Room already booked for these dates.");
                }
            }
        }

        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        reservations.add(reservation);

        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>(rooms.values());

        for(Reservation reservation: reservations) {
            if(reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate)) {
                availableRooms.remove(reservation.getRoom());
            }
        }

        if(!availableRooms.isEmpty()) {
            return availableRooms;
        }

        Calendar cal = Calendar.getInstance();

        cal.setTime(checkInDate);
        cal.add(Calendar.DATE, 7);
        Date newCheckIn = cal.getTime();

        cal.setTime(checkOutDate);
        cal.add(Calendar.DATE, 7);
        Date newCheckOut = cal.getTime();

        List<IRoom> recommendedRooms = new ArrayList<>(rooms.values());

        for(Reservation reservation: reservations) {
            if(reservation.getCheckInDate().before(newCheckOut) && reservation.getCheckOutDate().after(newCheckIn)) {
                recommendedRooms.remove(reservation.getRoom());
            }
        }

        if (!recommendedRooms.isEmpty()) {
            System.out.println("No rooms available for selected dates.");
            System.out.println("Recommended rooms for alternative dates.");
            System.out.println("New Check-in: " + newCheckIn);
            System.out.println("New Check-out: " + newCheckOut);
        }

        return recommendedRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();

        for(Reservation reservation: reservations) {
            if(reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    public void printAllReservations() {
        for(Reservation reservation: reservations) {
            System.out.println(reservation);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }
}
