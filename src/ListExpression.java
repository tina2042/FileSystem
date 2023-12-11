// Konkretne wyrażenie dla polecenia ls
class ListExpression implements Expression {
    @Override
    public void interpret(Context context) {

        // Logika listowania plików i katalogów
        System.out.println("Listing contents of " + context.getCurrentNode().getName() + ":");
        if (context.getCurrentNode() instanceof Catalog catalog) {
            for (Node child : catalog.getChildrenNodes()) {
                System.out.println(child.getName());
            }
        } else {
            System.out.println("Error: Cannot list contents of a file.");
        }
    }
}