package tf.justdisablevac.voting.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tf.justdisablevac.voting.CooldownManager;
import tf.justdisablevac.voting.main;

public class sun implements CommandExecutor {

    private final CooldownManager cooldownManager = new CooldownManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getName();
            World world = Bukkit.getWorld("world");

            if(!world.hasStorm()) {
                player.sendMessage("§cIt's already sunny!");

            } else {
                if(cooldownManager.getCooldownSun(playerName)) {
                    player.sendMessage("§cYou've already voted!");

                } else {
                    Integer value = main.plugin.config.getInt("sun.remainingPlayers");
                    main.plugin.config.set("sun.remainingPlayers", value - 1);
                    main.plugin.saveConfig();
                    if(main.plugin.config.get("sun.remainingPlayers").equals(0)) {
                        world.setStorm(false);
                        Bukkit.broadcastMessage("§aSay hello to the sun on §2world");

                        for(Player players : Bukkit.getOnlinePlayers()) {
                            cooldownManager.setCooldownSun(players.getName(), false);
                        }

                        Integer onlinePlayers = Bukkit.getOnlinePlayers().size();
                        main.plugin.config.set("onlinePlayers", onlinePlayers);
                        main.plugin.config.set("sun.remainingPlayers", onlinePlayers);

                        main.plugin.config.set("players", null);

                        main.plugin.saveConfig();

                    } else {
                        Bukkit.broadcastMessage("§aNew vote for sun on §2world, " + main.plugin.config.get("sun.remainingPlayers") + " more needed! §aVote with /sun");
                        cooldownManager.setCooldownSun(playerName, true);
                    }
                }
            }
        }

        return true;

    }
}
