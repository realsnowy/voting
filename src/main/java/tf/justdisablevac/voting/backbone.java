package tf.justdisablevac.voting;

import org.bukkit.Bukkit;

public class backbone {

    public void increaseOnlinePlayers() {
        main.plugin.config.set("onlinePlayers", getOnlinePlayers());
        main.plugin.saveConfig();
    }
    public void decreaseOnlinePlayers() {
        main.plugin.config.set("onlinePlayers", getOnlinePlayers() - 1);
        main.plugin.saveConfig();
    }
    public void resetOnlinePlayers() {
        main.plugin.config.set("onlinePlayers", getOnlinePlayers());
        main.plugin.saveConfig();
    }
    public Integer getOnlinePlayers() {
        Bukkit.getScheduler().runTaskLater(main.plugin, new Runnable() {
            @Override
            public void run() {
                Integer players = Bukkit.getOnlinePlayers().size();
                main.plugin.config.set("onlinePlayers", players);
                main.plugin.saveConfig();
            }
        }, 0L);
        return main.plugin.config.getInt("onlinePlayers");
    }

    public void increaseRemainingPlayersDay() {
        Integer value = main.plugin.config.getInt("day.remainingPlayers");
        main.plugin.config.set("day.remainingPlayers", value + 1);
        main.plugin.saveConfig();
    }
    public void decreaseRemainingPlayersDay() {
        Integer value = main.plugin.config.getInt("day.remainingPlayers");
        main.plugin.config.set("day.remainingPlayers", value - 1);
        main.plugin.saveConfig();
    }
    public void resetRemainingPlayersDay() {
        main.plugin.config.set("day.remainingPlayers", getOnlinePlayers());
        main.plugin.saveConfig();
    }
    public Integer getRemainingPlayersDay() {
        return main.plugin.config.getInt("day.remainingPlayers");
    }

    public void increaseRemainingPlayersSun() {
        Integer value = main.plugin.config.getInt("sun.remainingPlayers");
        main.plugin.config.set("sun.remainingPlayers", value + 1);
        main.plugin.saveConfig();
    }
    public void decreaseRemainingPlayersSun() {
        Integer value = main.plugin.config.getInt("sun.remainingPlayers");
        main.plugin.config.set("sun.remainingPlayers", value - 1);
        main.plugin.saveConfig();
    }
    public void resetRemainingPlayersSun() {
        main.plugin.config.set("sun.remainingPlayers", getOnlinePlayers());
        main.plugin.saveConfig();
    }
    public Integer getRemainingPlayersSun() {
        return main.plugin.config.getInt("sun.remainingPlayers");
    }
}
