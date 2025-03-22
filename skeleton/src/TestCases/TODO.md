# Skeleton Implementálása
### Skeleton szekvencia tesztesetek

- [x] ~~1-Játék Indítás Teszt - Tekton inicializálás~~
- [x] ~~2-Játék Indítás Teszt - Rovarász inicializálás~~
- [x] ~~3: Játék Indítás Teszt - Gombász inicializálás~~
- [x] ~~6: Gombatest Szintlépés Teszt~~
- [x] ~~9: Rovar Képességek alaphelyzetbe állítása teszt~~
- [x] ~~10: Tekton Hatás Kifejtés: TektonHatas teszt~~
- [x] ~~11: Tekton Hatás Kifejtés: FonalFelszivodoHatas teszt~~
- [ ] 13: Tekton Hatás Kifejtés: FonalGatloHatas teszt 🚨
- [ ] 14: Tekton Hatás Kifejtés: GombatestGatloHatas teszt 🚨
- [x] ~~15: Tekton Kettétörés Teszt - van Rovar a tektonon~~
- [x] ~~16: Tekton Kettétörés Teszt - nincs Rovar a tektonon~~
- [x] ~~17: Gomba Spóra Termelés Teszt~~ 
- MEGJEGYZÉS: nézzük majd meg, hogy kell-e bele decision
- [x] ~~18: Spóra Elfogyasztás - GyorsitoSpora teszt~~
- [x] ~~19: Spóra Elfogyasztás - LassitoSpora teszt~~
- MEGJEGYZÉS: setEvesHatekonsag-nak ne int legyen a paramétere, mert nem lehet neki 0.25-öt adni --> LassitoSpora osztalyban még meg kell írni a maradékot
- [x] ~~20: Spóra Elfogyasztás - BenitoSpora teszt~~
- [x] ~~21: Spóra Elfogyasztás - VagastGatloSpora teszt~~
- MEGJEGYZÉS: nem kéne minden tulajdonságát állítani a rovarnak?
- [x] ~~22: Spóra Elfogyasztás - Spora teszt~~
- [x] ~~23: Játék Kiértékelés Teszt~~
- [x] ~~24: Gombafonal Elvágás Teszt~~
- [ ] 25: Gombafonal Folytonosság Megszakadása Teszt  🚨
- [x] ~~26: Rovar Irány Megadás Teszt~~
  - MEGJEGYZÉS: Változtatás a getSzomszedosTektonok() hivasban.
- [x] ~~27: Rovar Irány Megadás Teszt - Fonallal nem összekötött tektonok~~
  - MEGJEGYZÉS: Ha 2x futtatjuk egymás után, akkor másodjára hibás, elméletileg itt javítottam a reset()-el, de ezt szebben kéne megoldani ha lesz időnk.
- [x] ~~28: Rovar Irány Megadás Teszt - Cél tektonon van Rovar~~
- [x] ~~29: Spóra Szórás Teszt~~
  - MEGJEGYZÉS: Változás a szekvencia diagrammhoz képest
- [x] ~~30: Spóra Szórás Teszt - Nem szomszédos Tektonok~~
- [x] ~~31: Spóra Szórás Teszt - Nincs elég spórakészlet~~
- [x] ~~32: Spóra Szórás Teszt - Cél Tektonon már van GombaTest~~
  - MEGJEGYZÉS: Itt nem volt jó a szekvenciadiagramm, de a programban javítottam, valamint nem volt felvéve a decrease menet közben amikor megtörtént a szórás. Mind a 4 spóraszórás teszt együttesen is jól működik.
  - MEGJEGYZÉS2: A Tekton.VanGombaTestATektonon boolean nem biztos h. jó megoldás, mert a setGomba() állítja, de egy Gomba gt = null GombaTest-tel elronthat sokmindent.
- [ ] 33: Gombafonal Irányítás Teszt - Cél Tektonon van Spóra  🚨
  - MEGJEGYZÉS: nem t1 - t2 között, hanem f1, és t között kellene húzni a fonalat -> így a list[list] is bővíthető
- [ ] 34: Gombafonal Irányítás Teszt - Fonal nem lerakható  🚨
  - MEGJEGYZÉS: nem t1 - t2 között, hanem f1, és t között kellene húzni a fonalat -> így a list[list] is bővíthető
- [x] 36: ~~Gombatest Növesztés~~
- [x] 37: ~~Fonal Vásárlás Teszt~~
- [ ] 38: Gombafonal Folytonosság Megszakadása Teszt - Nincs megszakadás  🚨


### Feladatok
- [ ] Tesztek implementációja
- [ ] Tesztek JavaDoc dokumentációja
- [ ] Run config update windows virtual host-ra
- [ ] Új diagrammok felvétele, amik pixelesek voltak