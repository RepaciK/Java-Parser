//code to test my lexer
import java.util.List;

public class LexerTest
{
    public static void main(String[] args) throws Exception
    {

        String code =
                "student class\n" +
                        "hours create table\n" +
                        "enum string wholeNumber schedule decimalNumber\n" +
                        "list insert findOne delete\n" +
                        "from where return professor\n";

        Lexer lexer = new Lexer(code); //creates the lexer

        List<Token> tokens = lexer.Lex(); //runs it and gets tokens

        for (Token token : tokens) //prints out each token
        {
            System.out.println(token);
        }
    }
}