package time_tracking.controller;

import time_tracking.controller.commands.*;
import time_tracking.controller.commands.admin.AdminPageCommand;
import time_tracking.controller.commands.admin.MakeDecisionCommand;
import time_tracking.controller.commands.admin.MakeDecisionSubmitCommand;
import time_tracking.controller.commands.login.LoginCommand;
import time_tracking.controller.commands.login.LoginSubmitCommand;
import time_tracking.controller.commands.user.*;

import java.util.HashMap;
import java.util.Map;

/**
 * class which contains all commands, which are necessary to response user
 */
public class CommandHolder {
    public static final String NUMBER_BETWEEN_SLASHES_PATTERN = "/\\d+(?=/|$)";
    private Map<String, Command> commands = new HashMap<>();

    public CommandHolder() {
        fillCommands();
    }

    /**
     * method which convert url and find necessary command
     * @param key
     * @return command
     */
    Command findCommand(String key){
        String convertedKey = removeAllNumbersFromUrl(key);
        return commands.getOrDefault(convertedKey, new PageNotFoundCommand());
    }

    //TODO
    private void fillCommands(){
        commands.put("GET:/login", new LoginCommand());
        commands.put("POST:/login", new LoginSubmitCommand());

        commands.put("GET:/logout", new LogoutCommand());

        commands.put("GET:/userPage", new UserPageCommand());
        commands.put("GET:/adminPage", new AdminPageCommand());

        commands.put("GET:/addingActivity", new AddActivityCommand());
        commands.put("POST:/addingActivity", new AddActivitySubmitCommand());

        commands.put("GET:/setTime", new SetTimeCommand());
        commands.put("POST:/setTime", new SetTimeSubmitCommand());

        commands.put("GET:/makeDecision", new MakeDecisionCommand());
        commands.put("POST:/makeDecision", new MakeDecisionSubmitCommand());
    }

    //TODO розібратись чому цей метод треба
    /**
     * this method replaces all digits between slashes to "id"
     * this is necessary because search algorithm doesn't support regular expressions
     * @param url
     * @return converted url
     */
    private String removeAllNumbersFromUrl(String url){
        return url.replaceAll(NUMBER_BETWEEN_SLASHES_PATTERN, "/id");
    }
}