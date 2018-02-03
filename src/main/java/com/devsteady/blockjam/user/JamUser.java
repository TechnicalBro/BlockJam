package com.devsteady.blockjam.user;

import com.devsteady.blockjam.songs.NoteSelectionData;
import com.devsteady.blockjam.songs.Song;
import com.devsteady.onyx.player.User;
import com.devsteady.onyx.yml.Path;
import com.devsteady.onyx.yml.Skip;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class JamUser extends User {

    @Path("music-mode")
    @Getter
    @Setter
    private boolean musicMode = false;

    @Skip
    @Getter
    private NoteSelectionData noteSelectionData = null;

    @Skip
    @Getter @Setter
    private Song songCreationData = null;


    public JamUser(Player player) {
        super(player);
    }

    public boolean hasNoteSelectionData() {
        return noteSelectionData != null;
    }

    public void setNoteSelectionData(NoteSelectionData data) {
        this.noteSelectionData = data;
    }

    public boolean hasSongCreationData() {
        return songCreationData != null;
    }

}
