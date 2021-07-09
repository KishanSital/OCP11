package mypackage.services;

import mypackage.annotations.*;
import mypackage.models.*;

@Service("Interface for constructor reference with arguments")
@FunctionalInterface
public interface UserModelWithArguments {
    UserModel create(Long userId, String username, char[] password);
}
