import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 *
 * Mark Glasgow
 * Feb 2019
 */
class CharReader {       // class should have a meaningful name

    /**
     * A custom reader that reads first n characters from a file
     * swaps every even char with a blank
     * then prints the output.
     * For a file with less than n chars,
     * customCharReader does not care about how many characters were actually read by the BufferedReader.
     *
     * @param file :: The file passed in by the user
     * @param n :: characters to read as specified in AppApp.java
     *
     */
    void charReader(File file, int n) throws IOException {
        if(n < 0){ throw new IndexOutOfBoundsException("Array value 'n' must be set to a positive integer."); }

        /*
          Implementing using Reader + .read
          Best Solution just now (?)
          Although having to remove trailing white spaces seems like a waste of resources ?
         */

        char[] buffer = new char[n];
        int pos = 0;

        try(BufferedReader in =  new BufferedReader(new FileReader(file))){ // use try-as-resource
            while (pos < n) {
                int r = in.read(buffer, pos, n - pos);
                if (r == -1) break;
                pos += r; // track how many bytes we've read so far.
            }
        }

        for (int i = 1; i < pos; i += 2) buffer[i] = ' ';

        String str = String.valueOf(buffer);
        System.out.println(str.substring(0, pos)); // removing trailing spaces


        /*
          Implementation using .readNBytes + FileInputStream
          This requires you to know the charset encoding.

          UTF_8 characters can be more than 1 byte.
          US_ASCII has a strict 1-to-1 encoding so won't break, unless non-ascii characters show up.

          Getting encoding from the file instance:
          https://stackoverflow.com/questions/3825390/effective-way-to-find-any-files-encoding
         */

        byte[] buffer2 = new byte[n];
        int pos2;

        try(InputStream reader2 = new FileInputStream( file )){
            pos2 = reader2.readNBytes(buffer2, 0, n); // unlike just 'read', this does guarantee
            for (int i = 1; i < pos2; i += 2) buffer2[i] = ' ';
        }
        System.out.println( new String( buffer2, 0, pos2, StandardCharsets.US_ASCII ));



    }
}

