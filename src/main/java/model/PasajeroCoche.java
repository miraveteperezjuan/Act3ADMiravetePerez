package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasajeroCoche {
    private int id, id_paj, id_coche;


    public PasajeroCoche(int id_coche, int id_paj) {
        this.id_coche = id_coche;
        this.id_paj = id_paj;
    }


}
