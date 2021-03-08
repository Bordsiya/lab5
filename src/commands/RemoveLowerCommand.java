package commands;

import data.*;
import exceptions.IncorrectScriptInputException;
import exceptions.NoLowerElementException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.AskManager;
import utils.CollectionManager;

/**
 * The Removal Command for collection elements with type {@link SpaceMarine}, lower than assigned element
 * @author NastyaBordun
 * @version 1.1
 */

public class RemoveLowerCommand implements ICommand{
    /**
     * Base for all commands {@link CommandBase}
     */
    private CommandBase commandBase;
    /**
     * Manager for collection {@link CollectionManager}
     */
    private CollectionManager collectionManager;
    /**
     * Manager for re-asking {@link AskManager}
     */
    private AskManager askManager;
    /**
     * Constructor for the command
     * @param commandBase base for commands
     * @param askManager re-asking manager
     * @param collectionManager collection manager
     */
    public RemoveLowerCommand(CommandBase commandBase, CollectionManager collectionManager, AskManager askManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
        this.askManager = askManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#removeLower()
     * @see SpaceMarine#setName(String)
     * @see SpaceMarine#setCoordinates(Coordinates)
     * @see SpaceMarine#setHealth(Float)
     * @see SpaceMarine#setAchievements(String)
     * @see SpaceMarine#setWeaponType(Weapon)
     * @see SpaceMarine#setMeleeWeapon(MeleeWeapon)
     * @see SpaceMarine#setChapter(Chapter)
     * @see CollectionManager#removeLower(SpaceMarine)
     * @see AskManager#askName()
     * @see AskManager#askCoordinates()
     * @see AskManager#askHealth()
     * @see AskManager#askAchievements()
     * @see AskManager#askWeaponType()
     * @see AskManager#askMeleeWeapon()
     * @see AskManager#askChapter()
     */
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
