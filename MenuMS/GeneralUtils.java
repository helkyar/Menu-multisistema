import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;
import java.io.File;
import java.awt.Desktop;

public class GeneralUtils {

    // Ejecutables ----------------------------------------------------------------
    public static void ejecutaCmd(String comand) {
        try{
            String s;
            Process p;
            p = Runtime.getRuntime().exec(comand);  
            BufferedReader br = new BufferedReader(
            new InputStreamReader(p.getInputStream()));
        
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();  

        } catch (Exception e){ System.out.println(e.toString());}
    }

    // Abrir Escritorio ------------------------------------------------------------
    public static void openDesktop() {
		Desktop desktop = null;
		// Windows, recibe el valor del entorno capturando la carpeta "Program Files"
		File file = new File(System.getenv("programfiles"));

		try {
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
				desktop.open(file);
			} else {
				System.out.println("NO esta soportado por el entorno");
			}
		} catch (IOException e){ System.out.println(e.toString());}

    }

    //Librerías de Java ---------------------------------------------------------------
    public static boolean isAvailable(String className) {
        boolean isFound = false;
        try {
            Class.forName(className, false, null);
            isFound = true;
        } catch (ClassNotFoundException e) {
            isFound = false;
        }
        return isFound;
    }

    public static void javaLibraries(){
        String libraries = "Swing " + (GeneralUtils.isAvailable("javax.swing.JComponent") ? "present" : "absent") + "\n" +
                                   "JAI " + (GeneralUtils.isAvailable("javax.media.jai.ImageJAI") ? "present" : "absent") + "\n" +
                                   "SAX " + (GeneralUtils.isAvailable("org.xml.sax.XMLReader") ? "present" : "absent") + "\n" +
                                   "ImaginaryClass " + (GeneralUtils.isAvailable("imaginary.ImaginaryClass") ? "present" : "absent") + "\n" +
                                   "Java3d " + (GeneralUtils.isAvailable("javax.media.j3d.View") ? "present" : "absent");
        JOptionPane.showMessageDialog(null, libraries);
    }

    //Salir del programa ---------------------------------------------------------------
    public static void terminate(){
         try {
                Runtime.getRuntime().exec("cmd /c cls").waitFor();
                System.out.println("Saliendo del programa");
                System.exit(0);
            } catch (IOException  e) {
                System.out.println(e.toString());
            } catch (InterruptedException e){
                System.out.println(e.toString());
            }
    }
}
