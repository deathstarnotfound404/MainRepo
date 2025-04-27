package test;

import model.*;

import java.util.*;

public class Teszt_27 extends BaseTest{
    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    private List<Tekton> tektonList = new ArrayList<>();
    private List<Rovarasz> rovaraszList = new ArrayList<>();
    private List<Rovar>  rovarList = new ArrayList<>();

    protected void inicializalas() {
        Gombasz g = new Gombasz("TesztGombasz");
        Rovarasz r = new Rovarasz("TesztRovarasz");
        rovaraszList.add(r);

        field.addGombasz(g);
        field.addRovarasz(r);

        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new TektonHatas());
        Tekton t3 = new Tekton(new TektonHatas());
        Tekton t4 = new Tekton(new TektonHatas());
        Tekton t5 = new Tekton(new FonalDefenderHatas());
        tektonList.add(t1);
        tektonList.add(t2);
        tektonList.add(t3);
        tektonList.add(t4);
        tektonList.add(t5);

        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);
        t3.getTektonHatas().setTekton(t3);
        t4.getTektonHatas().setTekton(t4);
        t5.getTektonHatas().setTekton(t5);

        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }
        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }
        if (!t3.getTektonHatas().isHatasEsemenyfuggo()) {
            t3.hatasKifejtes();
        }
        if (!t4.getTektonHatas().isHatasEsemenyfuggo()){
            t4.hatasKifejtes();
        }
        if (!t5.getTektonHatas().isHatasEsemenyfuggo()){
            t5.hatasKifejtes();
        }

        field.addTekton(t1);
        field.addTekton(t2);
        field.addTekton(t3);
        field.addTekton(t4);
        field.addTekton(t5);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));
        Tekton.connectSzomszedok(Field.getTektonList().get(1), Field.getTektonList().get(2));
        Tekton.connectSzomszedok(Field.getTektonList().get(2), Field.getTektonList().get(3));
        Tekton.connectSzomszedok(Field.getTektonList().get(3), Field.getTektonList().get(4));

        Gomba gomba = new Gomba(t1, g, 0);
        g.addGomba(gomba);

        Rovar rovar = new Rovar();
        rovarList.add(rovar);
        rovar.setRovarasz(r);
        r.addRovar(rovar, t2);
        rovar.setHelyzet(t2);
        t2.setRovar(rovar);

        GombaFonal gf1 = new GombaFonal(gomba, t1, t2);
        GombaFonal gf2 = new GombaFonal(gomba, t2, t3);
        GombaFonal gf3 = new GombaFonal(gomba, t3, t4);
        GombaFonal gf4 = new GombaFonal(gomba, t4, t5);

        gomba.addFonal(gf1);
        gomba.addFonal(gf2);
        gomba.addFonal(gf3);
        gomba.addFonal(gf4);

        g.fonalVasarlas(gomba);
        g.fonalVasarlas(gomba);

        t1.addKapcsolodoFonalak(gf1);
        t2.addKapcsolodoFonalak(gf1);
        t2.addKapcsolodoFonalak(gf2);
        t3.addKapcsolodoFonalak(gf2);
        t3.addKapcsolodoFonalak(gf3);
        t4.addKapcsolodoFonalak(gf3);
        t4.addKapcsolodoFonalak(gf4);
        t5.addKapcsolodoFonalak(gf4);

        elvartParancsok.add("cutFonal -r0 -t1");
        elvartParancsok.add("exit");
    }

    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "cutFonal":
                int rovarNum = Integer.parseInt(parts[1].substring(2)); // "-r0" -> 0
                int tektonNum = Integer.parseInt(parts[2].substring(2));

                Rovar rovar = rovarList.get(rovarNum);
                Tekton kijeloltTekton = tektonList.get(tektonNum);

                //Gombafonal kivétele a modellből
                GombaFonal kivalaztottFonal = null;
                for(GombaFonal gf : rovar.getHelyzet().getKapcsolodoFonalak()) {
                    for(GombaFonal gf_2 : kijeloltTekton.getKapcsolodoFonalak()) {
                        if (gf.getID() == gf_2.getID()) {
                            //Van köztes fonal
                            kivalaztottFonal = gf;
                        }
                    }
                }

                if (rovar != null && kijeloltTekton != null && kivalaztottFonal != null) {
                    if(rovaraszList.get(0).fonalVagas(rovar, kivalaztottFonal)) {
                        log.append(rovaraszList.get(0).getName() + ": Sikeres fonalvágás a " + kijeloltTekton.getId() + ". tektonon.\n");
                    } else {
                        log.append(rovaraszList.get(0).getName() + ": Sikertelen fonalvágás.\n");
                    }
                }

            case "exit":
                break;

            default:
                log.append("Teszt: Ismeretlen parancs: ").append(parancs).append("\n");
                break;
        }
    }

    public void runTest() {
        log.append("[Test_27] - Elszakadt Gombafonal Elpusztul\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_27 - Bemenet]\n\t> " + kovetkezoElvart);

            System.out.print("[Teszt parancs] > ");
            String parancs = scanner.nextLine().trim();

            if (parancs.equalsIgnoreCase(kovetkezoElvart)) {
                elvartParancsok.poll();  // Eltávolítjuk a sorból
                parancsFeldolgozas(parancs);
            } else {
                log.append("[HIBA] Hibás parancs: ").append(parancs).append(" (várt: ").append(kovetkezoElvart).append(")\n");
                System.out.println("Nem megfelelő parancs ehhez a teszthez. A helyes parancsot add meg.");
            }
        }
        log.append("------------------------------------------------------------------------------\n\nCél állapot\n");
        log.append(field.printGameState());
        tesztWriteOut();
    }

    private void tesztWriteOut() {
        log.append("[Test_27] - Teszt vége\n");
        saveTestResult(log, "27");
    }

    @Override
    public String getResult() { return log.toString(); }
}
