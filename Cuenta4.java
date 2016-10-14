package cuenta_corriente;

/**
 * @author Jose A. Manas
 * @version 14/3/2012
 */
public class Cuenta4 implements Cuenta {
    private volatile int saldo = 0;

    public synchronized int meter(int cantidad) {
        String id = Thread.currentThread().getName();
        try {
            int total = saldo + cantidad;
            System.out.printf("%s: %d + %d -> %d%n",
                    id, saldo, cantidad, total);
            Thread.sleep(RETARDO);
            saldo = total;
        } catch (InterruptedException e) {
            System.err.println(id + ": " + e);
        }
        return saldo;
    }

    public synchronized int sacar(int cantidad) throws Exception {
        String id = Thread.currentThread().getName();
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
        }
        return saldo;
    }

}
