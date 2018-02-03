package com.devsteady.blockjam.menu.gui;

import com.devsteady.blockjam.BlockJam;
import com.devsteady.blockjam.songs.NoteSelectionData;
import com.devsteady.blockjam.songs.Song;
import com.devsteady.blockjam.songs.SongSection;
import com.devsteady.blockjam.user.JamUser;
import com.devsteady.onyx.chat.Chat;
import com.devsteady.onyx.inventory.menu.*;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * Inventory based GUI which contains a set of items (instruments) for the player to select from.
 * Right clicking the element will play a note in the tone that the player previously selected.
 */
public class InstrumentSelectionMenu extends ItemMenu {

    /**
     * This represents a single item in the inventory, with an action that's bound to happen when it's interacted with.
     */
    public static class InstrumentSelectionMenuItem extends MenuItem {

        private Instrument instrument;
        public InstrumentSelectionMenuItem(Instrument instrument) {
            super(instrument.name());
            addDescriptions("&7Right click to hear!");
            this.instrument = instrument;
        }

        @Override
        public void onClick(Player player, ClickType clickType) {
            JamUser user = BlockJam.API.getUser(player);

            NoteSelectionData data = user.getNoteSelectionData();

            if (clickType == ClickType.RIGHT) {
                player.playNote(player.getLocation(),instrument,new Note(1,data.getTone(),false));
            } else {
                data.setInstrument(instrument);
                getMenu().closeMenu(player);
            }
        }
    }


    private Song song;
    private SongSection section;

    public InstrumentSelectionMenu(Song song, SongSection section) {
        super("Select your instrument", 2);
        this.section = section;
        this.song = song;

        addMenuItem(new InstrumentSelectionMenuItem(Instrument.BASS_DRUM),0);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.BASS_GUITAR),1);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.BELL),2);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.CHIME),3);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.FLUTE),4);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.GUITAR),5);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.PIANO),6);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.SNARE_DRUM),7);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.STICKS),8);
        addMenuItem(new InstrumentSelectionMenuItem(Instrument.XYLOPHONE),9);

        addBehaviour(MenuAction.CLOSE, new MenuBehaviour() {
            @Override
            public void doAction(Menu menu, Player player) {
                JamUser user = BlockJam.API.getUser(player);

                NoteSelectionData data = user.getNoteSelectionData();

                if (data.hasToneSelected() && !data.hasInstrumentSelected()) {
                    /* We wait a tick as then the player won't have conflicting interfaces open */
                    BlockJam.getInstance().getThreadManager().runTaskOneTickLater(() -> {
                        new InstrumentSelectionMenu(song,section).openMenu(player);
                    });
                } else {
                    section.setTone(data.getTone());
                    section.setInstrument(data.getInstrument());
                    Chat.format(player,"&aYou have set this note to be a(n) &e%s&a [&7%s&a]",data.getInstrument().name(),data.getTone().name());
                }
            }
        });
    }


}
