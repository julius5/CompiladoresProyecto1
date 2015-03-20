
package mx.uach.compiladores.proyecto;

/**
 * @author Oswaldo Manuel Rios Flores / Alejandro Amaro Montes / Jesus Manuel
 * Garcia Rico
 * @since 14/03/2015
 * @version 1.0
 */
public class Token {
    
     /**
     * Tipos de datos para el tokens.
     *
     */
    public class Type
    {
        public static final int FINARCH = '.';
        public static final int FIN = ';';
        public static final int ADIC = '&';
        public static final int CONJ = '|';
        public static final int LLAVEDER = '}';
        public static final int LLAVEIZQ = '{';
        public static final int CORDER = ']';
        public static final int CORIZQ = '[';
        public static final int PARDER = ')';
        public static final int PARIZQ = '(';
        public static final int DEF = 800;
        public static final int TERMINO = 801;
        public static final int VARIABLE = 802;
    }

    private int tipo;
    private String datos;

    Token(int tipo, String datos)
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

    public void setData(String datos)
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
