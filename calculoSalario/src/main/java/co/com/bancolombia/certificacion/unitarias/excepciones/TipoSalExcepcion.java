package co.com.bancolombia.certificacion.unitarias.excepciones;
import java.lang.Exception;

public class TipoSalExcepcion extends Exception {

    private final String TIPOSALEXC = "El tipo de salario no se contempla en la organización";

    @Override
    public String getMessage(){
        String mensaje= TIPOSALEXC;
        return mensaje;
    }
}
