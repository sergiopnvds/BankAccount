package cuenta_corriente;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jose A. Manas
 * @version 21/2/2012
 */
public class CuentaTest
        extends TestCase {
    private static final Random random = new Random();

    public void test001()
            throws InterruptedException {
        int[][] acciones = new int[][]{
                {100, 100, 100, 100, 100},
                {100, 100, 100, 100, 100}
        };
        doit(acciones);
    }
    public void test002()
            throws InterruptedException {
        int[][] acciones = new int[][]{
                {1000, -100, -100, -100, -100},
                {-100, -100, -100, -100, -100}
        };
        doit(acciones);
    }

    public void test003()
            throws InterruptedException {
        int[][] acciones = new int[][]{
                {100, -100, 100, -100, 100},
                {100, -100, 100, -100, 100}
        };
        doit(acciones);
    }

    public void test101()
            throws InterruptedException {
        int[][] acciones = new int[][]{
                {-200, 100},
                {100, 100, 100, 100, 100}
        };
        doit(acciones);
    }

    private void doit(int[][] acciones) throws InterruptedException {
        Cuenta cuenta = new Cuenta1();
//        Cuenta cuenta = new Cuenta2();
//        Cuenta cuenta = new Cuenta3();
//        Cuenta cuenta = new Cuenta4();
        List<Cliente> clientes = new ArrayList<Cliente>();

        for (int i = 0; i < acciones.length; i++) {
            String id = String.valueOf(i);
            int[] cantidades = acciones[i];
            clientes.add(new Cliente(id, cuenta, cantidades));
        }

        for (Cliente cliente : clientes)
            cliente.start();

        for (Cliente cliente : clientes)
            cliente.join();


        int esperado = 0;
        for (int[] cantidades : acciones) {
            for (int n : cantidades)
                esperado += n;
        }
        assertEquals(esperado, cuenta.meter(0));
    }
}
