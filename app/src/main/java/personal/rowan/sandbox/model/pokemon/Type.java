
package personal.rowan.sandbox.model.pokemon;


public class Type {

    private Integer slot;
    private Type_ type;

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
     *     The type
     */
    public Type_ getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(Type_ type) {
        this.type = type;
    }

}
