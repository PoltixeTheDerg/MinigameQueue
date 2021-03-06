package poltixe.spigot.minigamequeue;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    // Get the config
    public FileConfiguration config = getConfig();

    // Boolean to store whether or not the game has started
    // TODO Create a GameState object for better consistency
    public boolean gameStarted = false;

    // Run when the plugin is enabled
    @Override
    public void onEnable() {
        // Adds a default option for minPlayersForStart to 4
        config.addDefault("minPlayersForStart", 4);
        // Copies the defaults to the config
        config.options().copyDefaults(true);
        // Saves the config
        saveConfig();

        // Set the time to day
        Bukkit.getWorld("world").setTime(8000);
        Bukkit.getWorld("world").getWorldBorder().setSize(100);

        // Registers the event listener
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        // Registers the commands
        this.getCommand("readycheck").setExecutor(new CommandCheckReadyStatus());
        this.getCommand("ready").setExecutor(new CommandReady());
        this.getCommand("forcestart").setExecutor(new CommandForceStart());
    }
}
