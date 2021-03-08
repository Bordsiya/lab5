package commands;

import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import java.io.File;
import java.io.IOException;

/**
 * Save Collection to File Command
 * @author NastyaBordun
 * @version 1.1
 */

public class SaveCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;
    /**
     * File for saving
     */
    private File file;
    /**
     * Manager for collection {@link CollectionManager}
     */
    private CollectionManager collectionManager;

    /**
     * Constructor for the command
     * @param commandBase base for commands
     * @param file файл для сохранения
     * @param collectionManager collection manager
     */
    public SaveCommand(CommandBase commandBase, File file, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.file = file;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#save()
     * @see CollectionManager#saveCollection(File)
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.save();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            collectionManager.saveCollection(this.file);
            System.out.println("Коллекция сохранена");
            return true;
        }
        catch (IOException e){
            System.out.println("Ошибка при записи в файл");
            return false;
        }
        catch (WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
