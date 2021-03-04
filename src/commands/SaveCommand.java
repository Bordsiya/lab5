package commands;

import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import java.io.File;
import java.io.IOException;

public class SaveCommand implements ICommand{

    private CommandBase commandBase;
    private File file;
    private CollectionManager collectionManager;

    public SaveCommand(CommandBase commandBase, File file, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.file = file;
        this.collectionManager = collectionManager;
    }

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
