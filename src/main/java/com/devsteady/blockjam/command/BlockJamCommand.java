package com.devsteady.blockjam.command;

import com.devsteady.blockjam.BlockJam;
import com.devsteady.blockjam.user.JamUser;
import com.devsteady.onyx.chat.Chat;
import com.devsteady.onyx.command.Command;
import com.devsteady.onyx.sound.Sounds;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BlockJamCommand {

    @Command(identifier = "music")
    public void onMusicCommand(Player player) {
        JamUser user = BlockJam.API.getUser(player);

        /* Toggle music mode */
        user.setMusicMode(!user.isMusicMode());
        Sounds.playSound(player, Sound.BLOCK_NOTE_PLING);
        Chat.message(player,user.isMusicMode() ? "&a&lMUSIC MODE ENABLED" : "&c&lMUSIC MODE DISABLED");
    }
}
