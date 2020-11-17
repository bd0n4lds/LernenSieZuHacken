import java.time.LocalDate;

/**
 * CustomerRegistrationValidator
 *
 * @author Brian Donaldson
 * @date 11/15/2020
 */
public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer(
                "Matthew",
                "matthew@nasa.gov",
                "+0898787879878",
                LocalDate.of(1995, 1,1)
        );

        // if valid we can store customer in db

        // Using combinator pattern
        CustomerRegistrationValidator.ValidationResult result = CustomerRegistrationValidator.isEmailValid()
                .and(CustomerRegistrationValidator.isPhoneNumberValid())
                .and(CustomerRegistrationValidator.isAnAdult())
                .apply(customer);

        System.out.println(result);

        if (result != CustomerRegistrationValidator.ValidationResult.SUCCESS) {
            throw new IllegalStateException(result.name());
        }

    }
}
