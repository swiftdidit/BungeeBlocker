package me.fleeking.bungeeblocker;

import me.fleeking.bungeeblocker.commands.VersionCommand;
import me.fleeking.bungeeblocker.events.VersionBlocker;
import me.fleeking.bungeeblocker.util.config.LanguageManager;
import me.fleeking.bungeeblocker.util.config.ReloadCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class BungeeBlocker extends Plugin {
    private static BungeeBlocker instance;
    private File dataFolder;
    private File configFile;
    private Configuration configuration;
    @Override
    public void onEnable() {
        this.instance = this;
        getLogger().info("Bungee Blocker has loaded successfully (Fleeking)");

        // Create plugin folder
        dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        // Create config file
        configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            try (InputStream inputStream = getResourceAsStream("config.yml")) {
                Files.copy(inputStream, configFile.toPath());
            } catch (IOException e) {
                getLogger().severe("Failed to create config.yml file!");
                e.printStackTrace();
                return;
            }
        }
        // Load config with default values
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            getLogger().severe("Failed to load config.yml file!");
            e.printStackTrace();
            return;
        }
        registerEvents();
        LanguageManager.initialize(configuration);
    }
    @Override
    public void onDisable() {
        getLogger().info("Bungee Blocker has disabled (Fleeking)");
    }
    public void registerEvents() {
        PluginManager pm = ProxyServer.getInstance().getPluginManager();
        pm.registerListener(this, new VersionBlocker(this));

        getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this));
        getProxy().getPluginManager().registerCommand(this, new VersionCommand(this));
    }
    public static BungeeBlocker getInstance() {
        return instance;
    }
    public Configuration getConfiguration() {
        return configuration;
    }
    public void reloadConfig() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            LanguageManager.initialize(configuration);
            getLogger().info("Configuration reloaded successfully.");
        } catch (IOException e) {
            getLogger().severe("Failed to reload config.yml file!");
            e.printStackTrace();
        }
    }
}

