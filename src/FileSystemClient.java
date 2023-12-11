import java.util.Scanner;

public class FileSystemClient {
    public static void main(String[] args) {
        // Tworzenie struktury plików

        Catalog rootCatalog = new Catalog(null, "root");
        Catalog subCatalog = new Catalog(rootCatalog, "subCatalog");
        File file1 = new File(subCatalog, "file1.txt", "Content of file 1");
        File file2 = new File(subCatalog, "file2.txt", "Content of file 2");


        // Obsługa poleceń z konsoli za pomocą interpretera
        Context context = new Context(rootCatalog, rootCatalog);
        context.setVariable("file1.txt", file1);
        context.setVariable("file2.txt", file2);
        context.setVariable("root", rootCatalog);
        context.setVariable("subCatalog", subCatalog);


        Parser parser = new Parser();


        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.print("Enter command (or 'exit' to quit): ");
            command = scanner.nextLine();
            if (!command.equalsIgnoreCase("exit")) {
                parser.parseAndExecute(command, context);
            }
        } while (!command.equalsIgnoreCase("exit"));

        scanner.close();
    }
}


