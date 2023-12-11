class MakeDirectoryExpression implements Expression {
    private final String directoryName;

    public MakeDirectoryExpression(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public void interpret(Context context) {
        Catalog currentCatalog = null;

        if (context.getCurrentNode() instanceof Catalog) {
            currentCatalog = (Catalog) context.getCurrentNode();
        }

        if (currentCatalog != null) {
            if(isCorrectDirectoryName(directoryName)){
            Catalog newDirectory = new Catalog(currentCatalog, directoryName);

            context.setVariable(directoryName, newDirectory);
            System.out.println("Directory " + directoryName + " created.");}
            else{
                System.out.println("Directory name is not correct");
            }
        } else {
            System.out.println("Error: Cannot create directory, current node is not a catalog.");
        }
    }

    private boolean isCorrectDirectoryName(String directoryName) {
        // Sprawdź, czy podana nazwa jest pusta
        if (directoryName.isEmpty()) {
            return false;
        }

        // Sprawdź, czy podana nazwa zawiera więcej niż jedną kropkę
        if (directoryName.indexOf('.') > 0) {
            return false;
        }

        // Nazwa katalogu jest prawidłowa
        return true;
    }
}