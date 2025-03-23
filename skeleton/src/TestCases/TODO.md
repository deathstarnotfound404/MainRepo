# Skeleton Implementálása
### Skeleton szekvencia tesztesetek

- [x] ~~1-Játék Indítás Teszt - Tekton inicializálás~~
- [x] ~~2-Játék Indítás Teszt - Rovarász inicializálás~~
- [x] ~~3: Játék Indítás Teszt - Gombász inicializálás~~
- [x] ~~6: Gombatest Szintlépés Teszt~~
- [x] ~~9: Rovar Képességek alaphelyzetbe állítása teszt~~
- [x] ~~10: Tekton Hatás Kifejtés: TektonHatas teszt~~
- [x] ~~11: Tekton Hatás Kifejtés: FonalFelszivodoHatas teszt~~
- [x] ~~13: Tekton Hatás Kifejtés: FonalGatloHatas teszt~~
- [x] ~~14: Tekton Hatás Kifejtés: GombatestGatloHatas teszt~~
- [x] ~~15: Tekton Kettétörés Teszt - van Rovar a tektonon~~
- [x] ~~16: Tekton Kettétörés Teszt - nincs Rovar a tektonon~~
- [x] ~~17: Gomba Spóra Termelés Teszt~~ 
- [x] ~~18: Spóra Elfogyasztás - GyorsitoSpora teszt~~
- [x] ~~19: Spóra Elfogyasztás - LassitoSpora teszt~~
- ~~MEGJEGYZÉS: setEvesHatekonsag-nak ne int legyen a paramétere, mert nem lehet neki 0.25-öt adni --> LassitoSpora osztalyban még meg kell írni a maradékot~~
- [x] ~~20: Spóra Elfogyasztás - BenitoSpora teszt~~
- [x] ~~21: Spóra Elfogyasztás - VagastGatloSpora teszt~~
- [x] ~~22: Spóra Elfogyasztás - Spora teszt~~
- [x] ~~23: Játék Kiértékelés Teszt~~
- [x] ~~24: Gombafonal Elvágás Teszt~~
- [x] ~~25: Gombafonal Folytonosság Megszakadása Teszt~~ 
- [x] ~~26: Rovar Irány Megadás Teszt~~
- [x] ~~27: Rovar Irány Megadás Teszt - Fonallal nem összekötött tektonok~~
  - ~~MEGJEGYZÉS~~: Ha 2x futtatjuk egymás után, akkor másodjára hibás, elméletileg itt javítottam a reset()-el, de ezt szebben kéne megoldani ha lesz időnk.
- [x] ~~28: Rovar Irány Megadás Teszt - Cél tektonon van Rovar~~
- [x] ~~29: Spóra Szórás Teszt~~
- [x] ~~30: Spóra Szórás Teszt - Nem szomszédos Tektonok~~
- [x] ~~31: Spóra Szórás Teszt - Nincs elég spórakészlet~~
- [x] ~~32: Spóra Szórás Teszt - Cél Tektonon már van GombaTest~~
  ~~- MEGJEGYZÉS2: A Tekton.VanGombaTestATektonon boolean nem biztos h. jó megoldás, mert a setGomba() állítja, de egy Gomba gt = null GombaTest-tel elronthat sokmindent.~~
- [x] ~~33: Gombafonal Irányítás Teszt - Cél Tektonon van Spóra~~
- [x] ~~34: Gombafonal Irányítás Teszt - Fonal nem lerakható~~
- [x] ~~36: Gombatest Növesztés~~
- [x] ~~37: Fonal Vásárlás Teszt~~
- [x] ~~38: Gombafonal Folytonosság Megszakadása Teszt - Nincs megszakadás~~


### Feladatok
- [x] ~~Tesztek implementációja~~
- [x] ~~Tesztek JavaDoc dokumentációja~~
- [ ] Run config update windows virtual host-ra
- [ ] Új diagrammok felvétele, amik pixelesek voltak