package test;

import model.*;

import java.util.*;

public class Teszt_25 extends BaseTest{
    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    private List<Tekton> tektonList = new ArrayList<>();
    private List<Gombasz> gombaszList = new ArrayList<>();
    private List<Rovarasz> rovaraszList = new ArrayList<>();
    private List<Rovar>  rovarList = new ArrayList<>();

    @Override
    protected void inicializalas() {
        Gombasz g = new Gombasz("TesztGombasz");
        gombaszList.add(g);

        Rovarasz r = new Rovarasz("TesztRovarasz");
        rovaraszList.add(r);

        field.addGombasz(g);
        field.addRovarasz(r);

        Tekton t0 = new Tekton(new TektonHatas());
        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new TektonHatas());
        Tekton t3 = new Tekton(new TektonHatas());

        tektonList.add(t0);
        tektonList.add(t1);
        tektonList.add(t2);
        tektonList.add(t3);

        t0.getTektonHatas().setTekton(t0);
        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);
        t3.getTektonHatas().setTekton(t3);

        for(int i = 0; i < 4; ++i) {
            if (!tektonList.get(i).getTektonHatas().isHatasEsemenyfuggo()) {
                tektonList.get(i).hatasKifejtes();
            }
        }

        field.addTekton(t0);
        field.addTekton(t1);
        field.addTekton(t2);
        field.addTekton(t3);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));
        Tekton.connectSzomszedok(Field.getTektonList().get(1), Field.getTektonList().get(2));
        Tekton.connectSzomszedok(Field.getTektonList().get(2), Field.getTektonList().get(3));

        Gomba g1 = new Gomba(t0, g, 6);
        Gomba g2 = new Gomba(t2, g, 6);

        g.addGomba(g1);
        g.addGomba(g2);

        GombaFonal gf0 = new GombaFonal(g1, t0, t1);
        g1.addFonal(gf0);

        GombaFonal gf1 = new GombaFonal(g2, t2, t3);
        g2.addFonal(gf1);

        Rovar rovar = new Rovar();
        rovarList.add(rovar);
        rovar.setRovarasz(r);
        r.addRovar(rovar, t0);
        rovar.setHelyzet(t0);
        t0.setRovar(rovar);

        t0.addKapcsolodoFonalak(gf0);
        t1.addKapcsolodoFonalak(gf0);
        t2.addKapcsolodoFonalak(gf1);
        t3.addKapcsolodoFonalak(gf1);

        elvartParancsok.add("cutFonal -r0 -t1");
        elvartParancsok.add("cutFonal -r0 -t3");
        elvartParancsok.add("exit");
    }

    @Override
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

                if (rovar != null && kijeloltTekton != null) {
                    if(rovaraszList.get(0).fonalVagas(rovar, kivalaztottFonal)) {
                        log.append("Success: GombaFonal cutting completed.\n").append("Rovar: r").append(rovarNum).append("\n\n");
                    } else {
                        log.append("Incomplete: GombaFonal cutting not completed.\n\n");
                    }
                }

            case "exit":
                break;

            default:
                log.append("Teszt: Ismeretlen parancs: ").append(parancs).append("\n");
                break;
        }
    }

    @Override
    public void runTest() {
        log.append("[Test_25] - Fonal Elvágás\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_25 - Bemenet]\n\t> " + kovetkezoElvart);

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
        log.append("[Test_25] - Teszt vége\n");
        saveTestResult(log, "25");
    }

    @Override
    public String getResult() {
        return log.toString();
    }
}
