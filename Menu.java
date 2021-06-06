/**
 * author Hananel Hadad.
 *
 * @param <T> the type parameter
 */
public interface Menu<T> extends Animation {
    /**
     * add selection.
     *
     * @param key       to wait for.
     * @param message   to write.
     * @param returnVal to return.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets status.
     *
     * @return the chosen option.
     */
    T getStatus();

    /**
     * add sub menu.
     *
     * @param key     key.
     * @param message massage.
     * @param subMenu submenu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
