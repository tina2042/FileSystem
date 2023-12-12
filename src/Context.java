import java.util.HashMap;
import java.util.Map;

class Context {
    private Catalog rootNode;
    private Node currentNode;
    private final Map<String, Node> variables;

    public Context(Node currentNode, Catalog rootNode) {
        this.rootNode=rootNode;
        this.currentNode = currentNode;
        this.variables = new HashMap<>();
    }

    public Node getCurrentNode() {
        return currentNode;
    }


    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public void setVariable(String name, Node value) {
        variables.put(name, value);
    }

    public Node getVariable(String name) {
        return variables.get(name);
    }

    public Catalog getCurrentNodeAsCatalog() {
        return (Catalog)currentNode;
    }
    public static boolean containsCatalog(Catalog currentCatalog, String catalogName) {
        if (currentCatalog.getName().equals(catalogName)) {
            return true;
        }

        for (Node component : currentCatalog.getChildrenNodes()) {
            if (component instanceof Catalog) {
                if (containsCatalog((Catalog) component, catalogName)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Catalog getDirectoryByPath(String path) {

        String[] parts = path.split("/");
        Catalog current = rootNode;
        boolean found = false;

        if(parts.length==1){

            if(current.getName().equals(parts[0])){
                found = true;
            } else {
                for (Node component : current.getChildrenNodes()) {
                    if (component instanceof Catalog && component.getName().equals(parts[0])) {
                        current = (Catalog) component;
                        found = true;
                        break;
                    }
                }
            }
            if(found) return current;
            else{
            return (Catalog)currentNode;}
        }


        for (String part : parts) {
            if (part.isEmpty()) {
                continue;  // Pomijamy puste części w przypadku ścieżki zakończonej "/"
            }


            if(current.getName().equals(part)){
                found = true;
                break;
            } else {

            for (Node component : current.getChildrenNodes()) {
                if (component instanceof Catalog && component.getName().equals(part)) {
                    current = (Catalog) component;
                    found = true;
                    break;
                }
            }
            }

            if (!found) {

                // Jeżeli nie znaleziono katalogu o danej nazwie, zwracamy null
                return null;
            }
        }

        // Jeżeli udało się przejść przez całą ścieżkę, zwracamy znaleziony katalog
        return (current instanceof Catalog) ? (Catalog) current : null;

    }

    public File getFileByPath(String path) {
        String[] parts = path.split("/");
        Node current = currentNode;

        for (String part : parts) {
            if (part.isEmpty()) {
                continue; // Ignoruj puste części
            }

            if (!(current instanceof Catalog)) {
                return null; // Ścieżka zawiera coś, co nie jest katalogiem
            }

            Catalog currentCatalog = (Catalog) current;
            Node nextNode = currentCatalog.getChild(part);

            if (nextNode == null) {
                return null; // Katalog w ścieżce nie istnieje
            }

            current = nextNode;
        }

        if (current instanceof File) {
            return (File) current;
        } else {
            return null; // Ostatni element ścieżki nie jest plikiem
        }
    }

}
