class ChangeDirectoryExpression implements Expression {
    private final String directoryName;

    public ChangeDirectoryExpression(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public void interpret(Context context) {
        if (directoryName.equals("..")) {
            // Obsługa zmiany katalogu na katalog nadrzędny
            Node currentNode = context.getCurrentNode();
            if (currentNode != null) {
                Node parentNode = currentNode.getParentNode();
                if (parentNode != null) {
                    context.setCurrentNode(parentNode);
                    System.out.println("Changed directory to " + parentNode.getName());
                } else {
                    System.out.println("Error: Already in the root directory.");
                }
            } else {
                System.out.println("Error: Current node is null.");
            }
        } else {
            // Standardowa obsługa zmiany katalogu na podany
            if (context.getCurrentNode() instanceof Catalog currentCatalog) {
                Node[] children = currentCatalog.getChildrenNodes();
                boolean directoryFound = false;
                for (Node child : children) {
                    if (child instanceof Catalog && child.getName().equals(directoryName)) {
                        context.setCurrentNode(child);
                        directoryFound = true;
                        System.out.println("Changed directory to " + directoryName);
                        break;
                    }
                }
                if (!directoryFound) {
                    System.out.println("Error: Directory not found.");
                }
            } else {
                System.out.println("Error: Current node is not a directory.");
            }
        }
    }
}