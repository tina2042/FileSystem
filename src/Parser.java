
class Parser {
    public void parseAndExecute(String command, Context context) {
        String[] tokens = command.split(" ");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.equalsIgnoreCase("ls")) {
                new ListExpression().interpret(context);
            } else if (token.equalsIgnoreCase("cd")) {
                new ChangeDirectoryExpression(getNextToken(tokens, i)).interpret(context);
                i++;
            } else if (token.equalsIgnoreCase("mv")) {
                new MoveExpression(getNextToken(tokens, i), getNextToken(tokens, i + 1)).interpret(context);
                i+=2;
            } else if(token.equalsIgnoreCase("cat")) {
                new CatExpression(getNextToken(tokens, i)).interpret(context);
                i++;
            } else if(token.equalsIgnoreCase("mkdir")) {
                new MakeDirectoryExpression(getNextToken(tokens, i)).interpret(context);
                i++;
            } else if(token.equalsIgnoreCase("cpy")) {
                new CopyExpression(getNextToken(tokens, i), getNextToken(tokens, i+1)).interpret(context);
                i+=2;
            } else if(token.equalsIgnoreCase("more")) {
                new MoreExpression(getNextToken(tokens, i)).interpret(context);
                i++;
            } else if(token.equalsIgnoreCase("tree")) {
                new TreeExpression().interpret(context);
            }
            else {
                // Zmienna (nazwa katalogu lub pliku)
                System.out.println("Nie znaleziono komendy");
            }
        }
    }

    private String getNextToken(String[] tokens, int currentIndex) {
        if (currentIndex + 1 < tokens.length) {
            return tokens[currentIndex + 1];
        }
        return null;
    }
}