package model;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public Customer(String firstName, String lastName, String email) {
        if(!Pattern.matches(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer: " + firstName + " " + lastName + ", Email: " + email;
    }
}
