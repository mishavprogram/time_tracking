package time_tracking.utils.constants;

/**
 * This class contains paths to jsp pages and URLs, which are supported
 */
public final class PagesPath {
    public static final String REDIRECT = "REDIRECT";
    public static final String FORWARD = "FORWARD";

    private static final String VIEW_JSP_CLASSPATH = "/WEB-INF/pages/";

    public static final String USER_HOME = "/userPage";
    public static final String ADMIN_HOME = "/adminPage";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String ADDING_ACTIVITY = "/addingActivity";

    public static final String USER_HOME_PAGE = VIEW_JSP_CLASSPATH + "userPage.jsp";
    public static final String ADMIN_HOME_PAGE = VIEW_JSP_CLASSPATH + "adminPage.jsp";
    public static final String LOGIN_PAGE = VIEW_JSP_CLASSPATH + "login.jsp";
    public static final String ERROR_PAGE = VIEW_JSP_CLASSPATH + "error.jsp";

    public static final String ADDING_ACTIVITY_PAGE = VIEW_JSP_CLASSPATH+"addAct.jsp";
    public static final String SET_TIME_PAGE = VIEW_JSP_CLASSPATH + "setTimePage.jsp";
    public static final String MAKE_DECISION_PAGE = VIEW_JSP_CLASSPATH+"makeDecisionPage.jsp";
    public static final String SET_TIME = "/setTime";
    public static final String MAKE_DECISION = "/makeDecision";
}