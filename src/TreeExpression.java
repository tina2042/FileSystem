class TreeExpression implements Expression {
    @Override
    public void interpret(Context context) {
        Node currentNode = context.getCurrentNode();
        if (currentNode instanceof Catalog currentCatalog) {
            System.out.println("Tree structure:");

            // Wyświetl strukturę katalogu jako drzewo
            System.out.println(currentCatalog.getName());
            printTreeStructure(currentCatalog, 1);
        } else {
            System.out.println("Error: Current node is not a directory.");
        }
    }

    private void printTreeStructure(Catalog catalog, int depth) {

        for (Node child : catalog.getChildrenNodes()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("  "); // Wcięcia dla reprezentacji drzewa
            }
            System.out.println(" - " + child.getName());
            if (child instanceof Catalog) {
                printTreeStructure((Catalog) child, depth + 1);
            }
        }
    }
}