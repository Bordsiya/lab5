package comparators;

import data.SpaceMarine;
import java.util.Comparator;

public class AchievementsComparator implements Comparator<SpaceMarine> {

    public int compare(SpaceMarine sp1, SpaceMarine sp2){
        if(sp1.getAchievements().length() > sp2.getAchievements().length()){
            return -1;
        }
        else if (sp1.getAchievements().length() == sp2.getAchievements().length()){
            return 0;
        }
        else{
            return 1;
        }
    }

}
