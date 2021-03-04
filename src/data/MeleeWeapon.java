package data;

public enum MeleeWeapon {
    CHAIN_AXE,
    MANREAPER,
    LIGHTING_CLAW,
    POWER_BLADE,
    POWER_FIST;

    public static void printMeleeWeapon(){
        System.out.println("Допустимые значения поля meleeWeapon");
        for(MeleeWeapon wp : MeleeWeapon.values()){
            System.out.println(wp);
        }
    }

}
