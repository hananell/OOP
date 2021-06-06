import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * author Hananel Hadad.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T>, Animation, Task {
    private String title;
    private HighScoresTable highScoresTable;
    private ArrayList<Selection> selections;
    private KeyboardSensor keyboardSensor;
    private T status;
    private Map<String, MenuAnimation> subs = new HashMap<>();
    private AnimationRunner runner;
    private GUI gui;

    /**
     * Instantiates a new Menu animation.
     *
     * @param titlee  the titlee
     * @param hst     the hst
     * @param ks      the ks
     * @param runnerr the runnerr
     * @param guii    the guii
     */
    public MenuAnimation(String titlee, HighScoresTable hst, KeyboardSensor ks, AnimationRunner runnerr, GUI guii) {
        this.title = titlee;
        this.highScoresTable = hst;
        this.selections = new ArrayList<>();
        this.keyboardSensor = ks;
        this.runner = runnerr;
        this.gui = guii;
    }


    /**
     * add selection.
     *
     * @param key      key.
     * @param choice   choice.
     * @param toReturn toReturn.
     */
    public void addSelection(String key, String choice, T toReturn) {
        this.selections.add(new Selection(key, choice, toReturn));
    }

    /**
     * @return the chosen option.
     */
    public T getStatus() {
        return status;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new MenuBackground(this.title);
    }

    /**
     * do one frame of this animation.
     *
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.getBackground().drawOn(d);

        //print all selections
        d.setColor(Color.white);
        for (int i = 0; i < this.selections.size(); i++) {
            String massage = this.selections.get(i).getMassage();
            String key = this.selections.get(i).getKey();
            d.drawText(250, 200 + 80 * i, massage + "   -   " + key, 32);
        }

        //check keys
        for (String key : subs.keySet()) {
            if (keyboardSensor.isPressed(key)) {
                MenuAnimation curSub = subs.get(key);
                runner.run(curSub);
                this.status = (T) curSub.getStatus();
                curSub.reset();
                return;
            }
        }

        for (Selection selection : this.selections) {
            if (this.keyboardSensor.isPressed(selection.getKey())) {
                this.status = (T) selection.getReturn();
            }
        }

    }

    /**
     * determine if the animation should stop.
     *
     * @return true if it should.
     */
    public boolean shouldStop() {
        return this.status != null;
    }

    /**
     * @param key     key.
     * @param message massage.
     * @param subMenu submenu.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        //cast menuTask to T!
        this.addSelection(key, message, (T) new MenuTask((MenuAnimation) subMenu));
        this.subs.put(key, (MenuAnimation) subMenu);
    }

    /**
     * run this menu.
     * @return T.
     */
    public T run() {
        this.runner.run(this);
        return this.status;
    }

    /**
     * Add selections from file.
     *
     * @param filePath the file path
     */
    public void addSelectionsFromFile(String filePath) {
        try {
            Reader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader);
            String nameLine = LevelSpecificationReader.reaNoComments(br);
            String fileLine = LevelSpecificationReader.reaNoComments(br);
            //if the file is empty
            if (nameLine == null || fileLine == null) {
                return;
            }
            //else, read the file
            do {
                String[] split = nameLine.split(":");
                //cast task to T!
                this.addSelection(split[0], split[1], (T) new GameTask(runner, gui, highScoresTable, fileLine));
                nameLine = LevelSpecificationReader.reaNoComments(br);
                fileLine = LevelSpecificationReader.reaNoComments(br);
            } while (nameLine != null && fileLine != null);


        } catch (IOException e) {
            System.out.println("error in sub");
        }

    }

    /**
     * Reset.
     */
    public void reset() {
        this.status = null;
    }
}
