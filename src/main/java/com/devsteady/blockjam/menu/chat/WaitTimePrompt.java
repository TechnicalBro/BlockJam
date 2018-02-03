package com.devsteady.blockjam.menu.chat;

import com.devsteady.blockjam.songs.Song;
import com.devsteady.blockjam.songs.SongSection;
import com.devsteady.onyx.chat.Chat;
import org.bukkit.conversations.*;

public class WaitTimePrompt extends NumericPrompt implements ConversationAbandonedListener {

    private SongSection section;
    public WaitTimePrompt(SongSection section) {
        this.section = section;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext conversationContext, Number number) {
        section.setWaitTicks((int)number);
        return new MessagePrompt() {
            @Override
            protected Prompt getNextPrompt(ConversationContext conversationContext) {
                return Prompt.END_OF_CONVERSATION;
            }

            @Override
            public String getPromptText(ConversationContext conversationContext) {
                return Chat.format("&aWait time set to &7%s&a ticks",section.getWaitTicks());
            }
        };
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return Chat.format("&6Enter wait time &o(ticks)&r&6:");
    }

    @Override
    public void conversationAbandoned(ConversationAbandonedEvent conversationAbandonedEvent) {
        //todo revert.
    }
}
