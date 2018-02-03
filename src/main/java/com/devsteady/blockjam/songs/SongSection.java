package com.devsteady.blockjam.songs;

import com.devsteady.onyx.block.Blocks;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Song Sections hold a location for the block which defines its action, a location / entity identifier for the sign which
 * holds its information, along with what note, instrument, or wait / time to perform at this particular sequence in the song.
 */
public class SongSection {

    @Getter
    private Location blockLocation;

    @Getter
    private Location wallSignLocation;

    @Getter
    private SongBlockType action;

    private int toneId;

    private int instrumentId;

    @Getter
    @Setter
    private int waitTicks;

    public SongSection(Location blockLoc, SongBlockType action) {
        this.blockLocation = blockLoc;
        this.action = action;
    }

    public void play(Player player) {
        //todo?
    }

    public void setInstrument(Instrument instrument) {
        instrumentId = instrument.getType();
    }

    public Instrument getInstrument() {
        return Instrument.getByType((byte)instrumentId);
    }

    public void setTone(Note.Tone tone) {
        toneId = tone.getId();
    }

    public Note.Tone getTone() {
        return Note.Tone.getById((byte)toneId);
    }

    public Note getNote() {
        return new Note(1, Note.Tone.getById((byte)toneId),false);
    }

    /**
     * Check whether or not the block related to this section of the song is valid or not.
     * @return true if the block is there, false otherwise.
     */
    public boolean isBlockValid() {
        Block songSectionBlock = Blocks.getBlockAt(blockLocation);

        switch (action) {
            case START:
                return songSectionBlock.getType() == Material.DIAMOND_BLOCK;
            case PLAY_NOTE:
                return songSectionBlock.getType() == Material.IRON_BLOCK;
            case END:
                return songSectionBlock.getType() == Material.GOLD_BLOCK;
            case WAIT:
                return songSectionBlock.getType() == Material.NETHERRACK;
        }

        return false;
    }

    /**
     * Check whether or not the sign (attached to block) is valid or not.
     * @return true if the sign is there, false otherwise.
     */
    public boolean isSignValid() {
        return false;
        //todo check if information on sign exists.
    }

    /**
     * Update the data for the block, and also update the sign for the block aswell to
     * hold all the information regarding this song section.
     */
    public void updateBlock() {
        Block block = Blocks.getBlockAt(blockLocation);


    }

}
