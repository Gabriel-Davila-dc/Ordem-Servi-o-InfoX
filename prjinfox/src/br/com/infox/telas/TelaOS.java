/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author gabri
 */
public class TelaOS extends javax.swing.JInternalFrame {

    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //a linha abixocria uma variavel para adicionar uma situação
    String tipo;
    /**
     * Creates new form TelaOS
     */
    public TelaOS() throws ClassNotFoundException {
        initComponents();
        conexao = ModuloConexao.conector();
       
    }

    
    
     private void listarNomes(){
      DefaultListModel<String> modelo = new DefaultListModel<>();
      lstClientes.setModel(modelo);
      String readLista = "select * from tbclientes where nomecli like '"+ txtCliPesquisar.getText() + "%'" + "order by nomecli";
       try {
          
           pst = conexao.prepareStatement(readLista);
           rs = pst.executeQuery();
           while(rs.next())
           {
               lstClientes.setVisible(true);
               modelo.addElement(rs.getString(2));
           }
          

       } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
       }
   }
   
   private void BuscarNome(){
       int linha = lstClientes.getSelectedIndex();
       if (linha>=0) {
        
          String readNome = "select * from tbclientes where nomecli like '" + txtCliPesquisar.getText() + "%'" + " order by nomecli limit " + (linha) + ",1";
           try {
               pst = conexao.prepareStatement(readNome);
               rs = pst.executeQuery();
               while(rs.next()){
                   lstClientes.setVisible(false);                  
                   txtcliId.setText(Integer.toString(rs.getInt(1)));
                   txtCliPesquisar.setText(rs.getString(2));
                   
                 
               }
                
         
           } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
           }
       }
       else{  lstClientes.setVisible(false);}
   }
   
   private void emitir_Os(){
   String sql = "insert into tbos (tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli) values(?,?,?,?,?,?,?,?)";
  
       try {
           pst = conexao.prepareStatement(sql);
           pst.setString(1, tipo);
           pst.setString(2, cboOsSItuacao.getSelectedItem().toString());
           pst.setString(3, txtOSEquipamento.getText());
           pst.setString(4, txtOsDefeito.getText());
           pst.setString(5, txtOsServico.getText());
           pst.setString(6, txtOsTecnico.getText());
           //.replace substitui a virgula"," pelo ponto"."
           pst.setString(7, txtOsValor.getText().replace(",", "."));
           pst.setString(8, txtcliId.getText());
           
           //validação dos campos obrigatorios
           
           if ((txtcliId.getText().isEmpty()) ||( txtOSEquipamento.getText().isEmpty()) || (txtOsDefeito.getText().isEmpty()) || (cboOsSItuacao.getSelectedItem().equals(" "))) {
               JOptionPane.showInputDialog(null, "Preencha todos os campos obrigatórios!");
           } else {
               int adicionado = pst.executeUpdate();
               if (adicionado > 0) {
               JOptionPane.showMessageDialog(null, "OS emitido com Sucesso");
               btnOsAdd.setEnabled(false);
               btnOsPesquisaqr.setEnabled(false);
               btnOsImprimir.setEnabled(true);
               }
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
   }
    
   //metodo para pesquisar uma os
   
   private void pesquisar_os(){
   
       // a linha abaixo cria uma caixa de entrada do tipo JOption pane
       String num_os = JOptionPane.showInputDialog("Numero da Os");
       String sql = "select os,date_format(data_os,'%d/%m/%y - %h:%i'), tipo, situacao,equipamento,defeito,servico,tecnico,valor,idcli from tbos where os = " + num_os;
       try {
           pst = conexao.prepareStatement(sql);
           rs = pst.executeQuery();
           
           if(rs.next()){
           txtNumOs.setText(rs.getString(1));
           txtDataOS.setText(rs.getString(2));
           //setando os radio buttons
           String rbtTipo = rs.getString(3);
           if(rbtTipo.equalsIgnoreCase("OS")){rbtOS.setSelected(true); tipo = "OS";}
           else{rbtOrçamento.setSelected(true); tipo = "Orçamento";}
           cboOsSItuacao.setSelectedItem(rs.getString(4));
           txtOSEquipamento.setText(rs.getString(5));
           txtOsDefeito.setText(rs.getString(6));
           txtOsServico.setText(rs.getString(7));
           txtOsTecnico.setText(rs.getString(8));
           txtOsValor.setText(rs.getString(9));
           txtcliId.setText(rs.getString(10));
           //evitando problemas
           btnOsAdd.setEnabled(false);
           txtCliPesquisar.setEnabled(false);
           lstClientes.setVisible(false);
           //ativar demais botôees
           btnOsExcluir.setEnabled(true);
           btnOsAlterar.setEnabled(true);
           btnOsImprimir.setEnabled(true);
           
           
           
           }               
           else{JOptionPane.showMessageDialog(null, "OS não Cadastrada");}
           
       } catch (java.sql.SQLSyntaxErrorException e) {
           JOptionPane.showMessageDialog(null, "OS Inválida");
          
       }
       catch (Exception e2){
       JOptionPane.showMessageDialog(null, e2);
       }
   }
   
   
   private void alterar_os(){
   String sql = "update tbos set tipo = ?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? where os = ?";
    try {
           pst = conexao.prepareStatement(sql);
           pst.setString(1, tipo);
           pst.setString(2, cboOsSItuacao.getSelectedItem().toString());
           pst.setString(3, txtOSEquipamento.getText());
           pst.setString(4, txtOsDefeito.getText());
           pst.setString(5, txtOsServico.getText());
           pst.setString(6, txtOsTecnico.getText());
           //.replace substitui a virgula"," pelo ponto"."
           pst.setString(7, txtOsValor.getText().replace(",", "."));
           pst.setString(8, txtNumOs.getText());
           
           //validação dos campos obrigatorios
           
           if ((txtNumOs.getText().isEmpty()) ||( txtOSEquipamento.getText().isEmpty()) || (txtOsDefeito.getText().isEmpty()) || (cboOsSItuacao.getSelectedItem().equals(" "))) {
               JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
           } else {
               int adicionado = pst.executeUpdate();
               if (adicionado > 0) {
               JOptionPane.showMessageDialog(null, "OS alterada com Sucesso");
              limpar();
               }
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
   }
   
   private void excluir_os(){
   
  //a estrutura abaixo confima a exclusão do usuario
        
        int confirma= JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este Cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if(confirma== JOptionPane.YES_OPTION){
            String sql = "delete from tbos where os = ?";
            try {
              limpar();             
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }
    
   private void limpar(){
               //limpar
               txtcliId.setText(null);
               txtOSEquipamento.setText(null);
               txtOsDefeito.setText(null);
               txtOsServico.setText(null);
               txtOsTecnico.setText(null);
               txtOsValor.setText(null);
               txtNumOs.setText(null);
               txtDataOS.setText(null);
               txtCliPesquisar.setText(null);
               
               //habilitar os objtos
               btnOsAdd.setEnabled(true);
               btnOsPesquisaqr.setEnabled(true);
               cboOsSItuacao.setSelectedIndex(0);
               txtCliPesquisar.setEnabled(true);
              
                //Desabilitar os botões
                btnOsAlterar.setEnabled(false);
                btnOsExcluir.setEnabled(false);
                btnOsImprimir.setEnabled(false);
             
          
            
   }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDataOS = new javax.swing.JTextField();
        txtNumOs = new javax.swing.JTextField();
        rbtOrçamento = new javax.swing.JRadioButton();
        rbtOS = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cboOsSItuacao = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtcliId = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstClientes = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtOsDefeito = new javax.swing.JTextField();
        txtOsValor = new javax.swing.JTextField();
        txtOSEquipamento = new javax.swing.JTextField();
        txtOsServico = new javax.swing.JTextField();
        txtOsTecnico = new javax.swing.JTextField();
        btnOsAdd = new javax.swing.JButton();
        btnOsExcluir = new javax.swing.JButton();
        btnOsAlterar = new javax.swing.JButton();
        btnOsPesquisaqr = new javax.swing.JButton();
        btnOsImprimir = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Data");

        jLabel2.setText("N OS");

        txtDataOS.setEnabled(false);

        txtNumOs.setEnabled(false);

        buttonGroup1.add(rbtOrçamento);
        rbtOrçamento.setText("Orçamento");
        rbtOrçamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrçamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOS);
        rbtOS.setText("OS");
        rbtOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rbtOrçamento)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNumOs)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(rbtOS)
                        .addGap(70, 70, 70))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtDataOS, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOrçamento)
                    .addComponent(rbtOS))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, -1, -1));

        jLabel3.setText("Situação");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 131, -1, -1));

        cboOsSItuacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Na Bancada", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando Peça", "Abandonado pelo Cliente", "Retornou" }));
        getContentPane().add(cboOsSItuacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 128, 179, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lupamini.png"))); // NOI18N

        jLabel5.setText("*id");

        txtcliId.setEnabled(false);

        lstClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstClientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(12, 12, 12)
                .addComponent(txtcliId, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtcliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 0, -1, 160));

        jLabel6.setText("*Equipamento");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 80, -1));

        jLabel7.setText("*Defeito");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 50, -1));

        jLabel8.setText("Serviço");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 270, 50, -1));

        jLabel9.setText("Valor Total");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        jLabel10.setText("Técnico");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));
        getContentPane().add(txtOsDefeito, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 495, -1));

        txtOsValor.setText("0");
        getContentPane().add(txtOsValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 180, -1));
        getContentPane().add(txtOSEquipamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 495, -1));
        getContentPane().add(txtOsServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 495, -1));
        getContentPane().add(txtOsTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 210, -1));

        btnOsAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botaoOsAdd.png"))); // NOI18N
        btnOsAdd.setToolTipText("Adicionar");
        btnOsAdd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOsAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, -1, -1));

        btnOsExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botaoOsExcluir.png"))); // NOI18N
        btnOsExcluir.setToolTipText("Excluir");
        btnOsExcluir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOsExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsExcluir.setEnabled(false);
        btnOsExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, -1, -1));

        btnOsAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botaoOsEditar.png"))); // NOI18N
        btnOsAlterar.setToolTipText("Editar");
        btnOsAlterar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOsAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAlterar.setEnabled(false);
        btnOsAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, -1, -1));

        btnOsPesquisaqr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botaoOsPesquisar.png"))); // NOI18N
        btnOsPesquisaqr.setToolTipText("Buscar");
        btnOsPesquisaqr.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOsPesquisaqr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsPesquisaqr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsPesquisaqrActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsPesquisaqr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        btnOsImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botaoOsImprimir.png"))); // NOI18N
        btnOsImprimir.setToolTipText("Imprimir");
        btnOsImprimir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOsImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsImprimir.setEnabled(false);
        getContentPane().add(btnOsImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, -1, -1));

        setBounds(0, 0, 620, 530);
    }// </editor-fold>//GEN-END:initComponents

    private void rbtOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOSActionPerformed
        //atribuindo um texto à variável tipo
        tipo = "OS";
    }//GEN-LAST:event_rbtOSActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
          // evento disparado quando soltar tecla
          
        listarNomes();
         
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void lstClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstClientesMouseClicked
        // seleciona o cliente
         BuscarNome();
    }//GEN-LAST:event_lstClientesMouseClicked

    private void rbtOrçamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOrçamentoActionPerformed
        //atribuindo um texto à variável tipo
        tipo = "Orçamento";
        
    }//GEN-LAST:event_rbtOrçamentoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // Ao abrir o Form marcar o radio button Orçamento
        rbtOrçamento.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAddActionPerformed
        // chamar o metodo emitir OS
        emitir_Os();
       
    }//GEN-LAST:event_btnOsAddActionPerformed

    private void btnOsPesquisaqrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsPesquisaqrActionPerformed
        // Chamar metodo pesquisar OS
        pesquisar_os();
    }//GEN-LAST:event_btnOsPesquisaqrActionPerformed

    private void btnOsAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAlterarActionPerformed
        // metodo para editar
        alterar_os();
       
    }//GEN-LAST:event_btnOsAlterarActionPerformed

    private void btnOsExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsExcluirActionPerformed
        // excluir OS
        excluir_os();
       
    }//GEN-LAST:event_btnOsExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsAdd;
    private javax.swing.JButton btnOsAlterar;
    private javax.swing.JButton btnOsExcluir;
    private javax.swing.JButton btnOsImprimir;
    private javax.swing.JButton btnOsPesquisaqr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOsSItuacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstClientes;
    private javax.swing.JRadioButton rbtOS;
    private javax.swing.JRadioButton rbtOrçamento;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtDataOS;
    private javax.swing.JTextField txtNumOs;
    private javax.swing.JTextField txtOSEquipamento;
    private javax.swing.JTextField txtOsDefeito;
    private javax.swing.JTextField txtOsServico;
    private javax.swing.JTextField txtOsTecnico;
    private javax.swing.JTextField txtOsValor;
    private javax.swing.JTextField txtcliId;
    // End of variables declaration//GEN-END:variables
}
