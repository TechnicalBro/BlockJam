package com.devsteady.blockjam.user;

import com.devsteady.blockjam.BlockJam;
import com.devsteady.onyx.game.listener.IUserManagerHandler;
import com.devsteady.onyx.game.players.UserManager;
import com.devsteady.onyx.yml.Path;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JamUserManager extends UserManager<JamUser> implements IUserManagerHandler {
    @Path("users")
    private Map<UUID, JamUser> users = new HashMap<>();


    private BlockJam plugin;
    public JamUserManager(BlockJam plugin) {
        this.plugin = plugin;
    }


    @Override
    public void handleJoin(Player player) {
        addUser(new JamUser(player));
    }

    @Override
    public void handleLeave(Player player) {
        removeUser(player);
    }
}
