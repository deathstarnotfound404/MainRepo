package FungoriumClasses;
import java.util.*;


/**
 * A {@code Gombafonal} osztály egy gombafonalat reprezentál, amely két {@code Tekton} között húzódik.
 * A fonalak kapcsolatot teremtenek a gombák között és biztosítják az összeköttetést a tektonok között.
 *
 * @author Botos Dániel, Kozma Szabolcs, Czene ZSombor
 * @version 1.0
 * @since 2025-03-18
 */
public class Gombafonal implements IDestroyable {
    private Gomba AlapGomba;
    private Tekton startTekton;
    private Tekton celTekton;

    /**
     * Létrehoz egy új {@code Gombafonal} objektumot a megadott kiindulási és cél {@code Tekton}-okkal.
     *
     * @param startTekton A fonal kezdőpontja.
     * @param celTekton A fonal végpontja.
     */
    public Gombafonal(Tekton startTekton, Tekton celTekton) {
        this.startTekton = startTekton;
        this.celTekton = celTekton;
    }

    /**
     * Beállítja az alap gombát, amelyhez a fonal tartozik.
     *
     * @param AlapGomba Az alap gomba.
     */
    public void setAlapGomba(Gomba AlapGomba) {
        this.AlapGomba = AlapGomba;
    }

    /**
     * Visszaadja az alap gombát, amelyhez ez a fonal tartozik.
     *
     * @return Az alap gomba.
     */
    public Gomba getAlapGomba() {
        return AlapGomba;
    }
    
    /**
     * Visszaadja a fonal kezdő {@code Tekton}-ját.
     *
     * @return A kezdő {@code Tekton}.
     */
    public Tekton getStartTekton() {
        return startTekton;
    }

    /**
     * Visszaadja a fonal cél {@code Tekton}-ját.
     *
     * @return A cél {@code Tekton}.
     */
    public Tekton getCelTekton() {
        return celTekton;
    }

    /**
     * Felcseréli a fonal kezdő- és cél {@code Tekton}-ját.
     */
    private void switchStartCel() {
        Tekton uj_start = this.celTekton;
        Tekton uj_cel = this.startTekton;
        this.celTekton = uj_cel;
        this.startTekton = uj_start;
    }

    /**
     * Ellenőrzi, hogy a fonal kapcsolatban áll-e az alap gombával.
     * Ez azt jelenti, hogy a fonal egy folytonos láncon keresztül összeköthető az alap gomba {@code Tekton}-jával.
     *
     * @return {@code true}, ha a fonal folytonosan kapcsolódik az alap gombához, különben {@code false}.
     */
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

    /**
     * A fonal elpusztulását jelző metódus. A konkrét implementációt később kell meghatározni.
     */
    @Override
    public void elpusztul() {
        // Implementáció később
    }
}
