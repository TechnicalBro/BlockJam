package com.devsteady.blockjam.menu.chat;

import com.devsteady.blockjam.BlockJam;
import com.devsteady.blockjam.songs.Song;
import com.devsteady.blockjam.user.JamUser;
import com.devsteady.onyx.chat.Chat;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;

public class SongNamePrompt extends StringPrompt implements ConversationAbandonedListener {

    private Song song;
    public SongNamePrompt(Song song) {
        this.song = song;
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return Chat.format("&6Please enter your songs name&7 ->");
    }

    @Override
    public Prompt acceptInput(ConversationContext conversationContext, String s) {
        song.setName(s);
        return new MessagePrompt() {
            @Override
            protected Prompt getNextPrompt(ConversationContext conversationContext) {
                return Prompt.END_OF_CONVERSATION;
            }

            @Override
            public String getPromptText(ConversationContext conversationContext) {
                return Chat.format("&aSongs name has been set to &7%s&a.",song.getName());
            }
        };
    }

    @Override
    public void conversationAbandoned(ConversationAbandonedEvent conversationAbandonedEvent) {
        Player player = (Player)conversationAbandonedEvent.getContext().getForWhom();

        if (!conversationAbandonedEvent.gracefulExit()) {
            JamUser user = BlockJam.API.getUser(player);
            //todo break block.
            user.setSongCreationData(null);
            Chat.message(player,"&cYou need to give a name in order to begin.");
        } else {
            Chat.message(player,song.getName() + " is your songs name");
        }
    }
}
