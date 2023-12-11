class PathUtils {
    // Metoda pomocnicza do pobierania nazwy pliku z pełnej ścieżki
    public static String getFileName(String filePath) {
        int lastSeparatorIndex = filePath.lastIndexOf('/');
        if (lastSeparatorIndex != -1) {
            return filePath.substring(lastSeparatorIndex + 1);
        } else {
            return filePath;
        }
    }
}