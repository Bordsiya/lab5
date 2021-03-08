package commands;

import exceptions.WrongAmountOfElementsInCommandException;

/**
 * Command for program exiting
 * @author NastyaBordun
 * @version 1.1
 */

public class ExitCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;
    /**
     * Constructor for the command
     * @param commandBase base for commands
     */
    public ExitCommand(CommandBase commandBase){
        this.commandBase = commandBase;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#exit()
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.exit();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            return true;
        }
        catch (WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
