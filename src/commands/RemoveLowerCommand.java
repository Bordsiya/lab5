package commands;

import data.SpaceMarine;
import exceptions.IncorrectScriptInputException;
import exceptions.NoLowerElementException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.AskManager;
import utils.CollectionManager;

public class RemoveLowerCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;
    private AskManager askManager;

    public RemoveLowerCommand(CommandBase commandBase, CollectionManager collectionManager, AskManager askManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
        this.askManager = askManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.removeLower();
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
            if(!collectionManager.removeLower(spaceMarine)){
                throw new NoLowerElementException("В коллекции нет элемента, меньше введенного");
            }
            System.out.println("Все меньшие элементы коллекции удалены");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | NoLowerElementException | IncorrectScriptInputException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
