package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

public class ShowCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;

    public ShowCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

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
