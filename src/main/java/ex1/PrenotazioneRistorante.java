package ex1;

import lombok.Data;

import java.sql.Date;
import java.util.Scanner;

@Data
public class PrenotazioneRistorante {
    private String cognome;
    private Date data;
    private int numeroPersone;
    private String cellulare;

    PrenotazioneRistorante(String cognome, Date data, int numeroPersone, String cellulare) {
        this.cognome = cognome;
        this.data = data;
        this.numeroPersone = numeroPersone;
        this.cellulare = cellulare;
    }
}
