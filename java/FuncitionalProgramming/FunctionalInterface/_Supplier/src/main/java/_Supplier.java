
import java.util.List;
import java.util.function.Supplier;

/**
 * _Supplier
 *
 * @author
 * @date
 */
public class _Supplier {

    /**
     * @return
     */
    static String getDBConnectionUrl() {
        return "jdbc://localhost:5432/users";
    }

    /**
     * @return
     */
    static Supplier<List<String>> getDBConnectionUrlsSupplier = ()
            -> List.of(
            "jdbc://localhost:5432/users",
            "jdbc://localhost:5432/customer");
}
