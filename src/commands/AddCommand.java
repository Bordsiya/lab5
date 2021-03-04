package commands;

import data.SpaceMarine;
import exceptions.IncorrectScriptInputException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.AskManager;

public class AddCommand implements ICommand{

    private CommandBase commandBase;
    private AskManager askManager;
    private CollectionManager collectionManager;

    public AddCommand(CommandBase commandBase, AskManager askManager, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.askManager = askManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.add();
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
            collectionManager.addElement(spaceMarine);
            System.out.println("Элемент добавлен в коллекцию");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | IncorrectScriptInputException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
