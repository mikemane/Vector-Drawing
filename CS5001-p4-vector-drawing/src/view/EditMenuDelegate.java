package view;

/**
 * Created by un4 on 07/11/16.
 */
public interface EditMenuDelegate {
    /**
     * this undo the action.
     */
    boolean undoAction();

    /**
     * This redo the action.
     */
    boolean redoAction();
}
