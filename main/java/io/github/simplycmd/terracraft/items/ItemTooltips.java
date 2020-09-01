package io.github.simplycmd.terracraft.items;

import java.util.List;
import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ItemTooltips {
    public static final String[] zero = {
        "musket_ball", "silver_bullet", "tungsten_bullet", "meteor_shot",
        "flaming_arrow", "frostburn_arrow", "bone_arrow", "unholy_arrow", "jesters_arrow", "hellfire_arrow",
        "flare", "blue_flare",

        "copper_coin", "silver_coin", "gold_coin", "platinum_coin",
    };
    public static final String[] one = {
        "poison_dart",
        "seed"
    };
    public static final String[] two = {
        "wooden_helmet",
        "wooden_chestplate",
        "wooden_leggings",
        "wooden_boots",
    };
    public static final String[] three = {
        ""
    };
    public static final String[] four = {
        ""
    };

    public static void Tooltipper(ItemStack stack, List<Text> tooltip, Boolean tip) {
        if (tip) {
            tooltip.add(new TranslatableText(stack.getItem().getTranslationKey() + ".tooltip"));
        }
    }
    public static void TooltipperArmor(ItemStack stack, List<Text> tooltip, Boolean tip, Boolean vanity, Integer defense) {
        tooltip.add(new TranslatableText("§9Equipable"));
        if (vanity) {
            tooltip.add(new TranslatableText("§9Vanity Item"));
        }
        tooltip.add(new TranslatableText("§9Armor: §6+" + defense + " Defense"));
        if (tip) {
            tooltip.add(new TranslatableText(stack.getItem().getTranslationKey() + ".tooltip"));
        }
    }
    public static void TooltipperArmor(ItemStack stack, List<Text> tooltip, Boolean tip, Boolean vanity, Integer defense, String set_bonus) {
        tooltip.add(new TranslatableText("§9Equipable"));
        if (vanity) {
            tooltip.add(new TranslatableText("§9Vanity Item"));
        } else {
            tooltip.add(new TranslatableText("§9Armor: §6+" + defense + " Defense"));
            tooltip.add(new TranslatableText("§9Set Bonus: §6" + set_bonus));
        }
        if (tip) {
            tooltip.add(new TranslatableText(stack.getItem().getTranslationKey() + ".tooltip"));
        }
    }
}