import utils.*;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        /*
        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader r = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        File file = new File("./SpaceShip.json");
        CollectionService collectionService = new CollectionService(file);

        String command = r.readLine();
        String [] commandArr = command.split(" ", 2);
        String beg = commandArr[1];
        String line = beg;
        String buf = line;
        while(!buf.equals("stop")) {
            buf = r.readLine();
            line += buf;
        }
        line = line.substring(0, line.length() - 4).replaceAll("\t+", "");
        System.out.println(line);


        ObjectMapper mapper = new ObjectMapper();
        try {
            data.SpaceMarine spaceMarine = mapper.readValue(line, data.SpaceMarine.class);
            System.out.println(spaceMarine);
            collectionService.addElement(spaceMarine);
        }
        catch (IOException e){
            e.printStackTrace();
        }



         */
        /*
        String achievement = "Спокойств";
        Stack<data.SpaceMarine> stack = new Stack<>();
        data.Chapter chapter = new data.Chapter();
        chapter.setName("Пролог");
        chapter.setWorld("ГП");
        data.Coordinates coordinates = new data.Coordinates();
        coordinates.setX(44);
        coordinates.setY(55.7);
        data.SpaceMarine spaceMarine = new data.SpaceMarine(1, "Марта", coordinates, 66.8F, "Спокойствие", data.Weapon.FLAMER, data.MeleeWeapon.POWER_BLADE, chapter);
        stack.push(spaceMarine);
        if(spaceMarine.getAchievements().indexOf(achievement) == 0){
            System.out.println(spaceMarine);
        }

         */
        //Collections.sort(stack);

        //for(data.SpaceMarine sm : stack){
            //System.out.println(sm);
        //}

        /*
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(javaTimeModule);
        String s = mapper.writeValueAsString(spaceMarine);
        System.out.println(s);
        data.SpaceMarine sp1 = mapper.readValue(s, data.SpaceMarine.class);
        System.out.println(sp1);

         */
    }
}
