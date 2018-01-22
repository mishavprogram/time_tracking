package time_tracking.controller.filter;

import time_tracking.controller.i18n.LocaleHolder;
import time_tracking.utils.constants.Attributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    /**
     * contains all supported locales
     */
    private LocaleHolder localeHolder = new LocaleHolder(LocaleHolder.DEFAULT_LOCALE);
    private static final String MESSAGE_PATH = "i18n.messages";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpSession session = req.getSession();
        setResourceBundle(session);
        Locale locale = localeHolder.getCurrentLocale();
        String localeName = extractLocale(req);
        if(localeName != null) {
            locale = findSupportedLocale(localeName);
        }
        req.setAttribute(Attributes.LOCALE, locale);
        session.setAttribute(Attributes.LOCALE, locale);
        session.setAttribute("SUPPORTED_LOCALES",LocaleHolder.SUPPORTED_LOCALES);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private String extractLocale(HttpServletRequest request){
        return request.getParameter(Attributes.LANG);
    }

    private Locale findSupportedLocale(String localeName) {
        for (Locale locale : LocaleHolder.SUPPORTED_LOCALES) {
            if (locale.getLanguage().equals(localeName)) {
                localeHolder.setCurrentLocale(locale);
                return locale;
            }
        }
        return LocaleHolder.DEFAULT_LOCALE;
    }

    private void setResourceBundle(HttpSession session){
        if(session.getAttribute(Attributes.BUNDLE_FILE)==null){
            session.setAttribute(Attributes.BUNDLE_FILE, MESSAGE_PATH);
        }
    }
}