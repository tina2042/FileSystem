public class CopyExpression implements Expression {
    private final String sourceFilePath;
    private final String destinationPath;

    public CopyExpression(String sourceFilePath, String destinationPath) {
        this.sourceFilePath = sourceFilePath;
        this.destinationPath = destinationPath;
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

            // Sprawdź, czy katalog docelowy istnieje
            if (destinationCatalog != null) {
                // Utwórz nowy plik w docelowym katalogu z nową nazwą
                String destinationFileName = PathUtils.getFileName(destinationPath);
                File newFile = new File(destinationCatalog, destinationFileName, sourceFile.getContent());

                System.out.println("File " + sourceFileName + " copied to " + destinationPath);
            } else {
                System.out.println("Error: Destination directory not found.");
            }
        } else {
            System.out.println("Error: Source file not found.");
        }
    }
}
