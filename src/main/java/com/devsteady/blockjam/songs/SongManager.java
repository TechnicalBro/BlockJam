package com.devsteady.blockjam.songs;

/**
 * Holds the data for songs, and provides useful methods for interaction.
 */
public class SongManager {

    private static SongManager instance = null;

    public static SongManager getInstance() {
        if (instance == null) {
            instance = new SongManager();
        }

        return instance;
    }


    protected SongManager() {

    }
}
