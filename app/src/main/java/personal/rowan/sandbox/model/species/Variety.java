
package personal.rowan.sandbox.model.species;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Variety {

    private Boolean isDefault;
    private Pokemon pokemon;

    /**
     * 
     * @return
     *     The isDefault
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 
     * @param isDefault
     *     The is_default
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 
     * @return
     *     The pokemon
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * 
     * @param pokemon
     *     The pokemon
     */
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

}
