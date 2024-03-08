
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
/**
 *
 * @author gabri
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

     Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() throws ClassNotFoundException {
        initComponents();
        
        conexao = ModuloConexao.conector();
        
        
    }
    
    private void consultar(){
        
        String sql="select * from tbusuarios where iduser= ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuTelefone.setText(rs.getString(3));
                txtUsuSenha.setText(rs.getString(5));
                               
                // A linha abaixose refere ao combobox               
               if(rs.getString(6).equals("admin"))
                cboUsuPerfil.setSelectedIndex(1);
               else{ cboUsuPerfil.setSelectedIndex(0); }
               
                 //a Linha abaixo habilita o botão adicionar
           btnUsoCreate.setEnabled(false);
                
            } else {
                txtUsuNome.setText(" ");
                txtUsuLogin.setText(" ");
                txtUsuTelefone.setText(" ");
                txtUsuSenha.setText(" ");
                cboUsuPerfil.setSelectedIndex(0);
                
                  //a Linha abaixo habilita o botão adicionar
           btnUsoCreate.setEnabled(true);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void adicionar(){
        
        String sql = "insert into tbusuarios(iduser,usuario,fone,login,senha,perfil) values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuTelefone.getText());
            pst.setString(4, txtUsuLogin.getText());
            pst.setString(5, txtUsuSenha.getText());
            pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
            
            //validação dos campos obrigatórios
            if ((txtUsuId.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
             JOptionPane.showInternalMessageDialog(null, "Preencha os campos obrigatórios");           
            } else {
            
                // A linha abaixo atualiza a tabela com os dados do formulario
                //a Estrutura abaixo é usada para dar feedback
                int adicionado =  pst.executeUpdate();
                if(adicionado>0){
                JOptionPane.showInternalMessageDialog(null, "Adicionado com sucesso");
                    txtUsuId.setText(" ");
                    txtUsuNome.setText(" ");
                    txtUsuLogin.setText(" ");
                    txtUsuTelefone.setText(" ");
                    txtUsuSenha.setText(" ");
                    cboUsuPerfil.setSelectedIndex(0);
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //criando o metodo para alterar dados do usuario
    private void alterar(){
    
        String sql = "update tbusuarios set usuario=?,fone=?,login=?,senha=?,perfil=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuTelefone.getText());
            pst.setString(3, txtUsuLogin.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
               pst.setString(6, txtUsuId.getText());
            
            //validação dos campos obrigatórios
            if ((txtUsuId.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
             JOptionPane.showInternalMessageDialog(null, "Preencha os campos obrigatórios");           
            } else {
            
                // A linha abaixo atualiza os dados do usuario
                //a Estrutura abaixo é usada para dar feedback
                int adicionado =  pst.executeUpdate();
                if(adicionado>0){
                JOptionPane.showInternalMessageDialog(null, "Dados alterados com sucesso com sucesso");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuSenha.setText(null);
                    cboUsuPerfil.setSelectedIndex(0);
            }
            }
              //a Linha abaixo habilita o botão adicionar
           btnUsoCreate.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //metodo que deleta usuarios
    private void remover(){
        
        //a estrutura abaixo confima a exclusão do usuario
        
        int confirma= JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if(confirma== JOptionPane.YES_OPTION){
            String sql = "delete from tbusuarios where iduser = ?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(txtUsuId.getText()));
               int adicionado =  pst.executeUpdate();
                if(adicionado>0){
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuSenha.setText(null);
                    cboUsuPerfil.setSelectedIndex(0);
                }
                //a Linha abaixo habilita o botão adicionar
           btnUsoCreate.setEnabled(true);
                
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }
        }
        
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
        Perfil = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuTelefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtUsuSenha = new javax.swing.JTextField();
        btnUsoCreate = new javax.swing.JButton();
        btnUsoDelete = new javax.swing.JButton();
        btnUsoUpdate = new javax.swing.JButton();
        btnUsoRead = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuarios");
        setPreferredSize(new java.awt.Dimension(620, 530));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("*ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("*Nome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("*Senha");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        Perfil.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Perfil.setText("*Perfil");
        getContentPane().add(Perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        txtUsuId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getContentPane().add(txtUsuId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 50, -1));

        txtUsuNome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getContentPane().add(txtUsuNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 320, -1));

        txtUsuLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getContentPane().add(txtUsuLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 200, -1));

        txtUsuTelefone.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getContentPane().add(txtUsuTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 200, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("*Login");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        cboUsuPerfil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user", "admin" }));
        getContentPane().add(cboUsuPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 190, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Telefone");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtUsuSenha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getContentPane().add(txtUsuSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 196, -1));

        btnUsoCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create_icon.png"))); // NOI18N
        btnUsoCreate.setToolTipText("Adicionar");
        btnUsoCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoCreate.setMaximumSize(new java.awt.Dimension(80, 80));
        btnUsoCreate.setMinimumSize(new java.awt.Dimension(80, 80));
        btnUsoCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsoCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 130, 170));

        btnUsoDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete_icon.png"))); // NOI18N
        btnUsoDelete.setToolTipText("Deletar");
        btnUsoDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoDelete.setMaximumSize(new java.awt.Dimension(80, 80));
        btnUsoDelete.setMinimumSize(new java.awt.Dimension(80, 80));
        btnUsoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsoDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 130, -1));

        btnUsoUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update_icon.png"))); // NOI18N
        btnUsoUpdate.setToolTipText("Editar");
        btnUsoUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoUpdate.setMaximumSize(new java.awt.Dimension(80, 80));
        btnUsoUpdate.setMinimumSize(new java.awt.Dimension(80, 80));
        btnUsoUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsoUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 130, -1));

        btnUsoRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read-icon.png"))); // NOI18N
        btnUsoRead.setToolTipText("Consultar");
        btnUsoRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoRead.setMaximumSize(new java.awt.Dimension(80, 80));
        btnUsoRead.setMinimumSize(new java.awt.Dimension(80, 80));
        btnUsoRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoReadActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsoRead, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 130, -1));

        jLabel6.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 160, -1));

        setBounds(0, 0, 620, 530);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsoReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoReadActionPerformed
        // pesquisar usuario metodo consultar
        consultar();
    }//GEN-LAST:event_btnUsoReadActionPerformed

    private void btnUsoCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoCreateActionPerformed
        // Adiciona um usuario com o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnUsoCreateActionPerformed

    private void btnUsoUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoUpdateActionPerformed
        // adiciona o metodo alterar
        alterar();
    }//GEN-LAST:event_btnUsoUpdateActionPerformed

    private void btnUsoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoDeleteActionPerformed
        // adiciona o metodo remover
        remover();
    }//GEN-LAST:event_btnUsoDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Perfil;
    private javax.swing.JButton btnUsoCreate;
    private javax.swing.JButton btnUsoDelete;
    private javax.swing.JButton btnUsoRead;
    private javax.swing.JButton btnUsoUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    private javax.swing.JTextField txtUsuTelefone;
    // End of variables declaration//GEN-END:variables
}
