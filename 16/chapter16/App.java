package chapter16;

import chapter16.repositories.ExpansionRepository;
import chapter16.repositories.FireArmsRepository;
import chapter16.serviceImpl.ExpansionServiceImpl;
import chapter16.serviceImpl.FireArmsServiceImpl;
import chapter16.services.ExpansionService;
import chapter16.services.FireArmsService;
import chapter16.views.ExpansionView;
import chapter16.views.FireArmsView;
import chapter16.views.LoggedInMenuView;
import chapter16.utils.StringUtilsMyPackage;

public class App {
    @SafeVarargs
    // can be applied to methods and constructors that cannot be overridden, and must contain varargs in parameter list
    public static void main(String... args) {

        assert true: "true";

        StringUtilsMyPackage.displayWelcomeMessage();

        LoggedInMenuView.chooseLanguage();

        FireArmsService fireArmServiceImpl = new FireArmsServiceImpl(new FireArmsRepository());
        ExpansionService expansionServiceImpl = new ExpansionServiceImpl(new ExpansionRepository());

        var fireArmsView = new FireArmsView(fireArmServiceImpl);
        var expansionView = new ExpansionView(expansionServiceImpl);

        var loggedInMenuView = new LoggedInMenuView(fireArmsView, expansionView);
        loggedInMenuView.displayMenu();

    }
}
