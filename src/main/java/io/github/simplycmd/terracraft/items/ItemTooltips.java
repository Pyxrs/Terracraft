package io.github.simplycmd.terracraft.items;

import java.util.List;
import java.util.Objects;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ItemTooltips {
    public static void Tooltipper(Float type, List<Text> tooltip, String specified_tooltip) {
        if (!(Objects.equals(specified_tooltip, null) || Objects.equals(specified_tooltip, ""))) {
            tooltip.add(new TranslatableText("§9" + specified_tooltip));
            //stack.getItem().getTranslationKey() + ".tooltip"
        }
    }
    public static void Tooltipper(Float type, List<Text> tooltip, String specified_tooltip, String set_bonus) {
        if (!(Objects.equals(specified_tooltip, null) || Objects.equals(specified_tooltip, ""))) {
            tooltip.add(new TranslatableText("§9" + specified_tooltip));
            //stack.getItem().getTranslationKey() + ".tooltip"
        }
    }
    /*public static void TooltipperArmor(ItemStack stack, List<Text> tooltip, Boolean tip, Boolean vanity, Integer defense) {
        tooltip.add(new TranslatableText("§9Equipable"));
        if (vanity) {
            tooltip.add(new TranslatableText("§9Vanity Item"));
        }
        tooltip.add(new TranslatableText("§9Armor: §6+" + defense + " Defense"));
        if (tip) {
            tooltip.add(new TranslatableText(stack.getItm().getTranslationKey() + ".tooltip"));
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
    }*/
}