package chapter10.interfaces;

import static chapter10.services.TriesValidationService.*;

public abstract interface MenuInterface {
    public void menuOptions();

    default void resetAllValidationServices() {
        resetTriesService();
    }

    public void displayMenu();

    public void init();
}
