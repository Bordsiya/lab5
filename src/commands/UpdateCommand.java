package commands;

import data.MeleeWeapon;
import data.SpaceMarine;
import data.Weapon;
import exceptions.IncorrectScriptInputException;
import exceptions.SpaceMarineNotFoundException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.AskManager;
import utils.CollectionManager;

/**
 * The Update Value Command of a Collection Element by ID
 * @author NastyaBordun
 * @version 1.1
 */

public class UpdateCommand implements ICommand{
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
    public UpdateCommand(CommandBase commandBase, CollectionManager collectionManager, AskManager askManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
        this.askManager = askManager;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     * @see CommandBase#update()
     * @see CollectionManager#searchById(Integer)
     * @see AskManager#questionCheck(String)
     * @see SpaceMarine#setName(String)
     * @see SpaceMarine#setCoordinateX(long)
     * @see SpaceMarine#setCoordinateY(Double)
     * @see SpaceMarine#setHealth(Float)
     * @see SpaceMarine#setAchievements(String)
     * @see SpaceMarine#setWeaponType(Weapon)
     * @see SpaceMarine#setMeleeWeapon(MeleeWeapon)
     * @see SpaceMarine#setChapterName(String)
     * @see SpaceMarine#setChapterWorld(String)
     */
    @Override
    public boolean execute(String str) {
        try{
            commandBase.update();
            String [] commandArr = str.split(" ");
            if(str.length() == 0 || commandArr.length != 1){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.searchById(Integer.parseInt(commandArr[0])) == null){
                throw new SpaceMarineNotFoundException("Не найден космический корабль с таким ID");
            }
            SpaceMarine collectionSpaceMarine = collectionManager.searchById(Integer.parseInt(commandArr[0]));

            if(askManager.questionCheck("name")) collectionSpaceMarine.setName(askManager.askName());
            if(askManager.questionCheck("координата x")) collectionSpaceMarine.setCoordinateX(askManager.askCoordinateX());
            if(askManager.questionCheck("координата y")) collectionSpaceMarine.setCoordinateY(askManager.askCoordinateY());
            if(askManager.questionCheck("health")) collectionSpaceMarine.setHealth(askManager.askHealth());
            if(askManager.questionCheck("achievements")) collectionSpaceMarine.setAchievements(askManager.askAchievements());
            if(askManager.questionCheck("weaponType")) collectionSpaceMarine.setWeaponType(askManager.askWeaponType());
            if(askManager.questionCheck("meleeWeapon")) collectionSpaceMarine.setMeleeWeapon(askManager.askMeleeWeapon());
            if(askManager.questionCheck("chapterName")) collectionSpaceMarine.setChapterName(askManager.askChapterName());
            if(askManager.questionCheck("chapterWorld")) collectionSpaceMarine.setChapterWorld(askManager.askChapterWorld());

            System.out.println("Значение элемента обновлено");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException | SpaceMarineNotFoundException | IncorrectScriptInputException e){
            System.out.println(e.getMessage());
            return false;
        }
        catch (NumberFormatException e){
            System.out.println("ID должно являться числом");
            return false;
        }
    }

}
