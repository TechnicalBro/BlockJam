package com.devsteady.blockjam.songs;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Instrument;
import org.bukkit.Note;

public class NoteSelectionData {
    private int toneId = -1;
    private int instrumentId = -1;

    public NoteSelectionData() {

    }

    public boolean hasToneSelected() {
        return toneId != -1;
    }

    public boolean hasInstrumentSelected() {
        return instrumentId != -1;
    }

    public void setTone(Note.Tone tone) {
        if (tone == null) {
            toneId = -1;
            return;
        }

        this.toneId = tone.getId();
    }

    public void setInstrument(Instrument instrument) {
        this.instrumentId = instrument.getType();
    }

    public Instrument getInstrument() {
        return Instrument.getByType((byte)instrumentId);
    }

    public Note.Tone getTone() {
        return Note.Tone.getById((byte)toneId);
    }
}
