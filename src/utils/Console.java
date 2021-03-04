package utils;

import commands.*;
import exceptions.IncorrectCommandException;
import exceptions.RecursionScriptException;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Console {
    private boolean work;
    private BufferedInputStream bf;
    private BufferedReader r;
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private CommandBase commandBase;
    private AskManager askManager;
    private FileManager fileManager;

    public Console(){
        this.bf = new BufferedInputStream(System.in);
        this.r = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        this.askManager = new AskManager(this.r);
        this.fileManager = new FileManager(this.r, this.askManager);
        this.commandBase = new CommandBase();

        fileManager.uploadEnvPath();
        fileManager.uploadFile();
        while(!fileManager.checkRWProperties()){
            System.out.println("Недостаточно прав доступа, выберите другой файл для взаимодействия");
            fileManager.uploadPath();
            fileManager.uploadFile();
        }
        System.out.println("Файл готов для работы");

        this.collectionManager = new CollectionManager(this.fileManager.getFile());
        this.commandManager = new CommandManager(
                new HelpCommand(this.commandBase),
                new InfoCommand(this.commandBase, this.collectionManager),
                new ShowCommand(this.commandBase, this.collectionManager),
                new AddCommand(this.commandBase, this.askManager, this.collectionManager),
                new UpdateCommand(this.commandBase, this.collectionManager, this.askManager),
                new RemoveCommand(this.commandBase, this.collectionManager),
                new ClearCommand(this.commandBase, this.collectionManager),
                new SaveCommand(this.commandBase, this.fileManager.getFile(), this.collectionManager),
                new ExecuteScriptCommand(this.commandBase),
                new ExitCommand(this.commandBase),
                new RemoveGreaterCommand(this.commandBase, this.collectionManager, this.askManager),
                new RemoveLowerCommand(this.commandBase, this.collectionManager, this.askManager),
                new ReorderCommand(this.commandBase, this.collectionManager),
                new FilterAchievementsCommand(this.commandBase, this.collectionManager),
                new AscendingWeaponTypeCommand(this.commandBase, this.collectionManager),
                new DescendingAchievementsCommand(this.commandBase, this.collectionManager));

        this.work = true;
        interactiveMode();

    }

    public void interactiveMode(){
        while(this.work){
            try{
                String command = this.r.readLine().trim();
                int flag = chooseCommand(this.r, command, true);
                if(flag == 2){
                    this.work = false;
                }
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
            }
            catch (IncorrectCommandException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public int scriptMode(String path){
        try{
            FileManager fileManager1 = new FileManager(this.askManager);
            if(!(fileManager1.uploadScriptPath(path) && fileManager1.uploadScriptFile() && fileManager1.checkRProperties())){
                System.out.println("Файл с таким путем недоступен");
                return 0;
            }

            FileInputStream file = new FileInputStream(fileManager1.getFile());
            BufferedInputStream bf2 = new BufferedInputStream(file);
            BufferedReader r2 = new BufferedReader(new InputStreamReader(bf2, StandardCharsets.UTF_8));
            askManager.toScriptMode(r2);

            System.out.println("Взаимодействие с файлом-скриптом");

            boolean isWork = true;
            String line = r2.readLine().trim();
            while(line != null && isWork){
                try{
                    String[] commandArr = line.trim().split(" ", 2);
                    if(commandArr[0].equals("execute_script")){
                        throw new RecursionScriptException("Ошибка: рекурсивный вызов файла-скрипта");
                    }
                    else{
                        if(chooseCommand(r2, line, false) == 2){
                            isWork = false;
                        }
                    }
                }
                catch (RecursionScriptException | IncorrectCommandException e){
                    System.out.println(e.getMessage());
                }
                line = r2.readLine();
            }
            if(!isWork){
                return 2;
            }
            else return 1;
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не найден");
            return 0;
        }
        catch (IOException e){
            System.out.println("Ошибка ввода");
            return 0;
        }
    }

    private int chooseCommand(BufferedReader bf, String command, boolean mode) throws IOException, IncorrectCommandException {
        String[] commandArr = command.trim().split(" ", 2);
        if(commandArr.length == 0) throw new IncorrectCommandException("Введена некорректная команда");
        String line;
        if(commandArr.length == 1){
            line = "";
        }
        else{
            line = commandArr[1];
        }
        switch (commandArr[0]){
            case "help":
                if(!commandManager.help(line)) return 0;
                break;
            case "info":
                if(!commandManager.info(line)) return 0;
                break;
            case "show":
                if(!commandManager.show(line)) return 0;
                break;
            case "add":
                if(!commandManager.add(line)) return 0;
                break;
            case "update":
                if(!commandManager.update(line)) return 0;
                break;
            case "remove_by_id":
                if(!commandManager.removeById(line)) return 0;
                break;
            case "clear":
                if(!commandManager.clear(line)) return 0;
                break;
            case "save":
                if(!commandManager.save(line)) return 0;
                break;
            case "execute_script":
                if(!commandManager.executeScript(line)) return 0;
                else return scriptMode(line);
            case "exit":
                if(commandManager.exit(line)) return 2;
                break;
            case "remove_greater":
                if(!commandManager.removeGreater(line)) return 0;
                break;
            case "remove_lower":
                if(!commandManager.removeLower(line)) return 0;
                break;
            case "reorder":
                if(!commandManager.reorder(line)) return 0;
                break;
            case "filter_starts_with_achievements":
                if(!commandManager.filterAchievements(line)) return 0;
                break;
            case "print_field_ascending_weapon_type":
                if(!commandManager.ascendingWeaponType(line)) return 0;
                break;
            case "print_field_descending_achievements":
                if(!commandManager.descendingAchievements(line)) return 0;
                break;
            default:
                throw new IncorrectCommandException("Введена некорректная команда");
        }
        return 1;
    }


}
