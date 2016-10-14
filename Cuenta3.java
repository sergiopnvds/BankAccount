package cuenta_corriente;

import java.util.concurrent.Semaphore;

/**
 * @author Jose A. Manas
 * @version 14/3/2012
 */
public class Cuenta3 implements Cuenta {
    private volatile int saldo = 0;

    private Semaphore semaforo = new Semaphore(1);

    public int meter(int cantidad) {
        String id = Thread.currentThread().getName();
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            System.err.println(id + ": " + e);
            return saldo;
        }

        try {
            int total = saldo + cantidad;
            System.out.printf("%s: %d + %d -> %d%n",
                    id, saldo, cantidad, total);
            Thread.sleep(RETARDO);
            saldo = total;
        } catch (InterruptedException e) {
            System.err.println(id + ": " + e);
        } finally {
            semaforo.release();
        }
        return saldo;
    }

    public int sacar(int cantidad) throws Exception {
        String id = Thread.currentThread().getName();
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            System.err.println(id + ": " + e);
            return saldo;
        }
        try {
            if (saldo < cantidad) {
                System.out.printf("%s: %d < %d%n",
                        id, saldo, cantidad);
                throw new Exception();
            }
            int total = saldo - cantidad;
            System.out.printf("%s: %d - %d -> %d%n",
                    id, saldo, cantidad, total);
            Thread.sleep(RETARDO);
            saldo = total;
        } catch (InterruptedException e) {
            System.err.println(id + ": " + e);
        } finally {
            semaforo.release();
        }
        return saldo;
    }

}
