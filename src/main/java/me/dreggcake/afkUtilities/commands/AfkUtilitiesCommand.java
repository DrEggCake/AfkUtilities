package me.dreggcake.afkUtilities.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import me.dreggcake.afkUtilities.config.AfkLangYaml;
import org.bukkit.command.CommandSender;
import org.mineacademy.fo.Common;

@CommandAlias("afkutilities")
@CommandPermission("afkutilities.admin")
public class AfkUtilitiesCommand extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender) {
        Common.tell(sender, AfkLangYaml.getHelpMessage());
    }
}
