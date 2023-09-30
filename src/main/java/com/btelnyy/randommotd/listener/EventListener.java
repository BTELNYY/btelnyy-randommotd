package com.btelnyy.randommotd.listener;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import com.btelnyy.randommotd.RandomMOTD;
import com.btelnyy.randommotd.constants.config.MotdConfig;
import com.btelnyy.randommotd.constants.config.PluginConfig;
import com.btelnyy.randommotd.service.MOTDUpdater;
import com.btelnyy.randommotd.service.Utils;
import com.btelnyy.randommotd.service.file_manager.Configuration;
import com.btelnyy.randommotd.service.file_manager.FileID;

public class EventListener implements Listener 
{
    private static final Configuration language = RandomMOTD.getInstance().getFileManager().getFile(FileID.LANGUAGE).getConfiguration();
    
    @EventHandler
    public void onServerListPing(ServerListPingEvent ev)
    {
        if(PluginConfig.getInstance().pluginMode == 0){
            modeZeroHandle(ev);
        }
        if(PluginConfig.getInstance().pluginMode == 1)
        {
            modeOneHandle(ev);
        }
    }

    private void modeZeroHandle(ServerListPingEvent ev)
    {
        String currentMotd = ev.getMotd();
        Random rand = new Random();
        String randomElement = MotdConfig.getInstance().motds.get(rand.nextInt(MotdConfig.getInstance().motds.size()));
        String newMotd = currentMotd.replace(MotdConfig.getInstance().replaceTextTrigger, randomElement);
        ev.setMotd(Utils.specialColoured(newMotd));
    }

   private void modeOneHandle(ServerListPingEvent ev)
    {
        String currentMotd = ev.getMotd();
        String newMotd = currentMotd.replace(MotdConfig.getInstance().replaceTextTrigger, MotdConfig.getInstance().motds.get(MOTDUpdater.currentMotdIndex));
        ev.setMotd(Utils.specialColoured(newMotd));
    }
}
