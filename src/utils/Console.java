package utils;

import exceptions.IncorrectCommandException;
import exceptions.RecursionScriptException;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Working environment for command line reading
 * @author NastyaBordun
 * @version 1.1
 */

public class Console {
    /**
     * Variable to identify the need for further work
     */
    private boolean work;
    /**
     * Reader for line reading support {@link BufferedReader#readLine()}
     */
    private BufferedReader r;
    /**
     * Class providing getting correct information from the user {@link AskManager}
     */
    private AskManager askManager;

    private Business business = null;

    /**
     * Constructor - new working environment creating
     * @see FileManager#uploadEnvPath()
     * @see FileManager#uploadFile()
     * @see FileManager#checkRWProperties()
     * @see FileManager#uploadPath()
     * @see Console#interactiveMode()
     */
    public Console(BufferedReader r, AskManager askManager){
        this.r = r;
        this.askManager = askManager;
        this.work = true;
    }

    /**
     * Setting {@link Business} editor for commands
     * @param business
     */
    public void setBusiness(Business business) {
        this.business = business;
    }

    /**
     * Setting working state
     * @param work needful state
     */
    public void setWork(boolean work){
        this.work = work;
    }

    /**
     * Checking the working state
     * @return working state
     */
    public boolean isWork() {
        return work;
    }

    /**
     * Work in the interactive mode
     */
    public void interactiveMode(){
        while(this.work){
            try{
                String command = this.r.readLine().trim();
                if(business.chooseCommand(command)){
                    System.out.println("Команда выполнена успешно");
                }
                else{
                    System.out.println("Команда не выполнена");
                }
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
            }
            catch (IncorrectCommandException | RecursionScriptException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Work with a script
     * @param path path to file passed by chooseCommand method
     * @param lastWork last value of working state for recursion
     * @see FileManager#uploadScriptPath(String)
     * @see FileManager#uploadScriptFile()
     * @see FileManager#checkRProperties()
     * @see AskManager#addScriptMode(BufferedReader)
     */
    public void scriptMode(String path, boolean lastWork){
        try{
            FileManager fileManager1 = new FileManager(this.askManager);
            if(!(fileManager1.uploadScriptPath(path) && fileManager1.uploadScriptFile() && fileManager1.checkRProperties())){
                System.out.println("Файл с таким путем недоступен");
            }
            else{
                FileInputStream file = new FileInputStream(fileManager1.getFile());
                BufferedInputStream bf2 = new BufferedInputStream(file);
                BufferedReader r2 = new BufferedReader(new InputStreamReader(bf2, StandardCharsets.UTF_8));
                askManager.addScriptMode(r2);

                System.out.println("Взаимодействие с файлом-скриптом");

                this.setWork(true);
                String line = r2.readLine().trim();
                while(line != null && this.work){
                    try{
                        if(business.chooseCommand(line)){
                            System.out.println("Команда выполнена успешно");
                        }
                        else{
                            System.out.println("Команда не выполнена");
                        }

                    }
                    catch (IncorrectCommandException | RecursionScriptException e){
                        System.out.println(e.getMessage());
                    }
                    line = r2.readLine();
                }
            }
            if(this.work){
                this.setWork(lastWork);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не найден");
            this.setWork(lastWork);
        }
        catch (IOException e){
            System.out.println("Ошибка ввода");
                this.setWork(lastWork);
        }
    }


}
