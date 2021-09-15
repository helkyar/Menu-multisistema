import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

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

    //Opciones de consola ----------------------------------------------------------------------------
    if (num == 1){
        while(true){
            String entryC = JOptionPane.showInputDialog("Opciones de Consola de WINDOWS:\n" + 
            "1: Abrir CMD \n2: Ejecutar TASKLIST \n3: Ejecutar NESTAT \n4: Ejecutar POWERSHELL \n5: Volver al menu principal \n" + sugestion);
            
            int n = Integer.parseInt(entryC);
            
            if(n <5 && n>0) {sugestion = "Introduce un numero";}
            else {sugestion = "Numero incorrecto. Prueba otra vez";}

            /*
              GeneralUtils es una clase declarada en el archivo con ese nombre. 
              Ahí se gestionan todas las funciones.
            */
            if(n == 1) {GeneralUtils.ejecutaCmd("cmd /c dir");} //abrir cmd
            if(n == 2) {GeneralUtils.ejecutaCmd("tasklist.exe");} //ejecutar tasklist
            if(n == 3) {GeneralUtils.ejecutaCmd("netstat -ano");} // ejecutar netstat
            if(n == 4){GeneralUtils.ejecutaCmd("powershell.exe $PSVersionTable.PSVersion");} //ejecutar poweshell
            if(n == 5){menuWindows("Introduce un numero");} //menú principal
        }
    }

    //Opciones gráficas ----------------------------------------------------------------------------------
    if(num == 2){ 
        while (true) {                
            String entryG = JOptionPane.showInputDialog("Opciones Graficas de WINDOWS:\n" + 
            "\n1: Abrir Services \n2: Abrir Panel de Control \n3: Abrir Explorador de Archivos" +
	    "\n4: Diskmol \n5:Comprobar Librerias Java \n6: Volver al menu principal \n" + sugestion);
            
            int n = Integer.parseInt(entryG);
            
            if(n <5 && n>0) {sugestion = "Introduce un numero";}
            else {sugestion = "Numero incorrecto. Prueba otra vez";}
            
            if(n == 1){GeneralUtils.ejecutaCmd("cmd /c services.msc");} //abrir servicios
            if(n == 2){GeneralUtils.ejecutaCmd("cmd /c control.exe");} //abrir panel de control
	        if(n == 3){GeneralUtils.openDesktop();} //abrir escritorio
            if(n == 4){GeneralUtils.ejecutaCmd("C:\\sysinternals\\SysinternalsSuite\\Diskmon.exe");} // abrir diskmon
            if(n == 5){GeneralUtils.javaLibraries();} //librerías java
            if(n == 6){menuWindows("Introduce un numero");} //menú principal
        }
    }
            
    if(num == 3){GeneralUtils.terminate();} //terminar programa
    else{menuWindows("Introduce un numero");}  
}

//===================================== LNX ===========================================================
public static void menuLinux(String sugestion){
    while(true){

            String entry = JOptionPane.showInputDialog(">>>>>>>>>>>> MENU <<<<<<<<<<<<<" +
                "\n1: Abrir archivos \n2: Abrir procesos \n3: Abrir variables de entorno \n4: Salir \n" + sugestion);

            int n = Integer.parseInt(entry);

            if(n == 1){GeneralUtils.ejecutaCmd("ls -aF");} //listar directorios

            //if(n == 2){GeneralUtils.ejecutaCmd("systemctl");}
            if(n == 2){GeneralUtils.ejecutaCmd("htop");} //mostrar procesos

            if(n == 3){
                System.getenv().forEach((k, v) -> {
                System.out.println(k + ":" + v);});} //variables de entorno
            
            if(n == 4) {GeneralUtils.terminate();} //terminar programa

        }
    }
}