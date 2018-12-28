package com.carlosmdarribasapps.usal.peliculasdb;

import com.carlosmdarribasapps.usal.view.View;

public class Main {

    public static void main(String[] args) {

        String[]menus = new String[6];
        StringBuilder sb = new StringBuilder();

        //Creamos el menu principal
        sb.append("\nMENU PRINCIPAL:")
                .append("\n\t1) Archivos")
                .append("\n\t2) Peliculas")
                .append("\n\t3) Directores")
                .append("\n\t4) Actores")
                .append("\n\t5) Listado")
                .append("\n\ts) Salir")
                .append("\nSeleccione: ");
        menus[0]=sb.toString();

        //Creamos el menu de archivos
        sb.setLength(0);
        sb.append("\nOpciones sobre Archivos:")
                .append("\n\t1) Exportar directores a encolumnado")
                .append("\n\t2) Exportar peliculas a documento HTML")
                .append("\n\tv) Volver al menú:")
                .append("\nSeleccione opción: ");
        menus[1]=sb.toString();

        //Creamos el menu de películas
        sb.setLength(0);
        sb.append("\nOpciones sobre Peliculas:")
                .append("\n\t1) Nueva película")
                .append("\n\t2) Eliminar película")
                .append("\n\t3) Modificar película")
                .append("\n\t4) Mostrar información de película")
                .append("\n\tv) Volver al menú:")
                .append("\nSeleccione: ");
        menus[2]=sb.toString();

        //Creamos el menu de directores
        sb.setLength(0);
        sb.append("\nOpciones sobre Directores:")
                .append("\n\t1) Nuevo director")
                .append("\n\t2) Eliminar director")
                .append("\n\t3) Modificar director")
                .append("\n\tv) Volver al menú:")
                .append("\nSeleccione: ");
        menus[3]=sb.toString();

        //Creamos el menu de actores
        sb.setLength(0);
        sb.append("\nOpciones sobre Actores:")
                .append("\n\t1) Nuevo actor")
                .append("\n\t2) Eliminar actor")
                .append("\n\t3) Modificar actor")
                .append("\n\t4) Listar películas de un actor")
                .append("\n\tv) Volver al menú:")
                .append("\nSeleccione: ");
        menus[4]=sb.toString();

        //Creamos el menu de listados
        sb.setLength(0);
        sb.append("\nOpciones sobre Listado:")
                .append("\n\t1) Mostrar películas alfabeticamente")
                .append("\n\t2) Mostrar directores por nacionalidad y edad")
                .append("\n\t3) Mostrar actores por debut y nombre")
                .append("\n\tv) Volver al menú:")
                .append("\nSeleccione: ");
        menus[5]=sb.toString();


        // Creamos la vista y mostramos el menu.
        View v = new View();
        v.runMenu(menus);
    }
}