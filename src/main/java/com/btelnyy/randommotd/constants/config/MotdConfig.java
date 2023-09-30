package com.btelnyy.randommotd.constants.config;

import java.util.List;

import com.btelnyy.randommotd.constants.ConfigData;
import com.btelnyy.randommotd.service.file_manager.Configuration;

public class MotdConfig extends ConfigData 
{
    private static MotdConfig instance;

    public List<String> motds = null;
    public String replaceTextTrigger = null;

    @Override
    public void load(Configuration config)
    {
        instance = this;
        motds = config.getStringList("motd");
        replaceTextTrigger = config.getString("replace_text_trigger");
    }    

    public static MotdConfig getInstance()
    {
        return instance;
    }
}
