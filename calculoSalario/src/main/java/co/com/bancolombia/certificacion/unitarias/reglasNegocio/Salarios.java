package co.com.bancolombia.certificacion.unitarias.reglasNegocio;

import co.com.bancolombia.certificacion.unitarias.excepciones.TipoSalExcepcion;
import co.com.bancolombia.certificacion.unitarias.pojos.EmpleadoPojo;

import static co.com.bancolombia.certificacion.unitarias.constantes.ConsMensajes.*;
import static co.com.bancolombia.certificacion.unitarias.constantes.ConsSalarios.*;

public class Salarios {

    private EmpleadoPojo empleado;
    private String error;

    public Salarios(EmpleadoPojo empleado){
        this.empleado = empleado;
        error = "";
    }

    public String getError() {
        return error;
    }

    public EmpleadoPojo getEmpleado() {
        return empleado;
    }

    private boolean validar(boolean calcBono) throws TipoSalExcepcion {

        if(empleado.getSalario().getCantHrs() <= 0){
            error = INGRESECANTHORAS;
            return  false;
        }

        if(empleado.getTipoSalario() == null || empleado.getTipoEmpleado()== null){
            throw new NullPointerException();
        }

        switch (empleado.getTipoSalario()){
            case BASICO:
                if(empleado.getSalario().getVlrHora() < VLRHORA){
                    error = VLRMINHORA;
                    return  false;
                }
                if(empleado.getSalario().getCantHrs() <= 0){
                    error = CANTHRSINFCERO;
                    return  false;
                }
                if(calcBono && empleado.getSalario().getVlrSalario() <= 0){
                    error = SALARIOINFESMLV;
                    return false;
                }
                break;
            case INTEGRAL:
                if(empleado.getSalario().getVlrHora() <= SMLVDINTE){
                    error = VLRMINHORAINTE;
                    return  false;
                }
                if(calcBono && empleado.getSalario().getVlrSalario() <= SMLVINTE){
                    error = SALARIOINTEINFESM;
                    return false;
                }
                break;
            default:
                throw new TipoSalExcepcion();
        }
        return true;

    }

    private boolean calcSalario() throws TipoSalExcepcion {

        if(!validar(false)){
            return  false;
        }

        empleado.getSalario().setVlrSalario(empleado.getSalario().getVlrHora() * empleado.getSalario().getCantHrs());
        return true;

    }

    private boolean calcBoni() throws TipoSalExcepcion {

        if(!validar(true)){
            return  false;
        }

        if(empleado.getPorcCumpli() > 0){

            double bono = (empleado.getSalario().getVlrSalario() * (empleado.getPorcCumpli()/100)) > empleado.getSalario().getVlrSalario() ? empleado.getSalario().getVlrSalario() : (empleado.getSalario().getVlrSalario() * (empleado.getPorcCumpli()/100));
            empleado.getSalario().setVlrBono(bono);
        }
        return true;

    }

    public boolean calcPago() throws TipoSalExcepcion {

        if(!calcSalario()){
            return false;
        }
        if(!calcBoni()){
            return  false;
        }

        empleado.getSalario().setVlrPagar(empleado.getSalario().getVlrBono() + empleado.getSalario().getVlrSalario());
        return true;
    }
}
