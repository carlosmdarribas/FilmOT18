package com.carlosmdarribasapps.usal.view;

import com.carlosmdarribasapps.usal.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class View {
    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);

    public void runMenu(String[] menus) {
        boolean salir = false;
        String respuesta = null;

        try {
            controller.arranque();
        } catch (IOException e) {
            System.err.println("ERROR: los datos no pudieron ser importados\nAbortando ejecucion.");
            salir = true;
        }

        while(!salir) {
            System.out.print(menus[0]);
            do {
                respuesta = scanner.nextLine();
            } while(respuesta.isEmpty());

            switch(respuesta){
                /*
                case "1": menuArchivos(menus[1]);  break;
                case "2": menuClientes(menus[2]);  break;
                case "3": menuReslutados(menus[3]);break;
                case "4": menuListados(menus[4]);  break;
                case "5": menuVentas(menus[5]);    break;
                case "S": case "s": salir = true;  break;
                */
                default: System.err.println("ERROR: Introduzca un caracter valido"); break;
            }
        }

        try {
            controller.salidaPrograma();
        } catch(IOException e) {
            System.err.println("ERROR: los datos no pudieron ser exportados.");
        }
    }
}
