package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

public class ClearCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;

    public ClearCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.clear();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            collectionManager.clearCollection();
            System.out.println("Коллекция очищена");
            return true;
        }
        catch (EmptyCollectionException | WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
