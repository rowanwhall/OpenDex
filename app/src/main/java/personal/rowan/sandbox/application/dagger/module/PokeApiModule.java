package personal.rowan.sandbox.application.dagger.module;

import dagger.Module;
import dagger.Provides;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.util.RetrofitServiceFactory;
import retrofit2.Retrofit;

/**
 * Created by Rowan Hall
 */

@Module
public class PokeApiModule {

    @Provides
    PokemonService providePokemonService() {
        return RetrofitServiceFactory.createRetrofitService(PokemonService.class, PokemonService.BASE_URL);
    }

}
