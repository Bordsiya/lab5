package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

/**
 * Sorting command of the collection in reverse order
 * @author NastyaBordun
 * @version 1.1
 */

public class ReorderCommand implements ICommand{
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
    public ReorderCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#reorder()
     * @see CollectionManager#collectionSize()
     * @see CollectionManager#reorderCollection()
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.reorder();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            collectionManager.reorderCollection();
            System.out.println("Коллекция отсортирована в обратном порядке");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | EmptyCollectionException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
