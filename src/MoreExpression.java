// Konkretne wyrażenie dla polecenia more
class MoreExpression implements Expression {
    private final String fileName;

    public MoreExpression(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void interpret(Context context) {
        Node fileNode = context.getVariable(fileName);

        if (fileNode instanceof File file) {
            Catalog currentCatalog = context.getCurrentNodeAsCatalog();

            if (currentCatalog != null && currentCatalog.containsChild(file.getName())) {
                // Plik znajduje się w obecnym katalogu
                String content = file.getContent();
                System.out.println("Content of " + fileName + " (paged):");

                // Wyświetl zawartość stronicami
                int pageSize = 50;
                for (int i = 0; i < content.length(); i += pageSize) {
                    int endIndex = Math.min(i + pageSize, content.length());
                    System.out.println(content.substring(i, endIndex));
                }
            } else {
                System.out.println("Error: " + fileName + " is not in the current directory.");
            }
        } else {
            System.out.println("Error: " + fileName + " is not a file.");
        }
    }
}
