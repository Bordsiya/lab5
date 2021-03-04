package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import comparators.AchievementsComparator;
import comparators.WeaponTypeComparator;
import data.SpaceMarine;
import exceptions.EmptyCollectionException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;


public class CollectionManager {
    private Stack<SpaceMarine> stack;
    private LocalDateTime lastSave = null;
    private LocalDateTime lastInit = null;

    public CollectionManager(File jsonFile){
        this.stack = new Stack<>();
        if(jsonFile.length() != 0){
            try{
                loadStack(jsonFile);
                this.lastInit = LocalDateTime.now();
                this.lastSave = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void loadStack(File jsonFile) throws IOException {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(javaTimeModule);
        SpaceMarine [] spaceMarines = mapper.readValue(jsonFile, SpaceMarine[].class);
        for(SpaceMarine sm : spaceMarines){
            this.getCollection().push(sm);
        }
    }

    public LocalDateTime getLastSave(){
        return this.lastSave;
    }

    public LocalDateTime getLastInit(){
        return this.lastInit;
    }

    public Stack<SpaceMarine> getCollection(){
        return this.stack;
    }

    public void setCollection(Stack<SpaceMarine> newStack){
        this.stack = newStack;
    }

    public void setLastSave(LocalDateTime localDateTime){
        this.lastSave = localDateTime;
    }

    public int collectionSize(){
        return stack.size();
    }

    public Integer getNewId(){
        Integer mxId = 0;
        for(SpaceMarine sm : this.getCollection()){
            if (sm.getId() > mxId) mxId = sm.getId();
        }
        return mxId + 1;
    }

    public void printCollection() throws EmptyCollectionException {
        if(this.getCollection().size() == 0) throw new EmptyCollectionException("Коллекция пуста");
        for (SpaceMarine sm : this.getCollection()){
            System.out.println("Космический корабль\n" + sm.toString());
        }
    }

    public void addElement(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        System.out.println(spaceMarine);
        this.stack.push(spaceMarine);
    }

    public SpaceMarine searchById(Integer id){
        for(SpaceMarine sm : this.stack){
            if(sm.getId().equals(id)){
                return sm;
            }
        }
        return null;
    }

    public boolean removeElementById(Integer id){
        SpaceMarine spaceMarine = searchById(id);
        if (spaceMarine != null){
            stack.remove(spaceMarine);
            return true;
        }
        else{
            return false;
        }
    }

    public void clearCollection(){
        stack.clear();
    }

    public void saveCollection(File file) throws IOException {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(javaTimeModule);
        mapper.writeValue(file, this.getCollection());
        this.setLastSave(LocalDateTime.now());
    }

    public void reorderCollection(){
        ArrayList<SpaceMarine> arrayList = new ArrayList<>(this.getCollection());
        Collections.reverse(arrayList);
        Stack<SpaceMarine> newStack = new Stack<>();
        newStack.addAll(arrayList);
        this.setCollection(newStack);
    }

    public ArrayList<SpaceMarine> startsWithAchievements(String achievement){
        ArrayList<SpaceMarine> spaceMarines = new ArrayList<>();
        for (SpaceMarine sm : this.getCollection()){
            if(sm.getAchievements().indexOf(achievement) == 0){
                spaceMarines.add(sm);
            }
        }
        return spaceMarines;
    }

    public void ascendWeaponType(){
        WeaponTypeComparator cmp = new WeaponTypeComparator();
        ArrayList<SpaceMarine> list = new ArrayList<>(this.getCollection());
        Collections.reverse(list);
        list.sort(cmp);
        Stack<SpaceMarine> newStack = new Stack<>();
        Collections.reverse(list);
        newStack.addAll(list);
        printWeaponTypes(newStack);
    }

    public void printWeaponTypes(Stack <SpaceMarine> stack){
        for(SpaceMarine sm : stack){
            System.out.println(sm.getWeaponType().toString());
        }
    }

    public void descendAchievements(){
        AchievementsComparator cmp = new AchievementsComparator();
        ArrayList<SpaceMarine> list = new ArrayList<>(this.getCollection());
        Collections.reverse(list);
        list.sort(cmp);
        Stack<SpaceMarine> newStack = new Stack<>();
        Collections.reverse(list);
        newStack.addAll(list);
        printAchievements(newStack);
    }

    public void printAchievements(Stack<SpaceMarine> stack){
        for(SpaceMarine sm : stack){
            System.out.println(sm.getAchievements());
        }
    }

    public boolean removeGreater(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        Stack<SpaceMarine> bufStack = this.getCollection();
        if(!this.getCollection().contains(spaceMarine)){
            bufStack.push(spaceMarine);
        }
        Collections.sort(bufStack);
        boolean flag = false;
        boolean checkpoint = false;
        for (SpaceMarine sm : bufStack){
            if (sm.equals(spaceMarine)){
                flag = true;
            }
            else if (flag){
                checkpoint = true;
                removeElementById(sm.getId());
            }
        }
        return checkpoint;
    }

    public boolean removeLower(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        Stack<SpaceMarine> bufStack = this.getCollection();
        if(!this.getCollection().contains(spaceMarine)){
            bufStack.push(spaceMarine);
        }
        Collections.sort(bufStack);
        boolean flag = true;
        boolean checkpoint = false;
        for (SpaceMarine sm : bufStack){
            if (sm.equals(spaceMarine)){
                flag = false;
            }
            else if (flag){
                checkpoint = true;
                removeElementById(sm.getId());
            }
        }
        return checkpoint;
    }

    @Override
    public String toString(){
        if(stack.isEmpty()){
            return "Коллекция пуста";
        }
        String res = "Коллекция:\n";
        for(SpaceMarine sm : this.stack){
            res += sm;
            res += "\n";
        }
        res += "\nДата последнего сохранения:\n";
        res += lastSave.toString();
        res += "\nДата последнего взаимодействия:\n";
        res += lastInit.toString();
        return res;
    }

}
