import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Lexer
{
    private TextManager textManager; //to read one char at a time
    private HashMap<String, Token.TokenTypes> keywords; //stores the keywords
    private HashMap<String, Token.TokenTypes> punctuation; //stores puncuation
    private int indentationLevel; //keeps track of current indentation levels

    public Lexer(String text) //creates lexer with given text
    {
        textManager = new TextManager(text);
        keywords = new HashMap<>();
        punctuation = new HashMap<>();
        indentationLevel = 0;

        //SharQL keywords -------------------------------------
        keywords.put("create", Token.TokenTypes.CREATE);
        keywords.put("table", Token.TokenTypes.TABLE);
        keywords.put("wholeNumber", Token.TokenTypes.WHOLENUMBER);
        keywords.put("decimalNumber", Token.TokenTypes.DECIMALNUMBER);
        keywords.put("string", Token.TokenTypes.STRING);
        keywords.put("enum", Token.TokenTypes.ENUM);
        keywords.put("list", Token.TokenTypes.LIST);
        keywords.put("insert", Token.TokenTypes.INSERT);
        keywords.put("findOne", Token.TokenTypes.FINDONE);
        keywords.put("delete", Token.TokenTypes.DELETE);
        keywords.put("from", Token.TokenTypes.FROM);
        keywords.put("where", Token.TokenTypes.WHERE);
        keywords.put("return", Token.TokenTypes.RETURN);

        //SharQL punctuation ----------------------------------
        punctuation.put("(", Token.TokenTypes.OPENPAREN);
        punctuation.put(",", Token.TokenTypes.COMMA);
        punctuation.put(")", Token.TokenTypes.CLOSEPAREN);
        punctuation.put("<", Token.TokenTypes.LESSTHAN);
        punctuation.put(">", Token.TokenTypes.GREATERTHAN);
        punctuation.put("[", Token.TokenTypes.LEFTBRACE);
        punctuation.put("]", Token.TokenTypes.RIGHTBRACE);
        punctuation.put("=", Token.TokenTypes.EQUAL);
        punctuation.put("<>", Token.TokenTypes.NOTEQUAL);
        punctuation.put("<=", Token.TokenTypes.LESSTHANEQUAL);
        punctuation.put(">=", Token.TokenTypes.GREATERTHANEQUAL);
        punctuation.put(".", Token.TokenTypes.DOT);
    }

    public List<Token> Lex() throws SyntaxErrorException //creates tokens, holds tokens, & add tokens to list
    {
        List<Token> tokens = new LinkedList<>();

        while(!textManager.isAtEnd())
        {
            char current = textManager.peekCharacter();

            if(current == '/' && textManager.hasNextCharacter() && textManager.peekCharacter(1) == '*')
            {
                readComment();
            }
            else if(Character.isLetter(current))
            {
                tokens.add(readWord());
            }
            else if(Character.isDigit(current)) //checks if char is a num
            {
                tokens.add(readNumber());
            }
            else if(punctuation.containsKey(String.valueOf(current)))
            {
                tokens.add(readPunctuation());
            }
            else if(current == '"') //checks if string is starting
            {
                tokens.add(readString());
            }
            else if(current == ' ') //ignores the spaces
            {
                textManager.getCharacter();
            }
            else if(current == '\n') //creates a NEWLINE token
            {
                int line = textManager.getLineNumber();
                int character = textManager.getCharacterPosition();

                textManager.getCharacter();
                tokens.add(new Token(Token.TokenTypes.NEWLINE, line, character));

                //checks the spaces at the start of the next line
                if(!textManager.isAtEnd() && textManager.peekCharacter() != '\n')
                {
                    int newIndentationLevel = readIndentation();

                    //adds an indent if the new line is farther in
                    if(newIndentationLevel > indentationLevel)
                    {
                        tokens.add(new Token(Token.TokenTypes.INDENT,
                                textManager.getLineNumber(),
                                textManager.getCharacterPosition()));

                        indentationLevel = newIndentationLevel;
                    }
                    //adds dedents if the new line goes back
                    else if(newIndentationLevel < indentationLevel)
                    {
                        while(indentationLevel > newIndentationLevel)
                        {
                            tokens.add(new Token(Token.TokenTypes.DEDENT,
                                    textManager.getLineNumber(),
                                    textManager.getCharacterPosition()));

                            indentationLevel--;
                        }
                    }
                }
            }
            else //moves past whatever isn't recongnized yet
            {
                textManager.getCharacter();
            }
        }

        //adds any dedents that are left when the file ends
        while(indentationLevel > 0)
        {
            tokens.add(new Token(Token.TokenTypes.DEDENT,
                    textManager.getLineNumber(),
                    textManager.getCharacterPosition()));

            indentationLevel--;
        }

        //adds one more newline when the file is finished
        tokens.add(new Token(Token.TokenTypes.NEWLINE,
                textManager.getLineNumber(),
                textManager.getCharacterPosition()));

        return tokens;
    }

    private Token readWord() //reads the words from SharQL text
    {
        int line = textManager.getLineNumber(); //where the word starts
        int character = textManager.getCharacterPosition(); //pos where the word starts

        StringBuilder word = new StringBuilder(); //stores the word

        while(!textManager.isAtEnd() && Character.isLetterOrDigit(textManager.peekCharacter()))
        {
            word.append(textManager.getCharacter());
        }

        String wordText = word.toString(); //turns StringBuilder into reg string

        if(keywords.containsKey(wordText))
        {
            return new Token(keywords.get(wordText), line, character);
        }

        return new Token(Token.TokenTypes.IDENTIFIER, line, character, wordText);
    }

    private Token readNumber() //reads a number from the SharQL text
    {
        int line = textManager.getLineNumber(); //where the number starts
        int character = textManager.getCharacterPosition(); //pos where the number starts

        StringBuilder number = new StringBuilder(); //stores the number
        boolean foundDecimal = false; //keeps track if e found a decimal

        while(!textManager.isAtEnd())
        {
            char current = textManager.peekCharacter();

            if(Character.isDigit(current))
            {
                number.append(textManager.getCharacter());
            }
            else if(current == '.' && !foundDecimal)
            {
                foundDecimal = true;
                number.append(textManager.getCharacter());
            }
            else
            {
                break;
            }
        }

        return new Token(Token.TokenTypes.NUMBER, line, character, number.toString());
    }

    private Token readPunctuation()
    {
        int line = textManager.getLineNumber(); //where the punc starts
        int character = textManager.getCharacterPosition();

        String first = String.valueOf(textManager.getCharacter());

        if(textManager.hasNextCharacter()) //checks if theres another char after this one
        {
            String second = String.valueOf(textManager.peekCharacter());
            String twoCharacters = first + second;

            if(punctuation.containsKey(twoCharacters))
            {
                textManager.getCharacter();
                return new Token(punctuation.get(twoCharacters), line, character);
            }
        }

        if(punctuation.containsKey(first)) //if not two chars, use single char
        {
            return new Token(punctuation.get(first), line, character);
        }

        return null;
    }

    private Token readString() throws SyntaxErrorException
    {
        int line = textManager.getLineNumber();
        int character = textManager.getCharacterPosition();

        StringBuilder string = new StringBuilder();
        textManager.getCharacter(); //moves past the opening quote

        while(!textManager.isAtEnd() && textManager.peekCharacter() != '"')
        {
            string.append(textManager.getCharacter());
        }

        if(textManager.isAtEnd())
        {
            throw new SyntaxErrorException("String was never closed", line, character);
        }

        textManager.getCharacter(); //moves past closing quote

        return new Token(Token.TokenTypes.STRINGLITERAL, line, character, string.toString());
    }

    private int readIndentation() throws SyntaxErrorException
    {
        int spaces = 0; //counts the num of spaces at start of line

        while(!textManager.isAtEnd())
        {
            char current = textManager.peekCharacter();

            if(current == ' ')
            {
                spaces++;
                textManager.getCharacter();
            }
            else if(current == '\t')
            {
                spaces += 4; //the tab button  is 4 spaces
                textManager.getCharacter();
            }
            else
            {
                break;
            }
        }

        if(spaces % 4 != 0)
        {
            throw new SyntaxErrorException("Indentation must be a multiple of 4 spaces", textManager.getLineNumber(), textManager.getCharacterPosition());
        }

        return spaces / 4;
    }

    private void readComment() throws SyntaxErrorException
    {
        int line = textManager.getLineNumber(); //gets where the comment starts
        int character = textManager.getCharacterPosition(); //gets the position it starts

        textManager.getCharacter(); //moves past /
        textManager.getCharacter(); //moves past *

        while(!textManager.isAtEnd())
        {
            char current = textManager.getCharacter();

            if(current == '*' && !textManager.isAtEnd() && textManager.peekCharacter() == '/') //checks if the end of the comment is found
            {
                textManager.getCharacter(); //moves past /
                return;
            }
        }

        throw new SyntaxErrorException("Comment was never closed", line, character);
    }


}