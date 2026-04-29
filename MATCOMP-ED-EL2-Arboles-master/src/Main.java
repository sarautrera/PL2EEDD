public static <T> void main(String[] args) {
    ArbolBinarioDeBusquedaEnteros miArbol = new ArbolBinarioDeBusquedaEnteros();

    //añadimos los números de 0 a 128 en orden
    for (int i = 0; i <= 128; i++) {
        miArbol.add(i);
    }

    //calculamos la suma
    System.out.println("Suma total: " + miArbol.getSuma());

    //verificamos que la suma es la misma cuando se suman los subárboles
    int sumaRaiz = miArbol.dato;
    int sumaI = (miArbol.getSubArbolIzquierda() != null) ? sumarEnMain(miArbol.getSubArbolIzquierda()) : 0;
    int sumaD = (miArbol.getSubArbolDerecha() != null) ? sumarEnMain(miArbol.getSubArbolDerecha()) : 0;

    System.out.println("Suma Izquierda + Derecha + Raíz: " + (sumaI + sumaD + sumaRaiz));

    // altura
    System.out.println("Altura del árbol: " + miArbol.getAltura());

    // camino al 110 y su longitud
    Comparable[] camino = miArbol.getCamino(110);
    System.out.print("Camino al 110: ");
    if (camino != null) {
        // recorremos el array uno a uno para imprimir los valores
        for (int i = 0; i < camino.length; i++) {
            System.out.print(camino[i] + " ");
        }
        System.out.println(); // salto de línea al terminar
        System.out.println("Longitud del camino: " + (camino.length - 1));
    }
}

//metodo para poder sumar por la derecha y por la izquierda por separado
public static int sumarEnMain(ArbolBinarioDeBusqueda<Integer> nodo) {
    if (nodo == null || nodo.dato == null) return 0;
    int suma = nodo.dato;
    if (nodo.getSubArbolIzquierda() != null) suma += sumarEnMain(nodo.getSubArbolIzquierda());
    if (nodo.getSubArbolDerecha() != null) suma += sumarEnMain(nodo.getSubArbolDerecha());
    return suma;
}