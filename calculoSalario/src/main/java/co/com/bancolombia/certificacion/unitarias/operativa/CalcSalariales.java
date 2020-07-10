package co.com.bancolombia.certificacion.unitarias.operativa;

import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoCalculo;
import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoSalario;
import co.com.bancolombia.certificacion.unitarias.excepciones.CesantiasExcepcion;
import co.com.bancolombia.certificacion.unitarias.excepciones.PrimasExcepcion;
import co.com.bancolombia.certificacion.unitarias.excepciones.TipoSalExcepcion;
import co.com.bancolombia.certificacion.unitarias.pojos.CesantiasPojo;
import co.com.bancolombia.certificacion.unitarias.pojos.EmpleadoPojo;
import co.com.bancolombia.certificacion.unitarias.pojos.PrimasPojo;
import co.com.bancolombia.certificacion.unitarias.reglasNegocio.Cesantias;
import co.com.bancolombia.certificacion.unitarias.reglasNegocio.Primas;
import co.com.bancolombia.certificacion.unitarias.reglasNegocio.Salarios;

import static co.com.bancolombia.certificacion.unitarias.constantes.ConsMensajes.*;
import static co.com.bancolombia.certificacion.unitarias.constantes.ConsSalarios.*;

public class CalcSalariales {

    private String error;
    private CesantiasPojo cesantias;
    private EmpleadoPojo empleado;
    private PrimasPojo prima;

    public CalcSalariales(){
        error = "";
    }

    public String getError() {
        return error;
    }

    public void setEmpleado(EmpleadoPojo empleado) {
        this.empleado = empleado;
    }

    public EmpleadoPojo getEmpleado() {
        return empleado;
    }

    private boolean validar(TipoCalculo tipoCalculo){

        if(empleado.getTipoSalario() == null || empleado.getTipoEmpleado()== null){
            throw new NullPointerException();
        }
        if(empleado.getDiasTrabajados() <= 0 && !tipoCalculo.equals(TipoCalculo.SALARIO)){
            error = DIASTRABANEGA;
            return false;
        }
        switch (tipoCalculo){

            case SALARIO:
                if(empleado.getSalario().getCantHrs() <= 0){
                    error = CANTHRSINFCERO;
                }
                if(empleado.getSalario().getVlrHora() < VLRHORA){
                    error = VLRMINHORA;
                    return  false;
                }
                break;
            case PRIMA:
            case CESANTIAS:
                if(empleado.getTipoSalario() == TipoSalario.BASICO) {

                    if (empleado.getSalario().getVlrSalario() < SMLV) {
                        error = SALARIOINFESMLV;
                        return false;
                    }
                }
                else{
                    if (empleado.getSalario().getVlrSalario() <= SMLVINTE) {
                        error = SALARIOINTEINFESM;
                        return false;
                    }
                }
                break;
        }
        return true;
    }

    public boolean CalcularSalario() throws TipoSalExcepcion {

        if(!validar(TipoCalculo.SALARIO)){
            return false;
        }
        Salarios salario = new Salarios(empleado);

        if(!salario.calcPago()){
            error = salario.getError();
        }

        empleado.setSalario(salario.getEmpleado().getSalario());
        return true;
    }

    public boolean CalcularPrima() throws PrimasExcepcion, TipoSalExcepcion {

        if(!validar(TipoCalculo.PRIMA)){
            return false;
        }

        Primas prima = new Primas(empleado);

        if(!prima.calcularPrima()){
            error = prima.getError();
        }

        empleado.setPrima(prima.getEmpleado().getPrima());
        return true;
    }

    public boolean CalcularCesantias() throws CesantiasExcepcion {

        if(!validar(TipoCalculo.CESANTIAS)){
            return false;
        }
        Cesantias cesantias = new Cesantias(empleado);

        if(!cesantias.calCensatias()){
            error = cesantias.getError();
        }
        empleado.setCesantias(cesantias.getEmpleado().getCesantias());
        return true;
    }

    public boolean CalcularIntCesan() throws CesantiasExcepcion {

        if(!validar(TipoCalculo.CESANTIAS)){
            return false;
        }
        Cesantias cesantias = new Cesantias(empleado);

        if(!cesantias.calcInteCesan()){
            error = cesantias.getError();
        }
        empleado.setCesantias(cesantias.getEmpleado().getCesantias());
        return true;
    }



}
