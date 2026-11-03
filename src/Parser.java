
import java.util.List;
import java.util.Optional;
import AST.compare;
import AST.compareOp;
import AST.findOne;
import AST.valueList;
import AST.value;
import AST.values;
import AST.insert;
import AST.delete;
import AST.from;
import AST.where;
import AST.return_;
import AST.returnName;
import AST.create;
import AST.field;
import AST.type;

public class Parser
{
    private TokenManager tokenManager; //keeps track of tokens from the lexer

    public Parser(List<Token> tokens) //gets the tokens from the lexer
    {
        tokenManager = new TokenManager(tokens);
    }

    public Optional<findOne> parse() throws SyntaxErrorException
    {
        return findone();
    }

    private void RequireNewLine() throws SyntaxErrorException //makes sure theres a new line
    {
        if(tokenManager.MatchAndRemove(Token.TokenTypes.NEWLINE).isEmpty())
        {
            throw new SyntaxErrorException
                    ("Expected a newline", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        while(tokenManager.MatchAndRemove(Token.TokenTypes.NEWLINE).isPresent()) //keeps removing newlines until theres none left
        {

        }
    }

    private Optional<compareOp> parseCompareOp()
    {
        if(tokenManager.MatchAndRemove(Token.TokenTypes.EQUAL).isPresent()) //checks what compare op is next
        {
            return Optional.of(compareOp.equal);
        }
        if(tokenManager.MatchAndRemove(Token.TokenTypes.LESSTHAN).isPresent())
        {
            return Optional.of(compareOp.lessthan);
        }
        if(tokenManager.MatchAndRemove(Token.TokenTypes.GREATERTHAN).isPresent())
        {
            return Optional.of(compareOp.greaterthan);
        }
        if(tokenManager.MatchAndRemove(Token.TokenTypes.NOTEQUAL).isPresent())
        {
            return Optional.of(compareOp.notequal);
        }
        if(tokenManager.MatchAndRemove(Token.TokenTypes.LESSTHANEQUAL).isPresent())
        {
            return Optional.of(compareOp.lessthanequal);
        }
        if(tokenManager.MatchAndRemove(Token.TokenTypes.GREATERTHANEQUAL).isPresent())
        {
            return Optional.of(compareOp.greaterthanequal);
        }
        return Optional.empty();
    }

    private Optional<compare> compare() throws SyntaxErrorException
    {
        Optional<Token> left = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER);

        if(left.isEmpty())
        {
            return Optional.empty();
        }

        Optional<compareOp> operator = parseCompareOp(); //gets the compare operator

        if(operator.isEmpty())
        {
            throw new SyntaxErrorException
                    ("Expected a comparison operator", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        compare newCompare = new compare(left.get().Value.orElse(""), operator.get()); //creates new comp obj
        Optional<Token> number = tokenManager.MatchAndRemove(Token.TokenTypes.NUMBER);

        if(number.isPresent())
        {
            newCompare.NUMBER = number.get().Value;
            return Optional.of(newCompare);
        }

        Optional<Token> string = tokenManager.MatchAndRemove(Token.TokenTypes.STRINGLITERAL); //checks for a string

        if(string.isPresent())
        {
            newCompare.STRINGLITERAL = string.get().Value;
            return Optional.of(newCompare);
        }

        Optional<Token> identifier = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER); //checks for an identifier

        if(identifier.isPresent())
        {
            newCompare.IDENTIFIER = identifier.get().Value;
            return Optional.of(newCompare);
        }

        Optional<findOne> foundFindOne = findone(); //checks for a findOne

        if(foundFindOne.isPresent())
        {
            newCompare.findOne = foundFindOne;
            return Optional.of(newCompare);
        }

        throw new SyntaxErrorException(
                "Expected a value after comparison operator", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber()
        );
    }

    private Optional<findOne> findone() throws SyntaxErrorException //"findOne", "(" compare ("," compare)* ")"
    {
        if(tokenManager.MatchAndRemove(Token.TokenTypes.FINDONE).isEmpty()) //if it doesnt start with findOne, its not a findone
        {
            return Optional.empty();
        }

        if(tokenManager.MatchAndRemove(Token.TokenTypes.OPENPAREN).isEmpty())
        {
            throw new SyntaxErrorException("Expected ( after findOne", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        findOne newFindOne = new findOne();

        Optional<compare> firstCompare = compare(); //gets the first comparison

        if(firstCompare.isEmpty())
        {
            throw new SyntaxErrorException("Expected a comparison in findOne", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        newFindOne.compare.add(firstCompare.get());

        while(tokenManager.MatchAndRemove(Token.TokenTypes.COMMA).isPresent()) //keeps getting comparisons if theres commas
        {
            Optional<compare> nextCompare = compare();

            if(nextCompare.isEmpty())
            {
                throw new SyntaxErrorException("Expected a comparison after comma", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newFindOne.compare.add(nextCompare.get());
        }

        if(tokenManager.MatchAndRemove(Token.TokenTypes.CLOSEPAREN).isEmpty()) //findOne needs a closing pareth
        {
            throw new SyntaxErrorException("Expected ) after findOne", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        return Optional.of(newFindOne);
    }

    private Optional<valueList> valueList() throws SyntaxErrorException
    {
        if(tokenManager.MatchAndRemove(Token.TokenTypes.LEFTBRACE).isEmpty()) //if theres no [
        {
            return Optional.empty();
        }

        valueList newValueList = new valueList();

        if(tokenManager.MatchAndRemove(Token.TokenTypes.RIGHTBRACE).isPresent()) //aloows for empty list
        {
            return Optional.of(newValueList);
        }

        Optional<findOne> firstFindOne = findone(); //gets the first findOne

        if(firstFindOne.isEmpty())
        {
            throw new SyntaxErrorException("Expected findOne in value list", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        newValueList.findOne.add(firstFindOne.get());

        while(tokenManager.MatchAndRemove(Token.TokenTypes.COMMA).isPresent()) //gets more findones if theres comma
        {
            Optional<findOne> nextFindOne = findone();

            if(nextFindOne.isEmpty())
            {
                throw new SyntaxErrorException("Expected findOne after comma", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newValueList.findOne.add(nextFindOne.get());
        }

        if(tokenManager.MatchAndRemove(Token.TokenTypes.RIGHTBRACE).isEmpty()) //needs a closing ]
        {
            throw new SyntaxErrorException("Expected ] after value list", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        return Optional.of(newValueList);
    }

    private Optional<value> value() throws SyntaxErrorException
    {
        value newValue = new value(); //creates a new value obj

        Optional<Token> number = tokenManager.MatchAndRemove(Token.TokenTypes.NUMBER); //checks for a number

        if(number.isPresent())
        {
            newValue.NUMBER = number.get().Value;
            return Optional.of(newValue);
        }

        Optional<Token> string = tokenManager.MatchAndRemove(Token.TokenTypes.STRINGLITERAL); //checks for a string

        if(string.isPresent())
        {
            newValue.STRINGLITERAL = string.get().Value;
            return Optional.of(newValue);
        }

        Optional<Token> identifier = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER); //checks for an identifier

        if(identifier.isPresent())
        {
            newValue.name = identifier.get().Value;
            return Optional.of(newValue);
        }

        Optional<findOne> foundFindOne = findone(); //checks for a findOne

        if(foundFindOne.isPresent())
        {
            newValue.findOne = foundFindOne;
            return Optional.of(newValue);
        }

        Optional<valueList> foundValueList = valueList(); //checks for a valueList

        if(foundValueList.isPresent())
        {
            newValue.valueList = foundValueList;
            return Optional.of(newValue);
        }

        return Optional.empty(); //if its not a value
    }

    private Optional<values> values() throws SyntaxErrorException
    {
        Optional<value> firstValue = value(); //gets the first value

        if(firstValue.isEmpty()) //if theres no first value its not values
        {
            return Optional.empty();
        }

        values newValues = new values(); //creates a new values obj
        newValues.value.add(firstValue.get()); //adds the first value to the list

        while(tokenManager.MatchAndRemove(Token.TokenTypes.COMMA).isPresent()) //keeps getting values if theres commas
        {
            Optional<value> nextValue = value();

            if(nextValue.isEmpty())
            {
                throw new SyntaxErrorException("Expected a value after comma", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newValues.value.add(nextValue.get()); //adds the next value to the list
        }

        return Optional.of(newValues);
    }

    private Optional<insert> insert() throws SyntaxErrorException
    {
        if(tokenManager.MatchAndRemove(Token.TokenTypes.INSERT).isEmpty()) //if theres no insert its not an insert
        {
            return Optional.empty();
        }

        Optional<Token> name = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER); //gets the table name

        if(name.isEmpty())
        {
            throw new SyntaxErrorException("Expected a table name after insert", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        insert newInsert = new insert(name.get().Value.orElse("")); //creates a new insert obj

        RequireNewLine(); //makes sure theres a newline

        if(tokenManager.MatchAndRemove(Token.TokenTypes.INDENT).isEmpty()) //checks for an indent
        {
            throw new SyntaxErrorException("Expected an indent after insert", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        Optional<values> firstValues = values(); //gets the first row of values

        if(firstValues.isEmpty())
        {
            throw new SyntaxErrorException("Expected values after insert", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        newInsert.values.add(firstValues.get()); //adds the first row

        while(true) //keeps checking for more rows
        {
            while(tokenManager.MatchAndRemove(Token.TokenTypes.NEWLINE).isPresent()) //skips newlines
            {

            }

            if(tokenManager.MatchAndRemove(Token.TokenTypes.DEDENT).isPresent()) //stops when the indent ends
            {
                break;
            }

            Optional<values> nextValues = values(); //gets the next row

            if(nextValues.isEmpty())
            {
                throw new SyntaxErrorException("Expected another row or dedent", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newInsert.values.add(nextValues.get()); //adds the next row
        }

        return Optional.of(newInsert);
    }

    private Optional<delete> delete() throws SyntaxErrorException
    {
        if(tokenManager.MatchAndRemove(Token.TokenTypes.DELETE).isEmpty()) //if theres no delete its not a delete
        {
            return Optional.empty();
        }

        Optional<Token> name = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER); //gets the table name

        if(name.isEmpty())
        {
            throw new SyntaxErrorException("Expected a table name after delete", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        delete newDelete = new delete(name.get().Value.orElse("")); //creates a new delete obj

        RequireNewLine(); //makes sure theres a newline

        if(tokenManager.MatchAndRemove(Token.TokenTypes.INDENT).isEmpty()) //checks for an indent
        {
            throw new SyntaxErrorException("Expected an indent after delete", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        Optional<findOne> firstFindOne = findone(); //checks for a findOne

        if(firstFindOne.isPresent())
        {
            newDelete.findOne.add(firstFindOne.get()); //adds the findOne
            values row = new values();
            value item = new value();
            item.findOne = firstFindOne;
            row.value.add(item);
            newDelete.values.add(row);
        }
        else
        {
            Optional<values> firstValues = values(); //checks for values

            if(firstValues.isEmpty())
            {
                throw new SyntaxErrorException("Expected values or findOne after delete", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newDelete.values.add(firstValues.get()); //adds the values
            for(value item : firstValues.get().value)
            {
                item.findOne.ifPresent(newDelete.findOne::add);
            }
        }

        while(true) //keeps checking for more rows
        {
            while(tokenManager.MatchAndRemove(Token.TokenTypes.NEWLINE).isPresent()) //skips newlines
            {

            }

            if(tokenManager.MatchAndRemove(Token.TokenTypes.DEDENT).isPresent()) //stops when the indent ends
            {
                break;
            }

            Optional<findOne> nextFindOne = findone(); //checks for another findOne

            if(nextFindOne.isPresent())
            {
                newDelete.findOne.add(nextFindOne.get()); //adds the next findOne
                values row = new values();
                value item = new value();
                item.findOne = nextFindOne;
                row.value.add(item);
                newDelete.values.add(row);
            }
            else
            {
                Optional<values> nextValues = values(); //checks for another row of values

                if(nextValues.isEmpty())
                {
                    throw new SyntaxErrorException("Expected another row or dedent", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
                }

                newDelete.values.add(nextValues.get()); //adds the next row
                for(value item : nextValues.get().value)
                {
                    item.findOne.ifPresent(newDelete.findOne::add);
                }
            }
        }

        return Optional.of(newDelete);
    }

    public Optional<insert> parseInsert() throws SyntaxErrorException
    {
        return insert(); //calls the insert method
    }

    public Optional<delete> parseDelete() throws SyntaxErrorException
    {
        return delete(); //calls the delete method
    }


    public Optional<AST.sharQL> sharQL() throws SyntaxErrorException
    {
        AST.sharQL program = new AST.sharQL(); //creates the full program

        while(true) //keeps reading statements
        {
            while(tokenManager.MatchAndRemove(Token.TokenTypes.NEWLINE).isPresent())
            {
                //skips extra newlines
            }

            if(tokenManager.Peek(0).isEmpty()) //checks if we reached the end
            {
                break;
            }

            Optional<AST.statement> nextStatement = statement(); //gets the next statement

            if(nextStatement.isEmpty())
            {
                throw new SyntaxErrorException(
                        "Expected a statement",
                        tokenManager.getCurrentLine(),
                        tokenManager.getCurrentColumnNumber());
            }

            program.statement.add(nextStatement.get()); //adds statement to program
        }

        return Optional.of(program);
    }



    private Optional<from> from() throws SyntaxErrorException
    {
        if(tokenManager.MatchAndRemove(Token.TokenTypes.FROM).isEmpty()) //checks for from
        {
            return Optional.empty();
        }

        Optional<Token> name = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER); //gets table name

        if(name.isEmpty())
        {
            throw new SyntaxErrorException("Expected table name after from", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        from newFrom = new from(name.get().Value.orElse("")); //creates from object

        RequireNewLine();

        if(tokenManager.MatchAndRemove(Token.TokenTypes.INDENT).isEmpty())
        {
            return Optional.of(newFrom); //where and return are optional
        }

        if(tokenManager.MatchAndRemove(Token.TokenTypes.WHERE).isPresent())
        {
            where newWhere = new where();

            Optional<compare> firstCompare = compare();

            if(firstCompare.isEmpty())
            {
                throw new SyntaxErrorException("Expected comparison after where", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newWhere.compare.add(firstCompare.get());

            while(tokenManager.MatchAndRemove(Token.TokenTypes.COMMA).isPresent())
            {
                Optional<compare> nextCompare = compare();

                if(nextCompare.isEmpty())
                {
                    throw new SyntaxErrorException("Expected comparison after comma", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
                }

                newWhere.compare.add(nextCompare.get());
            }

            newFrom.where = Optional.of(newWhere);
            RequireNewLine();
        }

        if(tokenManager.MatchAndRemove(Token.TokenTypes.RETURN).isPresent())
        {
            return_ newReturn = new return_();

            newReturn.returnName.add(returnName());

            while(tokenManager.MatchAndRemove(Token.TokenTypes.COMMA).isPresent())
            {
                newReturn.returnName.add(returnName());
            }

            newFrom.return_ = Optional.of(newReturn);
            RequireNewLine();
        }

        while(tokenManager.MatchAndRemove(Token.TokenTypes.NEWLINE).isPresent())
        {
        }

        if(tokenManager.MatchAndRemove(Token.TokenTypes.DEDENT).isEmpty())
        {
            throw new SyntaxErrorException("Expected dedent after from", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        return Optional.of(newFrom);
    }

    private returnName returnName() throws SyntaxErrorException
    {
        Optional<Token> name = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER);

        if(name.isEmpty())
        {
            throw new SyntaxErrorException("Expected name after return", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        returnName newReturnName = new returnName(name.get().Value.orElse(""));

        while(tokenManager.MatchAndRemove(Token.TokenTypes.DOT).isPresent())
        {
            Optional<Token> field = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER);

            if(field.isEmpty())
            {
                throw new SyntaxErrorException("Expected field after dot", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newReturnName.field.add(field.get().Value.orElse(""));
        }

        return newReturnName;
    }


    private Optional<create> create() throws SyntaxErrorException
    {
        if(tokenManager.MatchAndRemove(Token.TokenTypes.CREATE).isEmpty()) //checks for create
        {
            return Optional.empty();
        }

        if(tokenManager.MatchAndRemove(Token.TokenTypes.TABLE).isEmpty())
        {
            throw new SyntaxErrorException("Expected table after create", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        Optional<Token> tableName = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER); //gets table name

        if(tableName.isEmpty())
        {
            throw new SyntaxErrorException("Expected table name", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        create newCreate = new create(tableName.get().Value.orElse("")); //creates a new create object

        RequireNewLine();

        if(tokenManager.MatchAndRemove(Token.TokenTypes.INDENT).isEmpty())
        {
            throw new SyntaxErrorException("Expected indent after create table", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        while(true) //keeps reading fields until the table ends
        {
            while(tokenManager.MatchAndRemove(Token.TokenTypes.NEWLINE).isPresent())
            {
            }

            if(tokenManager.MatchAndRemove(Token.TokenTypes.DEDENT).isPresent())
            {
                break;
            }

            if(tokenManager.Peek(0).isEmpty())
            {
                throw new SyntaxErrorException("Expected a field or dedent", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newCreate.field.add(field()); //adds each field to the table
        }

        return Optional.of(newCreate);
    }

    private field field() throws SyntaxErrorException
    {
        type newType = new type(); //creates a new type object

        if(tokenManager.MatchAndRemove(Token.TokenTypes.STRING).isPresent())
        {
            newType.thestring_ORwholeNumberORdecimalNumber =
                    Optional.of(AST.string_ORwholeNumberORdecimalNumber.string_);
        }
        else if(tokenManager.MatchAndRemove(Token.TokenTypes.WHOLENUMBER).isPresent())
        {
            newType.thestring_ORwholeNumberORdecimalNumber =
                    Optional.of(AST.string_ORwholeNumberORdecimalNumber.wholeNumber);
        }
        else if(tokenManager.MatchAndRemove(Token.TokenTypes.DECIMALNUMBER).isPresent())
        {
            newType.thestring_ORwholeNumberORdecimalNumber =
                    Optional.of(AST.string_ORwholeNumberORdecimalNumber.decimalNumber);
        }
        else if(tokenManager.MatchAndRemove(Token.TokenTypes.ENUM).isPresent())
        {
            newType.enum_ = true; //this field is an enum

            if(tokenManager.MatchAndRemove(Token.TokenTypes.OPENPAREN).isEmpty())
            {
                throw new SyntaxErrorException("Expected ( after enum", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            Optional<Token> enumValue = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER);

            if(enumValue.isEmpty())
            {
                throw new SyntaxErrorException("Expected enum value", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newType.enumValue.add(enumValue.get().Value.orElse(""));

            while(tokenManager.MatchAndRemove(Token.TokenTypes.COMMA).isPresent())
            {
                enumValue = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER);

                if(enumValue.isEmpty())
                {
                    throw new SyntaxErrorException("Expected enum value after comma", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
                }

                newType.enumValue.add(enumValue.get().Value.orElse(""));
            }

            if(tokenManager.MatchAndRemove(Token.TokenTypes.CLOSEPAREN).isEmpty())
            {
                throw new SyntaxErrorException("Expected ) after enum", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }
        }
        else if(tokenManager.MatchAndRemove(Token.TokenTypes.LIST).isPresent())
        {
            newType.list = true; //this field is a list

            if(tokenManager.MatchAndRemove(Token.TokenTypes.LESSTHAN).isEmpty())
            {
                throw new SyntaxErrorException("Expected < after list", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            Optional<Token> tableName = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER);

            if(tableName.isEmpty())
            {
                throw new SyntaxErrorException("Expected table name in list", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newType.tableName.add(tableName.get().Value.orElse(""));

            if(tokenManager.MatchAndRemove(Token.TokenTypes.GREATERTHAN).isEmpty())
            {
                throw new SyntaxErrorException("Expected > after list", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }
        }
        else
        {
            Optional<Token> tableName = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER);

            if(tableName.isEmpty())
            {
                throw new SyntaxErrorException("Expected field type", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
            }

            newType.tableName.add(tableName.get().Value.orElse(""));
        }

        Optional<Token> fieldName = tokenManager.MatchAndRemove(Token.TokenTypes.IDENTIFIER); //gets field name

        if(fieldName.isEmpty())
        {
            throw new SyntaxErrorException("Expected field name", tokenManager.getCurrentLine(), tokenManager.getCurrentColumnNumber());
        }

        return new field(newType, fieldName.get().Value.orElse(""));
    }


    private Optional<AST.statement> statement() throws SyntaxErrorException
    {
        Optional<Token> next = tokenManager.Peek(0); //checks the next token

        if(next.isEmpty())
        {
            return Optional.empty();
        }

        AST.statement newStatement = new AST.statement(); //creates a statement object

        if(next.get().Type == Token.TokenTypes.CREATE)
        {
            newStatement.create = create();
        }
        else if(next.get().Type == Token.TokenTypes.INSERT)
        {
            newStatement.insert = insert();
        }
        else if(next.get().Type == Token.TokenTypes.DELETE)
        {
            newStatement.delete = delete();
        }
        else if(next.get().Type == Token.TokenTypes.FROM)
        {
            newStatement.from = from();
        }
        else
        {
            throw new SyntaxErrorException(
                    "Expected create, insert, delete, or from",
                    tokenManager.getCurrentLine(),
                    tokenManager.getCurrentColumnNumber());
        }

        return Optional.of(newStatement);
    }


}
