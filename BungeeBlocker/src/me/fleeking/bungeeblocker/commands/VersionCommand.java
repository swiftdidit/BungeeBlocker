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
                        replacements.put("playerversion", String.valueOf(senderVersion)
                                .replace("763", "1.20x")
                                .replace("762", "1.19.4")
                                .replace("761", "1.19.3")
                                .replace("760", "1.19.2")
                                .replace("759", "1.19")
                                .replace("758", "1.18.2")
                                .replace("757", "1.18x")
                                .replace("756", "1.17.1")
                                .replace("755", "1.17")
                                .replace("754", "1.16x")
                                .replace("753", "1.16.3")
                                .replace("751", "1.16.2")
                                .replace("736", "1.16.1")
                                .replace("735", "1.16")
                                .replace("578", "1.15.2")
                                .replace("575", "1.15.1")
                                .replace("573", "1.15")
                                .replace("498", "1.14.4")
                                .replace("490", "1.14.3")
                                .replace("485", "1.14.2")
                                .replace("480", "1.14.1")
                                .replace("477", "1.14")
                                .replace("404", "1.13.2")
                                .replace("401", "1.13.1")
                                .replace("393", "1.13")
                                .replace("340", "1.12.2")
                                .replace("338", "1.12.1")
                                .replace("335", "1.12")
                                .replace("47", "1.8x")
                                .replace("5", "1.7x")
                        );

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
                        replacements.put("playerversion", String.valueOf(targetVersion)
                                .replace("763", "1.20x")
                                .replace("762", "1.19.4")
                                .replace("761", "1.19.3")
                                .replace("760", "1.19.2")
                                .replace("759", "1.19")
                                .replace("758", "1.18.2")
                                .replace("757", "1.18x")
                                .replace("756", "1.17.1")
                                .replace("755", "1.17")
                                .replace("754", "1.16x")
                                .replace("753", "1.16.3")
                                .replace("751", "1.16.2")
                                .replace("736", "1.16.1")
                                .replace("735", "1.16")
                                .replace("578", "1.15.2")
                                .replace("575", "1.15.1")
                                .replace("573", "1.15")
                                .replace("498", "1.14.4")
                                .replace("490", "1.14.3")
                                .replace("485", "1.14.2")
                                .replace("480", "1.14.1")
                                .replace("477", "1.14")
                                .replace("404", "1.13.2")
                                .replace("401", "1.13.1")
                                .replace("393", "1.13")
                                .replace("340", "1.12.2")
                                .replace("338", "1.12.1")
                                .replace("335", "1.12")
                                .replace("47", "1.8x")
                                .replace("5", "1.7x")
                        );

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
                    replacements.put("playerversion", String.valueOf(targetVersion)
                            .replace("763", "1.20x")
                            .replace("762", "1.19.4")
                            .replace("761", "1.19.3")
                            .replace("760", "1.19.2")
                            .replace("759", "1.19")
                            .replace("758", "1.18.2")
                            .replace("757", "1.18x")
                            .replace("756", "1.17.1")
                            .replace("755", "1.17")
                            .replace("754", "1.16x")
                            .replace("753", "1.16.3")
                            .replace("751", "1.16.2")
                            .replace("736", "1.16.1")
                            .replace("735", "1.16")
                            .replace("578", "1.15.2")
                            .replace("575", "1.15.1")
                            .replace("573", "1.15")
                            .replace("498", "1.14.4")
                            .replace("490", "1.14.3")
                            .replace("485", "1.14.2")
                            .replace("480", "1.14.1")
                            .replace("477", "1.14")
                            .replace("404", "1.13.2")
                            .replace("401", "1.13.1")
                            .replace("393", "1.13")
                            .replace("340", "1.12.2")
                            .replace("338", "1.12.1")
                            .replace("335", "1.12")
                            .replace("47", "1.8x")
                            .replace("5", "1.7x")
                    );

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
