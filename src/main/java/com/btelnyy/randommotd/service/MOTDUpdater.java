package com.btelnyy.randommotd.service;

import java.util.Random;

import com.btelnyy.randommotd.constants.config.MotdConfig;

public class MOTDUpdater implements Runnable
 {
    public static int currentMotdIndex = 0;

    @Override
    public void run() 
    {
        Random rand = new Random();
        currentMotdIndex = rand.nextInt(MotdConfig.getInstance().motds.size());
    }
    
}
