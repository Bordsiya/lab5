package utils;

import commands.ICommand;
import exceptions.IncorrectCommandException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class for command announcement
 * @author NastyaBordun
 * @version 1.1
 */

public class CommandManager {

    private ICommand help;
    private ICommand info;
    private ICommand show;
    private ICommand add;
    private ICommand update;
    private ICommand remove;
    private ICommand clear;
    private ICommand save;
    private ICommand executeScript;
    private ICommand exit;
    private ICommand removeGreater;
    private ICommand removeLower;
    private ICommand reorder;
    private ICommand filterAchievements;
    private ICommand ascendingWeaponType;
    private ICommand descendingAchievements;

    public CommandManager(ICommand help,
                          ICommand info,
                          ICommand show,
                          ICommand add,
                          ICommand update,
                          ICommand remove,
                          ICommand clear,
                          ICommand save,
                          ICommand executeScript,
                          ICommand exit,
                          ICommand removeGreater,
                          ICommand removeLower,
                          ICommand reorder,
                          ICommand filterAchievements,
                          ICommand ascendingWeaponType,
                          ICommand descendingAchievements){
        this.help = help;
        this.info = info;
        this.show = show;
        this.add = add;
        this.update = update;
        this.remove = remove;
        this.clear = clear;
        this.save = save;
        this.executeScript = executeScript;
        this.exit = exit;
        this.removeGreater = removeGreater;
        this.removeLower = removeLower;
        this.reorder = reorder;
        this.filterAchievements = filterAchievements;
        this.ascendingWeaponType = ascendingWeaponType;
        this.descendingAchievements = descendingAchievements;
    }

    public boolean help(String str){
        return help.execute(str);
    }

    public boolean info(String str){
        return info.execute(str);
    }

    public boolean show(String str){
        return show.execute(str);
    }

    public boolean add(String str){
        return add.execute(str);
    }

    public boolean update(String str){
        return update.execute(str);
    }

    public boolean removeById(String str){
        return remove.execute(str);
    }

    public boolean clear(String str){
        return clear.execute(str);
    }

    public boolean save(String str){
        return save.execute(str);
    }

    public boolean executeScript(String str){
        return executeScript.execute(str);
    }

    public boolean exit(String str){
        return exit.execute(str);
    }

    public boolean removeGreater(String str){
        return removeGreater.execute(str);
    }

    public boolean removeLower(String str){
        return removeLower.execute(str);
    }

    public boolean reorder(String str){
        return reorder.execute(str);
    }

    public boolean filterAchievements(String str){
        return filterAchievements.execute(str);
    }

    public boolean ascendingWeaponType(String str){
        return ascendingWeaponType.execute(str);
    }

    public boolean descendingAchievements(String str){
        return descendingAchievements.execute(str);
    }

}
