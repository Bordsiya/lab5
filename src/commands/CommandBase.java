package commands;

public class CommandBase {

    public void help(){
        System.out.println("Вызвана команда help");
    }

    public void info(){
        System.out.println("Вызвана команда info");
    }

    public void show(){
        System.out.println("Вызвана команда show");
    }

    public void add(){
        System.out.println("Вызвана команда add");
    }

    public void update(){
        System.out.println("Вызвана команда update");
    }

    public void remove(){
        System.out.println("Вызвана команда remove_by_id");
    }

    public void clear(){
        System.out.println("Вызвана команда clear");
    }

    public void save(){
        System.out.println("Вызвана команда save");
    }

    public void executeScript(){
        System.out.println("Вызвана команда execute_script");
    }

    public void exit(){
        System.out.println("Вызвана команда exit");
    }

    public void removeGreater(){
        System.out.println("Вызвана команда remove_greater");
    }

    public void removeLower(){
        System.out.println("Вызвана команда remove_lower");
    }

    public void reorder(){
        System.out.println("Вызвана команда reorder");
    }

    public void filterAchievements(){
        System.out.println("Вызвана команда filter_starts_with_achievements");
    }

    public void ascendingWeaponType(){
        System.out.println("Вызвана команда print_field_ascending_weapon_type");
    }

    public void descendingAchievements(){
        System.out.println("Вызвана команда print_field_descending_achievements");
    }

}
