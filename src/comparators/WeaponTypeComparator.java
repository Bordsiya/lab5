package comparators;

import data.SpaceMarine;
import java.util.Comparator;

public class WeaponTypeComparator implements Comparator<SpaceMarine> {

    public int compare(SpaceMarine sp1, SpaceMarine sp2){
        if(sp1 .getWeaponType() == null){
            if(sp2.getWeaponType() == null){
                return 0;
            }
            return -1;
        }
        else if(sp2.getWeaponType() == null){
            if(sp1.getWeaponType() == null){
                return 0;
            }
            return 1;
        }
        else{
            if(sp1.getWeaponType().toString().length() > sp2.getWeaponType().toString().length()){
                return 1;
            }
            else if (sp1.getWeaponType().toString().length() == sp2.getWeaponType().toString().length()){
                return 0;
            }
            else{
                return -1;
            }
        }
    }

}
