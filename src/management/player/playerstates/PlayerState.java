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
     * Resets this state. For exeple if this state represents the player falling
     * in a hole, it makes the player on the highest part of the hole to make
     * him fall from the top again.
     */
    public void reset();

    /**
     * This method is called for states that requires a button to stay pushed.
     * This method is called when the player releases the pressed key.<br/>
     * Note that this method might not do anything if the player is not in an
     * item state (for exemple knocked back)
     */
    public void relaseItem();

    /**
     * This method is called at the end of the state if it has a finish. Is
     * basicaly sets the player to an other state or to be normal again.
     */
    public void end();
}
