import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;
// import java.util.*;
// import java.io.*;

public class MenuMultisistema{

public static void main(String[] args) throws IOException, InterruptedException {
    
    String OS = System.getProperty("os.name").toLowerCase();

    System.out.println("os.name: " + OS);
    String sugestion = "Introduce un numero";

    if (OS.contains("win")) {menuWindows(sugestion);}

    else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {menuLinux(sugestion);} 

    else {System.out.println("Sistema Operativo no soportado!!");}
}

//==================================== WDS ===========================================================
public static void menuWindows(String sugestion){

    String entry = JOptionPane.showInputDialog(">>>>>>>>>>>> MENU <<<<<<<<<<<<<\n" + 
        "1: Opciones de consola \n2: Opciones graficas \n3: Salir del programa \n" + sugestion);

    int num = Integer.parseInt(entry);

    if (num == 1){
        while(true){
            String entryC = JOptionPane.showInputDialog("Opciones de Consola de WINDOWS:\n" + 
            "1: Abrir CMD \n2: Ejecutar TASKLIST \n3: Ejecutar NESTAT \n4: Ejecutar POWERSHELL \n5: Volver al menu principal \n" + sugestion);
            
            int n = Integer.parseInt(entryC);
            
            if(n <5 && n>0) {sugestion = "Introduce un numero";}
            else {sugestion = "Numero incorrecto. Prueba otra vez";}
            
            if(n == 1) {GeneralUtils.ejecutaCmd("cmd /c dir");}
            if(n == 2) {GeneralUtils.ejecutaCmd("tasklist.exe");}
            if(n == 3) {GeneralUtils.ejecutaCmd("netstat -ano");}
            if(n == 4){GeneralUtils.ejecutaCmd("powershell.exe $PSVersionTable.PSVersion");}
            if(n == 5){menuWindows("Introduce un numero");}
        }
    }

    if(num == 2){ 
        while (true) {                
            String entryG = JOptionPane.showInputDialog("Opciones Graficas de WINDOWS:\n" + 
            "\n1: Abrir Services \n2: Abrir Panel de Control \n3: Diskmol \n4:Comprobar Librerias Java \n5: Volver al menu principal \n" + sugestion);
            
            int n = Integer.parseInt(entryG);
            
            if(n <5 && n>0) {sugestion = "Introduce un numero";}
            else {sugestion = "Numero incorrecto. Prueba otra vez";}
            
            if(n == 1){GeneralUtils.ejecutaCmd("cmd /c services.msc");}
            if(n == 2){GeneralUtils.ejecutaCmd("cmd /c control");}
            if(n == 3){GeneralUtils.ejecutaCmd("C:\\Users\\Admin\\Documents\\SysinternalsSuite\\Diskmon.exe");}
            if(n == 4){GeneralUtils.javaLibraries();}
            if(n == 5){menuWindows("Introduce un numero");}
        }
    }
            
    if(num == 3){GeneralUtils.terminate();}
    else{menuWindows("Introduce un numero");}  
}

//===================================== LNX ===========================================================
public static void menuLinux(String sugestion){
    while(true){

            String entry = JOptionPane.showInputDialog(">>>>>>>>>>>> MENU <<<<<<<<<<<<<" +
                "\n1: Abrir archivos \n2: Abrir procesos \n3: Abrir variables de entorno \n4: Salir \n" + sugestion);

            int n = Integer.parseInt(entry);

            if(n == 1){GeneralUtils.ejecutaCmd("ls -aF");}

            if(n == 2){GeneralUtils.ejecutaCmd("systemctl");}

            if(n == 3){
                System.getenv().forEach((k, v) -> {
                System.out.println(k + ":" + v);});}
            
            if(n == 4) {GeneralUtils.terminate();}

        }
    }
}

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
class GeneralUtils{
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

        } catch (Exception e){
            System.out.println(e.toString());}
    }

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
