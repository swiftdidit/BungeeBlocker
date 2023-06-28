package me.fleeking.bungeeblocker.util.config;

import me.fleeking.bungeeblocker.BungeeBlocker;
import net.md_5.bungee.config.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageManager {
    private static Configuration messages;
    private static Configuration servers;
    private final BungeeBlocker core;

    public LanguageManager(BungeeBlocker core){
        this.core = core;
    }

    public static void initialize(Configuration configuration) {
        messages = configuration.getSection("messages");
        servers = configuration.getSection("servers");
    }
    public static String getMessage(String key, int index) {
        String errorMsg = BungeeBlocker.getInstance().getConfiguration().getString("error-message");

        List<String> messageList = messages.getStringList(key);
        if (messageList != null && index >= 0 && index < messageList.size()) {
            return messageList.get(index);
        } else {
            return errorMsg;
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
    public static Map<String, String> createReplacements(String playerName, String serverName, String versionString) {
        Map<String, String> replacements = new HashMap<>();
        replacements.put("player", playerName);
        replacements.put("server", serverName);
        replacements.put("version", replaceVersions(versionString));
        replacements.put("playerversion", replaceVersions(versionString));
        return replacements;
    }
    public static String replaceVersions(String versionString) {
        if (versionString.equals("1.19.4")) {
            return "762";
        } else if (versionString.equals("1.7") || versionString.equals("1.7.2") || versionString.equals("1.7.4") ||
                versionString.equals("1.7.6") || versionString.equals("1.7.9") || versionString.equals("1.7.10")) {
            return "5";
        } else if (versionString.equals("1.8") || versionString.equals("1.8.1") || versionString.equals("1.8.4") || versionString.equals("1.8.6") ||
                versionString.equals("1.8.7") || versionString.equals("1.8.8") || versionString.equals("1.8.9")) {
            return "47";
        } else if (versionString.equals("1.20") || versionString.equals("1.20.1")) {
            return "763";
        } else if (versionString.equals("1.19.3")) {
            return "761";
        } else if (versionString.equals("1.19.2") || versionString.equals("1.19.1")) {
            return "760";
        } else if (versionString.equals("1.19")) {
            return "759";
        } else if (versionString.equals("1.18.2")) {
            return "758";
        } else if (versionString.equals("1.18.1") || versionString.equals("1.18")) {
            return "757";
        } else if (versionString.equals("1.17.1")) {
            return "756";
        } else if (versionString.equals("1.17")) {
            return "755";
        } else if (versionString.equals("1.16.4") || versionString.equals("1.16.5")) {
            return "754";
        } else if (versionString.equals("1.16.3")) {
            return "753";
        } else if (versionString.equals("1.16.2")) {
            return "751";
        } else if (versionString.equals("1.16.1")) {
            return "736";
        } else if (versionString.equals("1.16")) {
            return "735";
        } else if (versionString.equals("1.15.2")) {
            return "578";
        } else if (versionString.equals("1.15.1")) {
            return "575";
        } else if (versionString.equals("1.15")) {
            return "573";
        } else if (versionString.equals("1.14.4")) {
            return "498";
        } else if (versionString.equals("1.14.3")) {
            return "490";
        } else if (versionString.equals("1.14.2")) {
            return "485";
        } else if (versionString.equals("1.14.1")) {
            return "480";
        } else if (versionString.equals("1.14")) {
            return "477";
        } else if (versionString.equals("1.13.2")) {
            return "404";
        } else if (versionString.equals("1.13.1")) {
            return "401";
        } else if (versionString.equals("1.13")) {
            return "393";
        } else if (versionString.equals("1.12.2")) {
            return "340";
        } else if (versionString.equals("1.12.1")) {
            return "338";
        } else if (versionString.equals("1.12")) {
            return "335";
        } else if (versionString.equals("1.11.1") || versionString.equals("1.11.2")) {
            return "316";
        } else if (versionString.equals("1.11")) {
            return "315";
        } else if (versionString.equals("1.10") || versionString.equals("1.10.1") || versionString.equals("1.10.2")) {
            return "210";
        } else if (versionString.equals("1.9.3") || versionString.equals("1.9.4")) {
            return "110";
        } else if (versionString.equals("1.9.2")) {
            return "109";
        } else if (versionString.equals("1.9.1")) {
            return "108";
        } else if (versionString.equals("1.9")) {
            return "107";
        } else if (versionString.equals("107")) {
            return "1.9";
        } else if (versionString.equals("108")) {
            return "1.9.1";
        } else if (versionString.equals("109")) {
            return "1.9.2";
        } else if (versionString.equals("110")) {
            return "1.9.3";
        } else if (versionString.equals("210")) {
            return "1.10";
        } else if (versionString.equals("315")) {
            return "1.11";
        } else if (versionString.equals("316")) {
            return "1.11.1";
        } else if (versionString.equals("335")) {
            return "1.12";
        } else if (versionString.equals("338")) {
            return "1.12.1";
        } else if (versionString.equals("340")) {
            return "1.12.2";
        } else if (versionString.equals("393")) {
            return "1.13";
        } else if (versionString.equals("401")) {
            return "1.13.1";
        } else if (versionString.equals("404")) {
            return "1.13.2";
        } else if (versionString.equals("477")) {
            return "1.14";
        } else if (versionString.equals("480")) {
            return "1.14.1";
        } else if (versionString.equals("485")) {
            return "1.14.2";
        } else if (versionString.equals("490")) {
            return "1.14.3";
        } else if (versionString.equals("498")) {
            return "1.14.4";
        } else if (versionString.equals("573")) {
            return "1.15";
        } else if (versionString.equals("575")) {
            return "1.15.1";
        } else if (versionString.equals("578")) {
            return "1.15.2";
        } else if (versionString.equals("735")) {
            return "1.16";
        } else if (versionString.equals("736")) {
            return "1.16.1";
        } else if (versionString.equals("751")) {
            return "1.16.2";
        } else if (versionString.equals("753")) {
            return "1.16.3";
        } else if (versionString.equals("754")) {
            return "1.16.4";
        } else if (versionString.equals("755")) {
            return "1.17";
        } else if (versionString.equals("756")) {
            return "1.17.1";
        } else if (versionString.equals("757")) {
            return "1.18";
        } else if (versionString.equals("758")) {
            return "1.18.2";
        } else if (versionString.equals("759")) {
            return "1.19";
        } else if (versionString.equals("760")) {
            return "1.19.1";
        } else if (versionString.equals("761")) {
            return "1.19.2";
        } else if (versionString.equals("763")) {
            return "1.20";
        } else if (versionString.equals("5")) {
            return "1.7";
        } else if (versionString.equals("47")) {
            return "1.8";
        } else if (versionString.equals("762")) {
            return "1.19.4";
        }
        return versionString;
    }
}