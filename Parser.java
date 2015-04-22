import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {
    private File file;

    public void setFile(File f) {
        file = f;
    }
    public File getFile() {
        return file;
    }

    // Parser cannot be instantiated without file object
    private Parser()
        {
        }

    public Parser(File file)
        {
            this.file = file;
        }

    // using stringBuffer
    public String getContent() throws IOException {
        FileInputStream i = new FileInputStream(file);
        StringBuffer output = new StringBuffer();
        int data;
        while ((data = i.read()) > 0) {
            output.append((char) data);
        }
        return output.toString();
    }

    // using stringBuffer
    public String getContentWithoutUnicode() throws IOException {
        FileInputStream i = new FileInputStream(file);
        StringBuffer output = new StringBuffer();
        int data;
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                output.append((char) data);
            }
        }
        return output.toString();
    }

    // Only Writes should be synchronized
    public synchronized void saveContent(String content) throws IOException {
        FileOutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
