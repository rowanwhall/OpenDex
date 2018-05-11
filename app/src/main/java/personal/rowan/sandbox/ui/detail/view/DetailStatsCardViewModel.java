package personal.rowan.sandbox.ui.detail.view;

import io.realm.RealmObject;

public class DetailStatsCardViewModel
        extends RealmObject {

    private String mHP;
    private String mATK;
    private String mDEF;
    private String mSPATK;
    private String mSPDEF;
    private String mSPD;

    public DetailStatsCardViewModel() {

    }

    public DetailStatsCardViewModel (String HP, String ATK, String DEF, String SPATK, String SPDEF, String SPD) {
        mHP = HP;
        mATK = ATK;
        mDEF = DEF;
        mSPATK = SPATK;
        mSPDEF = SPDEF;
        mSPD = SPD;
    }

    public String getHP() {
        return mHP;
    }

    public String getATK() {
        return mATK;
    }

    public String getDEF() {
        return mDEF;
    }

    public String getSPATK() {
        return mSPATK;
    }

    public String getSPDEF() {
        return mSPDEF;
    }

    public String getSPD() {
        return mSPD;
    }
}
