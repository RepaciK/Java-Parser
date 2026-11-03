import java.util.List;
import java.util.Optional;

public class TokenManager
{
    private List<Token> tokens; //stores the tokens from the lexer

    public TokenManager(List<Token> tokens) //gets list of tokens
    {
        this.tokens = tokens;
    }

    public Optional<Token> MatchAndRemove(Token.TokenTypes type)
    {
        if(tokens.isEmpty()) //checks if any tokens are left
        {
            return Optional.empty();
        }

        if(tokens.get(0).Type == type) //checks if the first token type is what were looking for
        {
            return Optional.of(tokens.remove(0));
        }
        return Optional.empty();
    }

    public Optional<Token> Peek(int index) //makes sure it doesnt look past the token list
    {
        if(index < 0 || index >= tokens.size())
        {
            return Optional.empty();
        }
        return Optional.of(tokens.get(index));
    }

    public boolean nextTwoTokensMatch(Token.TokenTypes first, Token.TokenTypes second)
    {
        if(tokens.size() < 2) //makes sure theres at least 2 tokens
        {
            return false;
        }

        return tokens.get(0).Type == first && tokens.get(1).Type == second;
    }

    public int getCurrentLine()
    {
        if(tokens.isEmpty()) //returns line of first token
        {
            return 0;
        }
        return tokens.get(0).LineNumber;
    }

    public int getCurrentColumnNumber()
    {
        if(tokens.isEmpty())
        {
            return 0;
        }
        return tokens.get(0).ColumnNumber;
    }
}

