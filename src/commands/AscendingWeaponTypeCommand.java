package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

/**
 * Printing command for field weaponType of all collection elements, with types {@link data.SpaceMarine}, in ascending order
 * @author NastyaBordun
 * @version 1.1
 */

public class AscendingWeaponTypeCommand implements ICommand{
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
    public AscendingWeaponTypeCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#ascendingWeaponType()
     * @see CollectionManager#collectionSize()
     * @see CollectionManager#ascendWeaponType()
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.ascendingWeaponType();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            collectionManager.ascendWeaponType();
            return true;
        }
        catch (EmptyCollectionException | WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
