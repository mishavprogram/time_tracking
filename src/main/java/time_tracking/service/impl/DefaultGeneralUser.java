package time_tracking.service.impl;

import time_tracking.dao.DaoFactory;
import time_tracking.dao.UserDao;
import time_tracking.model.entity.RoleType;
import time_tracking.model.entity.User;
import time_tracking.service.GeneralUserService;
import time_tracking.service.exception.ServiceException;
import time_tracking.utils.constants.MessageKeys;

import java.util.List;
import java.util.Optional;

public class DefaultGeneralUser implements GeneralUserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    //TODO password from view must change in hash
    @Override
    public Optional<User> login(String email, String password) {
        UserDao userDao = daoFactory.createUserDao();
        Optional<User> user = userDao.getUserByEmail(email);

        userDao.close();

        if (user.isPresent()) {
            if (user.get().getPasswordHash().equals(password)) {
                return user;
            } else {
                throw new ServiceException(MessageKeys.WRONG_USER_PASSWORD);
            }
        } else
            return Optional.empty();
    }

    @Override
    public void create(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
