package commands;

import data.SpaceMarine;
import exceptions.EmptyCollectionException;
import exceptions.NoMatchException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import java.util.ArrayList;

public class FilterAchievementsCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;

    public FilterAchievementsCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.filterAchievements();
            String [] commandArr = str.trim().split(" ");
            if(str.length() == 0 || commandArr.length != 1){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            ArrayList<SpaceMarine> spaceMarines = collectionManager.startsWithAchievements(str);
            if(spaceMarines.size() == 0){
                throw new NoMatchException("Совпадения не найдено");
            }
            for(SpaceMarine sm : spaceMarines){
                System.out.println("Космический корабль\n" + sm.toString());
            }
            System.out.println("Все нужные члены коллекции выведены");
            return true;
        }
        catch (EmptyCollectionException | NoMatchException | WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
