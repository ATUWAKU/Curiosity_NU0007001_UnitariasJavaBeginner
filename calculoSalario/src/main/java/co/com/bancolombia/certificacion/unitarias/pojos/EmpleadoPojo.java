package co.com.bancolombia.certificacion.unitarias.pojos;

import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoEmpleado;
import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoSalario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoPojo {

    private TipoSalario tipoSalario;
    private TipoEmpleado tipoEmpleado;
    private SalarioPojo salario;
    private PrimasPojo prima;
    private CesantiasPojo cesantias;
    private int diasTrabajados;
    private double porcCumpli;

}
