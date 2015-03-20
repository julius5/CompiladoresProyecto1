/**
 * clase del Analizador de lexico.
 *
 * Obtiene una lista de tokens 
 * . ; & | { } [ ] '' <> ::=
 *
 * @author Oswaldo Manuel Rios Flores / Alejandro Amaro Montes / Jesus Manuel
 * Garcia Rico
 * @since 19/03/2015
 * @version 1.0
 *
 */
package mx.uach.compiladores.proyecto;

import java.util.ArrayList;

public class Lexer {
    
     /**
     * Transforma a tokens los datos recividos
     *
     * @param input datos.
     * @return tokens.
     */
    
    public static ArrayList<Token> getToken(String input)
    {
        // Eliminamos los espacios en blanco
        input = input.replaceAll("\\s", "");

        // Almacena la lista de tokens temporalmente.
        ArrayList<Token> token = new ArrayList<>();

        // Recorre la cadena de texto, letra por letra.
        for (int i = 0; i < input.length(); i++)
        {
            // Si el caracter no coincide con ningun token:
            if( input.charAt(i) != Token.Type.FINARCH
             && input.charAt(i) != Token.Type.FIN
             && input.charAt(i) != Token.Type.ADIC
             && input.charAt(i) != Token.Type.CONJ
             && input.charAt(i) != Token.Type.LLAVEDER
             && input.charAt(i) != Token.Type.LLAVEIZQ
             && input.charAt(i) != Token.Type.CORDER
             && input.charAt(i) != Token.Type.CORIZQ
             && input.charAt(i) != Token.Type.PARDER
             && input.charAt(i) != Token.Type.PARIZQ)
            {
                if (input.charAt(i) == ':'
                 && (i + 3) <= input.length() 
                 && input.charAt(i + 1) == ':'
                 && input.charAt(i + 2) == '=')
                {
                    token.add(new Token(Token.Type.DEF, "::="));
                    i += 2;
                }
                else if (input.charAt(i) == '\''
                      && input.indexOf('\'', i + 1) != -1)
                {
                    int ni = input.indexOf('\'', i + 1);
                    token.add
                    (   new Token
                        (   Token.Type.TERMINO,
                            input.substring
                            (   i,
                                ni + 1
                            )
                        )
                    );
                    i = ni;
                }
                else if (input.charAt(i) == '<'
                      && input.indexOf('>', i + 1) != -1
                      && (i + 1) < input.length() 
                      && Character.isLetter(input.charAt(i + 1)) == true)
                {
                    int ni = input.indexOf('>', i + 1);

                    String svar = input.substring(i, ni + 1);

                    for (int j = 1; j < svar.length() - 1; j++)
                    {
                        if (Character.isDigit(svar.charAt(j)) != true
                         && Character.isLetter(svar.charAt(j)) != true)
                        {
                            throw new Error
                            (   String.format("Error lexico en columna: %d", i)
                            );
                        }
                    }

                    token.add
                    (   new Token
                        (   Token.Type.VARIABLE,
                            svar
                        )
                    );
                    i = ni;
                }
                else
                {
                    throw new Error
                    (   String.format("Error lexico en columna: %d", i)
                    );
                }
            }
            else
            {
                token.add
                (   new Token
                    (   input.charAt(i),
                        String.valueOf(input.charAt(i))
                    )
                );
            }
        }

        return token;
    }
    
}
