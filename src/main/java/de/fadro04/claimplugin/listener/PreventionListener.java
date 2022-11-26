package de.fadro04.claimplugin.listener;

import de.fadro04.claimplugin.ClaimPlugin;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PreventionListener implements Listener {
    private final ClaimPlugin plugin;

    public PreventionListener(ClaimPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            Chunk chunk = event.getClickedBlock().getChunk();
            String chunkID = chunk.getX() + "." + chunk.getZ();

            if (plugin.isChunk(chunkID)) {
                Player player = event.getPlayer();

                if (!plugin.getOwner(chunkID).equals(player.getUniqueId())) {
                    if (!player.isOp()) {
                        event.setCancelled(true);
                        player.sendMessage("You are not allowed to build here");
                    }
                }
            }
        }
    }
}
