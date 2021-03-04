package commands;

import exceptions.NullLastSaveException;
import exceptions.WrongAmountOfElementsInCommandException;
import utils.CollectionManager;
import java.time.format.DateTimeFormatter;

public class InfoCommand implements ICommand{

    private CommandBase commandBase;
    private CollectionManager collectionManager;

    public InfoCommand(CommandBase commandBase, CollectionManager collectionManager){
        this.commandBase = commandBase;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String str) {
        try{
            commandBase.info();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            System.out.println("Размер коллекции: " + collectionManager.collectionSize());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            try{
                if (collectionManager.getLastSave() == null){
                    throw new NullLastSaveException("Данная коллекция еще не сохранялась");
                }
                System.out.println("Дата последнего сохранения: " + collectionManager.getLastSave().format(formatter));
            }
            catch (NullLastSaveException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Дата инициализации: " + collectionManager.getLastInit().format(formatter));
            return true;
        }
        catch (WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
