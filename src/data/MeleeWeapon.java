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

    @Override
    public String toString(){
        switch (this){
            case CHAIN_AXE:
                return "CHAIN_AXE";
            case MANREAPER:
                return "MANREAPER";
            case LIGHTING_CLAW:
                return "LIGHTING_CLAW";
            case POWER_BLADE:
                return "POWER_BLADE";
            case POWER_FIST:
                return "POWER_FIST";
        }
        throw new IllegalArgumentException();
    }

}
