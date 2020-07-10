package co.com.bancolombia.certificacion.unitarias.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimasPojo implements Serializable {

    private double primaLegal;
    private double primaExtra;
}
