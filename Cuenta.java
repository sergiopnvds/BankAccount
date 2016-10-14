package cuenta_corriente;

/**
 * @author Jose A. Manas
 * @version 14/3/2012
 */
public interface Cuenta {
    static final int RETARDO = 100;

    public int meter(int cantidad);

    public int sacar(int cantidad) throws Exception;

}
