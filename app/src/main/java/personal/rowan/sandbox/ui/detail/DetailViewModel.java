package personal.rowan.sandbox.ui.detail;

import android.text.SpannableStringBuilder;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import personal.rowan.sandbox.ui.detail.view.DetailFlavorCardViewModel;
import personal.rowan.sandbox.ui.detail.view.DetailSpritesCardViewModel;
import personal.rowan.sandbox.ui.detail.view.DetailStatsCardViewModel;

/**
 * Created by Rowan Hall
 */

public class DetailViewModel
        extends RealmObject {

    @PrimaryKey private String mName;
    private String mArtworkUrl;
    private String mModelUrl;
    private DetailFlavorCardViewModel mFlavorCardViewModel;
    private DetailSpritesCardViewModel mSpritesCardViewModel;
    private DetailStatsCardViewModel mStatsCardViewModel;

    public DetailViewModel() {

    }

    DetailViewModel(String name, String artworkUrl, String modelUrl) {
        mName = name;
        mArtworkUrl = artworkUrl;
        mModelUrl = modelUrl;
    }

    void setValues(String type, String weight, String height, String abilities,
                    String frontSpriteUrl, String backSpriteUrl,
                    String HP, String ATK, String DEF, String SPATK, String SPDEF, String SPD) {
        mFlavorCardViewModel = new DetailFlavorCardViewModel(type, weight, height, abilities);
        mSpritesCardViewModel = new DetailSpritesCardViewModel(frontSpriteUrl, backSpriteUrl);
        mStatsCardViewModel = new DetailStatsCardViewModel(HP, ATK, DEF, SPATK, SPDEF, SPD);
    }

    void setPokedexEntries(SpannableStringBuilder pokedexEntries) {
        mFlavorCardViewModel.setPokedexEntries(pokedexEntries);
    }

    boolean hasValues() {
        return mFlavorCardViewModel != null &&
                mSpritesCardViewModel != null &&
                mStatsCardViewModel != null;
    }

    boolean hasPokedexEntries() {
        return mFlavorCardViewModel != null &&
                mFlavorCardViewModel.hasPokedexEntries();
    }

    public String getName() {
        return mName;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public String getModelUrl() {
        return mModelUrl;
    }

    public DetailFlavorCardViewModel getFlavorCardViewModel() {
        return mFlavorCardViewModel;
    }

    public DetailSpritesCardViewModel getSpritesCardViewModel() {
        return mSpritesCardViewModel;
    }

    public DetailStatsCardViewModel getStatsCardViewModel() {
        return mStatsCardViewModel;
    }

}
