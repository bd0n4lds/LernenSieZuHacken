import android.database.Observable;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.Optional;

public interface RxAssetManagerAPI {
    Single<Optional<String>> getFileAsString(String fileName);

    Observable<String> getLocales();

    Completable close();
}
