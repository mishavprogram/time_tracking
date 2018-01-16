package time_tracking.controller.commands.login;

import time_tracking.controller.commands.CommandExecutor;
import time_tracking.model.entity.RoleType;
import time_tracking.model.entity.User;
import time_tracking.service.GeneralUserService;
import time_tracking.service.UserService;
import time_tracking.service.impl.GeneralUserImpl;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginSubmitCommand extends CommandExecutor {
    private static final String PARAM_EMAIL = "login_email";
    private static final String PARAM_PASSWORD ="login_password";

    private GeneralUserService userService = new GeneralUserImpl();

    public LoginSubmitCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Perform execute in LoginSubmitCommand."+". Thread : "+Thread.currentThread().getName());
        saveLoginDataToRequest(request);
        String pageToGo = PagesPath.LOGIN;
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        User user = userService.login(email, password);
        if( user!=null){
            pageToGo = getResultPageByUserRole(user);
            request.getSession().setAttribute(Attributes.USER_ID, user.getId());
            request.getSession().setAttribute(Attributes.USER_ROLE, user.getRole());

            request.getSession().setAttribute(Attributes.USER_NAME, user.getName());
            request.getSession().setAttribute(Attributes.USER_SURNAME, user.getSurname());
        }
        clearLoginDataFromRequest(request);
        return pageToGo;
    }

    private String getResultPageByUserRole(User user){
        String result = PagesPath.USER_HOME;
        if(user.getRole()== RoleType.ADMIN) {
            result = PagesPath.ADMIN_HOME;
        }
        return result;
    }

    private void saveLoginDataToRequest(HttpServletRequest request){
        request.setAttribute(Attributes.PREVIOUS_LOGIN_EMAIL, request.getParameter(PARAM_EMAIL));
        request.setAttribute(Attributes.PREVIOUS_LOGIN_PASSWORD, request.getParameter(PARAM_PASSWORD));
    }

    private void clearLoginDataFromRequest(HttpServletRequest request){
        request.removeAttribute(Attributes.PREVIOUS_LOGIN_EMAIL);
        request.removeAttribute(Attributes.PREVIOUS_LOGIN_PASSWORD);
    }
}