package de.fadro04.claimplugin.commands;

import de.fadro04.claimplugin.ClaimPlugin;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClaimCommand implements CommandExecutor {

    private final ClaimPlugin plugin;
    public ClaimCommand(ClaimPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            ClaimPlugin.INSTANCE.log("Du bist kein Spieler");
            return true;
        }

        Player player = (Player) sender;
        Chunk chunk = player.getLocation().getChunk();

        String chunkID = chunk.getX() + "." + chunk.getZ();
        if (plugin.isChunk(chunkID)) {
            player.sendMessage("This Chunk is already claimt.");
        } else {
            plugin.addChunk(chunkID, player.getUniqueId());
            player.sendMessage("You have succesfully claimt this chunk.");
        }

        return true;
    }
}
