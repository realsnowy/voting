package tf.justdisablevac.voting;

public class CooldownManager {

    public void setCooldownDay(String player) {
        main.plugin.config.set("players.day." + player, true);
        main.plugin.saveConfig();
    }
    public void setCooldownSun(String player) {
        main.plugin.config.set("players.sun." + player, true);
        main.plugin.saveConfig();
    }

    public void resetCooldowns() {
        main.plugin.config.set("players", null);
        main.plugin.saveConfig();
    }

    public boolean getCooldownDay(String player) {
        return main.plugin.config.getBoolean("players.day." + player, false);
    }
    public boolean getCooldownSun(String player) {
        return main.plugin.config.getBoolean("players.sun." + player, false);
    }
    public boolean getCooldowns() {
        return main.plugin.config.isSet("players");
    }
}