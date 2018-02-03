package com.devsteady.blockjam;

import com.devsteady.blockjam.command.BlockJamCommand;
import com.devsteady.blockjam.listener.SongBlockListener;
import com.devsteady.blockjam.user.JamUser;
import com.devsteady.blockjam.user.JamUserManager;
import com.devsteady.onyx.player.Players;
import com.devsteady.onyx.plugin.BukkitPlugin;
import org.bukkit.entity.Player;

public class BlockJam extends BukkitPlugin {

    private static BlockJam instance = null;

    /**
     * @return get the singleton instance of BlockJam plugin.
     */
    public static BlockJam getInstance() {
        return instance;
    }

    /* Handles per-player data for the plugin */
    private JamUserManager userManager = null;

    @Override
    public void startup() {

        userManager = new JamUserManager(this);

        registerListeners(
                /* Registers the user manager to listen for incoming connections to properly handle data */
                userManager,
                new SongBlockListener(this)

        );

        /*
        Register the commands for BlockJam
         */
        registerCommands(new BlockJamCommand());

        for (Player player : Players.allPlayers()) {
            userManager.addUser(player);
        }
    }

    @Override
    public void shutdown() {

    }

    @Override
    public String getAuthor() {
        return "TheGamersCave";
    }

    @Override
    public void initConfig() {
        /*
        Here instead of startup above as this method is
        invoked so startup can handle startup logic, where this configures */
        instance = this;
    }

    /**
     * Static API to easily interact with the plugins various components.
     */
    public static class API {
        public static JamUser getUser(Player player) {
            return getInstance().userManager.getUser(player);
        }
    }
}
