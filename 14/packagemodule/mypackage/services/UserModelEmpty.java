package mypackage.services;

import mypackage.annotations.*;
import mypackage.models.*;

@Service("Interface for no constructor reference")
@FunctionalInterface
public interface UserModelEmpty {
    UserModel create();
}
