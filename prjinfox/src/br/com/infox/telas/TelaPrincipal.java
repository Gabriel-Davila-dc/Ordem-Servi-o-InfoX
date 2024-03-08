/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author gabri
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() throws ClassNotFoundException {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblUsuariolbl = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menCadastro = new javax.swing.JMenu();
        menCadastroCliente = new javax.swing.JMenuItem();
        menCadastroOS = new javax.swing.JMenuItem();
        menCadastroUser = new javax.swing.JMenuItem();
        menRelatorio = new javax.swing.JMenu();
        menRelCli = new javax.swing.JMenuItem();
        menRelatorioSer = new javax.swing.JMenuItem();
        menAjudaSobre = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        menOpcoesSair = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("x - Sistema para controle de ordem de serviço");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        desktop.setPreferredSize(new java.awt.Dimension(620, 530));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(desktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 530));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/XAzulado.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 270, -1));

        lblUsuariolbl.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblUsuariolbl.setText("Usuario");
        getContentPane().add(lblUsuariolbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 190, -1));

        lblData.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblData.setText("Data");
        getContentPane().add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 180, -1));

        menCadastro.setText("Cadastro");

        menCadastroCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menCadastroCliente.setText("Cliente");
        menCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadastroClienteActionPerformed(evt);
            }
        });
        menCadastro.add(menCadastroCliente);

        menCadastroOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menCadastroOS.setText("OS");
        menCadastroOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadastroOSActionPerformed(evt);
            }
        });
        menCadastro.add(menCadastroOS);

        menCadastroUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menCadastroUser.setText("Usuários");
        menCadastroUser.setEnabled(false);
        menCadastroUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadastroUserActionPerformed(evt);
            }
        });
        menCadastro.add(menCadastroUser);

        Menu.add(menCadastro);

        menRelatorio.setText("Relatorio");
        menRelatorio.setEnabled(false);
        menRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelatorioActionPerformed(evt);
            }
        });

        menRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRelCli.setText("Clientes");
        menRelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelCliActionPerformed(evt);
            }
        });
        menRelatorio.add(menRelCli);

        menRelatorioSer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menRelatorioSer.setText("Serviços");
        menRelatorio.add(menRelatorioSer);

        Menu.add(menRelatorio);

        menAjudaSobre.setText("Ajuda");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setText("Sobre");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menAjudaSobre.add(jMenuItem5);

        Menu.add(menAjudaSobre);

        menOpcoesSair.setText("Opções");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem6.setText("Sair");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menOpcoesSair.add(jMenuItem6);

        Menu.add(menOpcoesSair);

        setJMenuBar(Menu);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // As linhas abaixo substituem as lblData e lbl Usuario ao inicializar o Jframe:
        
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
        
    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
             // Exibe uma caixa de dialogo
        int sair = JOptionPane.showConfirmDialog(null,"tem certeza que deseja Sair?","Atenção", JOptionPane.YES_NO_OPTION);
        
        if(sair == JOptionPane.YES_OPTION){
            System.exit(0);}
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // Chmando a tela sobre
        TelaSobre objtelasobre = new TelaSobre();
        objtelasobre.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void menCadastroUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadastroUserActionPerformed
        // Abrir a TelaUsuario dentro do desktop
        
        TelaUsuario objtelausuario;
        try {
            objtelausuario = new TelaUsuario();
            objtelausuario.setVisible(true);
        desktop.add(objtelausuario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_menCadastroUserActionPerformed

    private void menCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadastroClienteActionPerformed
        // Chamar a TelaCliente: 
        TelaCliente cliente;
        try {
            cliente = new TelaCliente();
             cliente.setVisible(true);
        desktop.add(cliente);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_menCadastroClienteActionPerformed

    private void menCadastroOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadastroOSActionPerformed
        // Chamar TelaOs
        TelaOS telaos;
        try {
            telaos = new TelaOS();
             telaos.setVisible(true);
        desktop.add(telaos);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_menCadastroOSActionPerformed

    private void menRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelatorioActionPerformed
       
    }//GEN-LAST:event_menRelatorioActionPerformed

    private void menRelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelCliActionPerformed
        // gerando um relatório de clientes:
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a inpressão deste relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            //imprimindo relatório com framework JasperReports
            try {       
// EU PAREI O PROJETO AQUI//             
                JasperPrint print = JasperFillManager.fillReport("C:/Users/gabri/JaspersoftWorkspace/MyReports/PapelFolha.jasper" , null, conexao);
                JasperViewer.viewReport(print,false);          
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_menRelCliActionPerformed

    
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaPrincipal().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Menu;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuariolbl;
    private javax.swing.JMenu menAjudaSobre;
    private javax.swing.JMenu menCadastro;
    private javax.swing.JMenuItem menCadastroCliente;
    private javax.swing.JMenuItem menCadastroOS;
    public static javax.swing.JMenuItem menCadastroUser;
    private javax.swing.JMenu menOpcoesSair;
    private javax.swing.JMenuItem menRelCli;
    public static javax.swing.JMenu menRelatorio;
    private javax.swing.JMenuItem menRelatorioSer;
    // End of variables declaration//GEN-END:variables
}