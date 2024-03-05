package com.ashkiano.freezingwand;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class WandListener implements Listener {

    private final List<String> WAND_LORE = Arrays.asList("Special Freezing Wand", "Click to freeze water!");

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Player player = event.getPlayer();

        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem == null || !heldItem.hasItemMeta()) {
            return;
        }

        ItemMeta meta = heldItem.getItemMeta();
        if (!meta.hasLore() || !meta.getLore().equals(WAND_LORE)) {
            return;
        }

        Block clickedBlock = event.getClickedBlock();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (clickedBlock.getType() == Material.WATER) {
                    clickedBlock.setType(Material.ICE);
                } else {
                    Block relativeBlock = clickedBlock.getRelative(event.getBlockFace());
                    if (relativeBlock.getType() == Material.WATER) {
                        relativeBlock.setType(Material.ICE);
                    }
                }
            }
        }.runTaskLater(FreezingWand.getInstance(), 1);
    }
}
