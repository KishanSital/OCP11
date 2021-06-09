package chapter14;

import chapter14.models.ExpansionModel;
import chapter14.serviceImpl.LoggedInMenuServiceImpl;
import chapter14.views.ExpansionView;
import chapter14.views.FireArmsView;
import mypackage.application.MyPackageApplication;
import mypackage.models.UserModel;
import mypackage.utils.StringUtilsMyPackage;
import mypackage.services.*;


public class App {
    @SafeVarargs // can be applied to methods and constructors that cannot be overridden, and must contain varargs in parameter list
    public static void main(String ... args) {

        StringUtilsMyPackage.displayWelcomeMessage();

        //Constructor reference
        UserModelWithArguments userModel = UserModel::new;
        MyPackageApplication.startLoginService(userModel.create(1L, "Kishan", "1234"));

        var fireArmsView = new FireArmsView();
        fireArmsView.insertStandardFireArms();

        var expansionView = new ExpansionView();
        expansionView.insertStandardExpansionPlans();

        var loggedInMenuServiceImpl = new LoggedInMenuServiceImpl(fireArmsView, expansionView);
        loggedInMenuServiceImpl.displayMenu();

    }
}
