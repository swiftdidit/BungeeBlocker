package me.fleeking.bungeeblocker.util.config;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import java.util.List;
import java.util.Map;

public class LanguageManager {
    private static Configuration messages;

    public static void initialize(Configuration configuration) {
        messages = configuration.getSection("messages");
    }

    public static String getMessage(String key, int index) {
        List<String> messageList = messages.getStringList(key);
        if (messageList != null && index >= 0 && index < messageList.size()) {
            return messageList.get(index);
        } else {
            return ChatColor.RED + "[BB] Message Error: Please check " + ChatColor.WHITE + "'config.yml'";
        }
    }

    public static String formatMessage(String key, int index, Map<String, String> replacements) {
        String message = getMessage(key, index);
        if (message != null) {
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                String placeholder = "%" + entry.getKey() + "%";
                String replacement = entry.getValue();
                message = message.replace(placeholder, replacement);
            }
        }
        return message;
    }
}