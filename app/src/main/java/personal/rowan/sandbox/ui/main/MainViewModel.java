package personal.rowan.sandbox.ui.main;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import personal.rowan.sandbox.util.PokemonUtil;

/**
 * Created by Rowan Hall
 */

public class MainViewModel
        extends RealmObject {

    private String mName;
    @PrimaryKey private Integer mNumber;
    private String mFormattedName;
    private String mFormattedNumber;
    private String mModelUrl;

    public MainViewModel() {

    }

    MainViewModel(String name, Integer number) {
        mName = name;
        mNumber = number;
        mFormattedName = PokemonUtil.formatName(mName);
        mFormattedNumber = PokemonUtil.formatNumber(mNumber);
        mModelUrl = PokemonUtil.buildPokemonModelUrl(mName);
    }

    public String getName() {
        return mName;
    }

    public Integer getNumber() {
        return mNumber;
    }

    public String getFormattedName() {
        return mFormattedName;
    }

    public String getFormattedNumber() {
        return mFormattedNumber;
    }

    public String getModelUrl() {
        return mModelUrl;
    }

}
