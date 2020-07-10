package co.com.bancolombia.certificacion.unitarias.front;

import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoEmpleado;
import co.com.bancolombia.certificacion.unitarias.enumeraciones.TipoSalario;
import co.com.bancolombia.certificacion.unitarias.excepciones.CesantiasExcepcion;
import co.com.bancolombia.certificacion.unitarias.operativa.CalcSalariales;
import co.com.bancolombia.certificacion.unitarias.pojos.EmpleadoPojo;
import co.com.bancolombia.certificacion.unitarias.pojos.SalarioPojo;

import java.text.NumberFormat;

public class Main {

    public static void main(String[] args){
        try{
            CalcSalariales calculo = new CalcSalariales();
            EmpleadoPojo empleado = new EmpleadoPojo();
            SalarioPojo salario = new SalarioPojo();

            salario.setCantHrs(240);
            salario.setVlrHora(3657.5125);

            empleado.setSalario(salario);
            empleado.setTipoEmpleado(TipoEmpleado.CONVENCIONADO);
            empleado.setTipoSalario(TipoSalario.BASICO);
            empleado.setPorcCumpli(10);

            calculo.setEmpleado(empleado);

            if(!calculo.CalcularSalario()){

                System.out.println(calculo.getError());
                return;
            }
            System.out.println("Tu Bonificación es " + NumberFormat.getCurrencyInstance().format(calculo.getEmpleado().getSalario().getVlrBono()));
            System.out.println("Tu salario es base es " + NumberFormat.getCurrencyInstance().format( calculo.getEmpleado().getSalario().getVlrSalario()));
            System.out.println("Total devengado " + NumberFormat.getCurrencyInstance().format(calculo.getEmpleado().getSalario().getVlrPagar()));

            empleado = calculo.getEmpleado();
            empleado.setDiasTrabajados(180);
            calculo.setEmpleado(empleado);

            if(!calculo.CalcularPrima()){
                System.out.println(calculo.getError());
                return;
            }

            System.out.println("Tu prima legal es " + NumberFormat.getCurrencyInstance().format( calculo.getEmpleado().getPrima().getPrimaLegal()));
            System.out.println("Tu prima extralegal es " + NumberFormat.getCurrencyInstance().format( calculo.getEmpleado().getPrima().getPrimaExtra()));
            System.out.println("Tu prima Total es " + NumberFormat.getCurrencyInstance().format( calculo.getEmpleado().getPrima().getPrimaExtra() + calculo.getEmpleado().getPrima().getPrimaLegal()));

            empleado = calculo.getEmpleado();
            calculo.setEmpleado(empleado);

            if(!calculo.CalcularCesantias()){
                System.out.println(calculo.getError());
                return;
            }

            System.out.println("Tus cesantias legales son " + NumberFormat.getCurrencyInstance().format( calculo.getEmpleado().getCesantias().getCesanLegal()));
            System.out.println("Tus cesantias legales son " + NumberFormat.getCurrencyInstance().format( calculo.getEmpleado().getCesantias().getCesanExtra()));
            System.out.println("El total a pagar por censantias es " + NumberFormat.getCurrencyInstance().format( calculo.getEmpleado().getCesantias().getCesanExtra() + calculo.getEmpleado().getCesantias().getCesanLegal()));

            empleado = calculo.getEmpleado();
            calculo.setEmpleado(empleado);

            if(!calculo.CalcularIntCesan()){
                System.out.println(calculo.getError());
                return;
            }
            System.out.println("Tus Intereses a las cesantías son " + NumberFormat.getCurrencyInstance().format( calculo.getEmpleado().getCesantias().getInteCesan()));


        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        catch (CesantiasExcepcion cesantiasExcepcion) {
            cesantiasExcepcion.printStackTrace();
        }
    }
}
