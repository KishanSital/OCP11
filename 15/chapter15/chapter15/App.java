package chapter15;

import chapter15.serviceImpl.StudentServiceImpl;
import chapter15.views.MenuView;
import chapter15.views.StudentView;
import mypackage.application.MyPackageApplication;
import mypackage.models.UserModel;
import mypackage.services.UserModelWithArguments;
import mypackage.utils.StringUtilsMyPackage;

import java.util.function.Supplier;

public class App {

    @SafeVarargs
    public static void main(String... args) {

        StringUtilsMyPackage.displayWelcomeMessage();
        UserModelWithArguments userModel = UserModel::new;
        MyPackageApplication.startLoginService(userModel.create(1L, "Kishan", "1234"));

        Supplier<StudentServiceImpl> studentServiceSupplier = StudentServiceImpl::new;
        StudentServiceImpl studentServiceImpl = studentServiceSupplier.get();

        var menuView = new MenuView(studentServiceImpl);
        menuView.displayMenu();

    }
}
