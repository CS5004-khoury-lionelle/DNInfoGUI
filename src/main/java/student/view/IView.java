package student.view;

/**
 * Interface for the view.
 */
public interface IView {

    /**
     * Run the view.
     */
    void run();

    /**
     * Set the view option. Likely used by a supervisor type controller, that then determines which
     * view to create.
     * 
     */
    enum ViewOption {
        /** NO view / command line execution only. */
        NONE,
        /** GUI view. */
        GUI,
        /** Console view. */
        CONSOLE;
    }
}
