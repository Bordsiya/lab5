package commands;

import exceptions.WrongAmountOfElementsInCommandException;
import utils.Console;

/**
 * Command for script executing from the certain file
 * @author NastyaBordun
 * @version 1.1
 */

public class ExecuteScriptCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;

    private Console console;
    /**
     * Constructor for the command
     * @param commandBase base for commands
     */
    public ExecuteScriptCommand(CommandBase commandBase, Console console){
        this.commandBase = commandBase;
        this.console = console;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#executeScript()
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.executeScript();
            String [] commandArr = str.trim().split(" ");
            if(str.length() == 0 || commandArr.length != 1){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            console.scriptMode(str, console.isWork());
            return true;
        }
        catch (WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
