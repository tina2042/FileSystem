import java.util.Scanner;

class CatExpression implements Expression {
    private final String fileName;

    public CatExpression(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void interpret(Context context) {
        Node fileNode = context.getVariable(fileName);

        if (fileNode == null) {
            if (context.getCurrentNode() instanceof Catalog currentCatalog) {
                Node[] children = currentCatalog.getChildrenNodes();
                boolean directoryFound = false;

                // Sprawdź, czy podana nazwa nie jest nazwą katalogu bieżącego
                for (Node child : children) {
                    if (child instanceof Catalog && child.getName().equals(fileName)) {
                        directoryFound = true;
                        break;
                    }
                }

                if (!directoryFound) {
                    // Jeśli nie jest to nazwa katalogu, utwórz nowy plik
                    System.out.println("File " + fileName + " created.");

                    // Wprowadź zawartość pliku, jeżeli użytkownik poda
                    System.out.print("Enter content for " + fileName + ": ");
                    Scanner scanner = new Scanner(System.in);
                    String fileContent = scanner.nextLine();

                    File newFile = new File(currentCatalog, fileName, fileContent);
                    context.setVariable(fileName, newFile);
                } else {
                    System.out.println("Error: Cannot create file with the same name as a directory.");
                }
            } else {
                System.out.println("Error: Current node is not a directory.");
            }
        } else if (fileNode instanceof File file && fileNode.getParentNode().equals(context.getCurrentNode())) {
            System.out.println("Content of " + fileName + ":");
            System.out.println(file.getContent());
        } else {
            System.out.println("Error: " + fileName + " is not a file or cannot be find");
        }
    }
}