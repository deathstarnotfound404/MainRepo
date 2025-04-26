package test;

import model.*;

import java.util.*;

public class Teszt_21 extends BaseTest{
    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    //moveRovar -r0 -t1 -rsz0

    private List<Tekton> tektonList = new ArrayList<>();
    private List<Rovarasz> rovaraszList = new ArrayList<>();
    private List<Rovar> rovarList = new ArrayList<>();


    @Override
    protected void inicializalas() {
        Gombasz g = new Gombasz("TesztGombasz");
        Rovarasz r = new Rovarasz("TesztRovarasz");
        rovaraszList.add(r);

        field.addGombasz(g);
        field.addRovarasz(r);

        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new FonalFelszivodoHatas());
        tektonList.add(t1);
        tektonList.add(t2);

        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);

        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }
        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }

        field.addTekton(t1);
        field.addTekton(t2);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));

        Gomba gomba = new Gomba(t1, g, 0);
        g.addGomba(gomba);

        Rovar rovar = new Rovar();
        rovarList.add(rovar);
        rovar.setRovarasz(r);
        r.addRovar(rovar, t1);
        rovar.setHelyzet(t1);
        t1.setRovar(rovar);

        GombaFonal gf = new GombaFonal(gomba, t1, t2);

        gomba.addFonal(gf);

        //Tekton init
        t2.setRovarLatogatottsag(5);
        t1.addKapcsolodoFonalak(gf);
        t2.addKapcsolodoFonalak(gf);

        elvartParancsok.add("moveRovar -r0 -t1 -rsz0");
        elvartParancsok.add("exit");
    }

    @Override
    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "moveRovar":
                int rovarNum = Integer.parseInt(parts[1].substring(2)); // "-r1" -> 1
                int tektonNum = Integer.parseInt(parts[2].substring(2)); // "-t1" -> 1
                int rovaraszNum = Integer.parseInt(parts[3].substring(4)); // "-rsz1" -> 1

                // Kikeressük az objektumokat
                Rovar rovar = rovarList.get(rovarNum);
                Tekton celTekton = tektonList.get(tektonNum);
                Rovarasz rovarasz = rovaraszList.get(rovaraszNum);

                if (rovar != null && celTekton != null && rovarasz != null) {
                    if (rovarasz.rovarIranyitas(rovar, celTekton)) {
                        log.append("Success: Rovar movement completed.\n" + "Rovar: r").append(rovarNum).append(" → Target: t").append(tektonNum).append("\n").append("Managed by: rsz").append(rovaraszNum).append("\n\n");
                        rovar.sporaEves();
                        String ret = celTekton.hatasKifejtes();
                        if(ret == "Felszivas"){
                            log.append("Success: Tekton hatás\nFonalFelszivoHatas: Felszívás\n");
                        } else if(ret == "NincsFonalFelszivas"){
                            log.append("Incomplete: Tekton hatás\nFonalFelszivoHatas\n");
                        }

                        List<Tekton> modositando = new ArrayList<>(Field.getTektonList());
                        for (Tekton t : modositando) {
                            if (t.tektonTores()){
                                log.append("Success: Tekton kettétörés\nTekton: ").append(tektonNum).append("\n");
                            }
                        }
                    } else {
                        log.append("Error: Rovar movement incomplete.\n");
                    }
                } else {
                    log.append("[HIBA] Nem található rovar/tekton/rovarász azonosító!\n");
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
        log.append("[Test_21] - Tekton Hatás - Fonal Felszívó Hatás\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_21 - Bemenet]\n\t> " + kovetkezoElvart);

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
    public String getResult() {
        return log.toString();
    }

    private void tesztWriteOut() {
        log.append("[Test_21] - Teszt vége\n");
        saveTestResult(log, "21");
    }
}
