package chapter21;

import chapter21.models.ExpansionModel;
import chapter21.models.FireArmModel;
import chapter21.repositories.ExpansionRepository;
import chapter21.repositories.FireArmsRepository;
import chapter21.serviceImpl.ExpansionServiceImpl;
import chapter21.serviceImpl.FireArmsServiceImpl;
import chapter21.services.ExpansionRepositoryService;
import chapter21.services.ExpansionService;
import chapter21.services.FireArmsRepositoryService;
import chapter21.services.FireArmsService;
import chapter21.utils.StringUtilsFireArmsCategory;
import chapter21.views.ExpansionView;
import chapter21.views.FireArmsView;
import chapter21.views.LoggedInMenuView;
import mypackage.application.MyPackageApplication;
import mypackage.models.UserModel;
import mypackage.services.UserModelWithArguments;
import mypackage.utils.StringUtilsMyPackage;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public final class App {
    @SafeVarargs
    // can be applied to methods and constructors that cannot be overridden, and must contain varargs in parameter list
    public static void main(String... args) throws Exception {

        StringUtilsMyPackage.displayWelcomeMessage();

        UserModelWithArguments userModel = UserModel::new;
        UserModel expectedUser = userModel.create(1L, "Kishan", new char[]{'1', '2', '3', '4'});

        MyPackageApplication.startLoginService(expectedUser);

        String dbUrl = "jdbc:mysql://localhost:3306/firearmsapp";
        Properties credentials = new Properties();
        credentials.setProperty("user", "root");
        credentials.setProperty("password", "root");

        FireArmsRepositoryService fireArmsRepositoryService = new FireArmsRepository(dbUrl, credentials);
        ExpansionRepositoryService expansionRepositoryService = new ExpansionRepository(dbUrl, credentials);

        ExpansionService expansionService = new ExpansionServiceImpl(expansionRepositoryService);
        FireArmsService fireArmService = new FireArmsServiceImpl(fireArmsRepositoryService);

        expansionService.createTables();

        {
            fireArmService.insertFireArms(
                    new FireArmModel(
                            1L,
                            (StringUtilsFireArmsCategory.HAND_GUNS.getCategoryName()),
                            "GLOCK-19",
                            5,
                            325.00).
                            new FireArmSpecification(
                            "9x19mm",
                            "600g",
                            "670g",
                            "855g",
                            30,
                            "102mm",
                            "28N"),
                    new FireArmModel(
                            2L,
                            (StringUtilsFireArmsCategory.LONG_GUNS.getCategoryName()),
                            "AK-47",
                            5,
                            1288.46).
                            new FireArmSpecification("7.62x39mm",
                            "3.47 Kg",
                            "3.90 Kg",
                            "4.39 Kg",
                            30,
                            "369 mm",
                            "17.7 N"));
        }

        {
            expansionService.insertExpansionPlans(
                    new ExpansionModel(java.sql.Date.valueOf(LocalDate.of(2017, Month.JANUARY, 01)), "Expand storage area", null),
                    new ExpansionModel(java.sql.Date.valueOf(LocalDate.of(2018, Month.JANUARY, 01)), "Expand variety of firearms", null),
                    new ExpansionModel(java.sql.Date.valueOf(LocalDate.of(2019, Month.JANUARY, 01)), "Employ new storage workers", null));
        }


        var fireArmsView = new FireArmsView(fireArmService);
        var expansionView = new ExpansionView(expansionService);

        var loggedInMenuView = new LoggedInMenuView(fireArmsView, expansionView);
        loggedInMenuView.displayMenu();

    }


}

