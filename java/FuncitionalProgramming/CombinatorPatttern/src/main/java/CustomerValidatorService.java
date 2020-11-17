import java.time.LocalDate;
import java.time.Period;

/**
 * CustomerValidatorService
 *
 * @author Brian Donaldson
 * @date 11/15/2020
 */
public class CustomerValidatorService {

    /**
     * @return if email is valid
     */
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    /**
     * @return if phone number is valid
     */
    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("+0");
    }

    /**
     * @return if date of birth is Adult
     */
    private boolean isAdult(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears() > 16;
    }

    /**
     * @return if customer is valid
     */
    public boolean isValid(Customer customer) {
        return isEmailValid(customer.getEmail()) &&
                isPhoneNumberValid(customer.getPhoneNumber()) &&
                isAdult(customer.getDob());
    }
}
