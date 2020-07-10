package co.com.bancolombia.certificacion.unitarias.reglasNegocio;

import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoSalario;
import co.com.bancolombia.certificacion.unitarias.excepciones.CesantiasExcepcion;
import co.com.bancolombia.certificacion.unitarias.pojos.CesantiasPojo;
import co.com.bancolombia.certificacion.unitarias.pojos.EmpleadoPojo;

import static co.com.bancolombia.certificacion.unitarias.constantes.ConsMensajes.*;
import static co.com.bancolombia.certificacion.unitarias.constantes.ConsSalarios.DIASANO;
import static co.com.bancolombia.certificacion.unitarias.constantes.ConsSalarios.SMLV;

public class Cesantias {

    private static final String CALCESANLEGAL = "Debe calcular las cesantias legales";
    private static final double PORCESANEXTRA = 0.25;
    private static final double PORCINTECESAN = 0.12;
    private EmpleadoPojo empleado;
    private String error;

    public Cesantias(EmpleadoPojo empleado){
        this.empleado = empleado;

        if(empleado.getCesantias() == null){
            this.empleado.setCesantias(new CesantiasPojo());
        }
        else{
            this.empleado.setCesantias(empleado.getCesantias());
        }

        error = "";
    }

    public EmpleadoPojo getEmpleado() {
        return empleado;
    }

    public String getError() {
        return error;
    }

    private boolean validar(boolean extraLegal) {

        if(empleado.getTipoSalario() == null || empleado.getTipoEmpleado()== null){
            throw new NullPointerException();
        }

        if(empleado.getTipoSalario() == TipoSalario.BASICO){

            if(empleado.getSalario().getVlrSalario() < SMLV) {
                error = SALARIOINFESMLV;
                return false;
            }
            if(empleado.getDiasTrabajados() <= 0){
                error = DIASTRABANEGA;
                return false;
            }
            if(extraLegal && empleado.getCesantias().getCesanLegal() < 0){
                error = CALCESANLEGAL;
                return false;
            }
        }
        else{
            error = EMPLESALINTE;
            return false;
        }
        return true;
    }

    private boolean calcCesanLegal() throws CesantiasExcepcion {

        if(!validar(false)){
            throw new CesantiasExcepcion(error);
        }

        double vlrCesanDia = empleado.getSalario().getVlrSalario() / DIASANO;
        empleado.getCesantias().setCesanLegal(vlrCesanDia * empleado.getDiasTrabajados());
        return  true;
    }

    private boolean calcCesanExtra() throws CesantiasExcepcion {

        if(!validar(true)){
            throw new CesantiasExcepcion(error);
        }
        if(empleado.getTipoSalario() == TipoSalario.BASICO){
            empleado.getCesantias().setCesanExtra(empleado.getCesantias().getCesanLegal() * PORCESANEXTRA);
        }
        else{
            empleado.getCesantias().setCesanExtra(0);
        }
        return  true;
    }

    public boolean calcInteCesan() throws CesantiasExcepcion {

        if(!validar(true)){
            throw new CesantiasExcepcion(error);
        }
        if(empleado.getTipoSalario() == TipoSalario.BASICO){
            empleado.getCesantias().setInteCesan( empleado.getCesantias().getCesanLegal() * PORCINTECESAN * (empleado.getDiasTrabajados()/DIASANO) );
        }
        else{
            empleado.getCesantias().setInteCesan(0);
        }
        return  true;
    }

    public boolean calCensatias() throws CesantiasExcepcion {

        if(!calcCesanLegal()){
            return  false;
        }
        if(!calcCesanExtra()){
            return false;
        }
        return true;
    }


}
