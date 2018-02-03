package com.devsteady.blockjam.menu.gui;

import com.devsteady.blockjam.BlockJam;
import com.devsteady.blockjam.songs.NoteSelectionData;
import com.devsteady.blockjam.songs.Song;
import com.devsteady.blockjam.songs.SongSection;
import com.devsteady.blockjam.user.JamUser;
import com.devsteady.onyx.chat.Chat;
import com.devsteady.onyx.inventory.menu.ItemMenu;
import com.devsteady.onyx.inventory.menu.MenuItem;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.material.MaterialData;

public class ToneSelectionMenu extends ItemMenu {
    public static class ToneSelectionMenuItem extends MenuItem {

        private Note.Tone tone;
        private Song song;
        private SongSection section;
        public ToneSelectionMenuItem(Note.Tone tone, Song song, SongSection section) {
            super(tone.name(), new MaterialData(Material.PAPER));
            this.tone = tone;
            this.song = song;
            this.section = section;
        }

        @Override
        public void onClick(Player player, ClickType clickType) {
            JamUser user = BlockJam.API.getUser(player);

            /* If they don't have any selection data give em' some */
            if (!user.hasNoteSelectionData()) {
                user.setNoteSelectionData(new NoteSelectionData());
            }

            /* Now, set the tone ;) */
            user.getNoteSelectionData().setTone(tone);

            Chat.message(player,String.format("&e%s&a has been selected for the tone.",tone.name()));

            //todo switch to instrument selection menu.

            getMenu().switchMenu(player, new InstrumentSelectionMenu(song,section));
        }
    }

    private Song song;
    private SongSection section;
    public ToneSelectionMenu(Song song, SongSection section) {
        super("Select Note", 1);

        this.song = song;
        this.section = section;

        addMenuItem(new ToneSelectionMenuItem(Note.Tone.A,song,section),1);
        addMenuItem(new ToneSelectionMenuItem(Note.Tone.B,song,section),2);
        addMenuItem(new ToneSelectionMenuItem(Note.Tone.C,song,section),3);
        addMenuItem(new ToneSelectionMenuItem(Note.Tone.D,song,section),4);
        addMenuItem(new ToneSelectionMenuItem(Note.Tone.E,song,section),5);
        addMenuItem(new ToneSelectionMenuItem(Note.Tone.F,song,section),6);
        addMenuItem(new ToneSelectionMenuItem(Note.Tone.G,song,section),7);
    }
}
