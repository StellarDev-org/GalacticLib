package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.util.InventoryUtil;
import com.massivecraft.massivecore.util.Txt;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class TxtUtil {

    // -------------------------------------------- //
    // COLORIZE & REPLACE
    // -------------------------------------------- //

    public String parse(String string) {
        return Txt.parse(string);
    }

    public String parse(String string, Object... args) {
        return Txt.parse(string, args);
    }

    public Collection<String> parse(Collection<String> list) {
        return Txt.parse(list);
    }

    public String parseAndReplace(String string, String... replacements) {
        if(replacements.length > 0) {
            Iterator<String> iterator = Arrays.asList(replacements).iterator();

            while(iterator.hasNext()) {
                String key = iterator.next();

                if(iterator.hasNext()) {
                    String value = iterator.next();

                    if(key == null || value == null) continue;

                    string = string.replace(key, value);
                }
            }
        }

        return parse(string);
    }

    public List<String> parseAndReplace(List<String> list, String... replacements) {
        return list.stream()
                .map(line -> parseAndReplace(line, replacements))
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    // -------------------------------------------- //
    // Material name tools
    // -------------------------------------------- //

    public String getMaterialName(Material material)
    {
        return Txt.getNicedEnum(material);
    }

    public String getItemName(ItemStack itemStack)
    {
        if (InventoryUtil.isNothing(itemStack)) return Txt.parse("<silver><em>Nothing");

        ChatColor color;

        try {
            color = (itemStack.getEnchantments().size() > 0) ? ChatColor.AQUA : ChatColor.WHITE;
        } catch (Throwable ex) {
            color = ChatColor.WHITE;
        }

        if(color == ChatColor.WHITE) {
            if(itemStack.getType() == Material.GOLDEN_APPLE) {
                short durability = itemStack.getDurability();

                switch (durability) {
                    case 1:
                        color = ChatColor.LIGHT_PURPLE;
                        break;
                    case 0:
                    default:
                        color = ChatColor.AQUA;
                        break;
                }
            }
        }

        if (itemStack.hasItemMeta())
        {
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta.hasDisplayName())
            {
                return color.toString() + ChatColor.ITALIC.toString() + itemMeta.getDisplayName();
            }
        }

        String presetDisplay = null;

        if(itemStack.getType() == Material.COAL) {
            short durability = itemStack.getDurability();

            switch (durability) {
                case 1:
                    presetDisplay = "Charcoal";
                    break;
                case 0:
                default:
                    presetDisplay = "Coal";
                    break;
            }
        } else if(itemStack.getType() == Material.INK_SACK) {
            short durability = itemStack.getDurability();

            switch (durability) {
                case 1:
                    presetDisplay = "Rose Red";
                    break;
                case 2:
                    presetDisplay = "Cactus Green";
                    break;
                case 3:
                    presetDisplay = "Cocoa Beans";
                    break;
                case 4:
                    presetDisplay = "Lapis Lazuli";
                    break;
                case 5:
                    presetDisplay = "Purple Dye";
                    break;
                case 6:
                    presetDisplay = "Cyan Dye";
                    break;
                case 7:
                    presetDisplay = "Light Gray Dye";
                    break;
                case 8:
                    presetDisplay = "Gray Dye";
                    break;
                case 9:
                    presetDisplay = "Pink Dye";
                    break;
                case 10:
                    presetDisplay = "Lime Dye";
                    break;
                case 11:
                    presetDisplay = "Dandelion Yellow";
                    break;
                case 12:
                    presetDisplay = "Light Blue Dye";
                    break;
                case 13:
                    presetDisplay = "Magenta Dye";
                    break;
                case 14:
                    presetDisplay = "Orange Dye";
                    break;
                case 15:
                    presetDisplay = "Bone Meal";
                    break;
                case 0:
                default:
                    presetDisplay = "Ink Sack";
                    break;
            }
        } else if(itemStack.getType() == Material.COOKED_FISH) {
            short durability = itemStack.getDurability();

            switch (durability) {
                case 1:
                    presetDisplay = "Cooked Salmon";
                    break;
                case 0:
                default:
                    presetDisplay = "Cooked Fish";
                    break;
            }
        } else if(itemStack.getType() == Material.RAW_FISH) {
            short durability = itemStack.getDurability();

            switch (durability) {
                case 1:
                    presetDisplay = "Raw Salmon";
                    break;
                case 2:
                    presetDisplay = "Clownfish";
                    break;
                case 3:
                    presetDisplay = "Pufferfish";
                    break;
                case 0:
                default:
                    presetDisplay = "Raw Fish";
                    break;
            }
        } else if(itemStack.getType() == Material.RED_ROSE) {
            short durability = itemStack.getDurability();

            switch (durability) {
                case 0:
                    presetDisplay = "Poppy";
                    break;
                case 1:
                    presetDisplay = "Blue Orchid";
                    break;
                case 2:
                    presetDisplay = "Allium";
                    break;
                case 3:
                    presetDisplay = "Azure Bluet";
                    break;
                case 4:
                    presetDisplay = "Red Tulip";
                    break;
                case 5:
                    presetDisplay = "Orange Tulip";
                    break;
                case 6:
                    presetDisplay = "White Tulip";
                    break;
                case 7:
                    presetDisplay = "Pink Tulip";
                    break;
                case 8:
                    presetDisplay = "Oxeye Daisy";
                    break;
            }
        } else if(itemStack.getType() == Material.CARROT_ITEM) {
            presetDisplay = "Carrot";
        } else if(itemStack.getType() == Material.POTATO_ITEM) {
            presetDisplay = "Potato";
        }

        return color + (presetDisplay != null? presetDisplay : Txt.getMaterialName(itemStack.getType()));
    }

    // -------------------------------------------- //
    // SHUFFLE
    // -------------------------------------------- //

    public String shuffle(String input) {
        List<Character> characters = new ArrayList<>();

        for(char c : input.toCharArray()){
            characters.add(c);
        }

        StringBuilder output = new StringBuilder(input.length());

        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }

        return output.toString();
    }

    // -------------------------------------------- //
    // PRETTY LOCATIONS
    // -------------------------------------------- //

    public String getPrettyLocation(Location location) {
        return getPrettyLocation(location, true);
    }

    public String getPrettyLocation(Location location, boolean includeWorld) {
        return (includeWorld? location.getWorld().getName() + ", " : "") + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
    }

}
