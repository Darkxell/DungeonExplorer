package management.player.playerstates;

import java.awt.image.BufferedImage;

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

    /** Called when the player presses the left key. */
    public void pressLeft();

    /** Called when the player presses the right key. */
    public void pressRight();

    /** Called when the player presses the up key. */
    public void pressUp();

    /** Called when the player presses the down key. */
    public void pressDown();

    /** Called when the player releases the left key. */
    public void releaseLeft();

    /** Called when the player releases the right key. */
    public void releaseRight();

    /** Called when the player releases the up key. */
    public void releaseUp();

    /** Called when the player releases the down key. */
    public void releaseDown();

    /**
     * Returns true if the sprite you get fom the player spritesheet is the lset
     * one and need horisontal reversing.
     */
    public boolean isInvertedRight();

    /**
     * Gets the action text above the R(Z) button. This might return
     * <code>NULL</code> in some cases
     */
    public BufferedImage getRightPText();
}
