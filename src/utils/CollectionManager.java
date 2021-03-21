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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Class for working with collection
 * @author NastyaBordun
 * @version 1.1
 */

public class CollectionManager {
    /**
     * Collection of elements {@link SpaceMarine}
     */
    private Stack<SpaceMarine> stack;
    /**
     * Last save time for the collection
     */
    private LocalDateTime lastSave = null;
    /**
     * Last initialization time for the collection
     */
    private LocalDateTime lastInit = null;

    /**
     * Creating a class to work with the collection
     * @param jsonFile file with collection
     * @see CollectionManager#loadStack(File)
     * @see CollectionManager#lastInit
     * @see CollectionManager#lastSave
     */
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

    /**
     * Loading stack elements from file
     * @param jsonFile file with collection
     * @throws IOException Input/Output exception
     * @see CollectionManager#getCollection()
     */
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
    /**
     * Getting collection last saving time
     * @return {@link CollectionManager#lastSave}
     */
    public LocalDateTime getLastSave(){
        return this.lastSave;
    }
    /**
     * Getting collection last initialization time
     * @return {@link CollectionManager#lastInit}
     */
    public LocalDateTime getLastInit(){
        return this.lastInit;
    }
    /**
     * Getting collection
     * @return {@link CollectionManager#stack}
     */
    public Stack<SpaceMarine> getCollection(){
        return this.stack;
    }

    /**
     * Setting field {@link CollectionManager#stack}
     * @param newStack новая коллекция
     */
    public void setCollection(Stack<SpaceMarine> newStack){
        this.stack = newStack;
    }

    /**
     * Setting collection last saving time
     * @param localDateTime new collection last save
     */
    public void setLastSave(LocalDateTime localDateTime){
        this.lastSave = localDateTime;
    }

    /**
     * Getting collection size
     * @return collection size
     */
    public int collectionSize(){
        return stack.size();
    }

    /**
     * Creating new ID for new element with type {@link SpaceMarine} of {@link CollectionManager#stack} collection
     * @return new ID
     * @see CollectionManager#getCollection()
     * @see SpaceMarine#getId()
     */
    public Integer getNewId(){
        Integer mxId = 0;
        for(SpaceMarine sm : this.getCollection()){
            if (sm.getId() > mxId) mxId = sm.getId();
        }
        return mxId + 1;
    }
    /**
     * Printing {@link CollectionManager#stack} collection
     * @throws EmptyCollectionException if collection is empty
     * @see CollectionManager#getCollection()
     */
    public void printCollection() throws EmptyCollectionException {
        if(this.getCollection().size() == 0) throw new EmptyCollectionException("Коллекция пуста");
        for (SpaceMarine sm : this.getCollection()){
            System.out.println("Космический корабль\n" + sm.toString());
        }
    }

    /**
     * Adding new element to collection {@link CollectionManager#stack}
     * @param spaceMarine new element with type {@link SpaceMarine}
     * @see CollectionManager#getNewId()
     * @see SpaceMarine#setId(Integer)
     * @see SpaceMarine#setCreationDate(LocalDateTime)
     */
    public void addElement(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        System.out.println(spaceMarine);
        this.stack.push(spaceMarine);
    }

    /**
     * Searching element with type {@link SpaceMarine} in collection {@link CollectionManager#stack} by ID
     * @param id ID of the searching element
     * @return founded element with type {@link SpaceMarine}
     * @see SpaceMarine#getId()
     */
    public SpaceMarine searchById(Integer id){
        for(SpaceMarine sm : this.stack){
            if(sm.getId().equals(id)){
                return sm;
            }
        }
        return null;
    }

    /**
     * Deleting element with type {@link SpaceMarine} {@link CollectionManager#stack} collection by ID
     * @param id ID of the deleting element
     * @return operation result
     * @see CollectionManager#searchById(Integer)
     * @see CollectionManager#getCollection()
     */
    public boolean removeElementById(Integer id){
        SpaceMarine spaceMarine = searchById(id);
        if (spaceMarine != null){
            this.getCollection().remove(spaceMarine);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Collection clearing {@link CollectionManager#stack}
     */
    public void clearCollection(){
        stack.clear();
    }

    /**
     * Collection {@link CollectionManager#stack} saving in file
     * @param file file for collection saving
     * @throws IOException Input/Output exception
     * @see CollectionManager#getCollection()
     * @see CollectionManager#setLastSave(LocalDateTime)
     */
    public void saveCollection(File file) throws IOException {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(javaTimeModule);
        mapper.writeValue(file, this.getCollection());
        this.setLastSave(LocalDateTime.now());
    }

    /**
     * Collection {@link CollectionManager#stack} sorting in reverse order
     * @see CollectionManager#getCollection()
     * @see CollectionManager#setCollection(Stack)
     */
    public void reorderCollection(){
        ArrayList<SpaceMarine> arrayList = new ArrayList<>(this.getCollection());
        Collections.reverse(arrayList);
        Stack<SpaceMarine> newStack = new Stack<>();
        newStack.addAll(arrayList);
        this.setCollection(newStack);
    }

    /**
     * Searching for the collection elements with type {@link SpaceMarine}, with achievements fields starts with the certain substring
     * @param achievement substring for searching
     * @return suitable SpaceMarines
     * @see CollectionManager#getCollection()
     */
    public ArrayList<SpaceMarine> startsWithAchievements(String achievement){
        ArrayList<SpaceMarine> spaceMarines = new ArrayList<>();
        for (SpaceMarine sm : this.getCollection()){
            if(sm.getAchievements().indexOf(achievement) == 0){
                spaceMarines.add(sm);
            }
        }
        return spaceMarines;
    }

    /**
     * Printing command for field weaponType of all collection elements, with types {@link SpaceMarine}, in ascending order
     * @see WeaponTypeComparator
     * @see CollectionManager#printWeaponTypes(Stack)
     * @see CollectionManager#getCollection()
     */
    public void ascendWeaponType(){
        WeaponTypeComparator cmp = new WeaponTypeComparator();
        ArrayList<SpaceMarine> list = new ArrayList<>(this.getCollection());
        list.sort(cmp);
        Stack<SpaceMarine> newStack = new Stack<>();
        newStack.addAll(list);
        printWeaponTypes(newStack);
    }

    /**
     * Printing weaponType fields of collection {@link CollectionManager#stack} elements with types {@link SpaceMarine}
     * @param stack collection
     * @see SpaceMarine#getWeaponType()
     */
    public void printWeaponTypes(Stack <SpaceMarine> stack){
        for(SpaceMarine sm : stack){
            if(sm.getWeaponType() == null){
                System.out.println("null");
            }
            else{
                System.out.println(sm.getWeaponType().toString());
            }
        }
    }
    /**
     * Printing command for field achievements of all collection elements, with types {@link SpaceMarine}, in descending order
     * @see AchievementsComparator
     * @see CollectionManager#printAchievements(Stack)
     * @see CollectionManager#getCollection()
     */
    public void descendAchievements(){
        AchievementsComparator cmp = new AchievementsComparator();
        ArrayList<SpaceMarine> list = new ArrayList<>(this.getCollection());
        list.sort(cmp);
        Stack<SpaceMarine> newStack = new Stack<>();
        newStack.addAll(list);
        printAchievements(newStack);
    }
    /**
     * Printing achievements fields of collection {@link CollectionManager#stack} elements with types {@link SpaceMarine}
     * @param stack collection
     */
    public void printAchievements(Stack<SpaceMarine> stack){
        for(SpaceMarine sm : stack){
            System.out.println(sm.getAchievements());
        }
    }

    /**
     * Removing for collection {@link CollectionManager#stack} elements with type {@link SpaceMarine}, bigger than assigned element
     * @param spaceMarine assigned element
     * @return operation result
     * @see SpaceMarine#setId(Integer)
     * @see SpaceMarine#setCreationDate(LocalDateTime)
     * @see CollectionManager#getCollection()
     * @see SpaceMarine#getName()
     * @see SpaceMarine#getId()
     * @see CollectionManager#removeElementById(Integer)
     */
    public boolean removeGreater(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        ArrayList<SpaceMarine> spaceMarines = new ArrayList<>(this.getCollection());
        if(!spaceMarines.contains(spaceMarine)){
            spaceMarines.add(spaceMarine);
        }
        Collections.sort(spaceMarines);
        boolean flag = false;
        boolean checkpoint = false;
        for (SpaceMarine sm : spaceMarines){
            if (sm.getName().length() == spaceMarine.getName().length()){
                flag = true;
            }
            else if (flag){
                checkpoint = true;
                removeElementById(sm.getId());
            }
        }
        return checkpoint;
    }
    /**
     * Removing for collection {@link CollectionManager#stack} elements with type {@link SpaceMarine}, lower than assigned element
     * @param spaceMarine assigned element
     * @return operation result
     * @see SpaceMarine#setId(Integer)
     * @see SpaceMarine#setCreationDate(LocalDateTime)
     * @see CollectionManager#getCollection()
     * @see SpaceMarine#getName()
     * @see SpaceMarine#getId()
     * @see CollectionManager#removeElementById(Integer)
     */
    public boolean removeLower(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        ArrayList<SpaceMarine> spaceMarines = new ArrayList<>(this.getCollection());
        if(!spaceMarines.contains(spaceMarine)){
            spaceMarines.add(spaceMarine);
        }
        Collections.sort(spaceMarines);
        boolean flag = true;
        boolean checkpoint = false;
        for (SpaceMarine sm : spaceMarines){
            if (sm.getName().length() == spaceMarine.getName().length()){
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
