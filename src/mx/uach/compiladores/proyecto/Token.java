/**
 * 
 *
 * @author Oswaldo Manuel Rios Flores / Alejandro Amaro Montes / Jesus Manuel
 * Garcia Rico
 * @since 19/03/2015
 * @version 1.0
 *
 */
package mx.uach.compiladores.proyecto;


public class Token {
    
     /**
     * tipos de variables  token.
     *
     * @version 1.0
     * @since 11/03/15
     */
    public class Tipo
    {
        public static final int FARCHIVO = '.';
        public static final int FIN = ';';
        public static final int ADJUN = '&';
        public static final int CONCAT = '|';
        public static final int LLAVEDER = '}';
        public static final int LLAVEIZQ = '{';
        public static final int CORCHETEDER = ']';
        public static final int CORCHETEIZQ = '[';
        public static final int PARENTESISDER = ')';
        public static final int PARENTESISIZQ = '(';
        public static final int DEFINICION = 900;
        public static final int TERMINO = 901;
        public static final int VARIABLE = 902;
    }

    private int tipo;
    private String datos;

    Token(int type, String data)
    {
        this.tipo = tipo;
        this.datos = datos;
    }

    public int getTipo()
    {
        return this.tipo;
    }

    public void setTipo(int tipo)
    {
        this.tipo = tipo;
    }

    public String getDatos()
    {
        return this.datos;
    }

    public void setDatos(String datos)
    {
        this.datos = datos;
    }

    @Override
    public String toString()
    {
        return
            String.format
            (   "Tipo: %s ~~~ Datos: %s",
                this.tipo, this.datos
            );
    }
    
}
