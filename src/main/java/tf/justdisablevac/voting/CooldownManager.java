package tf.justdisablevac.voting;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private final Map<String, Boolean> cooldownDay = new HashMap<>();
    private final Map<String, Boolean> cooldownSun = new HashMap<>();

    public void setCooldownDay(String player, boolean cooldowned){
        if(cooldowned) {
            cooldownDay.put(player, true);
            main.plugin.config.set("players.day." + player, true);
            main.plugin.saveConfig();
        } else {
            cooldownDay.remove(player);
            main.plugin.config.set("players", null);
            main.plugin.saveConfig();
        }
    }

    public void setCooldownSun(String player, boolean cooldowned){
        if(cooldowned) {
            cooldownSun.put(player, true);
            main.plugin.config.set("players.sun." + player, true);
            main.plugin.saveConfig();
        } else {
            cooldownSun.remove(player);
            main.plugin.config.set("players", null);
            main.plugin.saveConfig();
        }
    }

    public boolean getCooldownDay(String player){
        return cooldownDay.getOrDefault(player, false);
    }

    public boolean getCooldownSun(String player){
        return cooldownSun.getOrDefault(player, false);
    }
}