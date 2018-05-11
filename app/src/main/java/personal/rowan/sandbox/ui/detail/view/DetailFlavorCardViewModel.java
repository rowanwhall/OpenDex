package personal.rowan.sandbox.ui.detail.view;

import android.text.SpannableStringBuilder;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class DetailFlavorCardViewModel
        extends RealmObject {

    private String mType;
    private String mWeight;
    private String mHeight;
    private String mAbilities;
    @Ignore private SpannableStringBuilder mPokedexEntries;

    public DetailFlavorCardViewModel() {

    }

    public DetailFlavorCardViewModel(String formattedType, String weight, String height, String abilities) {
        mType = formattedType;
        mWeight = weight;
        mHeight = height;
        mAbilities = abilities;
    }

    public void setPokedexEntries(SpannableStringBuilder pokedexEntries) {
        mPokedexEntries = pokedexEntries;
    }

    public boolean hasPokedexEntries() {
        return mPokedexEntries != null;
    }

    public String getType() {
        return mType;
    }

    public String getWeight() {
        return mWeight;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getAbilities() {
        return mAbilities;
    }

    public SpannableStringBuilder getPokedexEntries() {
        return mPokedexEntries;
    }
}