
package personal.rowan.sandbox.model.species;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PokedexNumber {

    private Integer entryNumber;
    private Pokedex pokedex;

    /**
     * 
     * @return
     *     The entryNumber
     */
    public Integer getEntryNumber() {
        return entryNumber;
    }

    /**
     * 
     * @param entryNumber
     *     The entry_number
     */
    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    /**
     * 
     * @return
     *     The pokedex
     */
    public Pokedex getPokedex() {
        return pokedex;
    }

    /**
     * 
     * @param pokedex
     *     The pokedex
     */
    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

}
