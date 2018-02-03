package com.devsteady.blockjam.menu.gui;

import com.devsteady.onyx.inventory.menu.Menu;
import com.devsteady.onyx.inventory.menu.MenuBehaviour;
import org.bukkit.entity.Player;

public class NoteSelectionClearBehaviour implements MenuBehaviour{

    private static NoteSelectionClearBehaviour instance = null;

    public static NoteSelectionClearBehaviour getInstance() {
        if (instance == null) {
            instance = new NoteSelectionClearBehaviour();
        }

        return instance;
    }

    protected NoteSelectionClearBehaviour() {

    }

    @Override
    public void doAction(Menu menu, Player player) {

    }
}
