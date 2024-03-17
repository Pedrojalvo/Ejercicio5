package interfaz;

import dominio.Libreta;

public class Interfaz {

    public static void iniciar(String args[]) {
        Libreta libreta = new Libreta();
        if (args[0].equals("agregar")) {
            libreta.agregarContacto(args[1], args[2]);
        } else if (args[0].equals("eliminar")) {
            libreta.eliminarContacto(args[1]);
        } else if (args[0].equals("mostrar")) {
            System.out.println(libreta);
        } else if (args[0].equals("hoja")) {
            libreta.generarHojaDeCalculo();
            System.out.println("Hoja de cálculo generada ");
        } else {
            System.out.println("Opción incorrecta");
        }
    }
}
