package utils;

import java.io.BufferedReader;
import java.io.File;

public class FileManager {
    private String filePath = null;
    private File file;
    private BufferedReader r;
    private AskManager askManager;

    FileManager(BufferedReader r, AskManager askManager){
        this.r = r;
        this.askManager = askManager;
    }

    FileManager(AskManager askManager){
        this.askManager = askManager;
    }

    public File getFile(){
        return this.file;
    }

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

    public void uploadPath(){
        this.filePath = askManager.askPath();
    }

    public boolean uploadScriptPath(String filePath){
        if(filePath.length() == 0){
            return false;
        }
        else{
            this.filePath = filePath;
            return true;
        }
    }

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

    public boolean checkRWProperties(){
        if(this.file.canRead() && this.file.canWrite()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkRProperties(){
        if(this.file.canRead()){
            return true;
        }
        else{
            return false;
        }
    }


}
