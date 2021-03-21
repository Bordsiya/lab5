package utils;

import commands.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Class for working environment
 * @author NastyaBordun
 * @version 1.1
 */

public class Application {

    /**
     * Creates working environment
     * @see AskManager
     * @see FileManager
     * @see CommandBase
     * @see CollectionManager
     * @see Business
     * @see Console
     */
    public void createApplication(){
        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader r = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        AskManager askManager = new AskManager(r);
        FileManager fileManager = new FileManager(askManager);
        CommandBase commandBase = new CommandBase();

        fileManager.createHomeFile();

        CollectionManager collectionManager = new CollectionManager(fileManager.getFile());
        Console console = new Console(r, askManager);
        CommandManager commandManager = new CommandManager(
                new HelpCommand(commandBase),
                new InfoCommand(commandBase, collectionManager),
                new ShowCommand(commandBase, collectionManager),
                new AddCommand(commandBase, askManager, collectionManager),
                new UpdateCommand(commandBase, collectionManager, askManager),
                new RemoveCommand(commandBase, collectionManager),
                new ClearCommand(commandBase, collectionManager),
                new SaveCommand(commandBase, fileManager.getFile(), collectionManager),
                new ExecuteScriptCommand(commandBase, console),
                new ExitCommand(commandBase, console),
                new RemoveGreaterCommand(commandBase, collectionManager, askManager),
                new RemoveLowerCommand(commandBase, collectionManager, askManager),
                new ReorderCommand(commandBase, collectionManager),
                new FilterAchievementsCommand(commandBase, collectionManager),
                new AscendingWeaponTypeCommand(commandBase, collectionManager),
                new DescendingAchievementsCommand(commandBase, collectionManager));

        Business business = new Business(commandManager);
        console.setBusiness(business);

        console.interactiveMode();
    }

}
