package chapter3;

import chapter3.Entities.*;
import chapter3.Services.*;

public class App {
	

    public static void main(String[] args) {

        CalculationService calculation = new CalculationService();
        ParametersEntity parametersEntity = new ParametersEntity();
        MenuService menuService = new MenuService(calculation, parametersEntity);
        menuService.Menu();
    }
}
