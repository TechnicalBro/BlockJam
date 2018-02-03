package com.devsteady.blockjam.songs;

import com.devsteady.onyx.yml.YamlConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Songs!
 *
 * Each song has a name, along with sections that compose the whole piece.
 *
 * Each section has a particular action to perform, and related blocks / signs with it aswell.
 */
public class Song extends YamlConfig {

    @Getter
    @Setter
    private String name;


    private List<SongSection> sections = new ArrayList<>();

    @Getter
    private Location startLoc;

    public Song(Location start) {
        this.startLoc = start;
    }

    public void addSection(SongSection section) {
        sections.add(section);
    }

    /**
     * Checks if a location is actually a section to a composition by comparing the locations of blocks the song has recognized, to the
     * location of the block for the section.
     * @param loc location to check.
     * @return true if the location is a section for a song, false otherwise.
     */
    public boolean isSection(Location loc) {
        for(SongSection section : sections) {
            if (section.getBlockLocation().equals(loc)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Update the position of the "final" block.
     */
    public void updateGoldBlock() {
        if (sections.isEmpty()) {
            return;
        }

        SongSection lastSection = sections.get(sections.size() - 1);

        if (lastSection.getAction() != SongBlockType.END) {
            lastSection = new SongSection(lastSection.getBlockLocation().add(-2,0,0),SongBlockType.END);

        }
    }
}
