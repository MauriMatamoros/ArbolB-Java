/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.b;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mauricio
 */
public class ArbolB {

    private static Scanner input;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        input = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            int opcion = menu();
            if (opcion == 1) {
                System.out.println("Arbol Creado");
                Arbol = new Btree(3);
            } else if (opcion == 2) {
                int option = operaciones();
                if (option == 1) {
                    for (int i = 1; i < 100; i++) {
                        Arbol.insert(Arbol, i);
                    }
                } else {

                }

            } else if (opcion == 3) {
                System.out.print("Ingrese nombre de archivo: ");
                String name = input.next();
                File f = null;
                f = new File("./files/" + name + ".tree");
                FileOutputStream salida = null;
                ObjectOutputStream objeto = null;
                try {
                    salida = new FileOutputStream(f);
                    objeto = new ObjectOutputStream(salida);
                    Btree tree = Arbol;
                    objeto.writeObject(tree);
                    objeto.flush();
                    objeto.close();
                    salida.close();
                    System.out.println("Archivo Guardado");
                } catch (Exception e) {
                }
            } else if (opcion == 4) {
                File directorio = null;
                System.out.println("***Lista de archivos***");
                directorio = new File("./files");
                list(directorio.listFiles());
                System.out.print("Escriba nombre de archivo a abrir: ");
                String name = input.next();
                File f = null;
                f = new File("./files/" + name + ".tree");
                // arreglar el cargar no carga
                try {
                    if (f.exists()) {
                        try {
                            FileInputStream entrada = new FileInputStream(f);
                            ObjectInputStream objeto = new ObjectInputStream(entrada);
                            Btree temp;
                            ArrayList<Btree> temporary = new ArrayList();
                            try {
                                while ((temp = (Btree) objeto.readObject()) != null) {
                                    temporary.add(temp);
                                    System.out.println("entro");
                                }
                                System.out.println("entro");
                                Arbol = temporary.get(0);
                                System.out.println("entro");
                                System.out.println(Arbol.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Error");
                            } finally {
                                entrada.close();
                                objeto.close();
                            }
                        } catch (Exception e) {
                            System.out.println("Error al abrir archivos");
                        }
                    } else {
                        System.out.println("No existe ese archivo");
                    }
                    
                } catch (Exception e) {

                }
            } else if (opcion == 5) {
                System.out.println(Arbol.toString());
            } else if (opcion == 6) {
                valid = true;
            }
        }
    }

    public static int menu() {
        input = new Scanner(System.in);
        int opcion;
        System.out.println("*******Menu*******\n1. Crear Arbol B \n2. Operaciones "
                + "\n3. Guardar \n4. Cargar \n5. Imprimir \n6. Salir");
        opcion = input.nextInt();
        return opcion;
    }

    public static int operaciones() {
        input = new Scanner(System.in);
        int opcion;
        System.out.println("***Operaciones***\n1.Insertar \n2.Eliminar");
        opcion = input.nextInt();
        return opcion;
    }

    public static Btree Arbol;

    public static void list(File[] files) {
        for (File file : files) {
            if (file.getName().endsWith(".tree")) {
                System.out.println(file.getName());
            }
        }
    }

}
