
/*
    Esta es su clase principal. El unico metodo que debe implementar es
    public String[] solve(Maze maze)
    pero es libre de crear otros metodos y clases en este u otro archivo que desee.
*/
import java.util.*;
import java.util.concurrent.*;
import java.util.LinkedList;

public class Solver {

    Maze mapita;
    private boolean visited;///////

    ArrayList<String> rutas;
    public Solver() {
        // Sientase libre de implementar el contructor de la forma que usted lo desee
    }

    /////////////
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    //////////////////////

    public String solve(Maze mapita) {
        // Implemente su metodo aqui. Sientase libre de implementar métodos adicionales

        /**
         * Node actual = mapita.getStartingSpace();
         * actual = mapita.moveSouth(actual);
         * if (mapita.isExitSpace(actual.xIndex, actual.yIndex, actual.zIndex)) {
         * return "[S]";
         * }
         * 
         * return "[-1]";
         * }
         * 
         * public static void main(String[] args) {
         * Solver prueba = new Solver();
         * String result = prueba.solve();
         * System.out.print(result);
         * }
         */
        /// ---------------

        // --------boolean visitados;//
        ///////

        // ----visitados = true;// ------
        this.rutas = new ArrayList<>(); //direcciones que toma
        this.mapita = mapita;
        Node entrada = this.mapita.getStartingSpace(); //entrada
        solve(entrada,"");
        //System.out.println(rutas); //direcciones que toma
        //System.out.println(caminos); //puntos en los que pasa
        if(rutas.size() > 0) {
            String cmn = rutas.get(rutas.size() - 1);
            cmn = String.join(",",cmn.split("")); //separar por comas
            return "[" + cmn + "]";
        }
        return "[]";
    }

    private void solve(Node nodoActual,String cadena) {
        Node nodoNor = mapita.moveNorth(nodoActual);
        Node nodoSur = mapita.moveSouth(nodoActual);
        Node nodoOeste = mapita.moveWest(nodoActual);
        Node nodoEste = mapita.moveEast(nodoActual);
        Node nodoArriba = mapita.moveUp(nodoActual);
        Node nodoAbajo = mapita.moveDown(nodoActual);
        ///////////////////////////

        if(mapita.isExitSpace(nodoActual.xIndex, nodoActual.yIndex, nodoActual.zIndex)) { //es salida
            this.rutas.add(cadena); //agrega las direcciones que tomó
            return;
        }

        if (nodoActual != nodoNor && !nodoNor.danger && !nodoNor.visited) { //si nodo destino es diferente al actual y no hay peligro y no está visitado
            nodoActual.visited = true; //se marca visitado el nodo actual
            solve(nodoNor,cadena + "N"); //agrega la dirección a la cadena
            //revierte lo hecho anteriormente para evaluar la siguiente posibilidad
            nodoActual.visited = false;
        }
        if (nodoActual != nodoSur && !nodoSur.danger && !nodoSur.visited) {
            nodoActual.visited = true;
            solve(nodoSur,cadena + "S");
            nodoActual.visited = false;
        }
        if (nodoActual != nodoOeste && !nodoOeste.danger && !nodoOeste.visited) {
            nodoActual.visited = true;
            solve(nodoOeste,cadena + "W");
            nodoActual.visited = false;
        }
        if (nodoActual != nodoEste && !nodoEste.danger && !nodoEste.visited) {
            nodoActual.visited = true;
            solve(nodoEste,cadena + "E");
            nodoActual.visited = false;
        }
        if (nodoActual != nodoArriba && !nodoArriba.danger && !nodoArriba.visited) {
            nodoActual.visited = true;
            solve(nodoArriba,cadena + "U");
            nodoActual.visited = false;
        }
        if (nodoActual != nodoAbajo && !nodoAbajo.danger && !nodoAbajo.visited) {
            nodoActual.visited = true;
            solve(nodoAbajo,cadena + "D");
            nodoActual.visited = false;
        }
    }

    /*public static void main(String[] args) {
        Maze mapita;
        try {
            mapita = new Maze("tests/test-3.txt");
            Solver prueba = new Solver(mapita);
            String result = prueba.solve(null);
            System.out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}