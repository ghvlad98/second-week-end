package ex1;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;

@Data
public class Ristorante {
    private ArrayList<Tavolo> tables = new ArrayList<Tavolo>();

    public void aggTable(Tavolo tav) {
        tables.add(tav);
    }

    public void rimTable(Tavolo tav) {
        tables.remove(tav);
    }

    public boolean richiestaPrenotazione(String cognome, Date data, int numeroPersone, String cellulare) {
        String disp = disponibilitaTavolo(data, numeroPersone);
        for (Tavolo t: tables) {
            if (t.getNumero().equals(disp)) {
                t.getPrenotazioni().add(new PrenotazioneRistorante(cognome, data, numeroPersone, cellulare));
            }
        }
        return disp != null;
    }

    public String disponibilitaTavolo(Date data, int numeroPersone) {
        for (Tavolo t: this.tables) {
            if (t.getCapienza() >= numeroPersone) {
                if (t.getPrenotazioni() == null) {
                    return t.getNumero();
                } else {
                    boolean check = true;
                    for (PrenotazioneRistorante pre: t.getPrenotazioni()) {
                        if (pre.getData().equals(data)) {
                            check = false;
                            break;
                        }
                    }
                    if (check) return t.getNumero();
                }
            }
        }
        return null;
    }
}
