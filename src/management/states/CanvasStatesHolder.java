package management.states;

/** Static class that holds all the different canvas states. */
public abstract class CanvasStatesHolder {
    
    public static final MainMenuState MAINMENUSTATE = new MainMenuState();
    
    public static final FileSelectState FILESELECTSTATE = new FileSelectState();
    
    public static final GameState GAMESTATE = new GameState();

}
