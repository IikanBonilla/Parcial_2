package Class;

public class Motocicleta extends Vehiculo{
        private int cilindrada;
        

    public Motocicleta(int cilindrada, String marca, String modelo, String placa, int horaDeEntrada) {
        super(marca, modelo, placa, horaDeEntrada);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
        
    
}
