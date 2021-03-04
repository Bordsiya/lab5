package commands;

import exceptions.WrongAmountOfElementsInCommandException;

public class HelpCommand implements ICommand{

    private CommandBase commandBase;

    public HelpCommand(CommandBase commandBase){
        this.commandBase = commandBase;
    }


    @Override
    public boolean execute(String str) {
        try{
            commandBase.help();
            if(str.length() != 0){
                throw new WrongAmountOfElementsInCommandException("Неправильное количество аргументов для команды");
            }
            System.out.println("Программа поддерживает выполнение следующих команд:\n" +
                    "help - вывести справку по доступным командам\n" +
                    "info - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "add {element} - добавить новый элемент в коллекцию\n" +
                    "update id {element} - обновить значение элемента коллекции, id которого равен заданному\n" +
                    "remove_by_id id - удалить элемент из коллекции по его id\n" +
                    "clear - очистить коллекцию\n" +
                    "save - сохранить коллекцию в файл\n" +
                    "execute_script file_name - считать и исполнить скрипт из указанного файла\n" +
                    "exit - завершить программу (без сохранения в файл)\n" +
                    "remove_greater {element} - удалить из коллекции все элементы, превышающие заданный\n" +
                    "remove_lower {element} - удалить из коллекции все элементы, меньшие, чем заданный\n" +
                    "reorder - отсортировать коллекцию в порядке, обратном нынешнему\n" +
                    "filter_starts_with_achievements achievements - вывести элементы, значение поля achievements которых начинается с заданной подстроки\n" +
                    "print_field_ascending_weapon_type - вывести значения поля weaponType всех элементов в порядке возрастания\n" +
                    "print_field_descending_achievements - вывести значения поля achievements всех элементов в порядке убывания");
            return true;
        }
        catch (WrongAmountOfElementsInCommandException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
