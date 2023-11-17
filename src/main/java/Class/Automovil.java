package Class;

public class Automovil extends Vehiculo{
      private int numeroPuertas;
    // Constructores, getters y setters

    public int getHoraEntrada() {
        return horaEntrada;
    }

      @Override
    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

//      @Override
//    public void setHoraSalida(int horaSalida) {
//        this.horaSalida = horaSalida;
//    }

    // Otros métodos específicos para automóviles
    
    public Automovil(int numeroPuertas, String marca, String modelo, String placa, int horaEntrada) {
        super(marca, modelo, placa, horaEntrada);
        this.numeroPuertas = numeroPuertas;
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
    
  
  
}
