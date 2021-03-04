package data;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class SpaceMarine implements Comparable<SpaceMarine>{
    private Integer id = null;
    private String name = null;
    private Coordinates coordinates = null;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime creationDate = null;

    private Float health = null;
    private String achievements = null;
    private Weapon weaponType = null;
    private MeleeWeapon meleeWeapon = null;
    private Chapter chapter = null;

    public SpaceMarine(){

    }

    public SpaceMarine(Integer id, String name, Coordinates coordinates, Float health, String achievements, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.health = health;
        this.achievements = achievements;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }

    public Float getHealth(){
        return this.health;
    }

    public String getAchievements(){
        return this.achievements;
    }

    public Weapon getWeaponType(){
        return this.weaponType;
    }

    public MeleeWeapon getMeleeWeapon(){
        return this.meleeWeapon;
    }

    public Chapter getChapter(){
        return this.chapter;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCreationDate(LocalDateTime localDateTime){
        this.creationDate = localDateTime;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public void setCoordinateX(long x){
        this.coordinates.setX(x);
    }

    public void setCoordinateY(Double y){
        this.coordinates.setY(y);
    }

    public void setHealth(float health){
        this.health = health;
    }

    public void setAchievements(String achievements){
        this.achievements = achievements;
    }

    public void setWeaponType(Weapon weaponType){
        this.weaponType = weaponType;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon){
        this.meleeWeapon = meleeWeapon;
    }

    public void setChapter(Chapter chapter){
        this.chapter = chapter;
    }

    public void setChapterName(String name){
        this.chapter.setName(name);
    }

    public void setChapterWorld(String world){
        this.chapter.setWorld(world);
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SpaceMarine spaceMarine = (SpaceMarine) o;
        if (this.hashCode() != spaceMarine.hashCode()) return false;
        return (this.getName().equals(spaceMarine.getName()) && this.getId() == spaceMarine.getId()
        && this.getCoordinates().equals(spaceMarine.getCoordinates()) && this.getHealth() == spaceMarine.getHealth()
        && this.getAchievements().equals(spaceMarine.getAchievements()) && this.getWeaponType() == spaceMarine.getWeaponType()
        && this.getMeleeWeapon() == spaceMarine.getMeleeWeapon() && this.getChapter().equals(spaceMarine.getChapter()));
    }

    @Override
    public int hashCode(){
        int ans = 0;
        for (int i = 0; i < this.getName().length(); i++){
            ans = (ans + (int)this.getName().charAt(i)) % 2000000000;
        }
        ans = (ans + this.getAchievements().length()) % 2000000000;
        return ans + coordinates.hashCode();
    }

    @Override
    public String toString(){
        String res = "Id: " + this.getId() + "\n";
        res += "Название корабля: " + this.getName() + "\n";
        res += "Координаты:\n" + this.getCoordinates().toString();
        res += "Дата создания: " + this.getCreationDate() + "\n";
        res += "Здоровье: " + this.getHealth() + "\n";
        res += "Достижения: " + this.getAchievements() + "\n";
        res += "Тип оружия: " + this.getWeaponType() + "\n";
        res += "Тип оружия ближнего боя: " + this.getMeleeWeapon() + "\n";
        res += "Глава:\n" + this.getChapter().toString();
        return res;
    }
}
