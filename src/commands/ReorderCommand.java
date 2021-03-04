package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

public class ReorderCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;

    public ReorderCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

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
