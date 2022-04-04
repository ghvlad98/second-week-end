package ex1;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;

@Data
public class Tavolo {
    private String numero;
    private int capienza;
    private ArrayList<PrenotazioneRistorante> prenotazioni = new ArrayList<>();

    public Tavolo(String numero, int capienza) {
        this.numero = numero;
        this.capienza = capienza;
    }

    public void aggPre(PrenotazioneRistorante pre) {
        prenotazioni.add(pre);
    }

    public void rimPre(PrenotazioneRistorante pre) {
        prenotazioni.remove(pre);
    }


}
