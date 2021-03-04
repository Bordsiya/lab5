package commands;

import data.SpaceMarine;
import exceptions.IncorrectScriptInputException;
import exceptions.SpaceMarineNotFoundException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.AskManager;
import utils.CollectionManager;

public class UpdateCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;
    private AskManager askManager;

    public UpdateCommand(CommandBase commandBase, CollectionManager collectionManager, AskManager askManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
        this.askManager = askManager;
    }

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
