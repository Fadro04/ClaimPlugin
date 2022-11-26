package de.fadro04.claimplugin;

import de.fadro04.claimplugin.commands.ClaimCommand;
import de.fadro04.claimplugin.commands.UnclaimCommand;
import de.fadro04.claimplugin.commands.UnclaimallCommand;
import de.fadro04.claimplugin.listener.PreventionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class ClaimPlugin extends JavaPlugin {

    public static String PREFIX = "§1ClaimPlugin";
    public static ClaimPlugin INSTANCE;

    private HashMap<String, UUID> chunks;

    public ClaimPlugin() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.chunks = new HashMap<>();

        register();

        getServer().getPluginManager().registerEvents(new PreventionListener(this), this);

        log("Plugin geladen.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log("Plugin entladen.");
    }

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + text);
    }

    public void register(){
        Bukkit.getPluginCommand("claim").setExecutor(new ClaimCommand(this));
        Bukkit.getPluginCommand("unclaim").setExecutor(new UnclaimCommand(this));
        Bukkit.getPluginCommand("unclaimall").setExecutor(new UnclaimallCommand(this));
    }



    public void addChunk(String chunk, UUID owner) {
        chunks.put(chunk, owner);
    }

    public void delChunk(String chunk, UUID owner) {
        chunks.remove(chunk, owner);
    }

    public String[] delallChunk(UUID owner) {
        String[] abc = new String[10];
        int zaeler = 0;
        for(String s : chunks.keySet()) {
            abc[zaeler] = s;
            zaeler++;
        }
        return abc;
    }


    public boolean isChunk(String chunk) {
        return chunks.containsKey(chunk);
    }
    public UUID getOwner(String chunk) {
        return chunks.get(chunk);
    }
}
