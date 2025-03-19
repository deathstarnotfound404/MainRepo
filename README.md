# MainRepo

Ez a csapat fő repo-ja, ebben lesz tárolva a projektünk.
Ez most csak egy teszt, megnézni működik-e az integráció.

### Skeleton tesztprogram
- A program célja a publikus hívási lánc ellenőrzése a projekt üzleti modelljében.

A program fordítása és futtatásához a skeleton mappából az alábbi parancsokat kell kiadni:

```Windows```
```shell
mkdir bin
javac src\FungoriumClasses\*.java -d bin
javac -cp bin src\TestCases\*.java -d bin
javac -cp bin src\Skeleton.java -d bin
java -cp bin Skeleton
```

```Linux```
```shell
javac src/FungoriumClasses/*.java -d bin
javac -cp bin src/TestCases/*.java -d bin
javac -cp bin src/Skeleton.java -d bin
java -cp bin Skeleton
```
