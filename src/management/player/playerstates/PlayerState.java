package management.player.playerstates;

/**
 * A state of the player. During this state the player don't have his normal
 * behavior. He is for exemple swinging his sword, using an item, knocked back
 * or even falling in a hole...
 */
public interface PlayerState {

    /**
     * Updates the player according to this state. This method is called 50
     * times per second if it is the current player state, never otherwise.
     */
    public void update();

    /**
     * Executes the interact script of the player. This is equivalent to
     * pressing the R button in a GBA.
     */
    public void actionRpress();

    /**
     * Executes the interact script of the player. This is equivalent to
     * pressing the R button in a GBA.
     */
    public void actionRrelease();

    /**
     * Executes the action1 script of the player. This is equivalent to pressing
     * the A button in a GBA.
     */
    public void action1press();

    /**
     * Executes the action1 release script of the player. This is equivalent to
     * relasing the A button in a GBA.
     */
    public void action1release();

    /**
     * Executes the action2 script of the player. This is equivalent to pressing
     * the B button in a GBA.
     */
    public void action2press();

    /**
     * Executes the action2 release script of the player. This is equivalent to
     * relasing the B button in a GBA.
     */
    public void action2release();

    // public void pressLeft();

    // public void pressRight();

    // public void pressUp();

    // public void pressDown();

    // public void releaseLeft();

    // public void releaseRight();

    // public void releaseUp();

    // public void releaseDown();
}
