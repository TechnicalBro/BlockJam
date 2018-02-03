package com.devsteady.blockjam.listener;

import com.devsteady.blockjam.BlockJam;
import com.devsteady.blockjam.menu.chat.SongNamePrompt;
import com.devsteady.blockjam.menu.chat.WaitTimePrompt;
import com.devsteady.blockjam.menu.gui.ToneSelectionMenu;
import com.devsteady.blockjam.songs.Song;
import com.devsteady.blockjam.songs.SongBlockType;
import com.devsteady.blockjam.songs.SongSection;
import com.devsteady.blockjam.user.JamUser;
import com.devsteady.onyx.block.Direction;
import com.devsteady.onyx.chat.Chat;
import com.devsteady.onyx.player.Players;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SongBlockListener implements Listener {

    private BlockJam plugin;
    public SongBlockListener(BlockJam plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        JamUser user = BlockJam.API.getUser(player);

        /* We really don't have anything to stop non-composing users from doing */
        if (!user.isMusicMode()) {
            return;
        }

        Block block = e.getBlock();

        if (!user.hasSongCreationData()) {
            user.setSongCreationData(new Song(block.getLocation()));
        }
        Direction playerDirection = Players.getDirection(player);

        /*
        The logic for the front facing block is actually there to hand off to
        the songsection to generate the wall sign data that holds information about what's happening.
         */
        Block frontFacing = null;

        switch (playerDirection) {
            case NORTH:
                frontFacing = block.getRelative(BlockFace.SOUTH);
                break;
            case EAST:
                frontFacing = block.getRelative(BlockFace.WEST);
                break;
            case WEST:
                frontFacing = block.getRelative(BlockFace.EAST);
                break;
            case SOUTH:
                frontFacing = block.getRelative(BlockFace.NORTH);
                break;
        }

        if (frontFacing != null) {
            frontFacing.setType(Material.WALL_SIGN);
        }

        /* Handle the start of a song! */
        if (block.getType() == Material.DIAMOND_BLOCK) {
            /* Create a prompt for our sign data*/
            SongNamePrompt prompt = new SongNamePrompt(user.getSongCreationData());

            ConversationFactory factory = new ConversationFactory(plugin)
                    .withEscapeSequence("/quit")
                    .withModality(true)
                    .addConversationAbandonedListener(prompt)
                    .withTimeout(10)
                    .withFirstPrompt(prompt);

            factory.buildConversation(player).begin();
            return;
        }

        /*
        Prompt with (first) a menu of the key, and then
        once a key is selected the instrument.

        Perhaps make right click on item play a preview of the sound.
         */
        if (block.getType() == Material.IRON_BLOCK) {
            //open selection menu.

            SongSection section = new SongSection(block.getLocation(),SongBlockType.PLAY_NOTE);
            ToneSelectionMenu menu = new ToneSelectionMenu(user.getSongCreationData(),section);
            Chat.message(player,"&7Use the menus to chose a note and instrument");

            menu.openMenu(player);
            //wait for info?
        }

        /*
        Prompt for a wait (in ticks)
         */
        if (block.getType() == Material.NETHERRACK) {
            SongSection section = new SongSection(block.getLocation(), SongBlockType.WAIT);
            WaitTimePrompt prompt = new WaitTimePrompt(section);

            ConversationFactory factory = new ConversationFactory(plugin)
                    .withEscapeSequence("/quit")
                    .withModality(true)
                    .addConversationAbandonedListener(prompt)
                    .withTimeout(10)
                    .withFirstPrompt(prompt);

            factory.buildConversation(player).begin();
        }

        /*
        They actually shouldn't place a gold block... It's auto placed.
         */
        if (block.getType() == Material.GOLD_BLOCK) {

        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {

    }
}
