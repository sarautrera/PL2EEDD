public class Main2 {
    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros miArbolAleatorio = new ArbolBinarioDeBusquedaEnteros();

        //creamos un array básico
        int totalNumeros = 129; // del 0 al 128 inclusive
        int[] numeros = new int[totalNumeros];
        for (int i = 0; i < totalNumeros; i++) {
            numeros[i] = i;
        }

        // mezclamos manualmente
        for (int i = totalNumeros - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            // intercambio de valores
            int temporal = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = temporal;
        }

        //añadimos los números al árbol
        for (int n : numeros) {
            miArbolAleatorio.add(n);
        }

        // calculos de suma
        int sumaTotal = miArbolAleatorio.getSuma();
        System.out.println("Suma total (getSuma): " + sumaTotal);

        int sumaRaiz = miArbolAleatorio.dato;
        int sIzq = (miArbolAleatorio.getSubArbolIzquierda() != null) ? sumarEnMain(miArbolAleatorio.getSubArbolIzquierda()) : 0;
        int sDer = (miArbolAleatorio.getSubArbolDerecha() != null) ? sumarEnMain(miArbolAleatorio.getSubArbolDerecha()) : 0;

        System.out.println("Suma SubIzq + SubDer + Raiz: " + (sIzq + sDer + sumaRaiz));
        System.out.println("Altura del árbol: " + miArbolAleatorio.getAltura());

        //camino al 110 usando el Array que definimos antes
        Comparable[] camino110 = miArbolAleatorio.getCamino(110);

        System.out.print("Camino al 110: ");
        if (camino110 != null) {
            for (int i = 0; i < camino110.length; i++) {
                System.out.print(camino110[i] + " ");
            }
            System.out.println();
            System.out.println("Longitud del camino: " + (camino110.length - 1));
        } else {
            System.out.println("No se encontró el nodo.");
        }
    }

    public static int sumarEnMain(ArbolBinarioDeBusqueda<Integer> nodo) {
        if (nodo == null || nodo.dato == null) return 0;
        int suma = nodo.dato;
        if (nodo.getSubArbolIzquierda() != null) suma += sumarEnMain(nodo.getSubArbolIzquierda());
        if (nodo.getSubArbolDerecha() != null) suma += sumarEnMain(nodo.getSubArbolDerecha());
        return suma;
    }
}
