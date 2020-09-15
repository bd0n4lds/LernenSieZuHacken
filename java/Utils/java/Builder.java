import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * Universal builder class.
 *
 * @param <T>
 * @author
 */
public class Builder<T> {

    private T instance;

    private boolean ifCond = true;

    public Builder(Class<T> clazz) {

        try {

            instance = clazz.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {

            e.printStackTrace();

        }

    }
    
    public static <T> Builder<T> build(Class<T> clazz) {

        return new Builder<>(clazz);
    }

    public Builder<T> with(Consumer<T> setter) {

        if (ifCond)

            setter.accept(instance);

        return this;

    }

    public T get() {

        return instance;

    }

    public Builder<T> If(BooleanSupplier condition) {

        this.ifCond = condition.getAsBoolean();

        return this;

    }

    public Builder<T> endIf() {

        this.ifCond = true;

        return this;

    }


}