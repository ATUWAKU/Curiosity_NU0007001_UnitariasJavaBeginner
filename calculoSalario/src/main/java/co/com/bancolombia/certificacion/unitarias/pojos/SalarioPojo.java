package co.com.bancolombia.certificacion.unitarias.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalarioPojo {

    private double cantHrs;
    private double vlrHora;
    private double vlrSalario;
    private double vlrBono;
    private double vlrPagar;

}
