package me.fleeking.bungeeblocker.util.config;

import me.fleeking.bungeeblocker.BungeeBlocker;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
public class ReloadCommand extends Command {
    private final BungeeBlocker core;
    public ReloadCommand(BungeeBlocker core) {
        super("bbreload"); // Command name
        this.core = core;
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeeblocker.admin")) {
            BungeeBlocker.getInstance().reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Configuration reloaded successfully.");
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    core.getConfiguration().getString("permission-denied")));
        }
    }
}
