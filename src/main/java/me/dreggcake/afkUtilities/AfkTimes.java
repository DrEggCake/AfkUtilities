package me.dreggcake.afkUtilities;

import me.dreggcake.afkUtilities.config.AfkConfigYaml;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class AfkTimes {
    public static long getAfkTime(Player player) {
        // getting afk time specified in the permissions or the default from config.yml
        long afkTime = AfkConfigYaml.getAfkConfigYaml().getDefaultAfkTime() * 1000;
        String node = "afkutilities.time.";

        for (PermissionAttachmentInfo permInfo : player.getEffectivePermissions()) {
            String perm = permInfo.getPermission();

            if (perm.startsWith(node)) {
                String timePart = perm.substring(node.length());
                try {
                    long seconds = Long.parseLong(timePart);
                    afkTime = seconds * 1000;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return afkTime;
    }
}
