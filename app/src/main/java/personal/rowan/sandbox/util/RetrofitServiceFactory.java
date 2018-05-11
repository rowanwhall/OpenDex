package personal.rowan.sandbox.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rowan Hall
 */

public class RetrofitServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endpoint) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endpoint)
                .build();

        return retrofit.create(clazz);
    }

}
