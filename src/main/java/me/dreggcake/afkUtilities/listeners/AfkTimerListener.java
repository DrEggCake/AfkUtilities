package me.dreggcake.afkUtilities.listeners;

import me.dreggcake.afkUtilities.AfkTimes;
import me.dreggcake.afkUtilities.AfkUtilities;
import me.dreggcake.afkUtilities.config.AfkConfigYaml;
import me.dreggcake.afkUtilities.hooks.WorldGuardHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Messenger;

import java.util.*;

public final class AfkTimerListener implements Listener {
    // stores the last time player moved and list of players currently afk
    Map<UUID, Long> lastMoveTime = new HashMap<>();
    Set<UUID> afkPlayers = new HashSet<>();

    // runs checkAfk method every second (20 ticks)
    public AfkTimerListener() {
        Bukkit.getScheduler().runTaskTimer(AfkUtilities.getInstance(), this::checkAfk, 20L, 20L);
    }

    // if the player triggers a move event, reset their last move time and if they are in afk player list, remove them
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        lastMoveTime.put(uuid, System.currentTimeMillis());
        afkPlayers.remove(uuid);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        lastMoveTime.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
    }

    /*    method to check last move time of all online players, if players exceed the maximum afk time,
         should teleport them to the afk location
         */
    private void checkAfk() {
        long timeNow = System.currentTimeMillis();
        String regionName = AfkConfigYaml.getAfkConfigYaml().getRgName();

        if (regionName == null || regionName.isEmpty() || !WorldGuardHook.regionExists(regionName)) {
            Common.warning("Afk region is not set, head over to config.yml");
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID playerUUID = player.getUniqueId();
            if (WorldGuardHook.isInRegion(player)) {
                continue;
            }


            long afkTime = AfkTimes.getAfkTime(player);
            long lastMovementTime = lastMoveTime.getOrDefault(playerUUID, timeNow);

            if (timeNow - lastMovementTime >= afkTime) {
                afkPlayers.add(playerUUID);
                player.teleport(AfkConfigYaml.getAfkConfigYaml().getAfkLocation());
                Common.tell(player, "You have been moved to AFK Area!");
            }
        }
    }
}
