package com.btelnyy.randommotd.constants.config;

import com.btelnyy.randommotd.constants.ConfigData;
import com.btelnyy.randommotd.service.file_manager.Configuration;

public class PluginConfig extends ConfigData
{
    private static PluginConfig instance;
    
    public Boolean pluginEnabled = true;

    public void load(Configuration config)
    {
        instance = this;
        pluginEnabled = config.getBoolean("plugin_enabled");
    }

    public static PluginConfig getInstance()
    {
        return instance;
    }
}
