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
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.*;

public class VersionBlocker implements Listener {
    private final BungeeBlocker core;

    public VersionBlocker(BungeeBlocker core) {
        this.core = core;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerConnect(ServerConnectEvent event) {
        if (event.isCancelled()) {
            return;
        }
        ServerInfo target = event.getTarget();
        String serverName = target.getName();
        String playerName = event.getPlayer().getName();
        int playerProtocolVersion = Via.getAPI().getPlayerVersion(event.getPlayer().getUniqueId());
        List<String> allowedVersions = core.getConfiguration().getStringList("servers." + serverName);

        if(allowedVersions.isEmpty()){
            return;
        }
        boolean allowConnection = false;
        for (String version : allowedVersions) {
            int protocolVersion = getProtocolVersion(version);
            if (protocolVersion != -1 && protocolVersion == playerProtocolVersion) {
                allowConnection = true;
                break;
            }
        }
        if (!allowedVersions.contains(String.valueOf(playerProtocolVersion)) && !allowConnection) {
            if (core.getConfiguration().getBoolean("settings.staff-bypass")
                    && core.getConfiguration().getBoolean("settings.staff-bypass-msg")) {
                Map<String, String> replacements = LanguageManager.createReplacements(playerName, serverName, String.valueOf(playerProtocolVersion));
                String message = LanguageManager.formatMessage("staff-bypass-msg", 0, replacements);
                event.getPlayer().sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
                return;
            }
            event.setCancelled(true);

            if (event.getPlayer().hasPermission("bungeeblocker.admin")
                    && core.getConfiguration().getBoolean("settings.staff-permission")
                    && core.getConfiguration().getBoolean("settings.staff-msg")) {
                Map<String, String> replacements = LanguageManager.createReplacements(playerName, serverName, String.valueOf(playerProtocolVersion));
                String message1 = LanguageManager.formatMessage("staff-gamemsg", 0, replacements);
                ProxyServer.getInstance().broadcast(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message1)));
            }
            Map<String, String> replacements = LanguageManager.createReplacements(playerName, serverName, String.valueOf(playerProtocolVersion));
            String message3 = LanguageManager.formatMessage("console-logmsg", 0, replacements);
            ProxyServer.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', message3));

            if (core.getConfiguration().getBoolean("settings.player-message")) {
                Map<String, String> replacements2 = LanguageManager.createReplacements(playerName, serverName, allowedVersions.toString());
                String message2 = LanguageManager.formatMessage("player-gamemsg", 0, replacements2);
                event.getPlayer().sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message2)));
            }
        }
    }
    private int getProtocolVersion(String version) {
        switch (version) {
            case "1.8", "1.8.2", "1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9":
                return 47;
            case "1.7", "1.7.1", "1.7.2", "1.7.3", "1.7.4", "1.7.5", "1.7.6", "1.7.7", "1.7.8", "1.7.9", "1.7.10":
                return 5;
            case "1.20", "1.20.1":
                return 763;
            case "1.19.4":
                return 762;
            case "1.19.3":
                return 761;
            case "1.19.2", "1.19.1":
                return 760;
            case "1.19":
                return 759;
            case "1.18.2":
                return 758;
            case "1.18.1", "1.18":
                return 757;
            case "1.17.1":
                return 756;
            case "1.17":
                return 755;
            case "1.16.4", "1.16.5":
                return 754;
            case "1.16.3":
                return 753;
            case "1.16.2":
                return 751;
            case "1.16.1":
                return 736;
            case "1.16":
                return 735;
            case "1.15.2":
                return 578;
            case "1.15.1":
                return 575;
            case "1.15":
                return 573;
            case "1.14.4":
                return 498;
            case "1.14.3":
                return 490;
            case "1.14.2":
                return 485;
            case "1.14.1":
                return 480;
            case "1.14":
                return 477;
            case "1.13.2":
                return 404;
            case "1.13.1":
                return 401;
            case "1.13":
                return 393;
            case "1.12.2":
                return 340;
            case "1.12.1":
                return 338;
            case "1.12":
                return 335;
            case "1.11.1", "1.11.2":
                return 316;
            case "1.11":
                return 315;
            case "1.10", "1.10.1", "1.10.2":
                return 210;
            case "1.9.3", "1.9.4":
                return 110;
            case "1.9.2":
                return 109;
            case "1.9.1":
                return 108;
            case "1.9":
                return 107;
            default:
                return -1;
        }
    }
}