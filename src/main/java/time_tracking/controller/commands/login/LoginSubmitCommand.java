package time_tracking.controller.commands.login;

import time_tracking.controller.commands.CommandExecutor;
import time_tracking.model.entity.RoleType;
import time_tracking.model.entity.User;
import time_tracking.service.GeneralUserService;
import time_tracking.service.impl.DefaultGeneralUser;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginSubmitCommand extends CommandExecutor {
    private static final String PARAM_EMAIL = "login_email";
    private static final String PARAM_PASSWORD = "login_password";

    private GeneralUserService userService = new DefaultGeneralUser();

    public LoginSubmitCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        saveLoginDataToRequest(request);
        String pageToGo = PagesPath.LOGIN;
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        Optional<User> user = userService.login(email, password);
        if (user.isPresent()) {
            User person = user.get();
            pageToGo = getResultPageByUserRole(person);
            request.getSession().setAttribute(Attributes.USER_ID, person.getId());
            request.getSession().setAttribute(Attributes.USER_ROLE, person.getRole());

            request.getSession().setAttribute(Attributes.USER_NAME, person.getName());
            request.getSession().setAttribute(Attributes.USER_SURNAME, person.getSurname());
        }
        clearLoginDataFromRequest(request);
        return pageToGo;
    }

    private String getResultPageByUserRole(User user) {
        String result = PagesPath.USER_HOME;
        if (user.getRole() == RoleType.ADMIN) {
            result = PagesPath.ADMIN_HOME;
        }
        return result;
    }

    private void saveLoginDataToRequest(HttpServletRequest request) {
        request.setAttribute(Attributes.PREVIOUS_LOGIN_EMAIL, request.getParameter(PARAM_EMAIL));
        request.setAttribute(Attributes.PREVIOUS_LOGIN_PASSWORD, request.getParameter(PARAM_PASSWORD));
    }

    private void clearLoginDataFromRequest(HttpServletRequest request) {
        request.removeAttribute(Attributes.PREVIOUS_LOGIN_EMAIL);
        request.removeAttribute(Attributes.PREVIOUS_LOGIN_PASSWORD);
    }
}