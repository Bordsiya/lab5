package utils;

import exceptions.IncorrectCommandException;
import exceptions.RecursionScriptException;

import java.util.Stack;

/**
 * Class for refactoring the command line and contacting with {@link CommandManager}
 * @author NastyaBordun
 * @version 1.1
 */

public class Business {
    /**
     * Manger for commands
     * @see CommandManager
     */
    private CommandManager commandManager;
    /**
     * Name of scripts which are executing now
     */
    private Stack<String> scriptNames;

    /**
     * Constructor for class
     * @param commandManager {@link CommandManager}
     */
    public Business(CommandManager commandManager){
        this.commandManager = commandManager;
        scriptNames = new Stack<>();
    }

    /**
     * Refactoring string before the execution
     * @param commandArr command line
     * @return refactored String
     * @throws IncorrectCommandException undefined command
     */
    private String refactorString(String[] commandArr) throws IncorrectCommandException {
        if(commandArr.length == 0) throw new IncorrectCommandException("Введена некорректная команда");
        String line;
        if(commandArr.length == 1){
            line = "";
        }
        else{
            line = commandArr[1];
        }
        return line;
    }

    /**
     * Connecting with {@link CommandManager}
     * @param command command line
     * @return result of the execution
     * @throws IncorrectCommandException undefined command
     * @throws RecursionScriptException script recursion
     */
    public boolean chooseCommand(String command) throws IncorrectCommandException, RecursionScriptException {
        String[] commandArr = command.trim().split(" ", 2);
        String line = refactorString(commandArr);
        boolean res;
        switch (commandArr[0]){
            case "help":
                res =  commandManager.help(line);
                break;
            case "info":
                res = commandManager.info(line);
                break;
            case "show":
                res = commandManager.show(line);
                break;
            case "add":
                res = commandManager.add(line);
                break;
            case "update":
                res = commandManager.update(line);
                break;
            case "remove_by_id":
                res = commandManager.removeById(line);
                break;
            case "clear":
                res = commandManager.clear(line);
                break;
            case "save":
                res = commandManager.save(line);
                break;
            case "execute_script":
                for(String name : scriptNames){
                    if(name.equals(line)){
                        throw new RecursionScriptException("Ошибка: рекурсивный вызов файла-скрипта");
                    }
                }
                scriptNames.push(line);
                res = commandManager.executeScript(line);
                scriptNames.pop();
                break;
            case "exit":
                res = commandManager.exit(line);
                break;
            case "remove_greater":
                res = commandManager.removeGreater(line);
                break;
            case "remove_lower":
                res = commandManager.removeLower(line);
                break;
            case "reorder":
                res = commandManager.reorder(line);
                break;
            case "filter_starts_with_achievements":
                res = commandManager.filterAchievements(line);
                break;
            case "print_field_ascending_weapon_type":
                res = commandManager.ascendingWeaponType(line);
                break;
            case "print_field_descending_achievements":
                res = commandManager.descendingAchievements(line);
                break;
            default:
                throw new IncorrectCommandException("Введена некорректная команда");
        }
        return res;
    }
}
