package io.github.simplycmd.terracraft.items;

import java.util.List;
import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ItemTooltips {
    public static final String[] one = {
        ""
    };
    public static final String[] two = {
        "wooden_helmet",
        "wooden_chestplate",
        "wooden_leggings",
        "wooden_boots"
    };
    public static final String[] three = {
        ""
    };
    public static final String[] four = {
        ""
    };

    public static void Tooltipper(ItemStack stack, List<Text> tooltip) {
        Boolean found = false;
        String item = stack.getItem().toString();
        String[][] lines = {one, two, three, four};

        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < lines[x].length; i++) {
                if (Objects.equals(lines[x][i], item)) {
                    found = true;
                    if (x == 3) {
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip1"));
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip2"));
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip3"));
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip4"));
                    } else if (x == 2) {
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip1"));
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip2"));
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip3"));
                    } else if (x == 1) {
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip1"));
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip2"));
                    } else {
                        tooltip.add(new TranslatableText("item.terracraft." + item + ".tooltip1"));
                    }
                }
            }
        }
        if (!found) {throw new IllegalArgumentException("Tooltip entry for " + item + " not found!");}
    }
}