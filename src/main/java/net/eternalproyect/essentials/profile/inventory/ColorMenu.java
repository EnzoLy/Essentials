package net.eternalproyect.essentials.profile.inventory;

import lombok.AllArgsConstructor;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.util.CC;
import net.eternalproyect.essentials.util.ItemBuilder;
import net.eternalproyect.essentials.util.menu.Button;
import net.eternalproyect.essentials.util.menu.Menu;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ColorMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "&eSelect color";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        int i = 1;
        for(Material material : Material.values()){
            if(material.name().contains("WOOL")){
                if(material.name().contains("BROWN") || material.name().contains("PINK")){
                    continue;
                }
                String name = StringUtils.capitalize(material
                        .name()
                        .toLowerCase()
                        .replace("_wool", ""));

                buttons.put(i++, new ColorButton(material, CC.toChatColor(material) + name + " color"));
            }
        }

        return buttons;
    }

    @AllArgsConstructor
    public class ColorButton extends Button{

        private Material material;
        private String name;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(material).name(name).build();
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarSlot) {
            Profile profile = Profile.getByUuid(player.getUniqueId());
            profile.setColor(CC.toChatColor(material));
            player.sendMessage(CC.translate("&aYour new color is " + name + "."));

        }
    }
}
