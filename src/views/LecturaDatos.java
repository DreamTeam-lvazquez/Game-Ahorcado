/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author vazquez
 */
public class LecturaDatos {
    public String datoleido[]=new  String [20];
    
    public void lectura() throws IOException
    {
        
        
        try {
            File Archivo=new File ("ahorcado.txt");
            FileReader archivoleido=new FileReader(Archivo);
            BufferedReader archivolectura=new  BufferedReader(archivoleido);
            
            for (int x=0; x<20;x++){
                datoleido[x]=archivolectura.readLine();
                
                
                
            }
            archivoleido.close();
            
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
   } 
}
