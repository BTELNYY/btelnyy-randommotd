package com.btelnyy.randommotd.constants.config;

import com.btelnyy.randommotd.constants.ConfigData;
import com.btelnyy.randommotd.service.file_manager.Configuration;

public class PluginConfig extends ConfigData
{
    private static PluginConfig instance;
    
    public Boolean pluginEnabled = true;
    public int pluginMode = 0;
    public int motdUpdateDelaySeconds = 5;

    @Override
    public void load(Configuration config)
    {
        instance = this;
        pluginEnabled = config.getBoolean("plugin_enabled");
        pluginMode = config.getInt("plugin_mode");
        motdUpdateDelaySeconds = config.getInt("motd_update_delay_seconds");
    }

    public static PluginConfig getInstance()
    {
        return instance;
    }
}
