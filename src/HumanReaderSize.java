import java.io.File;

public class HumanReaderSize {

    private final String fileName;

    public HumanReaderSize(String fileName) {

        this.fileName = fileName;
    }

    public long size() {

        final File file = new File(fileName);

        if (file.isFile()) {
            System.out.println("File: " + fileName);

            final long bytes = file.length();
            return bytes;
        } else if (file.isDirectory()) {
            System.out.println("Directory from the file system: " + fileName);

            final long result = folderSize(file);
            return result;
        } else {

            throw new IllegalArgumentException("Nonexistent file or directory " + fileName);
        }
    }


    public static long folderSize(File directory) {
        long size = 0;
        File file = new File(directory.getAbsolutePath());
        String list[] = file.list();
        for (String i : list) {
            File fileIn = new File(directory.getAbsolutePath() + "/" + i);
            if (fileIn.isFile())
                size += fileIn.length();
            else
                size += folderSize(fileIn);
        }
        return size;
    }
}

