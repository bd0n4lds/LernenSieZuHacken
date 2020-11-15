
/**
 * _Consumer
 *
 * @author
 * @date
 */
public class Main {

    public static void main(String[] args) {

        // Normal java function
        _Consumer.Customer maria = new _Consumer.Customer("Maria", "99999");
        _Consumer.greetCustomer(maria);
        _Consumer.greetCustomerV2(maria, false);

        // Consumer Functional interface
        _Consumer.greetCustomerConsumer.accept(maria);
        _Consumer.greetCustomerConsumerV2.accept(maria, false);
    }
}
