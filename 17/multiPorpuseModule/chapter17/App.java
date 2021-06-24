package chapter17;

import chapter17.repositories.ExpansionRepository;
import chapter17.repositories.FireArmsRepository;
import chapter17.serviceImpl.ExpansionServiceImpl;
import chapter17.serviceImpl.FireArmsServiceImpl;
import chapter17.services.ExpansionService;
import chapter17.services.FireArmsService;
import chapter17.views.ExpansionView;
import chapter17.views.FireArmsView;
import chapter17.views.LoggedInMenuView;
import mypackage.application.MyPackageApplication;
import mypackage.models.UserModel;
import mypackage.services.UserModelWithArguments;
import mypackage.utils.StringUtilsMyPackage;

public class App {
    @SafeVarargs
    // can be applied to methods and constructors that cannot be overridden, and must contain varargs in parameter list
    public static void main(String... args) {

        StringUtilsMyPackage.displayWelcomeMessage();

        //Constructor reference
        UserModelWithArguments userModel = UserModel::new;
        MyPackageApplication.startLoginService(userModel.create(1L, "Kishan", "1234"));

        FireArmsService fireArmServiceImpl = new FireArmsServiceImpl(new FireArmsRepository());
        ExpansionService expansionServiceImpl = new ExpansionServiceImpl(new ExpansionRepository());

        var fireArmsView = new FireArmsView(fireArmServiceImpl);
        var expansionView = new ExpansionView(expansionServiceImpl);

        var loggedInMenuView = new LoggedInMenuView(fireArmsView, expansionView);
        loggedInMenuView.displayMenu();

    }
}
