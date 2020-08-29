package tf.justdisablevac.voting;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tf.justdisablevac.voting.commands.day;
import tf.justdisablevac.voting.commands.sun;
import tf.justdisablevac.voting.listeners.onJoin;
import tf.justdisablevac.voting.listeners.onLeave;

public final class main extends JavaPlugin {

    public FileConfiguration config;
    public static main plugin;

    private final CooldownManager cooldownManager = new CooldownManager();
    private final backbone backbone = new backbone();

    @Override
    public void onEnable() {
        plugin = this;
        this.config = getConfig();

        this.getServer().getPluginManager().registerEvents(new onJoin(), this);
        this.getServer().getPluginManager().registerEvents(new onLeave(), this);

        this.getCommand("day").setExecutor(new day());
        this.getCommand("sun").setExecutor(new sun());

        backbone.resetOnlinePlayers();
        backbone.resetRemainingPlayersDay();
        backbone.resetRemainingPlayersSun();
        cooldownManager.resetCooldowns();
    }
}
