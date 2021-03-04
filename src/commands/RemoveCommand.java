package commands;

import exceptions.ObjectDoesNotExistException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;

public class RemoveCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;

    public RemoveCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.remove();
            String [] commandArr = str.trim().split(" ");
            if(str.length() == 0 || commandArr.length != 1){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            if(!collectionManager.removeElementById(Integer.parseInt(commandArr[0]))){
                throw new ObjectDoesNotExistException("Объект не удален, его не существует");
            }
            System.out.println("Объект удален");
            return true;
        }
        catch (NumberFormatException e){
            System.out.println("Некорректный ID");
            return false;
        }
        catch (ObjectDoesNotExistException | WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
