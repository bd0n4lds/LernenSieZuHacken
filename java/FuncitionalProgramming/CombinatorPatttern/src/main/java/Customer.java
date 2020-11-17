import java.time.LocalDate;

/**
 * Customer
 *
 * @author Brian Donaldson
 * @date 11/15/2020
 */
public class Customer {

    /**
     * The first and last name of this student.
     */
    private final String name;

    /**
     * The Email of this student.
     */
    private final String email;

    /**
     * The Phone Number of this student.
     */
    private final String phoneNumber;

    /**
     * The Local Date of this student.
     */
    private final LocalDate dob;

    /**
     * Creates a new Customer with the given name.
     * The name should include both first and
     * last name.
     *
     * @param name        This is the first paramter to Customer Constructor
     * @param email       This is the second parameter to Customer Constructor
     * @param phoneNumber This is the third paramter to Customer Constructor
     * @param dob         This is the forth parameter to Customer Constructor
     */
    public Customer(String name, String email, String phoneNumber, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    /**
     * Gets the first and last name of this Customer's.
     *
     * @return this Customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the Email of this Customer's.
     *
     * @return this Customer's Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the Phone Number of this Customer's.
     *
     * @return this Customer's Phone Number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the Local Date of Birth of this Customer's.
     *
     * @return this Customer's  Date of Birth
     */
    public LocalDate getDob() {
        return dob;
    }
}
