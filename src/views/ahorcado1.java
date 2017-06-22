/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author lvazquez
 */
public class ahorcado1 extends javax.swing.JFrame {   //Se declaran las variables a utilizar para el juego 

    LecturaDatos le = new LecturaDatos();
    Procedimiento pro = new Procedimiento();
    public String capt[] = new String[20];
    String concat;
    int intentos = 1;
    //private String[] cap;
    //private int pasar;
    //private ImageIcon icono;
    private int contador = 0;
    //String[] listaPalabras;
    private StringBuilder palabra = new StringBuilder();
    private StringBuilder p1 = new StringBuilder();

    private Map<String, String> listarpalabras = new HashMap<>();

    /**
     * Creates new form ahorcado
     */
    public ahorcado1() {
        initComponents();
        centrarForma();
        txtPalabra.setEditable(false);
        txtDescripcion.setEditable(false);
        txtAdivinar.setEditable(false);
        txtPalabraerrada.setEditable(false);

    }

    private void Iniciar() {   ///con este metodo se llama el archivo para que se lea en la caja de texto descripcion
        File archivo = new File("C:\\Users\\vazquez\\Documents\\NetBeansProjects\\Ahorcado\\ahorcado.txt");
        leerArchivo(archivo);
        loadNextWord(contador);
        txtAdivinar.setEditable(true);
        txtAdivinar.requestFocus();
        btnIniciar.setEnabled(false);
        lbl_cambioIntento.setText("Intentos: 1");
        contador = 0;
    }

    public void Gano() {  //// con este metodo se va a demostrar cuando se gana y adivinio la palabra correcta

        String arregloPalabras[] = listarpalabras.get("" + contador).split(":");

        if (contador == 4 && arregloPalabras[0].equals(txtPalabra.getText().replace(" ", "").toUpperCase())) {
            JOptionPane.showMessageDialog(null, "Juego Terminado Ganaste");
            JOptionPane.showMessageDialog(null, "El Juego se Reiniciará");

            this.setVisible(false); // Borrará los datos y quitará la ventana del juego
            ahorcado1.main(null);
            return;

        }

        if (arregloPalabras[0].equals(txtPalabra.getText().replace(" ", "").toUpperCase())) {

            JOptionPane.showMessageDialog(null, "Eso es todo campeón ya GANASTE!!!");
            lbl_cambioIntento.setText("Intentos: 1");
            pasarSiguientePalabra();

        }
    }

    private void pasarSiguientePalabra() {
        palabra = new StringBuilder();

        lbimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorcado01.png")));
        lbl_cambioIntento.setText("Intentos: 1");
        intentos = 1;

        contador++;

        loadNextWord(contador);

        lbl_cambioIntento.setText("Intentos: 1");
    }

    public void Perdio() {

        if (intentos == 7) {

            JOptionPane.showMessageDialog(null, "Ya perdiste MEN!!!");
            txtPalabraerrada.setText("");
            lbl_cambioIntento.setText("Intentos: 1");
            pasarSiguientePalabra();
            
        }
    }

    private void centrarForma() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getSize();

        double posX = (pantalla.getWidth() - ventana.getWidth()) / 2.0;
        double posY = (pantalla.getHeight() - ventana.getHeight()) / 2.0;

        this.setLocation((int) posX, (int) posY);

    }

    private void leerArchivo(File archivo) {
        int conta = 0;
        try {
            BufferedReader input = new BufferedReader(new FileReader(archivo));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    listarpalabras.put("" + conta, line);
                    conta++;
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadNextWord(int indice) {

        String line = listarpalabras.get("" + indice);
        StringBuilder wordConGuion = new StringBuilder();

        String[] datos = line.split(":");
        palabra = new StringBuilder();

        for (Character car : datos[0].toUpperCase().toLowerCase().toCharArray()) {
            p1.append(car);
            p1.append(" ");

            palabra.append(car);
            palabra.append(" ");
            wordConGuion.append("_");
            wordConGuion.append(" ");
        }
        txtDescripcion.setText(datos[1]);
        txtPalabra.setText(wordConGuion.toString());
        txtAdivinar.setText("");
    }

    public void cambiarImagen() {
        switch (intentos) {
            case 2:
                lbimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorcado02.png")));

                lbl_cambioIntento.setText("Intentos: 2");

                break;
            case 3:
                lbimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorcado03.png")));
                lbl_cambioIntento.setText("Intentos: 3");

                break;
            case 4:
                lbimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorcado04.png")));
                lbl_cambioIntento.setText("Intentos: 4");

                break;
            case 5:
                lbimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorcado05.png")));
                lbl_cambioIntento.setText("Intentos: 5");
                Gano();

                break;
            case 6:
                lbimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorcado06.png")));
                lbl_cambioIntento.setText("Intentos: 6");
                Gano();

                break;
            case 7:

                lbimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorcado07.png")));
                lbl_cambioIntento.setText("Intentos: 7");

                String arregloPalabras[] = listarpalabras.get("" + contador).split(":");

                if (contador == 4) {
                    JOptionPane.showMessageDialog(null, "Juego Terminado Moriste");
                    JOptionPane.showMessageDialog(null, "El Juego se Reiniciará");

                    this.setVisible(false); // Borrará los datos y quitará la ventana del juego
                    ahorcado1.main(null);
                    return;

                }

                Perdio();

        }
    }

//int pasar=0;
//ImageIcon icono=null;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtAdivinar = new javax.swing.JTextField();
        txtPalabra = new javax.swing.JTextField();
        txtPalabraerrada = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbimage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_cambioIntento = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        btn_Reiniciar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(255, 204, 255));
        jLabel1.setFont(new java.awt.Font("Segoe Script", 2, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Juego del ahorcado");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel2.setBackground(new java.awt.Color(102, 0, 51));
        jLabel2.setFont(new java.awt.Font("MV Boli", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 51));
        jLabel2.setText("Palabra:");

        jLabel3.setFont(new java.awt.Font("MV Boli", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 51));
        jLabel3.setText("Descripción:");

        jLabel4.setFont(new java.awt.Font("MV Boli", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 51));
        jLabel4.setText("Adivinar:");

        jLabel5.setFont(new java.awt.Font("MV Boli", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 51));
        jLabel5.setText("Letras erradas:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(20, 20, 20))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtDescripcionAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        txtAdivinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdivinarActionPerformed(evt);
            }
        });
        txtAdivinar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdivinarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAdivinar)
            .addComponent(jScrollPane1)
            .addComponent(txtPalabra)
            .addComponent(txtPalabraerrada)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAdivinar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPalabraerrada, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
        );

        jLabel6.setBackground(new java.awt.Color(255, 153, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_1.png"))); // NOI18N
        jLabel6.setOpaque(true);

        lbimage.setBackground(new java.awt.Color(204, 153, 255));
        lbimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorcado01.png"))); // NOI18N
        lbimage.setOpaque(true);
        lbimage.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lbimageAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel3.setBackground(new java.awt.Color(203, 109, 220));

        lbl_cambioIntento.setFont(new java.awt.Font("Kristen ITC", 0, 24)); // NOI18N
        lbl_cambioIntento.setText("Intentos");

        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iniciar.png"))); // NOI18N
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btn_Reiniciar.setText("Reiniciar Juego");
        btn_Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReiniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_Reiniciar))
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_cambioIntento, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lbl_cambioIntento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Reiniciar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbimage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbimage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAdivinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdivinarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdivinarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:

        Iniciar();

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtDescripcionAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtDescripcionAncestorAdded
        // TODO add your handling code here:


    }//GEN-LAST:event_txtDescripcionAncestorAdded

    private void txtAdivinarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdivinarKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if (!(Character.isAlphabetic(car))) {
            evt.consume();
        } else {
            int pos = palabra.indexOf("" + car);
            StringBuilder adivina = new StringBuilder();
            if (pos >= 0) {
                adivina.append(txtPalabra.getText());
                while (pos >= 0) {

                    palabra.replace(pos, pos + 1, "-");
                    adivina.replace(pos, pos + 1, "" + car);
                    pos = palabra.indexOf("" + car);

                }
                txtPalabra.setText(adivina.toString());
                txtAdivinar.setText("");

                Gano();
            } else {
                evt.consume();
                intentos++;

                txtPalabraerrada.setText(txtPalabraerrada.getText() + car);
                cambiarImagen();
            }
        }

    }//GEN-LAST:event_txtAdivinarKeyTyped

    private void lbimageAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lbimageAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lbimageAncestorAdded

    private void btn_ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReiniciarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false); // Borrará los datos y quitará la ventana del juego
        ahorcado1.main(null);
        return;
    }//GEN-LAST:event_btn_ReiniciarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ahorcado1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ahorcado1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ahorcado1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ahorcado1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ahorcado1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btn_Reiniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbimage;
    private javax.swing.JLabel lbl_cambioIntento;
    private javax.swing.JTextField txtAdivinar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtPalabra;
    private javax.swing.JTextField txtPalabraerrada;
    // End of variables declaration//GEN-END:variables

    //To change body of generated methods, choose Tools | Templates.
}

/*
   
         
    }

    

   
 */
 /*
        public void limpiar(){
txtAdivinar.setText
      
        
    }

 */
