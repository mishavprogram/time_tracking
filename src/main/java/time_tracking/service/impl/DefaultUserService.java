package time_tracking.service.impl;

import time_tracking.dao.ActivityDao;
import time_tracking.dao.DaoFactory;
import time_tracking.dao.OrderDao;
import time_tracking.dao.UserDao;
import time_tracking.dao.impl.JDBCDaoFactory;
import time_tracking.model.entity.*;
import time_tracking.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

public class DefaultUserService implements UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void addActivity(String name, LocalDate startDate, LocalDate endDate, long userId) {
        UserDao userDao = daoFactory.createUserDao();
        Optional<User> user = userDao.findById(userId);

        if (user.isPresent()){
            Activity activity = new Activity.Builder()
                    .setName(name)
                    .setStatus(StatusActivity.PENDING)
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .setUser(user.get())
                    .build();

            ActivityDao activityDao = daoFactory.createActivityDao();
            activityDao.create(activity);

            long idActivity = activityDao.getLastInsertId();

            activity.setId(idActivity);

            Order order = new Order(0, StatusOrder.PENDING, Action.ADD, activity);

            OrderDao orderDao = daoFactory.createOrderDao();
            orderDao.create(order);

            ((JDBCDaoFactory)daoFactory).closeDangerConnection();
        }
    }

    @Override
    public void deleteActivity(long id) {

    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        UserDao userDao = daoFactory.createUserDao();
        Optional<User> user = userDao.getUserByEmail(email);
        if (user.get().equals(new User.Builder().getInstance())){
            return Optional.empty();
        }
        return user;
    }
}
