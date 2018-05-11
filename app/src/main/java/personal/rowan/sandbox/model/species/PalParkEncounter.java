
package personal.rowan.sandbox.model.species;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PalParkEncounter {

    private Integer rate;
    private Integer baseScore;
    private Area area;

    /**
     * 
     * @return
     *     The rate
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * 
     * @param rate
     *     The rate
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    /**
     * 
     * @return
     *     The baseScore
     */
    public Integer getBaseScore() {
        return baseScore;
    }

    /**
     * 
     * @param baseScore
     *     The base_score
     */
    public void setBaseScore(Integer baseScore) {
        this.baseScore = baseScore;
    }

    /**
     * 
     * @return
     *     The area
     */
    public Area getArea() {
        return area;
    }

    /**
     * 
     * @param area
     *     The area
     */
    public void setArea(Area area) {
        this.area = area;
    }

}
