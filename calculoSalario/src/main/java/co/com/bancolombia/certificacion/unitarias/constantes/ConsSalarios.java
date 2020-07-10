package co.com.bancolombia.certificacion.unitarias.constantes;

public class ConsSalarios {

    public static final double SMLV = 877803.0;
    public static final double HRSTRABAJAMES = 240;
    public static final double HRSTRABAJADIA = 8;
    public static final double CANTSMLVSALINTE = 10;
    public static final double VLRHORA = SMLV / HRSTRABAJAMES;
    public static final double SMLVD = VLRHORA * HRSTRABAJADIA;
    public static final double SMLVINTE = SMLV * CANTSMLVSALINTE;
    public static final double VLRHORAINTE = SMLVINTE / HRSTRABAJAMES;
    public static final double SMLVDINTE = VLRHORAINTE * HRSTRABAJADIA;
    public static final double DIASANO = 360;

}
