package me.dreggcake.afkUtilities.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.dreggcake.afkUtilities.config.AfkConfigYaml;
import me.dreggcake.afkUtilities.config.AfkLangYaml;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.settings.YamlStaticConfig;

@CommandAlias("afkutilities")
@CommandPermission("afkutilities.admin")
public class AfkReloadCommand extends BaseCommand {
    @Subcommand("reload")
    public void onReload(CommandSender sender) {

        // reload files from disk
        AfkConfigYaml.reloadConfig();
        YamlStaticConfig.load(AfkLangYaml.class);
        String msg = "&a{plugin} configuration reloaded successfully.";

        if (sender instanceof Player)
            Common.tell(sender, msg);
        else
            Common.log(msg);
    }
}
