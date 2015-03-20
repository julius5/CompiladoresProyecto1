
package mx.uach.compiladores.proyecto;

import java.util.ArrayList;

/**
 * clase del Analizador de sintactico.
 *
 */
    public class Parser {
    
    private ArrayList<Token> token;

    private int next = 0;
    private Token currentToken;

    private String output = "";

    /**
     * salida de datos
     *
     * @return La salida del analizador.
     */
    public String getOutput()
    {
        return output;
    }

    /**
     * Inicia el analisis.
     *
     * @param token token a analizar.
     */
    public void parse(ArrayList<Token> tokens)
    {
        this.token = tokens;
        currentToken = tokens.get(next++);
        prog();
    }

    /**
     * Verifica la sintaxis
     */
    private void prog()
    {
        conj();

        if (currentToken.getTipo() != Token.Type.FINARCH)
        {
            throw new Error
            (   String.format
                (   "Error de sintaxis. Dato: %s ~~~ Se esperaba: %s",
                    currentToken.getDatos(), (char) Token.Type.FINARCH
                )
            );
        }
    }

    /**
     * Verifica la sintaxis
     */
    private void conj()
    {
        prod();

        while (currentToken.getTipo() == Token.Type.FIN)
        {
            output = String.format("%s\n", output);

            currentToken = token.get(next++);
            if (currentToken.getTipo() == Token.Type.FINARCH)
            {
                break;
            }

            prod();
        }
    }

    /**
     * Verifica la sintaxis
     */
    private void prod()
    {
        if (currentToken.getTipo() == Token.Type.VARIABLE)
        {
            output = String.format("%s%s", output, currentToken.getDatos());
        }
        else
        {
            throw new Error
            (   "Error de sintaxis. "
            );
        }

        currentToken = token.get(next++);
        if (currentToken.getTipo() == Token.Type.DEF)
        {
            currentToken = token.get(next++);
            expr();

            output = String.format("%s%s", output, "::=");
        }
        else
        {
            throw new Error("Error de sintaxis. Se esperaba: ::=");
        }
    }

    /**
     * Verifica la sintaxis
     */
    private void expr()
    {
        term();

        while (currentToken.getTipo() == Token.Type.CONJ)
        {
            currentToken = token.get(next++);
            expr();

            output = String.format("%s%s", output, (char) Token.Type.CONJ);
        }
    }

    /**
     * Verifica la sintaxis
     */
    private void term()
    {
        fact();

        currentToken = token.get(next++);
        while (currentToken.getTipo() == Token.Type.CONJ)
        {
            output = String.format("%s%s", output, (char) Token.Type.ADIC);

            currentToken = token.get(next++);
            term();
        }
    }

    /**
     * Verifica la sintaxis
     */
    private void fact()
    {
        if (currentToken.getTipo() == Token.Type.LLAVEDER)
        {
            currentToken = token.get(next++);
            expr();

            if (currentToken.getTipo() == Token.Type.LLAVEIZQ)
            {
                output = String.format("%s%s", output, currentToken.getDatos());
            }
            else
            {
                throw new Error
                (   String.format
                    (   "Error de sintaxis. Dato: %s ~~~ Se esperaba: %s",
                        currentToken.getDatos(), (char) Token.Type.LLAVEDER
                    )
                );
            }
        }
        else if (currentToken.getTipo() == Token.Type.LLAVEIZQ)
        {
            currentToken = token.get(next++);
            expr();

            if (currentToken.getTipo() == Token.Type.CORDER)
            {
                output = String.format("%s%s", output, currentToken.getDatos());
            }
            else
            {
                throw new Error
                (   String.format
                    (   "Error de sintaxis. Dato: %s ~~~ Se esperaba: %s",
                        currentToken.getDatos(), (char) Token.Type.CORDER
                    )
                );
            }
        }
        else
        {
            prim();
        }
    }

    /**
     * Verifica la sintaxis
     */
    private void prim()
    {
        if (currentToken.getTipo() == Token.Type.PARIZQ)
        {
            currentToken = token.get(next++);
            expr();

            if (currentToken.getTipo() == Token.Type.PARDER)
            {
                output = String.format("%s%s", output, currentToken.getDatos());
            }
            else
            {
                throw new Error
                (   String.format
                    (   "Error de sintaxis. Dato: %s ~~~ Se esperaba: %s",
                        currentToken.getDatos(), (char) Token.Type.PARDER
                    )
                );
            }
        }
        else if (currentToken.getTipo() == Token.Type.VARIABLE
              || currentToken.getTipo() == Token.Type.TERMINO)
        {
            output = String.format("%s%s", output, currentToken.getDatos());
        }
        else
        {
            throw new Error
            (   String.format
                (   "Error de sintaxis. Se tiene: %s ~~~ ",
                    currentToken.getDatos()
                )
            )
                ;
        }
    }
    
}
