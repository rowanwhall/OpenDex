
package personal.rowan.sandbox.model.pokemon;


public class Ability {

    private Integer slot;
    private Boolean is_hidden;
    private Ability_ ability;

    /**
     * 
     * @return
     *     The slot
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * 
     * @param slot
     *     The slot
     */
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    /**
     * 
     * @return
     *     The is_hidden
     */
    public Boolean getIsHidden() {
        return is_hidden;
    }

    /**
     * 
     * @param isHidden
     *     The is_hidden
     */
    public void setIsHidden(Boolean isHidden) {
        this.is_hidden = isHidden;
    }

    /**
     * 
     * @return
     *     The ability
     */
    public Ability_ getAbility() {
        return ability;
    }

    /**
     * 
     * @param ability
     *     The ability
     */
    public void setAbility(Ability_ ability) {
        this.ability = ability;
    }

}
