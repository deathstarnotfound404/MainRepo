package FungoriumClasses;

import java.util.*;

public class Gombafonal implements IDestroyable {
    private Gomba AlapGomba;
    private Tekton startTekton;
    private Tekton celTekton;

    public Gombafonal(Tekton startTekton, Tekton celTekton) {
        this.startTekton = startTekton;
        this.celTekton = celTekton;
    }

    public void setAlapGomba(Gomba AlapGomba) {
        this.AlapGomba = AlapGomba;
    }

    public Gomba getAlapGomba() {
        return AlapGomba;
    }

    public Tekton getStartTekton() {
        return startTekton;
    }

    public Tekton getCelTekton() {
        return celTekton;
    }

    private void switchStartCel() {
        Tekton uj_start = this.celTekton;
        Tekton uj_cel = this.startTekton;
        this.celTekton = uj_cel;
        this.startTekton = uj_start;
    }

    public boolean connectedToAlapGomba() {
        List<List<Gombafonal>> listaLista = AlapGomba.getFonalLista();
        Tekton alapTekton = AlapGomba.getTekton();
        for(List<Gombafonal> l : listaLista){
            if(l.contains(this)) {
                List<Gombafonal> reversedList = new ArrayList<Gombafonal>(l);
                Collections.reverse(reversedList);
                //Teljes revese művelet
                int indexOfThis = reversedList.indexOf(this);
                for(Gombafonal f : reversedList){
                    this.switchStartCel();
                }
                //Reverse vége
                List<Gombafonal> subList = reversedList.subList(indexOfThis, reversedList.size());
                for(Gombafonal f : subList){
                    Gombafonal next;
                    if(f != subList.get(subList.size()-1)) {
                        next = subList.get(subList.indexOf(f)+1);
                    } else{ //ha az utolsó
                        if(f.getAlapGomba().getTekton() == alapTekton)
                            return true;
                        else return false;
                    }

                    if(f.celTekton != next.startTekton) { //összehasonlítása a start és cél Tektonoknak
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void elpusztul() {
    }
}
