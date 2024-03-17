package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Libreta {
    private String nombreFichero = "contactos.txt";
    private ArrayList<Contacto> contactos = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contacto contacto : contactos) {
            sb.append(contacto).append("\n");
        }
        return sb.toString();
    }

    public Libreta() {
        try {
            File fichero = new File(nombreFichero);
            fichero.createNewFile();
            Scanner sc = new Scanner(fichero);
            while (sc.hasNext()) {
                Contacto contacto = new Contacto();
                contacto.setNombre(sc.nextLine());
                contacto.setTelefono(sc.nextLine());
                contactos.add(contacto);
            }
            sc.close(); // Cerrar el Scanner después de su uso
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void volcarContactos() {
        System.out.println(contactos);
        try {
            FileWriter fw = new FileWriter(nombreFichero);
            for (Contacto contacto : contactos) {
                fw.write(contacto.getNombre() + "\n");
                fw.write(contacto.getTelefono() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public void agregarContacto(String nombre, String telefono) {
        Contacto contacto = new Contacto(nombre, telefono);
        contactos.add(contacto);
        this.volcarContactos();
    }

    public void eliminarContacto(String nombre) {
        Contacto contacto = new Contacto();
        contacto.setNombre(nombre);
        contactos.remove(contacto);
        this.volcarContactos();
    }

    public void generarHojaDeCalculo() {
        final Object[][] datos = new Object[contactos.size()][2];
        int i = 0;
        for (Contacto contacto : contactos) {
            datos[i][0] = contacto.getNombre();
            datos[i++][1] = contacto.getTelefono();
        }
        String[] columnas = new String[]{"Nombre", "Teléfono"};
        TableModel modelo = new DefaultTableModel(datos, columnas);
        final File file = new File("output/contactos.ods");
        try {
            SpreadSheet.createEmpty(modelo).saveAs(file);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
