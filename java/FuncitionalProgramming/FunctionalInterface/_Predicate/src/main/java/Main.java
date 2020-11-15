/**
 * _Predicate
 *
 * @author
 * @date
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Without predicate");
        System.out.println(_Predicate.isPhoneNumberValid("07000000000"));
        System.out.println(_Predicate.isPhoneNumberValid("0700000000"));
        System.out.println(_Predicate.isPhoneNumberValid("09009877300"));

        System.out.println("With predicate");
        System.out.println(_Predicate.isPhoneNumberValidPredicate.test("07000000000"));
        System.out.println(_Predicate.isPhoneNumberValidPredicate.test("0700000000"));
        System.out.println(_Predicate.isPhoneNumberValidPredicate.test("09009877300"));

        System.out.println("Is phone number valid and contains number 3 = " +
                _Predicate.isPhoneNumberValidPredicate.and(_Predicate.containsNumber3).test("09009877300")
        );

        System.out.println("Is phone number valid and contains number 3 = " +
                _Predicate.isPhoneNumberValidPredicate.or(_Predicate.containsNumber3).test("07000000000")
        );
    }
}