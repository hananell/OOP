
/**
 * @param <T> the type parameter.
 * @author Hananel Hadad.
 * The type Menu task.
 */
public class MenuTask<T> implements Task {
    private MenuAnimation<T> menu;

    /**
     * Instantiates a new Menu task.
     *
     * @param menuu menu.
     */
    public MenuTask(MenuAnimation menuu) {
        this.menu = menuu;
    }


    /**
     * run the menu.
     *
     * @return its status.
     */
    public T run() {
        return this.menu.run();
    }
}
