package commands;

import exceptions.ObjectDoesNotExistException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

/**
 * The Removal Command for collection elements with type {@link data.SpaceMarine} by ID
 * @author NastyaBordun
 * @version 1.1
 */

public class RemoveCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;
    /**
     * Manager for collection {@link CollectionManager}
     */
    private CollectionManager collectionManager;
    /**
     * Constructor for the command
     * @param commandBase base for commands
     * @param collectionManager collection manager
     */
    public RemoveCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#remove()
     * @see CollectionManager#removeElementById(Integer)
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.remove();
            String [] commandArr = str.trim().split(" ");
            if(str.length() == 0 || commandArr.length != 1){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(!collectionManager.removeElementById(Integer.parseInt(commandArr[0]))){
                throw new ObjectDoesNotExistException("Объект не удален, его не существует");
            }
            System.out.println("Объект удален");
            return true;
        }
        catch (NumberFormatException e){
            System.out.println("Некорректный ID");
            return false;
        }
        catch (ObjectDoesNotExistException | WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
