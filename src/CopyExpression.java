import java.util.regex.Pattern;

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

            Catalog sourceCatalog=context.getDirectoryByPath(sourceFilePath);
            if (sourceCatalog != null) {
                // Pobierz nazwę pliku z pełnej ścieżki źródłowej
                String sourceFileName = sourceCatalog.getName();
                if(destinationPath!=null){
                // Pobierz obiekt katalogu dla ścieżki docelowej
                Catalog destinationCatalog = context.getDirectoryByPath(destinationPath);

                // Sprawdź, czy katalog docelowy istnieje
                if (destinationCatalog != null) {
                    // Utwórz nowy plik w docelowym katalogu z nową nazwą
                    String destinationCatalogName = PathUtils.getFileName(destinationPath);
                    if(isCorrectDirectoryName(destinationCatalogName)) {

                        Catalog newDirectory = new Catalog(destinationCatalog, destinationCatalogName);
                        destinationCatalog.removeChild(newDirectory);
                        for(Node child: sourceCatalog.getChildrenNodes())
                        {newDirectory.addChild(child);}
                        destinationCatalog.addChild(newDirectory);
                        context.setVariable(destinationCatalogName, newDirectory);
                        System.out.println("Catalog " + sourceFileName + " copied to " + destinationPath);
                    } else{
                        System.out.println("Error: Catalog name is incorrect.");
                    }

                } else {
                    System.out.println("Error: Destination directory not found.");
                }}else{
                    System.out.println("Error: Destination path is incorrect.");
                }
            } else {
                System.out.println("Error: Source file not found.");
            }
        }
    }
    private boolean isCorrectDirectoryName(String directoryName) {
        // Sprawdź, czy podana nazwa jest pusta
        if (directoryName.isEmpty()) {
            return false;
        }
        // Wyrażenie regularne do sprawdzenia, czy ciąg zawiera tylko litery i cyfry
        String regex = "^[a-zA-Z0-9]+$";

        // Sprawdź dopasowanie wyrażenia regularnego
        return Pattern.matches(regex, directoryName);
    }
}
