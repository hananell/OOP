import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * author Hananel Hadad.
 */
public class HighScoresTable {
    private LinkedList<ScoreInfo> list;
    private int size;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param sizee the sizee
     */
    public HighScoresTable(int sizee) {
        this.list = new LinkedList<>();
        this.size = sizee;
    }

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param listt list.
     * @param sizee size.
     */
    public HighScoresTable(LinkedList<ScoreInfo> listt, int sizee) {
        this.list = listt;
        this.size = sizee;
    }

    /**
     * Sets size.
     *
     * @param sizee the sizee
     */
    public void setSize(int sizee) {
        this.size = sizee;
    }

    /**
     * Add a high-score in the right place.
     *
     * @param scoree score to add.
     */
    public void add(ScoreInfo scoree) {
        int place = this.getRank(scoree.getScore());
        //add to --place because getRank() returns one more of the real index
        if (place < this.size) {
            this.list.add(place, scoree);
        }
        //if the table is too full, remove the last
        if (this.list.size() > this.size) {
            Iterator<ScoreInfo> i = list.iterator();
            while (i.hasNext()) {
                Object o = i.next();
            }
            i.remove();
        }
    }

    /**
     * Size int.
     *
     * @return table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the current high scores. The list is sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        return this.list;
    }

    /**
     * return the rank of the current score: where will it be on the list if added?.
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     *
     * @param score current score.
     * @return respective ank.
     */
    public int getRank(int score) {
        int ind = 0;
        //move till the given score is higher then a current score, or till the end
        for (ScoreInfo info : list) {
            if (score <= info.getScore()) {
                ind++;
                //if find the right place, return it
            } else {
                return ind;
            }
        }
        //if we finished the list, if we are still within the size return the ind, if not return the size
        if (ind < this.size) {
            return ind;
        }
        return this.size;

    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.list.clear();
    }


    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename to load.
     * @throws IOException if there was a problem.
     */
    public void load(File filename) throws IOException {
        this.clear();
        BufferedReader is = new BufferedReader(// wrapper that reads ahead
                new InputStreamReader(// wrapper that reads characters
                        new FileInputStream(filename)));

        String line;
        String name;
        String score;
        // null means no more data in the stream
        //the entire file will be loaded, no matter the size, for the static constructor,
        //we don't know yet the size
        while ((line = is.readLine()) != null) {
            String[] split = line.split(" ");
            name = split[0];
            score = split[1];
            this.list.addLast(new ScoreInfo(name, Integer.parseInt(score)));
        }
        is.close();

        //in case we do know the size, correct accordingly
        if (this.size != 0) {
            LinkedList<ScoreInfo> listi = new LinkedList<>();
            for (int i = 0; i < this.size; i++) {
                listi.addLast(this.list.get(i));
            }
            this.list = listi;
        }
    }


    /**
     * Save table data to the specified file.
     *
     * @param filename to save.
     * @throws IOException if there was a problem.
     */
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        os = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
        int ind = 0;
        for (ScoreInfo infi : list) {
            os.println(infi.getName() + " " + infi.getScore());
        }
        os.close();
    }

    /**
     * static constructor.
     * Read a table from file and return it.
     * this size is 3.
     * If the file does not exist, or there is a problem with reading it, an empty table is returned.
     *
     * @param filename to read.
     * @return its high score table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable ans = new HighScoresTable(3);
        try {
            ans.load(filename);
        } catch (Exception e) {
            return ans;
        }
        return ans;
    }
}