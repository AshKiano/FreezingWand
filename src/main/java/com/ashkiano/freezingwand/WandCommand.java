package com.ashkiano.freezingwand;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack wand = new ItemStack(Material.STICK, 1);
            ItemMeta meta = wand.getItemMeta();
            if (meta != null) {
                meta.setDisplayName("§bFreezing Wand");
                meta.setLore(Arrays.asList("Special Freezing Wand", "Click to freeze water!"));
                wand.setItemMeta(meta);
            }

            player.getInventory().addItem(wand);
            player.sendMessage("§aYou received the freezing wand!");
        }
        return true;
    }
}