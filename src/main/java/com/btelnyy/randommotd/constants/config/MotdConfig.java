package com.btelnyy.randommotd.constants.config;

import java.util.List;
import java.util.logging.Level;

import com.btelnyy.randommotd.RandomMOTD;
import com.btelnyy.randommotd.constants.ConfigData;
import com.btelnyy.randommotd.service.file_manager.Configuration;

public class MotdConfig extends ConfigData 
{
    private static MotdConfig instance;

    public List<String> motds;
    public String replaceTextTrigger;

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
