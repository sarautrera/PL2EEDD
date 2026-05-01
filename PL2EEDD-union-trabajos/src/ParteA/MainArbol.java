package ParteA;
public class MainArbol {

    public static void main(String[] args) {

        // PRUEBA 1: DATOS ORDENADOS
        MiListaDoble<Integer> datosOrdenados = new MiListaDoble<>();
        for (int i = 0; i <= 128; i++) { // Genera una lista con números secuenciales del 0 al 128.
            datosOrdenados.agregar(i);
        }
        ejecutarPrueba("PRUEBA 1: DATOS ORDENADOS (0-128)", datosOrdenados);

        // PRUEBA 2: DATOS ALEATORIOS
        MiListaDoble<Integer> datosAzar = new MiListaDoble<>();
        int[] valoresAzar = {64, 32, 96, 16, 48, 80, 112, 110, 5, 20, 100}; // Inserta valores específicos
        for (int valor : valoresAzar) {
            datosAzar.agregar(valor);
        }
        ejecutarPrueba("PRUEBA 2: DATOS ALEATORIOS (MANUAL)", datosAzar);
    }

    private static void ejecutarPrueba(String titulo, MiListaDoble<Integer> datos) {
        // Recorremos la lista de datos.
        ArbolBinarioDeBusquedaEntero arbol = new ArbolBinarioDeBusquedaEntero();
        for (int i = 0; i < datos.size(); i++) {
            arbol.add(datos.getDatoEn(i));
        }
        // Imprimimos el encabezado de la prueba actual
        System.out.println("________________"+titulo+"________________");

        // Calculamos la suma total de los elementos.
        int sumaArbol = arbol.getSuma();
        System.out.println("Suma total: " + sumaArbol);

        // NUEVO: Validación de la suma de los subárboles y la raíz.
        int sumaRaiz = arbol.isVacio() ? 0 : arbol.getDatoRaiz();
        ArbolBinarioDeBusquedaEntero subIzquierdo = (ArbolBinarioDeBusquedaEntero) arbol.getSubArbolIzquierda();
        ArbolBinarioDeBusquedaEntero subDerecho = (ArbolBinarioDeBusquedaEntero) arbol.getSubArbolDerecha();

        int sumaIzquierda = subIzquierdo.getSuma();
        int sumaDerecha = subDerecho.getSuma();
        int sumaSubarboles = sumaRaiz + sumaIzquierda + sumaDerecha;

        System.out.println("Suma Raiz (" + sumaRaiz + ") + Sub. Izq. (" + sumaIzquierda + ") + Sub. Der. (" + sumaDerecha + "): " + sumaSubarboles);

        if (sumaArbol == sumaSubarboles) {
            System.out.println("¿La suma coincide con la suma de los subárboles?: SI");
        } else {
            System.out.println("¿La suma coincide con la suma de los subárboles?: NO");
        }

        // Extraemos las listas de los diferentes recorridos del árbol.
        MiListaDoble<Integer> listaCentral = arbol.getListaOrdenCentral();
        MiListaDoble<Integer> listaPre = arbol.getListaPreOrden();
        MiListaDoble<Integer> listaPost = arbol.getListaPostOrden();

        int sumaIn = listaCentral.obtenerSumaEnteros();
        int sumaPre = listaPre.obtenerSumaEnteros();
        int sumaPost = listaPost.obtenerSumaEnteros();

        System.out.println("Suma en Orden Central: " + sumaIn);
        System.out.println("Suma en Pre-Orden: " + sumaPre);
        System.out.println("Suma en Post-Orden: " + sumaPost);

        // Comprobamos si las sumas de los recorridos coinciden
        boolean coinciden = (sumaIn == sumaPre && sumaIn == sumaPost && sumaIn == sumaArbol);
        if (coinciden) {
            System.out.println("¿Todas las sumas coinciden?: SI");
        } else {
            System.out.println("¿Todas las sumas coinciden?: NO");
        }

        // Calculamos la altura del árbol.
        System.out.println("Altura del arbol: " + arbol.getAltura());

        // Buscamos el camino hacia el número 110.
        MiListaDoble<Integer> camino110 = arbol.getCamino(110);
        if (!camino110.estaVacia()) {
            System.out.println("Camino al 110: " + camino110.toString());
            System.out.println("Longitud del camino: " + (camino110.size() - 1));
        } else {
            System.out.println("El valor 110 no existe en este arbol.");
        }
        System.out.println();
    }
}