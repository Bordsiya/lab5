package utils;

import java.io.BufferedReader;
import java.io.File;

/**
 * Class for files uploading
 * @author NastyaBordun
 * @version 1.1
 */

public class  FileManager {
    /**
     * Path to file
     */
    private String filePath = null;
    /**
     * File
     */
    private File file;
    /**
     * Manager for file (to get the correct path to the file in bad case)
     */
    private AskManager askManager;

    /**
     * Creating file manager (constructor)
     * @param askManager askManager for file
     */
    FileManager(AskManager askManager){
        this.askManager = askManager;
    }

    /**
     * Getting the file
     * @return {@link FileManager#file}
     */
    public File getFile(){
        return this.file;
    }

    /**
     * For creating the working file
     * @see FileManager#uploadEnvPath()
     * @see FileManager#uploadFile()
     * @see FileManager#checkRWProperties()
     * @see FileManager#uploadPath()
     */
    public void createHomeFile(){
        uploadEnvPath();
        uploadFile();
        while(!checkRWProperties()){
            System.out.println("Недостаточно прав доступа, выберите другой файл для взаимодействия");
            uploadPath();
            uploadFile();
        }
        System.out.println("Файл готов для работы");
    }

    /**
     * Getting filePath from environment variable VAR {@link FileManager#filePath}
     * In the bad case - {@link FileManager#uploadPath()}
     */
    public void uploadEnvPath(){
        String path = System.getenv("VAR");
        if(path == null){
            System.out.println("Не удалось получить путь из переменной окружения VAR");
            uploadPath();
        }
        else{
            System.out.println("Удалось получить путь из переменной окружения VAR");
            this.filePath = path;
        }
    }

    /**
     * Getting filePath from the user {@link FileManager#filePath}
     * @see AskManager#askPath()
     */
    public void uploadPath(){
        this.filePath = askManager.askPath();
    }

    /**
     * path uploading to script file {@link FileManager#filePath}
     * @param filePath path to script file
     * @return true - path is uploaded; false - path is empty
     */
    public boolean uploadScriptPath(String filePath){
        if(filePath.length() == 0){
            return false;
        }
        else{
            this.filePath = filePath;
            return true;
        }
    }

    /**
     * File uploading {@link FileManager#file} with path {@link FileManager#filePath}
     * @see FileManager#uploadPath()
     * @see FileManager#uploadFile()
     */
    public void uploadFile(){
        if(filePath != null){
            File newFile = new File(filePath);
            if (!newFile.exists()){
                System.out.println("Путь некорректен");
                uploadPath();
                uploadFile();
            }
            else{
                this.file = newFile;
            }
        }
        else{
            System.out.println("Пути не существует");
            uploadPath();
            uploadFile();
        }
    }
    /**
     * Uploading script file {@link FileManager#file} with path {@link FileManager#filePath}
     * @return result of script file uploading
     */
    public boolean uploadScriptFile(){
        if(this.filePath != null){
            File newFile = new File(filePath);
            if (!newFile.exists()){
                return false;
            }
            else{
                this.file = newFile;
                return true;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Checking for reading and writing properties for file {@link FileManager#file}
     * @return checking result
     */
    public boolean checkRWProperties(){
        if(this.file.canRead() && this.file.canWrite()){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Checking for reading properties for file {@link FileManager#file}
     * @return checking result
     */
    public boolean checkRProperties(){
        if(this.file.canRead()){
            return true;
        }
        else{
            return false;
        }
    }


}
