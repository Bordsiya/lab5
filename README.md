# lab5
ITMO, 1 course

Compilation on Helios
```
javac -d bin -cp "src:lib/jackson-annotations-2.12.1.jar:lib/jackson-core-2.12.1.jar:lib/jackson-databind-2.12.1.jar:lib/jackson-datatype-jsr310-2.12.1.jar" src/programm/Main.java
cp -R lib/ bin/
jar cfm MyJar.jar mf.txt ./commands ./comparators ./data ./utils ./programm ./exceptions
cp MyJar.jar ../
rm MyJar.jar
java -jar MyJar.jar
```
