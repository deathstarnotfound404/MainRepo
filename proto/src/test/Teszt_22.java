package test;

import model.*;

import java.util.*;

public class Teszt_22 extends BaseTest{

    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    //growFonal -t1 -t2 -g0 -gsz0

    private List<Tekton> tektonList = new ArrayList<>();
    private List<Gombasz> gombaszList = new ArrayList<>();
    private List<Gomba>  gombaList = new ArrayList<>();

    @Override
    protected void inicializalas() {
        Gombasz g = new Gombasz("TesztGombasz");
        //Rovarasz r = new Rovarasz("TesztRovarasz");
        gombaszList.add(g);

        field.addGombasz(g);
        //field.addRovarasz(r);

        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new FonalGatloHatas());
        Tekton t3 = new Tekton(new TektonHatas());
        tektonList.add(t1);
        tektonList.add(t2);
        tektonList.add(t3);

        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);
        t3.getTektonHatas().setTekton(t3);

        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }
        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }
        if(!t3.getTektonHatas().isHatasEsemenyfuggo()){
            t3.hatasKifejtes();
        }

        field.addTekton(t1);
        field.addTekton(t2);
        field.addTekton(t3);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));
        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(2));

        Gomba gomba = new Gomba(t1, g, 0);
        g.addGomba(gomba);
        gombaList.add(gomba);

        GombaFonal gf = new GombaFonal(gomba, t1, t3);

        gomba.addFonal(gf);

        //Tekton init
        t1.addKapcsolodoFonalak(gf);
        t3.addKapcsolodoFonalak(gf);

        elvartParancsok.add("growFonal -t1 -t2 -g0 -gsz0");
        elvartParancsok.add("exit");
    }

    @Override
    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "growFonal":
                int tektonNum1 = Integer.parseInt(parts[1].substring(2)); // "-t1" -> 1
                int tektonNum2 = Integer.parseInt(parts[2].substring(2)); // "-t2" -> 2
                int gombaNum = Integer.parseInt(parts[3].substring(2));   // "-g0" -> 0
                int gombaszNum = Integer.parseInt(parts[4].substring(4)); // "-gsz0" -> 0

                // Kikeressük az objektumokat
                Tekton startTekton = tektonList.get(tektonNum1);
                Tekton celTekton = tektonList.get(tektonNum2);
                Gomba gomba = gombaList.get(gombaNum);
                Gombasz gombasz = gombaszList.get(gombaszNum);

                if (gomba != null && celTekton != null && gombasz != null) {
                    if (gombasz.gombafonalIranyitas(gomba, startTekton, celTekton, false)) {
                        log.append("Incomplete: Mycellium growth failed.\n" + "Tekton: t1 -> t2").append(gombaNum).append(" → Target: t2").append(tektonNum2).append("\n").append("Managed by: gsz").append(gombaszNum).append("\n\n");
                        String ret = celTekton.hatasKifejtes();
                        if(ret == "FonalGatlo"){
                            log.append("Success: Tekton hatás\nFonalFelszivoHatas: Gátló\n");
                        } else if(ret == "NincsFonalGatlo"){
                            log.append("Incomplete: Tekton hatás\nFonalGatloHatas\n");
                        }

                        List<Tekton> modositando = new ArrayList<>(Field.getTektonList());
                        for (Tekton t : modositando) {
                            if (t.tektonTores()){
                                log.append("Success: Tekton kettétörés\nTekton: ").append(tektonNum1).append("\n");
                            }
                        }
                    } else {
                        log.append("Incomplete: Fonal growth incomplete.\n");
                        log.append("Success: Tekton hatás\nFonalFelszivoHatas: Gátló\n");
                    }
                } else {
                    log.append("[HIBA] Nem található gomba/tekton/gombász azonosító!\n");
                }
                break;

            case "exit":
                break;

            default:
                log.append("Teszt: Ismeretlen parancs: ").append(parancs).append("\n");
                break;
        }
    }

    @Override
    public void runTest() {
        log.append("[Test_22] - Tekton Hatás - Fonal Gátló Hatás\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_22 - Bemenet]\n\t> " + kovetkezoElvart);

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

    @Override
    public String getResult() { return log.toString(); }

    private void tesztWriteOut() {
        log.append("[Test_22] - Teszt vége\n");
        saveTestResult(log, "22");
    }
}
