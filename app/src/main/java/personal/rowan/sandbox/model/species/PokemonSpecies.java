
package personal.rowan.sandbox.model.species;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PokemonSpecies {

    private Integer capture_rate;
    private Habitat habitat;
    private Color color;
    private Boolean forms_switchable;
    private Shape shape;
    private List<Name> names = new ArrayList<Name>();
    private Integer id;
    private List<EggGroup> egg_groups = new ArrayList<EggGroup>();
    private Integer base_happiness;
    private Generation generation;
    private List<FlavorTextEntry> flavor_text_entries = new ArrayList<FlavorTextEntry>();
    private GrowthRate growth_rate;
    private Integer hatch_counter;
    private List<Genera> genera = new ArrayList<Genera>();
    private Object evolves_from_species;
    private List<Object> form_descriptions = new ArrayList<Object>();
    private List<Variety> varieties = new ArrayList<Variety>();
    private String name;
    private EvolutionChain evolution_chain;
    private Boolean has_gender_differences;
    private Boolean is_baby;
    private Integer gender_rate;
    private List<PalParkEncounter> pal_park_encounters = new ArrayList<PalParkEncounter>();
    private Integer order;
    private List<PokedexNumber> pokedex_numbers = new ArrayList<PokedexNumber>();

    /**
     * 
     * @return
     *     The capture_rate
     */
    public Integer getCaptureRate() {
        return capture_rate;
    }

    /**
     * 
     * @param captureRate
     *     The capture_rate
     */
    public void setCaptureRate(Integer captureRate) {
        this.capture_rate = captureRate;
    }

    /**
     * 
     * @return
     *     The habitat
     */
    public Habitat getHabitat() {
        return habitat;
    }

    /**
     * 
     * @param habitat
     *     The habitat
     */
    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    /**
     * 
     * @return
     *     The color
     */
    public Color getColor() {
        return color;
    }

    /**
     * 
     * @param color
     *     The color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 
     * @return
     *     The forms_switchable
     */
    public Boolean getFormsSwitchable() {
        return forms_switchable;
    }

    /**
     * 
     * @param formsSwitchable
     *     The forms_switchable
     */
    public void setFormsSwitchable(Boolean formsSwitchable) {
        this.forms_switchable = formsSwitchable;
    }

    /**
     * 
     * @return
     *     The shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * 
     * @param shape
     *     The shape
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * 
     * @return
     *     The names
     */
    public List<Name> getNames() {
        return names;
    }

    /**
     * 
     * @param names
     *     The names
     */
    public void setNames(List<Name> names) {
        this.names = names;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The egg_groups
     */
    public List<EggGroup> getEggGroups() {
        return egg_groups;
    }

    /**
     * 
     * @param eggGroups
     *     The egg_groups
     */
    public void setEggGroups(List<EggGroup> eggGroups) {
        this.egg_groups = eggGroups;
    }

    /**
     * 
     * @return
     *     The base_happiness
     */
    public Integer getBaseHappiness() {
        return base_happiness;
    }

    /**
     * 
     * @param baseHappiness
     *     The base_happiness
     */
    public void setBaseHappiness(Integer baseHappiness) {
        this.base_happiness = baseHappiness;
    }

    /**
     * 
     * @return
     *     The generation
     */
    public Generation getGeneration() {
        return generation;
    }

    /**
     * 
     * @param generation
     *     The generation
     */
    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    /**
     * 
     * @return
     *     The flavor_text_entries
     */
    public List<FlavorTextEntry> getFlavorTextEntries() {
        return flavor_text_entries;
    }

    /**
     * 
     * @param flavorTextEntries
     *     The flavor_text_entries
     */
    public void setFlavorTextEntries(List<FlavorTextEntry> flavorTextEntries) {
        this.flavor_text_entries = flavorTextEntries;
    }

    /**
     * 
     * @return
     *     The growth_rate
     */
    public GrowthRate getGrowthRate() {
        return growth_rate;
    }

    /**
     * 
     * @param growthRate
     *     The growth_rate
     */
    public void setGrowthRate(GrowthRate growthRate) {
        this.growth_rate = growthRate;
    }

    /**
     * 
     * @return
     *     The hatch_counter
     */
    public Integer getHatchCounter() {
        return hatch_counter;
    }

    /**
     * 
     * @param hatchCounter
     *     The hatch_counter
     */
    public void setHatchCounter(Integer hatchCounter) {
        this.hatch_counter = hatchCounter;
    }

    /**
     * 
     * @return
     *     The genera
     */
    public List<Genera> getGenera() {
        return genera;
    }

    /**
     * 
     * @param genera
     *     The genera
     */
    public void setGenera(List<Genera> genera) {
        this.genera = genera;
    }

    /**
     * 
     * @return
     *     The evolves_from_species
     */
    public Object getEvolvesFromSpecies() {
        return evolves_from_species;
    }

    /**
     * 
     * @param evolvesFromSpecies
     *     The evolves_from_species
     */
    public void setEvolvesFromSpecies(Object evolvesFromSpecies) {
        this.evolves_from_species = evolvesFromSpecies;
    }

    /**
     * 
     * @return
     *     The form_descriptions
     */
    public List<Object> getFormDescriptions() {
        return form_descriptions;
    }

    /**
     * 
     * @param formDescriptions
     *     The form_descriptions
     */
    public void setFormDescriptions(List<Object> formDescriptions) {
        this.form_descriptions = formDescriptions;
    }

    /**
     * 
     * @return
     *     The varieties
     */
    public List<Variety> getVarieties() {
        return varieties;
    }

    /**
     * 
     * @param varieties
     *     The varieties
     */
    public void setVarieties(List<Variety> varieties) {
        this.varieties = varieties;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The evolution_chain
     */
    public EvolutionChain getEvolutionChain() {
        return evolution_chain;
    }

    /**
     * 
     * @param evolutionChain
     *     The evolution_chain
     */
    public void setEvolutionChain(EvolutionChain evolutionChain) {
        this.evolution_chain = evolutionChain;
    }

    /**
     * 
     * @return
     *     The has_gender_differences
     */
    public Boolean getHasGenderDifferences() {
        return has_gender_differences;
    }

    /**
     * 
     * @param hasGenderDifferences
     *     The has_gender_differences
     */
    public void setHasGenderDifferences(Boolean hasGenderDifferences) {
        this.has_gender_differences = hasGenderDifferences;
    }

    /**
     * 
     * @return
     *     The is_baby
     */
    public Boolean getIsBaby() {
        return is_baby;
    }

    /**
     * 
     * @param isBaby
     *     The is_baby
     */
    public void setIsBaby(Boolean isBaby) {
        this.is_baby = isBaby;
    }

    /**
     * 
     * @return
     *     The gender_rate
     */
    public Integer getGenderRate() {
        return gender_rate;
    }

    /**
     * 
     * @param genderRate
     *     The gender_rate
     */
    public void setGenderRate(Integer genderRate) {
        this.gender_rate = genderRate;
    }

    /**
     * 
     * @return
     *     The pal_park_encounters
     */
    public List<PalParkEncounter> getPalParkEncounters() {
        return pal_park_encounters;
    }

    /**
     * 
     * @param palParkEncounters
     *     The pal_park_encounters
     */
    public void setPalParkEncounters(List<PalParkEncounter> palParkEncounters) {
        this.pal_park_encounters = palParkEncounters;
    }

    /**
     * 
     * @return
     *     The order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 
     * @param order
     *     The order
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 
     * @return
     *     The pokedex_numbers
     */
    public List<PokedexNumber> getPokedexNumbers() {
        return pokedex_numbers;
    }

    /**
     * 
     * @param pokedexNumbers
     *     The pokedex_numbers
     */
    public void setPokedexNumbers(List<PokedexNumber> pokedexNumbers) {
        this.pokedex_numbers = pokedexNumbers;
    }

}
