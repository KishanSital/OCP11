package chapter16.services;

import chapter16.annotations.Service;

import static chapter16.serviceImpl.TriesValidationServiceImpl.resetTriesService;

@Service
public abstract interface MenuService {
    // none of these abstract methods are required to be implement and overridden
    String toString();
    int hashCode();
    boolean equals(Object object);
    default void menuOptions() {}
    default void displayMenu() {}
    default void init() {}
    default void printOutMenuOptions(){};

    default void resetAllValidationServices() {
        reset();
    }
    private static void reset(){
        resetTriesService();
    }

}
