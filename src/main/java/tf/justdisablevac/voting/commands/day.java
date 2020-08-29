package tf.justdisablevac.voting.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tf.justdisablevac.voting.CooldownManager;
import tf.justdisablevac.voting.main;

public class day implements CommandExecutor {

    private final CooldownManager cooldownManager = new CooldownManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getName();
            World world = Bukkit.getWorld("world");

            if(world.getTime() < 12542) {
                player.sendMessage("§cWait until night!");

            } else if(world.getTime() >= 12542) {
                if(cooldownManager.getCooldownDay(playerName)) {
                    player.sendMessage("§cYou've already voted!");

                } else {
                    Integer value = main.plugin.config.getInt("day.remainingPlayers");
                    main.plugin.config.set("day.remainingPlayers", value - 1);
                    main.plugin.saveConfig();
                    if(main.plugin.config.get("day.remainingPlayers").equals(0)) {
                        world.setTime(1000);
                        Bukkit.broadcastMessage("§aSay hello to the new day on §2world");

                        for(Player players : Bukkit.getOnlinePlayers()) {
                            cooldownManager.setCooldownDay(players.getName(), false);
                        }

                        Integer onlinePlayers = Bukkit.getOnlinePlayers().size();
                        main.plugin.config.set("onlinePlayers", onlinePlayers);
                        main.plugin.config.set("day.remainingPlayers", onlinePlayers);

                        main.plugin.config.set("players", null);

                        main.plugin.saveConfig();

                    } else {
                        Bukkit.broadcastMessage("§aNew vote for day on §2world, " + main.plugin.config.get("day.remainingPlayers") + " more needed! §aVote with /day");
                        cooldownManager.setCooldownDay(playerName, true);
                    }
                }
            }
        }

        return true;

    }
}
