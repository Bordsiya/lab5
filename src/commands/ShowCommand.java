package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

/**
 * Command for printing all of collection elements
 * @author NastyaBordun
 * @version 1.1
 */

public class ShowCommand implements ICommand{
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
    public ShowCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#show()
     * @see CollectionManager#printCollection()
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.show();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            System.out.println("Содержание коллекции:\n");
            collectionManager.printCollection();
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | EmptyCollectionException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
