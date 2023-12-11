class MoveExpression implements Expression {
    private final String sourceFilePath;
    private final String destinationPath;

    public MoveExpression(String sourceName, String destinationName) {
        this.sourceFilePath = sourceName;
        this.destinationPath = destinationName;
    }

    @Override
    public void interpret(Context context) {

        // Pobierz obiekt pliku dla źródła
        File sourceFile = context.getFileByPath(sourceFilePath);

        // Sprawdź, czy plik źródłowy istnieje
        if (sourceFile != null) {
            // Pobierz nazwę pliku z pełnej ścieżki źródłowej
            String sourceFileName = sourceFile.getName();

            // Pobierz obiekt katalogu dla ścieżki docelowej
            Catalog destinationCatalog = context.getDirectoryByPath(destinationPath);
            String destinationFileName = PathUtils.getFileName(destinationPath);
            // Sprawdź, czy katalog docelowy istnieje
            if (destinationCatalog != null) {
                // Sprawdź, czy plik źródłowy i docelowy znajdują się w tym samym katalogu
                if (!sourceFile.getParentNode().equals(destinationCatalog)) {
                    // Przenieś plik do docelowego katalogu
                    sourceFile.setName(destinationFileName);
                    context.setVariable(destinationFileName,sourceFile);
                    sourceFile.getParentNode().removeChild(sourceFile);  // Usuń plik ze źródłowego katalogu
                    destinationCatalog.addChild(sourceFile);  // Dodaj plik do docelowego katalogu

                    System.out.println("File " + sourceFileName + " moved to " + destinationPath);
                } else {
                    System.out.println("Error: Source and destination are the same directory.");
                }
            } else {
                System.out.println("Error: Destination directory not found.");
            }
        } else {
            System.out.println("Error: Source file not found.");
        }

    }

}