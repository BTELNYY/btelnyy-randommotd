package com.btelnyy.randommotd.constants;

import com.btelnyy.randommotd.service.file_manager.Configuration;

public class ConfigData {
    private static ConfigData instance;

    public void load(Configuration config) {
        instance = this;
    }

    public static ConfigData getInstance(){
        return instance;
    }
}
