package commands;

import data.SpaceMarine;
import exceptions.IncorrectScriptInputException;
import exceptions.NoGreaterElementException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.AskManager;
import utils.CollectionManager;

public class RemoveGreaterCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;
    private AskManager askManager;

    public RemoveGreaterCommand(CommandBase commandBase, CollectionManager collectionManager, AskManager askManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
        this.askManager = askManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.removeGreater();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            SpaceMarine spaceMarine = new SpaceMarine();
            spaceMarine.setName(askManager.askName());
            spaceMarine.setCoordinates(askManager.askCoordinates());
            spaceMarine.setHealth(askManager.askHealth());
            spaceMarine.setAchievements(askManager.askAchievements());
            spaceMarine.setWeaponType(askManager.askWeaponType());
            spaceMarine.setMeleeWeapon(askManager.askMeleeWeapon());
            spaceMarine.setChapter(askManager.askChapter());
            if(!collectionManager.removeGreater(spaceMarine)){
                throw new NoGreaterElementException("В коллекции нет элемента, большего введенного");
            }
            System.out.println("Все большие элементы коллекции удалены");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | NoGreaterElementException | IncorrectScriptInputException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
