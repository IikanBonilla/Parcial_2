/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.parcial_2;
import static spark.Spark.*;
import com.google.gson.Gson;

import java.util.LinkedList;
import Class.Automovil;
import Class.Motocicleta;
import Class.Vehiculo;
import java.time.LocalTime;
import java.util.ArrayList;

public class Parcial_2 {

    public static void main(String[] args) {
       /*
         * Esto nos sirve para generar un formato json para retornar la data del array
         * sin tener que acomodarla bonita de manera manual
         */
        Gson gson = new Gson();

        ArrayList<Vehiculo> autos = new ArrayList<>();
        ArrayList<Vehiculo> motocicletas = new ArrayList<>();

        // Automovil creado por defecto
        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987", 2);
        autos.add(auto);

        Motocicleta moto = new Motocicleta(600, "Honda", "CBR600", "XYZ789", 3);
        motocicletas.add(moto);


        // Definir endpoints

        // Listado de automovile
        get("/autos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(autos);
        });

        // Por defecto
        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motocicletas);
        });



        // endpoint GET para agregar un Motocicleta
        get("/agregarMotos/:marca/:modelo/:placa/:cilindrado", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int numeroPuertas = Integer.parseInt(req.params(":cilindrado"));

            // Crear un nuevo automóvil y agregarlo al parqueadero
            Motocicleta nuevaMoto = new Motocicleta(numeroPuertas, marca, modelo,placa, obtenerHoraActual());
            motocicletas.add(nuevaMoto);
            return gson.toJson(nuevaMoto);
        });
        // endpoint GET para agregar un automóvil
        get("/agregarAutos/:marca/:modelo/:placa/:numeroPuertas", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));

            // Crear un nuevo automóvil y agregarlo al parqueadero
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo,placa, obtenerHoraActual());
            autos.add(nuevoAuto);

            return gson.toJson(nuevoAuto);
        });
        get("/setHoraDeSalida/:tipoDeVehiculo/:placa/:horaDeSalida", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String tipoDeVehiculo = req.params(":tipoDeVehiculo");
            String placa = req.params(":placa");
            int horaDeSalida = Integer.parseInt(req.params(":horaDeSalida"));
            if(horaDeSalida >= 0 && horaDeSalida <= 24){
                if(tipoDeVehiculo.equals("Automovil")){
                    obtenerVehiculoPorPlaca(autos, placa).setHoraSalida(horaDeSalida);
                    obtenerVehiculoPorPlaca(autos, placa).uptDineroGenerado();
                    obtenerVehiculoPorPlaca(autos, placa).setHoraEntrada(-1);
                    return gson.toJson(autos);
                }else if(tipoDeVehiculo.equals("Motocicleta")){
                    obtenerVehiculoPorPlaca(motocicletas, placa).setHoraSalida(horaDeSalida);
                    obtenerVehiculoPorPlaca(motocicletas, placa).uptDineroGenerado();
                    obtenerVehiculoPorPlaca(motocicletas, placa).setHoraEntrada(-1);

                    return gson.toJson(motocicletas);
                }
            }
            return null;
        });

        get("/motosActuales", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> motosActuales = new ArrayList<>();
            for (Vehiculo item:
                 motocicletas) {
                if(item.horaSalida == -1)
                    motosActuales.add(item);
            }
            return gson.toJson(motosActuales);
        });
        get("/autosActuales", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> autosActuales = new ArrayList<>();
            for (Vehiculo item: autos) {
                if(item.horaSalida == -1)
                    autosActuales.add(item);
            }
            return gson.toJson(autosActuales);
        });
        get("/motosReporte", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> ganancias = new ArrayList<>();
            for (Vehiculo item: motocicletas) {
                if(item.horaSalida != -1)
                    ganancias.add(item);
            }
            return gson.toJson(ganancias);
        });
        get("/autosReporte", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> ganancias = new ArrayList<>();
            for (Vehiculo item: autos) {
                if(item.horaSalida != -1)
                    ganancias.add(item);
            }
            return gson.toJson(ganancias);
        });


    }
    public static int obtenerHoraActual() {
        LocalTime now = LocalTime.now();
        return now.getHour();
    }
    public static Vehiculo obtenerVehiculoPorPlaca(ArrayList<Vehiculo> vehiculos, String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return vehiculo;
            }
        }
        return null;
    }
}
