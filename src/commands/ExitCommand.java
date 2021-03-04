package commands;

import exceptions.WrongAmountOfElementsInCommandException;

public class ExitCommand implements ICommand{

    private CommandBase commandBase;

    public ExitCommand(CommandBase commandBase){
        this.commandBase = commandBase;
    }

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
