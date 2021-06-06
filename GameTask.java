import biuoop.GUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.util.List;

/**
 * The type Game task.
 *
 * @author Hananel Hadad.
 */
public class GameTask implements Task<Void> {
    private AnimationRunner animationRunner;
    private GUI gui;
    private HighScoresTable highScoresTable;
    private String fileName;

    /**
     * Instantiates a new Game task.
     *
     * @param ani       the ani
     * @param guii      the guii
     * @param hst       the hst
     * @param fileNamee file name.
     */
    public GameTask(AnimationRunner ani, GUI guii, HighScoresTable hst, String fileNamee) {
        this.animationRunner = ani;
        this.gui = guii;
        this.highScoresTable = hst;
        this.fileName = fileNamee;
    }

    /**
     * run.
     *
     * @return nothing.
     */
    public Void run() {
        LevelSpecificationReader levelSpecification = new LevelSpecificationReader();
        Reader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (IOException e) {
            System.out.println("error in opening file");
        }

        List<LevelInformation> levels = levelSpecification.fromReader(new BufferedReader(reader));


        GameFlow gf = new GameFlow(this.animationRunner, this.gui, this.highScoresTable);
        gf.runLevels(levels);
        return null;
    }
}
