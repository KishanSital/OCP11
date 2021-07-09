package chapter17;

import chapter17.models.ExpansionModel;
import chapter17.models.FireArmModel;
import chapter17.repositories.ExpansionRepository;
import chapter17.repositories.FireArmsRepository;
import chapter17.serviceImpl.ExpansionServiceImpl;
import chapter17.serviceImpl.FireArmsServiceImpl;
import chapter17.services.ExpansionService;
import chapter17.services.FireArmsService;
import chapter17.utils.StringUtilsFireArmsCategory;
import chapter17.views.ExpansionView;
import chapter17.views.FireArmsView;
import chapter17.views.LoggedInMenuView;
import mypackage.application.MyPackageApplication;
import mypackage.models.UserModel;
import mypackage.services.UserModelWithArguments;
import mypackage.utils.StringUtilsMyPackage;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.*;

public class App {

    @SafeVarargs
    // can be applied to methods and constructors that cannot be overridden, and must contain varargs in parameter list
    public static void main(String... args) throws Exception {

        StringUtilsMyPackage.displayWelcomeMessage();

        UserModelWithArguments userModel = UserModel::new;
        UserModel expectedUser = userModel.create(1L, "Kishan", new char[]{'1', '2', '3', '4'});

        // working with Console class
        MyPackageApplication.startLoginService(expectedUser);

        FireArmsService fireArmServiceImpl = new FireArmsServiceImpl(new FireArmsRepository());
        ExpansionService expansionServiceImpl = new ExpansionServiceImpl(new ExpansionRepository());

        // insert standard firearms and expansion plans
        {
            fireArmServiceImpl.getFireArms().add(new FireArmModel(1L,
                    (StringUtilsFireArmsCategory.HAND_GUNS.getCategoryName()),
                    "GLOCK-19",
                    5, 325.00).new FireArmSpecification("9x19mm",
                    "600g",
                    "670g",
                    "855g",
                    30,
                    "102mm",
                    "28N"));

            fireArmServiceImpl.getFireArms().add(new FireArmModel(2L,
                    (StringUtilsFireArmsCategory.LONG_GUNS.getCategoryName()),
                    "AK-47",
                    5, 1288.46).new FireArmSpecification("7.62x39mm",
                    "3.47 Kg",
                    "3.90 Kg",
                    "4.39 Kg",
                    30,
                    "369 mm",
                    "17.7 N"));

            var zoneId = TimeZone.getDefault().toZoneId();

            expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2017, Month.JANUARY, 01, 00, 00), zoneId),
                    "Expand storage area/vergroot opslag plaats",
                    null));
            expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2018, Month.JANUARY, 01, 00, 00), zoneId),
                    "Expand variety of firearms/verscheidenheid aan vuurwapens uitbreiden",
                    null));
            expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2019, Month.JANUARY, 01, 00, 00), zoneId),
                    "Employ new storage workers/nieuwe opslagmedewerkers in dienst nemen",
                    null));
        }


        var fireArmsView = new FireArmsView(fireArmServiceImpl);
        var expansionView = new ExpansionView(expansionServiceImpl);

        var loggedInMenuView = new LoggedInMenuView(fireArmsView, expansionView);
        loggedInMenuView.displayMenu();

    }
}

