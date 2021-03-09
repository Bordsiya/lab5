# lab5
ITMO, 1 course

Path to file SpaceShip.json is in environment variable VAR:  
env  
export VAR='...'  

We can also add to .kshrc:  
export VAR='...'  


Compilation on Helios:  
javac -d bin -cp "src:lib/jackson-annotations-2.12.1.jar:lib/jackson-core-2.12.1.jar:lib/jackson-databind-2.12.1.jar:lib/jackson-datatype-jsr310-2.12.1.jar" src/Main.jar  
cp -R lib/ bin/  
jar cfm MyJar.jar mf.txt ./commands ./comparators ./data ./exceptions ./programm ./utils  
java -jar MyJar.jar  

