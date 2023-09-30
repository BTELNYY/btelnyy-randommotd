package com.btelnyy.randommotd;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import com.btelnyy.randommotd.constants.ConfigData;
import com.btelnyy.randommotd.constants.config.PluginConfig;
import com.btelnyy.randommotd.listener.EventListener;
import com.btelnyy.randommotd.service.file_manager.Configuration;
import com.btelnyy.randommotd.service.file_manager.FileID;
import com.btelnyy.randommotd.service.file_manager.FileManager;

import java.util.logging.Level;

public class RandomMOTD extends JavaPlugin {

    // An instance of the plugin, so we don't need to make everything static
    private static RandomMOTD instance;
    private static final Configuration language = RandomMOTD.getInstance().getFileManager().getFile(FileID.LANGUAGE).getConfiguration();
    
    private Configuration config;
    private ConfigData configData;
    private FileManager fileManager;

    @Override
    public void onEnable() {
        // Self-explanatory
        instance = this;

        // Generate files
        fileManager = new FileManager(this);
        fileManager.addFile(FileID.LANGUAGE, fileManager.create(null, "language.yml"));
        fileManager.addFile(FileID.MOTD, fileManager.create(null, "motd.yml"));

        // Load config
        saveDefaultConfig();
        {
            // Load it into a more user-friendly object
            config = new Configuration(getConfig());

            // Cache it
            configData = new ConfigData();
            configData.load(config);
        }

        // Register commands

        // SnakeYAML fix
        Thread.currentThread().setContextClassLoader(this.getClassLoader());
        // Register events
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        // GitHub message
        getLogger().info("Check out the project on GitHub! https://github.com/BTELNYY/btelnyy-randommotd");

        if(!PluginConfig.getInstance().pluginEnabled)
        {
            getLogger().info(language.get("plugin_disabled_message").toString());
            getPluginLoader().disablePlugin(instance);
        }
    }


    @Override
    public void onDisable(){

    }




    public void log(Level level, Object message){
        getLogger().log(level, message.toString());
    }

    @SuppressWarnings("unused")
    private void registerCommandExecutor(String commandName, CommandExecutor commandExecutor) {
        PluginCommand command = getCommand(commandName);

        /*
        If the command is null java will trigger an error any ways, the goal with this is to
        not trigger the error, so calling an explicit NullPointerError does not make things any better
         */
        if (command == null) {
            getLogger().severe("The command " + commandName + " could not be registered, please contact the plugin authors " + getDescription().getAuthors());
            return;
        }
        command.setExecutor(commandExecutor);
    }
    @SuppressWarnings("unused")
    private void registerCommandExecutor(String commandName, CommandExecutor commandExecutor, TabCompleter completer) {
        PluginCommand command = getCommand(commandName);

        /*
        If the command is null java will trigger an error any ways, the goal with this is to
        not trigger the error, so calling an explicit NullPointerError does not make things any better
         */
        if (command == null) {
            getLogger().severe("The command " + commandName + " could not be registered, please contact the plugin authors " + getDescription().getAuthors());
            return;
        }
        command.setTabCompleter(completer);
        command.setExecutor(commandExecutor);
    }

    public static RandomMOTD getInstance() {
        return instance;
    }

    public ConfigData getConfigData() {
        return configData;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
