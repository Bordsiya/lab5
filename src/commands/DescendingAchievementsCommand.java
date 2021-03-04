package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

public class DescendingAchievementsCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;

    public DescendingAchievementsCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.descendingAchievements();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            collectionManager.descendAchievements();
            return true;
        }
        catch (EmptyCollectionException | WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
