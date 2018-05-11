
package personal.rowan.sandbox.model.species;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class FlavorTextEntry {

    private Version version;
    private String flavor_text;
    private Language_ language;

    /**
     * 
     * @return
     *     The version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The flavor_text
     */
    public String getFlavorText() {
        return flavor_text;
    }

    /**
     * 
     * @param flavorText
     *     The flavor_text
     */
    public void setFlavorText(String flavorText) {
        this.flavor_text = flavorText;
    }

    /**
     * 
     * @return
     *     The language
     */
    public Language_ getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    public void setLanguage(Language_ language) {
        this.language = language;
    }

}
