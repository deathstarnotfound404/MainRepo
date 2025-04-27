# MainRepo

Ez a csapat fő repo-ja, ebben lesz tárolva a projektünk.
Ez most csak egy teszt, megnézni működik-e az integráció.

## A csapat tagjai:
- Bekő Máté
- Botos Dániel
- Czene Zsombor
- Kozma Szabolcs
- Pünkösti Györk

### Skeleton tesztprogram
- A program célja a publikus hívási lánc ellenőrzése a projekt üzleti modelljében.

A program fordítása és futtatásához a skeleton mappából az alábbi parancsokat kell kiadni:

```Program letöltése```
```shell
git clone https://github.com/deathstarnotfound404/MainRepo.git
```

```Windows cmd```
```shell
mkdir bin
javac src\CallTracer\*.java -d bin
javac -cp bin src\FungoriumClasses\*.java -d bin
javac -cp bin src\TestCases\*.java -d bin
javac -cp bin src\Skeleton.java -d bin
java -cp bin Skeleton

```

```Linux```
```shell
javac src/CallTracer/*.java -d bin
javac -cp bin src/FungoriumClasses/*.java -d bin
javac -cp bin src/TestCases/*.java -d bin
javac -cp bin src/Skeleton.java -d bin
java -cp bin Skeleton
```

### Prototípus program
- A program célja az elkészített üzleti modell implementálásának tesztelése, és a játék parancssoros megvalósítása.
- A prototípus letöltéséhez a MainRepo proto branch-ét kell klónozni, vagy letölteni zippelve.
A program fordítása és futtatásához a root gyökérmappából kell kiadni az alábbi parancsokat kell kiadni:

```Windows cmd```
```shell
mkdir bin
javac proto\src\model*.java -d bin
javac -cp bin proto\src\model*.java -d bin
javac -cp bin proto\src\test*.java -d bin
javac -cp bin proto\src\proto*.java -d bin
java -cp bin proto.Main
```
