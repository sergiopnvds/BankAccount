package cuenta_corriente;

import es.upm.dit.adsw.lab1.Tiempos;

/**
 * @author Jose A. Manas
 * @version 14/3/2012
 */
public class Cliente extends Thread {
    static final int RETARDO = 10;
    private final Cuenta cuenta;
    private final int[] cantidades;

    public Cliente(String id, Cuenta cuenta, int[] cantidades) {
        this.cantidades = cantidades;
        setName(id);
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        for (int n : cantidades) {
            if (n >= 0)
                mete(n);
            else
                saca(-n);
            Tiempos.sleep((long) (Math.random()* RETARDO));
        }
    }

    private void mete(int n) {
        cuenta.meter(n);
    }

    private void saca(int n) {
        while (true) {
            try {
                cuenta.sacar(n);
                return;
            } catch (Exception e) {
                Tiempos.sleep(50);
            }
        }
    }
}
