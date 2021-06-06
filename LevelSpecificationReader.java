import java.io.BufferedReader;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> ans = new ArrayList<>();
        BufferedReader is = new BufferedReader(reader);
        String line;
        //read one line. if it's null it's the end. if it's start level add the level and continue
        try {
            line = reaNoComments(is);
            if (line == null) {
                return ans;
            }
            do {
                LevelInformation information = this.readOneLevel(is, line);
                ans.add(information);
                line = reaNoComments(is);
            } while (line != null);
        } catch (Exception e) {
            System.out.println("error in reading file");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                System.out.println("error in closing");
            }
        }
        return ans;
    }


    /**
     * read one level.
     *
     * @param br   reader.
     * @param line string to hold line.
     * @return levelInformation.
     * @throws Exception if reading fail.
     */
    private SomeLevel readOneLevel(BufferedReader br, String line) throws Exception {
        //now line is  "START_LEVEL"
        if (!line.equals("START_LEVEL")) {
            System.out.println("not on the start");
        }
        SomeLevel level = new SomeLevel();
        line = reaNoComments(br);
        level.buildName(line);
        line = reaNoComments(br);
        level.buildinitialBallVelocitiesAndNumber(line);
        line = reaNoComments(br);
        level.buildBackground(line);
        line = reaNoComments(br);
        level.buildPaddleSpeed(line);
        line = reaNoComments(br);
        level.buildPaddleWidth(line);
        line = reaNoComments(br);

        //blocks definitions
        String[] split = line.split(":");
        Reader blocksReader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(split[1]));
        BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromReader(blocksReader);

        line = reaNoComments(br);
        split = line.split(":");
        int startX = Integer.parseInt(split[1]);
        line = reaNoComments(br);
        split = line.split(":");
        int startY = Integer.parseInt(split[1]);
        line = reaNoComments(br);
        split = line.split(":");
        int rowHeight = Integer.parseInt(split[1]);
        line = reaNoComments(br);
        level.buildNumberOfBlocks(line);
        line = reaNoComments(br);
        if (!line.equals("START_BLOCKS")) {
            System.out.println("not START_BLOCKS");
        }
        line = reaNoComments(br);
        //blocks initialization
        List<Block> blockList = new ArrayList<>();
        int i = 0;
        while (!line.equals("END_BLOCKS")) {
            int lineWidth = 0;
            for (int j = 0; j < line.length(); j++) {
                //line.charAt(j) is the symbol
                //lineWidth accumulate the width of all the blocks in this line
                if (factory.isBlockSymbol("" + line.charAt(j))) {
                    Block blocki = factory.getBlock("" + line.charAt(j), startX + lineWidth, startY + i * rowHeight);
                    blockList.add(blocki);
                    lineWidth += blocki.getCollisionRectangle().getWidth();
                }
                if (factory.isSpaceSymbol("" + line.charAt(j))) {
                    lineWidth += factory.getSpaceWidth("" + line.charAt(j));
                }
            }
            line = reaNoComments(br);
            //next line will start lowers
            i++;
        }
        level.buildListOfBlocks(blockList);
        return level;
    }

    /**
     * read next line, ignore comments.
     *
     * @param is reader.
     * @return the string.
     * @throws IOException if was problem.
     */
    protected static String reaNoComments(BufferedReader is) throws IOException {
        String line;
        do {
            line = is.readLine();
            if (line == null) {
                return null;
            }
        } while (line.isEmpty() || line.charAt(0) == '#' || line.equals("END_LEVEL"));
        return line;
    }
}
