package commands;

import exceptions.WrongAmountOfElementsInCommandException;

public class ExecuteScriptCommand implements ICommand{

    private CommandBase commandBase;

    public ExecuteScriptCommand(CommandBase commandBase){
        this.commandBase = commandBase;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.executeScript();
            String [] commandArr = str.trim().split(" ");
            if(str.length() == 0 || commandArr.length != 1){
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
