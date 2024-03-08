package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
//a linha abaixo importa recursos da bliblioteca sqlitejdbc

/**
 *
 * @author gabri
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
   
   
    public TelaCliente() throws ClassNotFoundException {
        initComponents();
        conexao = ModuloConexao.conector();
       
    }

    private void adicionar() {

        String sql = "insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone1.getText());
            pst.setString(4, txtCliEmail.getText());

            //validação dos campos obrigatórios
            if ((txtCliNome.getText().isEmpty()) || (txtCliFone1.getText().isEmpty())) {
                JOptionPane.showInternalMessageDialog(null, "Preencha os campos obrigatórios");
            } else {

                // A linha abaixo atualiza a tabela com os dados do formulario
                //a Estrutura abaixo é usada para dar feedback
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showInternalMessageDialog(null, "Adicionado com sucesso");
                    txtCliNome.setText(null);
                    txtCliId.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliEmail.setText(null);
                    txtCliFone1.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void remover(){
        
        //a estrutura abaixo confima a exclusão do usuario
        
        int confirma= JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este Cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if(confirma== JOptionPane.YES_OPTION){
            String sql = "delete from tbclientes where idcli = ?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(txtCliId.getText()));
               int adicionado =  pst.executeUpdate();
                if(adicionado>0){
                    txtCliId.setText(null);
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone1.setText(null);   
                     txtCliEmail.setText(null);
                }
                 //a Linha abaixo habilita o botão adicionar
           btnAdd.setEnabled(true);
                
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }
    
     private void alterar(){
    
        String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(5, txtCliId.getText());
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone1.getText());
            pst.setString(4, txtCliEmail.getText());
            
            //validação dos campos obrigatórios
            if ((txtCliNome.getText().isEmpty()) || (txtCliId.getText().isEmpty()) ) {
             JOptionPane.showInternalMessageDialog(null, "Preencha os campos obrigatórios");           
            } else {
            
                // A linha abaixo atualiza os dados do usuario
                //a Estrutura abaixo é usada para dar feedback
                int adicionado =  pst.executeUpdate();
                if(adicionado>0){
                JOptionPane.showInternalMessageDialog(null, "Dados alterados com sucesso com sucesso");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliId.setText(null);
                    txtCliFone1.setText(null);
                    txtCliEmail.setText(null);   
                     
           //a Linha abaixo habilita o botão adicionar
           btnAdd.setEnabled(true);
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    

   private void listarNomes(){
      DefaultListModel<String> modelo = new DefaultListModel<>();
      ListaCli.setModel(modelo);
      String readLista = "select * from tbclientes where nomecli like '"+ txtCliPesquisar.getText() + "%'" + "order by nomecli";
       try {
          
           pst = conexao.prepareStatement(readLista);
           rs = pst.executeQuery();
           while(rs.next())
           {
               scrPane.setVisible(true);
               modelo.addElement(rs.getString(2));
           }
          
 ///////          conexao.close();
       } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
       }
   }
   
   private void BuscarNome(){
       int linha = ListaCli.getSelectedIndex();
       if (linha>=0) {
        
          String readNome = "select * from tbclientes where nomecli like '" + txtCliPesquisar.getText() + "%'" + " order by nomecli limit " + (linha) + ",1";
           try {
               pst = conexao.prepareStatement(readNome);
               rs = pst.executeQuery();
               while(rs.next()){
                   scrPane.setVisible(false);                  
                   txtCliId.setText(Integer.toString(rs.getInt(1)));
                   txtCliNome.setText(rs.getString(2));
                   txtCliEmail.setText(rs.getString(3));
                   txtCliFone1.setText(rs.getString(4));
                   txtCliEndereco.setText(rs.getString(5));
               }
                
           //a Linha abaixo desabilita o botão adicionar
           btnAdd.setEnabled(false);
           } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
           }
       }
       else{  scrPane.setVisible(false);}
   }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliId = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        scrPane = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaCli = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        txtCliFone1 = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("*Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel2.setText("Endereço");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jLabel3.setText("*Telefone");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jLabel4.setText("email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));
        getContentPane().add(txtCliNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 358, -1));
        getContentPane().add(txtCliEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 458, -1));

        txtCliId.setEnabled(false);
        getContentPane().add(txtCliId, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 120, -1));
        getContentPane().add(txtCliEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 359, -1));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create_icon.png"))); // NOI18N
        btnAdd.setToolTipText("Adicionar");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setMaximumSize(new java.awt.Dimension(80, 80));
        btnAdd.setMinimumSize(new java.awt.Dimension(80, 80));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 130, 170));

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update_icon.png"))); // NOI18N
        btnAlterar.setToolTipText("Editar");
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.setMaximumSize(new java.awt.Dimension(80, 80));
        btnAlterar.setMinimumSize(new java.awt.Dimension(80, 80));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 130, -1));

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete_icon.png"))); // NOI18N
        btnRemover.setToolTipText("Deletar");
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.setMaximumSize(new java.awt.Dimension(80, 80));
        btnRemover.setMinimumSize(new java.awt.Dimension(80, 80));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 130, -1));

        jLabel5.setText("* Campos obrigatórios");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtCliPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 260, 30));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/lupa1.png"))); // NOI18N
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, -1));

        ListaCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaCliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ListaCli);

        scrPane.setViewportView(jScrollPane2);

        getContentPane().add(scrPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 610, 140));

        jLabel6.setText("Id");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 256, 20, 30));
        getContentPane().add(txtCliFone1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 188, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Adiciona um cliente com o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // adiciona o metodo alterar
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // adiciona o metodo remover
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // evento disparado quando soltar tecla
        listarNomes();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void ListaCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaCliMouseClicked
        // 
        BuscarNome();
    }//GEN-LAST:event_ListaCliMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaCli;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane scrPane;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone1;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    // End of variables declaration//GEN-END:variables
}
