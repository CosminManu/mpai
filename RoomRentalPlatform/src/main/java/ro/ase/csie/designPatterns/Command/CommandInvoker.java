package ro.ase.csie.designPatterns.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * The Invoker is responsible for executing commands.It could maintain a history of executed commands for undo functionality.
 */
public class CommandInvoker {
    private List<Command> commandHistory = new ArrayList<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }
}
