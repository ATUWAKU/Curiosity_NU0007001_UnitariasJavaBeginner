package co.com.bancolombia.certificacion.unitarias.reglasNegocio;

import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoEmpleado;
import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoSalario;
import co.com.bancolombia.certificacion.unitarias.excepciones.PrimasExcepcion;
import co.com.bancolombia.certificacion.unitarias.excepciones.TipoSalExcepcion;
import co.com.bancolombia.certificacion.unitarias.pojos.EmpleadoPojo;
import co.com.bancolombia.certificacion.unitarias.pojos.PrimasPojo;

import static co.com.bancolombia.certificacion.unitarias.constantes.ConsMensajes.*;
import static co.com.bancolombia.certificacion.unitarias.constantes.ConsSalarios.SMLV;
import static co.com.bancolombia.certificacion.unitarias.constantes.ConsSalarios.SMLVINTE;

public class Primas {


    private EmpleadoPojo empleado;
    private double primaExtra;
    private String error;

    public Primas(EmpleadoPojo empleado){
        this.empleado = empleado;
        this.empleado.setPrima(new PrimasPojo());
        error = "";
    }

    public EmpleadoPojo getEmpleado() {
        return empleado;
    }

    public String getError() {
        return error;
    }

    private boolean validar() throws TipoSalExcepcion {

        if(empleado.getTipoSalario() == null || empleado.getTipoEmpleado()== null){
            throw new NullPointerException();
        }
        if(empleado.getDiasTrabajados() <= 0){
            error = DIASTRABANEGA;
            return false;
        }
        switch (empleado.getTipoSalario()){

            case BASICO:
                if(empleado.getSalario().getVlrSalario()< SMLV){
                    error = SALARIOINFESMLV;
                    return  false;
                }
                break;
            case INTEGRAL:
                if(empleado.getSalario().getVlrSalario() < SMLVINTE){
                    error = SALARIOINTEINFESM;
                    return  false;
                }
                break;
            default:
                throw new TipoSalExcepcion();
        }
        return  true;
    }

    private boolean calcPrimaExtra() throws PrimasExcepcion, TipoSalExcepcion {

        if(!validar()){
            throw new PrimasExcepcion(error);
        }

        if(empleado.getTipoSalario() == TipoSalario.BASICO) {

            primaExtra = empleado.getSalario().getVlrSalario() * 1.5;
        }
        else{
            primaExtra = empleado.getSalario().getVlrSalario();
        }
        if(empleado.getTipoEmpleado() == TipoEmpleado.CONVENCIONADO){
            primaExtra = primaExtra + (empleado.getSalario().getVlrSalario() * 0.1);
        }
        empleado.getPrima().setPrimaExtra(primaExtra);
        return true;
    }

    private boolean calcPrimaLegal() throws PrimasExcepcion, TipoSalExcepcion {

        if(!validar()){
            return false;
        }
        if(empleado.getTipoSalario() == TipoSalario.BASICO) {

            empleado.getPrima().setPrimaLegal(((empleado.getSalario().getVlrSalario() * 0.5) / 180) * empleado.getDiasTrabajados());
        }
        return true;
    }

    public boolean calcularPrima() throws PrimasExcepcion, TipoSalExcepcion {

        if(!calcPrimaLegal()){
            return false;
        }
        if(!calcPrimaExtra()){
            return false;
        }
        return true;
    }
}
