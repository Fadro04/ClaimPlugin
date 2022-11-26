package de.fadro04.claimplugin.commands;

import de.fadro04.claimplugin.ClaimPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UnclaimallCommand implements CommandExecutor {

    private final ClaimPlugin plugin;

    public UnclaimallCommand(ClaimPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            ClaimPlugin.INSTANCE.log("Du bist kein Spieler.");
            return true;
        }
        Player player = (Player) sender;

        String[] abc = plugin.delallChunk(player.getUniqueId());
        for (String s : abc) {
            System.out.println(s);
        }

        player.sendMessage("You deleted all your claimt chunks.");


        return false;
    }
}
