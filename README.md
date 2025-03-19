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
