package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

public class AscendingWeaponTypeCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;

    public AscendingWeaponTypeCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.ascendingWeaponType();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(collectionManager.collectionSize() == 0){
                throw new EmptyCollectionException("Коллекция пуста");
            }
            collectionManager.ascendWeaponType();
            return true;
        }
        catch (EmptyCollectionException | WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
