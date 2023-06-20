package me.fleeking.bungeeblocker.commands;

import com.viaversion.viaversion.api.Via;
import me.fleeking.bungeeblocker.BungeeBlocker;
import me.fleeking.bungeeblocker.util.config.LanguageManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VersionCommand extends Command {
    private final BungeeBlocker core;

    public VersionCommand(BungeeBlocker core) {
        super("checkver"); // Command name
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeeblocker.admin")) {
            switch (args.length) {
                case 0: {
                    if (sender instanceof ProxiedPlayer) {
                        ProxiedPlayer player = (ProxiedPlayer) sender;
                        int senderVersion = Via.getAPI().getPlayerVersion(sender);
                        String senderName = player.getName();
                        String serverName = player.getServer().getInfo().getName();

                        Map<String, String> replacements = new HashMap<>();
                        replacements.put("player", senderName);
                        replacements.put("server", serverName);
                        replacements.put("playerversion", String.valueOf(senderVersion));

                        String message = LanguageManager.formatMessage("version-of-player", 0, replacements);
                        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
                    } else {
                        if (args.length != 1) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    core.getConfiguration().getString("checkver-usage")));
                            return;
                        }

                        String playerName = args[0];
                        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(playerName);

                        if (target == null || !target.isConnected()) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    core.getConfiguration().getString("invalid-player")));
                            return;
                        }

                        int targetVersion = Via.getAPI().getPlayerVersion(target);
                        String targetName = target.getName();
                        String serverName = target.getServer().getInfo().getName();

                        Map<String, String> replacements = new HashMap<>();
                        replacements.put("player", targetName);
                        replacements.put("server", serverName);
                        replacements.put("playerversion", String.valueOf(targetVersion));

                        String message = LanguageManager.formatMessage("version-of-player", 0, replacements);
                        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
                    }
                    break;
                }
                case 1: {
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

                    if (target == null || !target.isConnected()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                core.getConfiguration().getString("invalid-player")));
                        return;
                    }
                    int targetVersion = Via.getAPI().getPlayerVersion(target);
                    String targetName = target.getName();
                    String serverName = target.getServer().getInfo().getName();
                    Map<String, String> replacements = new HashMap<>();
                    replacements.put("player", targetName);
                    replacements.put("server", serverName);
                    replacements.put("playerversion", String.valueOf(targetVersion));

                    String message = LanguageManager.formatMessage("version-of-player", 0, replacements);
                    sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
                    break;
                }
                default: {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            core.getConfiguration().getString("checkver-usage")));
                    break;
                }
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    core.getConfiguration().getString("permission-denied")));
        }
    }
}
