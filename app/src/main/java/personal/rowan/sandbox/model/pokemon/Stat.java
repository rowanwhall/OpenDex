
package personal.rowan.sandbox.model.pokemon;


import personal.rowan.sandbox.util.PokemonUtil;

public class Stat {

    private Stat_ stat;
    private Integer effort;
    private Integer base_stat;

    /**
     * 
     * @return
     *     The stat
     */
    public Stat_ getStat() {
        return stat;
    }

    /**
     * 
     * @param stat
     *     The stat
     */
    public void setStat(Stat_ stat) {
        this.stat = stat;
    }

    /**
     * 
     * @return
     *     The effort
     */
    public Integer getEffort() {
        return effort;
    }

    /**
     * 
     * @param effort
     *     The effort
     */
    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    /**
     * 
     * @return
     *     The base_stat
     */
    public Integer getBaseStat() {
        return base_stat;
    }

    /**
     * 
     * @param baseStat
     *     The base_stat
     */
    public void setBaseStat(Integer baseStat) {
        this.base_stat = baseStat;
    }

    public String getBaseStatString() {
        return String.valueOf(base_stat);
    }

}
