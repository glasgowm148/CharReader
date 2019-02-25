import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

class App {

	public static void main(String []args) throws IOException {

		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("."));
	    chooser.setDialogTitle("Select a File :: ");
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            new CharReader().charReader(selectedFile, 10000);
	    } 
	    else {
	      System.out.println("No Selection Made ");
	    }
	}
}
