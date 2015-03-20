
package mx.uach.compiladores.proyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase principal del compilador
 *
 * <ul>
 * <li>Prog -> ConjProds;</li>
 * <li>ConjProds -> ConjProds | Prod</li>
 * <li>Prod -> Variable DEF (Definicion) expr;</li>
 * <li>Expr -> Expr ALT (alternacion) Term |Term</li>
 * <li>Term -> Term & Factor | Factor</li>
 * <li>Factor -> {Expr}|[Expr]|Primario</li>
 * <li>Primario -> (Expr) |Variable|Terminal</li>
 * </ul>
 *
 * @author Oswaldo Manuel Rios Flores / Alejandro Amaro Montes / Jesus Manuel
 * Garcia Rico
 * @since 14/03/2015
 * @version 1.0
 *
 */
public class Compilador {
    
    public static void main(String[] args)
    {
        Parser parser = new Parser();
        parser.parse
        (   Lexer.getTokens
            (   getInputFrom("input")
            )
        );

        System.out.println(parser.getOutput());
    }

    /**
     * Obtiene los caracteres del archivo.
     *
     * @param file Este es el nombre del archivo a analizar.
     * @return Resultados luego de pasar por el compilador.
     */
    private static String getInputFrom(String file)
    {
        String text = "";
        FileReader rfile = null;
        String line = "";

        try
        {
            rfile = new FileReader(file);
            BufferedReader buff = new BufferedReader(rfile);
            while ((line = buff.readLine()) != null)
            {
                text = String.format("%s%s", text, line);
            }
        }
        catch (FileNotFoundException ex)
        {
            throw new RuntimeException("El archivo no se encontro");
        }
        catch (IOException ex)
        {
            throw new RuntimeException("Error de entrada o salida");
        }
        finally
        {
            if (rfile != null)
            {
                try
                {
                    rfile.close();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(
                            "El archivo no se cerro completamente");
                }
            }
        }

        return text;
    }
    
}
