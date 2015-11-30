package management.floors.floorsmanagers;

public interface FloorManager {
    
    /** Sets the current floor to fit the saved data. */
    public void setFloorFromSave(); // TODO

    /**
     * This method updates the floor. This includes checking the position of
     * tileEntities to see if they are on pressure plate, trigger events if the
     * player is on a certain location...
     */
    public void update();
}
