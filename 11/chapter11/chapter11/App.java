package chapter11;

import chapter11.services.*;

public class App {

    public static void main(String...args){

        MenuService menuService = new MenuService();
        menuService.displayMenu();

    }
}
