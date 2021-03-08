package commands;

import exceptions.NullLastSaveException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import java.time.format.DateTimeFormatter;

/**
 * Printing command for collection information
 * @author NastyaBordun
 * @version 1.1
 */

public class InfoCommand implements ICommand{
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
    public InfoCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#info()
     * @see CollectionManager#collectionSize()
     * @see CollectionManager#getLastInit()
     * @see CollectionManager#getLastSave()
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.info();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            System.out.println("Размер коллекции: " + collectionManager.collectionSize());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            try{
                if (collectionManager.getLastSave() == null){
                    throw new NullLastSaveException("Данная коллекция еще не сохранялась");
                }
                System.out.println("Дата последнего сохранения: " + collectionManager.getLastSave().format(formatter));
            }
            catch (NullLastSaveException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Дата инициализации: " + collectionManager.getLastInit().format(formatter));
            return true;
        }
        catch (WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
