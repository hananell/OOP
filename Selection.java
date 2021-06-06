/**
 * author Hananel Hadad.
 */
public class Selection {
    private String key;
    private String massage;
    private Object toReturn;

    /**
     * Constructor.
     *
     * @param keyy      key
     * @param toPrintt  toPrint.
     * @param toReturnn toReturn.
     */
    public Selection(String keyy, String toPrintt, Object toReturnn) {
        this.key = keyy;
        this.massage = toPrintt;
        this.toReturn = toReturnn;
    }

    /**
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the key.
     */
    public String getMassage() {
        return this.massage;
    }

    /**
     * @return the key.
     */
    public Object getReturn() {
        return this.toReturn;
    }
}
