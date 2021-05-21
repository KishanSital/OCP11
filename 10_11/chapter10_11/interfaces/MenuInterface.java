package chapter10_11.interfaces;

import static chapter10_11.services.TriesValidationService.*;

public abstract interface MenuInterface {
    public void menuOptions();

    default void resetAllValidationServices() {
        resetTriesService();
    }

    public void displayMenu();

    public void init();
}
