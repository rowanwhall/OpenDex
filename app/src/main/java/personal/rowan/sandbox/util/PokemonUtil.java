package personal.rowan.sandbox.util;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.List;

import personal.rowan.sandbox.model.pokemon.Ability;
import personal.rowan.sandbox.model.pokemon.Type;
import personal.rowan.sandbox.model.species.Color;
import personal.rowan.sandbox.model.species.EggGroup;
import personal.rowan.sandbox.model.species.FlavorTextEntry;
import personal.rowan.sandbox.model.species.GrowthRate;
import personal.rowan.sandbox.model.species.Habitat;
import personal.rowan.sandbox.model.species.Language_;
import personal.rowan.sandbox.model.species.PokemonSpecies;
import personal.rowan.sandbox.model.species.Shape;
import personal.rowan.sandbox.model.species.Version;

/**
 * Created by Rowan Hall
 */

public class PokemonUtil {

    /*public static String createDetailString(PokemonSpecies pokemon) {
        if(pokemon == null) return "";

        StringBuilder b = new StringBuilder();

        Integer captureRate = pokemon.getCaptureRate();
        if(captureRate != null && captureRate > 0) {
            b.append("Capture Rate: ")
                    .append(captureRate)
                    .append("\n");
        }

        Habitat habitat = pokemon.getHabitat();
        if(habitat != null) {
            b.append("Habitat: ")
                    .append(habitat.getName())
                    .append("\n");
        }

        Color color = pokemon.getColor();
        if(color != null) {
            b.append("Color: ")
                    .append(color.getName())
                    .append("\n");
        }

        Shape shape = pokemon.getShape();
        if(shape != null) {
            b.append("Shape: ")
                    .append(shape.getName())
                    .append("\n");
        }

        List<EggGroup> eggGroups = pokemon.getEggGroups();
        if(eggGroups != null && !eggGroups.isEmpty()) {
            b.append("Egg Groups: ");
            int size = eggGroups.size();
            for(int i = 0; i < size; i++) {
                b.append(eggGroups.get(i).getName());
                if(i < size - 1) {
                    b.append(", ");
                }
            }
            b.append("\n");
        }

        Integer baseHappiness = pokemon.getBaseHappiness();
        if(baseHappiness != null) {
            b.append("Base Happiness: ")
                    .append(baseHappiness)
                    .append("\n");
        }

        GrowthRate growthRate = pokemon.getGrowthRate();
        if(growthRate != null) {
            b.append("Growth Rate: ")
                    .append(growthRate.getName())
                    .append("\n");
        }

        Integer hatchCounter = pokemon.getHatchCounter();
        if(hatchCounter != null) {
            b.append("Hatch Counter: ")
                    .append(hatchCounter)
                    .append("\n");
        }

        return b.toString();
    }*/

    public static SpannableStringBuilder getPokedexEntriesString(PokemonSpecies pokemon) {
        SpannableStringBuilder b = new SpannableStringBuilder();

        List<FlavorTextEntry> flavorTextEntries = getEnglishFlavorTextEntries(pokemon);
        if(!flavorTextEntries.isEmpty()) {
            for(FlavorTextEntry flavorTextEntry : flavorTextEntries) {
                Version version = flavorTextEntry.getVersion();
                if(version != null) {
                    SpannableString name = new SpannableString(formatName(version.getName()));
                    name.setSpan(new StyleSpan(Typeface.BOLD), 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    b.append(name)
                            .append("\n")
                            .append(flavorTextEntry.getFlavorText().replace("\n", " "))
                            .append("\n\n");
                }
            }
        }

        return b;
    }

    private static List<FlavorTextEntry> getEnglishFlavorTextEntries(PokemonSpecies pokemon) {
        List<FlavorTextEntry> englishEntries = new ArrayList<>();
        List<FlavorTextEntry> allEntries = pokemon.getFlavorTextEntries();
        if(allEntries == null || allEntries.isEmpty()) return englishEntries;
        for(FlavorTextEntry entry : allEntries) {
            Language_ language = entry.getLanguage();
            if(language != null && "en".equals(language.getName())) {
                englishEntries.add(entry);
            }
        }
        return englishEntries;
    }

    public static String formatName(String name) {
        return capitalizeAllWords(name.replace("-", " ").trim());
    }

    public static String formatNumber(Integer number) {
        String numberString = String.valueOf(number);
        while(numberString.length() < 3) {
            numberString = "0" + numberString;
        }
        return "#" + numberString;
    }

    public static String buildPokemonArtworkUrl(String name) {
        return "https://img.pokemondb.net/artwork/" +
                name +
                ".jpg";
    }

    public static String buildPokemonModelUrl(String name) {
        return "https://img.pokemondb.net/sprites/x-y/normal/" +
                name +
                ".png";
    }

    public static String getFormattedType(List<Type> types) {
        if(types == null || types.isEmpty()) {
            return "";
        }
        String type1 = types.get(0).getType().getName();
        if(types.size() <= 1) {
            return PokemonUtil.capitalizeWord(type1);
        } else {
            return PokemonUtil.capitalizeWord(types.get(1).getType().getName()) + "/" + PokemonUtil.capitalizeWord(type1);
        }
    }

    public static String getFormattedWeight(Double weight) {
        return String.valueOf(weight / 10) + " kg";
    }

    public static String getFormattedHeight(Double height) {
        return String.valueOf(height / 10) + " m";
    }

    public static String getFormattedAbilities(List<Ability> abilities) {
        if(abilities == null || abilities.isEmpty()) {
            return "";
        }
        Ability ability1 = abilities.get(0);
        if(abilities.size() == 1) {
            return PokemonUtil.formatAbilityName(ability1);
        } else {
            StringBuilder b = new StringBuilder();
            for(int i = abilities.size() - 1; i >= 0; i--) {
                b.append(PokemonUtil.formatAbilityName(abilities.get(i)));
                if(i > 0) {
                    b.append("\n");
                }
            }
            return b.toString();
        }
    }

    private static String formatAbilityName(Ability ability) {
        String formattedName = formatName(ability.getAbility().getName());
        return ability.getIsHidden() ? formattedName + " (H)" : formattedName;
    }

    /**
     * String Util methods
     */

    private static String capitalizeAllWords(String string) {
        StringBuilder b = new StringBuilder();
        String[] words = string.split(" ");
        for(String word : words) {
            b.append(capitalizeWord(word)).append(" ");
        }
        return b.toString();
    }

    private static String capitalizeWord(String string) {
        if(string == null || string.isEmpty()) return string;
        if(string.length() == 1) return string.toUpperCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

}
