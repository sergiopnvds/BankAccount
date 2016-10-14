package cuenta_corriente;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jose A. Manas
 * @version 14/3/2012
 */
public class Cuenta2 implements Cuenta {
    private volatile int saldo = 0;

    private Lock llave = new ReentrantLock();

    public int meter(int cantidad) {
        String id = Thread.currentThread().getName();
        llave.lock();
        try {
            int total = saldo + cantidad;
            System.out.printf("%s: %d + %d -> %d%n",
                    id, saldo, cantidad, total);
            Thread.sleep(RETARDO);
            saldo = total;
        } catch (InterruptedException e) {
            System.err.println(id + ": " + e);
        } finally {
            llave.unlock();
        }
        return saldo;
    }

    public int sacar(int cantidad) throws Exception {
        String id = Thread.currentThread().getName();
        llave.lock();
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
            llave.unlock();
        }
        return saldo;
    }

}
