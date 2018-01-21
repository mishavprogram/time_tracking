package time_tracking.controller.commands.admin;

import time_tracking.controller.commands.CommandExecutor;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeDecisionSubmitCommand extends CommandExecutor {
    public MakeDecisionSubmitCommand() {
        super(PagesPath.MAKE_DECISION_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
