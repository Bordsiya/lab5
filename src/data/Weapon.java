package data;

public enum Weapon {
    MELTAGUN,
    BOLT_RIFLE,
    FLAMER,
    MULTI_MELTA,
    MISSILE_LAUNCHER;

    public static void printWeapon(){
        System.out.println("Допустимые значения поля weaponType");
        for(Weapon wp : Weapon.values()){
            System.out.println(wp);
        }
    }
}
