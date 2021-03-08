package commands;

import data.*;
import exceptions.IncorrectScriptInputException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import utils.AskManager;

/**
 * The command for adding a new element to the collection
 * @author NastyaBordun
 * @version 1.1
 */

public class AddCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;
    /**
     * Manager for re-asking {@link AskManager}
     */
    private AskManager askManager;
    /**
     * Manager for collection {@link CollectionManager}
     */
    private CollectionManager collectionManager;

    /**
     * Constructor for the command
     * @param commandBase base for commands
     * @param askManager re-asking manager
     * @param collectionManager collection manager
     */
    public AddCommand(CommandBase commandBase, AskManager askManager, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.askManager = askManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#add()
     * @see SpaceMarine#setName(String)
     * @see SpaceMarine#setCoordinates(Coordinates)
     * @see SpaceMarine#setHealth(Float)
     * @see SpaceMarine#setAchievements(String)
     * @see SpaceMarine#setWeaponType(Weapon)
     * @see SpaceMarine#setMeleeWeapon(MeleeWeapon)
     * @see SpaceMarine#setChapter(Chapter)
     * @see CollectionManager#addElement(SpaceMarine)
     */
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
