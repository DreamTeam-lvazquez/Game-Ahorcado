/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;

/**
 *
 * @author vazquez
 */
public class Procedimiento {
    LecturaDatos le=new LecturaDatos();
    public String comparado[]=new String[20];
    
    public void rtausuario(String cap[]) throws IOException {
       le.lectura();
       for (int x=0; x<20;x++){
           if (cap[x].equals(String.valueOf(le.datoleido[1].charAt(x)))){
               comparado[x] =String.valueOf(le.datoleido[1].charAt(x));
           }else{
               comparado[x]="";
           }
       }
    }
    
}
