package de.azubi.kniffel.gui.fx.table;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreRow {
    private Object scoreName;
    private Object player1;
    private Object player2;
    private Object player3;
    private Object player4;
    private Object player5;

    public ScoreRow(Object... args){
        Field[] fields = getClass().getDeclaredFields();
        int i = 0;
        for (Object arg : args) {
            try {
                fields[i++].set(this, arg);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ScoreRow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ScoreRow(Object scoreName, Object[] players){
        Field[] fields = getClass().getDeclaredFields();
        int i = 0;
        try {
            fields[i++].set(this, scoreName);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ScoreRow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object getScoreName()

    {
        return scoreName;
    }

    public Object getPlayer1()

    {
        return player1;
    }


    public Object getPlayer2()
    {
        return player2;
    }


    public Object getPlayer3()

    {
        return player3;
    }


    public Object getPlayer4()
    {

        return player4;
    }

    public Object getPlayer5()
    {
        return player5;
    }
}

