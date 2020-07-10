package co.com.bancolombia.certificacion.unitarias.constantes;

import java.text.NumberFormat;

import static co.com.bancolombia.certificacion.unitarias.constantes.ConsSalarios.*;

public class ConsMensajes {

    public static final String SALARIOINFESMLV = "El salario del empleado no debe ser inferior a $" + NumberFormat.getCurrencyInstance().format(SMLV);
    public static final String SALARIOINTEINFESM = "El salario integral del empleado no debe ser inferior a $" + NumberFormat.getCurrencyInstance().format(SMLVINTE);
    public static final String CANTHRSINFCERO = "La cantidad de horas trabajadas no puede ser cero o negativo";
    public static final String DIASTRABANEGA = "Los días trabajados no pueden ser iguales o inferiores a cero";
    public static final String EMPLESALINTE = "El empleado tiene un salario integral, este no recibe prestaciones";
    public static final String VLRMINHORA = "El valor mínimo de la hora trabajada es " + NumberFormat.getCurrencyInstance().format(VLRHORA);
    public static final String VLRMINHORAINTE = "El empleado tiene un salario integral, este no recibe prestaciones";
    public static final String INGRESECANTHORAS = "Debe ingresar la cantidad de horas trabajadas";

}
