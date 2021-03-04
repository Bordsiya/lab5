package utils;

import data.*;
import exceptions.*;
import java.io.BufferedReader;
import java.io.IOException;

public class AskManager {

    private BufferedReader bf;
    private BufferedReader bf2;
    private boolean interactiveMode;

    public AskManager(BufferedReader bf){
        this.bf = bf;
        this.interactiveMode = true;
    }

    public void toScriptMode(BufferedReader bf2){
        setBf2(bf2);
        setInteractiveMode(false);
    }

    private void setBf2(BufferedReader bf2){
        this.bf2 = bf2;
    }

    private void setInteractiveMode(boolean interactiveMode){
        this.interactiveMode = interactiveMode;
    }

    public String askName() throws IncorrectScriptInputException {
        boolean flag = false;
        String name = null;
        while(!flag){
            try {
                System.out.println("Введите поле name: ");
                if(interactiveMode){
                    name = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    name = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (name.length() == 0) {
                    throw new EmptyNameException("Поле name не может быть пустым");
                }
                flag = true;
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (EmptyNameException e){
                System.out.println(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return name;
    }

    public Coordinates askCoordinates() throws IncorrectScriptInputException {
        Coordinates coordinates = new Coordinates(askCoordinateX(), askCoordinateY());
        return coordinates;
    }

    public long askCoordinateX() throws IncorrectScriptInputException {
        boolean flag = false;
        long coordinateX = 0;
        while(!flag){
            try {
                System.out.println("Введите координату x поля coordinates: ");
                String strX = "";
                if(interactiveMode){
                    strX = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strX = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (Math.abs(Long.parseLong(strX)) > 991) {
                    throw new IncorrectCoordinateXException("Координата x поля coordinates не может быть больше 991 по модулю");
                }
                flag = true;
                coordinateX = Long.parseLong(strX);
            }
            catch (NumberFormatException e){
                System.out.println("Введено некорректное число");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IncorrectCoordinateXException e){
                System.out.println(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return coordinateX;
    }

    public Double askCoordinateY() throws IncorrectScriptInputException {
        boolean flag = false;
        Double coordinateY = null;
        while(!flag){
            try {
                System.out.println("Введите координату y поля coordinates: ");
                String strY = "";
                if(interactiveMode){
                    strY = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strY = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (strY.length() == 0) {
                    throw new EmptyCoordinateYException("Координата Y поля coordinates не может быть пустой");
                }
                if (Math.abs(Double.parseDouble(strY)) > 767) {
                    throw new IncorrectCoordinateYException("Координата Y поля coordinates не может быть больше 767 по модулю");
                }
                flag = true;
                coordinateY = Double.parseDouble(strY);

            }
            catch (NumberFormatException e){
                System.out.println("Введено некорректное число");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (EmptyCoordinateYException | IncorrectCoordinateYException e){
                System.out.println(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (NullPointerException e){
                System.out.println("Неожиданная ошибка");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return coordinateY;
    }

    public Float askHealth() throws IncorrectScriptInputException {
        boolean flag = false;
        Float health = null;
        while(!flag){
            try {
                System.out.println("Введите поле health: ");
                String strHealth = "";
                if(interactiveMode){
                    strHealth = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strHealth = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(strHealth.length() != 0){
                    if (Float.parseFloat(strHealth) <= 0) {
                        throw new IncorrectHealthException("Поле health должно быть больше 0");
                    }
                    flag = true;
                    health = Float.parseFloat(strHealth);
                }
                else{
                    flag = true;
                    health = null;
                }
            }
            catch (NumberFormatException e){
                System.out.println("Введено некорректное число");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IncorrectHealthException e){
                System.out.println(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (NullPointerException e){
                System.out.println("Неожиданная ошибка");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return health;
    }

    public String askAchievements() throws IncorrectScriptInputException {
        String achievements = null;
        boolean flag = false;
        while(!flag){
            flag = true;
            try{
                System.out.println("Введите поле achievements: ");
                if(interactiveMode){
                    achievements = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    achievements = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
                flag = false;
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        if(achievements.equals("")) return null;
        else return achievements;
    }

    public Weapon askWeaponType() throws IncorrectScriptInputException {
        boolean flag = false;
        Weapon weaponType = null;
        while(!flag){
            flag = true;
            try{
                System.out.println("Введите поле weaponType: ");
                Weapon.printWeapon();
                String strWeaponType = "";
                if(interactiveMode){
                    strWeaponType = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strWeaponType = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(strWeaponType.length() == 0){
                    weaponType = null;
                }
                else{
                    weaponType = Weapon.valueOf(strWeaponType);
                }
            }
            catch (IllegalArgumentException e){
                flag = false;
                System.out.println("Некорректный weaponType");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                flag = false;
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return weaponType;
    }

    public MeleeWeapon askMeleeWeapon() throws IncorrectScriptInputException {
        boolean flag = false;
        MeleeWeapon meleeWeapon = null;
        while(!flag){
            flag = true;
            try{
                System.out.println("Введите поле meleeWeapon: ");
                MeleeWeapon.printMeleeWeapon();
                String strMeleeWeapon = "";
                if(interactiveMode){
                    strMeleeWeapon = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strMeleeWeapon = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                meleeWeapon = MeleeWeapon.valueOf(strMeleeWeapon);
            }
            catch (IllegalArgumentException e){
                flag = false;
                System.out.println("Некорректный meleeWeapon");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IOException e){
                flag = false;
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return meleeWeapon;
    }

    public Chapter askChapter() throws IncorrectScriptInputException {
        Chapter chapter = new Chapter(askChapterName(), askChapterWorld());
        return chapter;
    }

    public String askChapterName() throws IncorrectScriptInputException {
        boolean flag = false;
        String chapterName = null;
        while(!flag){
            try{
                System.out.println("Введите поле name для Chapter: ");
                if(interactiveMode){
                    chapterName = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    chapterName = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(chapterName.length() == 0){
                    throw new EmptyChapterNameException("Имя name поля chapter не может быть пустым");
                }
                flag = true;
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (EmptyChapterNameException e){
                System.out.println(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }

        }
        return chapterName;
    }

    public String askChapterWorld() throws IncorrectScriptInputException {
        boolean flag = false;
        String chapterWorld = null;
        while(!flag){
            try{
                System.out.println("Введите поле world для Chapter: ");
                if(interactiveMode){
                    chapterWorld = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    chapterWorld = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(chapterWorld.length() == 0){
                    throw new EmptyChapterWorldException("Имя world поля chapter не может быть пустым");
                }
                flag = true;
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (EmptyChapterWorldException e){
                System.out.println(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }
        return chapterWorld;
    }

    public String askPath(){
        boolean flag = false;
        String path = null;
        while(!flag){
            try{
                System.out.println("Введите путь к файлу (exit - выход из программы)");
                path = bf.readLine().trim().replaceAll("\uFFFD", "");
                if(path.length() == 0){
                    throw new EmptyPathException("Путь к файлу не может быть пустым");
                }
                else if(path.equals("exit")){
                    System.exit(0);
                }
                flag = true;
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
            }
            catch (EmptyPathException e){
                System.out.println(e.getMessage());
            }
        }
        return path;
    }

    public boolean questionCheck(String field) throws IncorrectScriptInputException {
        while(true){
            try{
                String ans;
                if(interactiveMode) {
                    System.out.println("Хотите ли изменить значение поля " + field + "?\n1 - да\n2 - нет");
                    ans = bf.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    ans = bf2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if(ans.equals("1")){
                    return true;
                }
                else if(ans.equals("2")){
                    return false;
                }
                else{
                    throw new IncorrectAnswerException("Некорректный ответ");
                }
            }
            catch (IOException e){
                System.out.println("Ошибка ввода");
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
            catch (IncorrectAnswerException e){
                System.out.println(e.getMessage());
                if(!interactiveMode) throw new IncorrectScriptInputException("");
            }
        }



    }


}
