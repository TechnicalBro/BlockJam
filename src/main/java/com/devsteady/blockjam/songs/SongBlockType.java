package com.devsteady.blockjam.songs;

/**
 * Song actions define what action to perform at a specific section of the song.
 */
public enum SongBlockType {
    START("start"),
    PLAY_NOTE("play"),
    END("end"),
    WAIT("wait");

    private String name;
    SongBlockType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
