package time_tracking.service.impl;

import time_tracking.model.entity.RoleType;
import time_tracking.model.entity.User;
import time_tracking.service.GeneralUserService;

import java.util.List;

public class GeneralUserImpl implements GeneralUserService {
    public static final String adminEmail = "mishav@ukr.net";
    public static final String adminPasswordHash = "1";

    public static final String userEmail = "zarit@ukr.net";
    public static final String userPasswordHash = "1";

    @Override
    public User login(String email, String password) {
        if (email.equals(adminEmail) && password.equals(adminPasswordHash))
            return new User(1, "Misha","Vin", RoleType.ADMIN, adminEmail, adminPasswordHash);
        if (email.equals(userEmail) && password.equals(userPasswordHash))
            return new User(1,"Vitalik", "Zarit", RoleType.USER, userEmail, userPasswordHash);
        else return null;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
