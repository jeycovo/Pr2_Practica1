//package practica1;
import java.io.*;
import java.util.*;

public class PruebaContenedor {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        File fichero = new File("src/datos.dat");
        RandomAccessFile dato = new RandomAccessFile(fichero, "r");

        File fichero2 = new File("src/datos_no.dat");
        RandomAccessFile dato2 = new RandomAccessFile(fichero2, "r");

        File salida1 = new File("src/salida1.txt");
        RandomAccessFile out = new RandomAccessFile(salida1, "rw");

        ContenedorDeEnteros a = new ContenedorDeEnteros();
        //  Creamos un contenedor de 100000 elementos

        long[] insercion = inser(a,dato);
        long[] exitosa  = oper(a, dato);
        long[] infractuosa = oper(a, dato2);

        //  Resultados escritos en el fichero salida1.txt
        //  Comparación de Inserciones
        out.write("\nTiempo Inserciones:".getBytes());
        for (int i = 0; i < 10; i++) {
            out.write('\n');
            out.write(Long.toString(insercion[i]).getBytes());
        }
        //  Comparación de Busquedas
        out.write("\nComparación de Busquedas \n Exitosas    Infractuosas".getBytes());
        for (int i = 0; i < 10; i++) {
            out.write("\n    ".getBytes());
            out.write(Long.toString(exitosa[i]).getBytes());
            out.write("             ".getBytes());
            out.write(Long.toString(infractuosa[i]).getBytes());
        }
        /*
        //  Resultados por pantalla
        //  Comparación de Inserciones
        System.out.println("Tiempo Inserciones:");
        for (int i = 0; i < 10; i++) {
            System.out.println(insercion[i]);
        }
        //Comparación de Busquedas
        System.out.println("Comparación de Busquedas \n Exitosas    Infractuosas");
        for (int i = 0; i < 10; i++) {
            System.out.println("    " + exitosa[i] + "              "+ infractuosa[i]);
        }
        */
    }

    /**
     *
     * @param fichero      Fichero donde se buscan los datos insertados en el contenedor
     * @return              Vector con los tiempos de ejecución de las distintas operaciones
     * @throws IOException  Necesaria para manejar las clases RandomAccessFile y File
     */
    protected static long[] inser(ContenedorDeEnteros a ,RandomAccessFile fichero) throws IOException{
        int limite=0;
        long[]v = new long[10];

        for(int i = 0; i<10 ; i++){
            //Inserciones
            long start = System.currentTimeMillis();
            for(int j=limite; j<(limite+10000); j++){
                a.insertar(fichero.read());
                fichero.seek(j);
            }
            //duda promedio
            v[i] = (System.currentTimeMillis() - start)/10;
        }
        return v;
    }

    protected static long[] oper(ContenedorDeEnteros a, RandomAccessFile fichero) throws IOException{
        int limite = 0;
        // Objeto donde almacenamos los valores de tiempo
        long[] v = new long[10];
        for(int i = 0; i<10 ; i++){
            //Busquedas
            long start = System.currentTimeMillis();
            //ponemos el puntero a 0
            fichero.seek(0);
            limite += 10000;
            for(int j=0; j<limite; j++){
                a.buscar(fichero.read());
                fichero.seek(j);
            }
            v[i] = (System.currentTimeMillis() - start)/10;
        }
        return v;
    }
}
