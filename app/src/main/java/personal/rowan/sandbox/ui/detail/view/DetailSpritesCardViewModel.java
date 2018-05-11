package personal.rowan.sandbox.ui.detail.view;

import io.realm.RealmObject;

public class DetailSpritesCardViewModel
        extends RealmObject {

    private String mFrontSpriteUrl;
    private String mBackSpriteUrl;

    public DetailSpritesCardViewModel() {

    }

    public DetailSpritesCardViewModel(String frontSpriteUrl, String backSpriteUrl) {
        mFrontSpriteUrl = frontSpriteUrl;
        mBackSpriteUrl = backSpriteUrl;
    }

    public String getFrontSpriteUrl() {
        return mFrontSpriteUrl;
    }

    public String getBackSpriteUrl() {
        return mBackSpriteUrl;
    }

}
