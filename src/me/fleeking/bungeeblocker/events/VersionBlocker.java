package me.fleeking.bungeeblocker.events;

import com.viaversion.viaversion.api.Via;
import me.fleeking.bungeeblocker.BungeeBlocker;
import me.fleeking.bungeeblocker.util.config.LanguageManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.*;

public class VersionBlocker implements Listener {
    private final BungeeBlocker core;
    private static VersionBlocker instance;
    private final Configuration config;

    public VersionBlocker(BungeeBlocker core) {
        this.core = core;
        this.instance = this;
        this.config = core.getConfiguration();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerConnect(ServerConnectEvent event) {
        int playerVersion = Via.getAPI().getPlayerVersion(event.getPlayer().getUniqueId());
        String playerName = event.getPlayer().getName();
        String serverName = event.getTarget().getName();

        List<String> allowedVersions = core.getConfiguration().getStringList("servers." + serverName);

        if (allowedVersions.isEmpty()) {
            return;
        }

        // Check for specific versions and assign the corresponding player version
        if(core.getConfiguration().getStringList("servers." + serverName).contains("1.8")){
            playerVersion = (int) 1.8;
            return;
        }

        if(core.getConfiguration().getStringList("servers." + serverName).contains("1.7")){
            playerVersion = (int) 1.7;
            return;
        }

        if(core.getConfiguration().getStringList("servers." + serverName).contains("1.20")){
            playerVersion = (int) 1.8;
            return;
        }

        String playerVersionString = String.valueOf(playerVersion);

        // Check if the player's version matches any allowed version
        if (!allowedVersions.contains(playerVersionString)) {
            // Staff Bypass
            if (core.getConfiguration().getBoolean("settings.staff-bypass")
                    && core.getConfiguration().getBoolean("settings.staff-bypass-msg")) {
                Map<String, String> replacements = new HashMap<>();
                replacements.put("player", playerName);
                replacements.put("server", serverName);
                replacements.put("playerversion", playerVersionString);

                String message = LanguageManager.formatMessage("staff-bypass-msg", 0, replacements);
                event.getPlayer().sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
                return;
            }

            // Admin Alert Message
            if (event.getPlayer().hasPermission("bungeeblocker.admin")
                    && core.getConfiguration().getBoolean("settings.staff-permission")
                    && core.getConfiguration().getBoolean("settings.staff-msg")) {
                Map<String, String> replacements = new HashMap<>();
                replacements.put("player", playerName);
                replacements.put("server", serverName);
                replacements.put("playerversion", playerVersionString);

                String message = LanguageManager.formatMessage("staff-gamemsg", 0, replacements);
                ProxyServer.getInstance().broadcast(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
            }

            // Console Msg
            Map<String, String> replacements = new HashMap<>();
            replacements.put("player", playerName);
            replacements.put("server", serverName);
            replacements.put("playerversion", playerVersionString);

            String message = LanguageManager.formatMessage("console-logmsg", 0, replacements);
            ProxyServer.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', message));

            // PlayerMsg
            if (core.getConfiguration().getBoolean("settings.player-message")) {
                Map<String, String> replacements2 = new HashMap<>();
                replacements2.put("player", playerName);
                replacements2.put("server", serverName);
                replacements2.put("version", allowedVersions.toString()
                        .replace("763", "1.20x")
                        .replace("47", "1.8x")
                        .replace("5", "1.7x")
                );

                String message2 = LanguageManager.formatMessage("player-gamemsg", 0, replacements2);
                event.getPlayer().sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message2)));
            }
            event.setCancelled(true);
        }
    }

    public static VersionBlocker getInstance() {
        return instance;
    }
}
