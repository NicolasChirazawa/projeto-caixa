/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import DAO.ConexaoDAO;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author User
 */
public class frmCRUDFuncionarioVIEW extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public frmCRUDFuncionarioVIEW() {
        initComponents();
        //ComboBox vazia
        cboPerfilFuncionario.setSelectedIndex(-1);

        conn = new ConexaoDAO().conectaBD();
    }

    /*
    
    public void nomesEsquerda (){

    String nome_usuario = lblUsuarioCRUDFuncionario.getText();
    String perfil = lblNivelCRUDFuncionario.getText();
    System.out.println(perfil);
    }
    
     */
    private void inserirUsuario() {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "INSERT INTO Funcionario (nome_funcionario, username_funcionario, senha_funcionario, perfil_funcionario) VALUES (?, ?, ?, ?) ";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, txtNomeFuncionario.getText());
            pstm.setString(2, txtUsernameFuncionario.getText());
            pstm.setString(3, txtSenhaFuncionario.getText());
            pstm.setString(4, (String) cboPerfilFuncionario.getSelectedItem());

            //Validação dos campos de dados
            if (txtNomeFuncionario.getText().isEmpty()
                    || txtUsernameFuncionario.getText().isEmpty()
                    || txtSenhaFuncionario.getText().isEmpty()
                    || //Resolver problema combo box mensagem
                    cboPerfilFuncionario.getSelectedItem().equals(-1)) {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");

            } else {

                pstm.executeUpdate();

                ResultSet rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                    txtCodigoFuncionario.setText(String.valueOf(rs.getInt(1)));
                }
                JOptionPane.showMessageDialog(null, "Usuario Cadastrado. Seu id é: " + rs.getInt(1));

                pstm.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "frmCRUDFuncionarioVIEW(Inserir):  " + erro);
        }

    }

    private void consultarUsuario() {
        String sql = "SELECT * FROM Funcionario where codigo_funcionario=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtCodigoFuncionario.getText());
            rs = pstm.executeQuery();

            if (rs.next()) {
                txtNomeFuncionario.setText(rs.getString(2));
                txtUsernameFuncionario.setText(rs.getString(3));
                txtSenhaFuncionario.setText(rs.getString(4));
                //A linha abaixo se refere ao combo box
                cboPerfilFuncionario.setSelectedItem(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");

                txtNomeFuncionario.setText("");
                txtUsernameFuncionario.setText("");
                txtSenhaFuncionario.setText("");
                cboPerfilFuncionario.setSelectedItem(null);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CRUDFuncionario(Consultar): " + erro);
        }
    }

    private void alterarUsuario() {
        String sql = "UPDATE Funcionario set nome_funcionario=?, username_funcionario=?, senha_funcionario=?, perfil_funcionario=? where codigo_funcionario=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtNomeFuncionario.getText());
            pstm.setString(2, txtUsernameFuncionario.getText());
            pstm.setString(3, txtSenhaFuncionario.getText());
            pstm.setString(4, cboPerfilFuncionario.getSelectedItem().toString());
            pstm.setString(5, txtCodigoFuncionario.getText());

            //Validação dos campos de dados
            if (txtCodigoFuncionario.getText().isEmpty()
                    || txtNomeFuncionario.getText().isEmpty()
                    || txtUsernameFuncionario.getText().isEmpty()
                    || txtSenhaFuncionario.getText().isEmpty()
                    || //Resolver problema combo box mensagem
                    cboPerfilFuncionario.getSelectedItem().equals(-1)) {
                JOptionPane.showMessageDialog(null, "Dados do usuário não alterados");

            } else {

                int adicionado = pstm.executeUpdate();

                txtNomeFuncionario.setText("");
                txtUsernameFuncionario.setText("");
                txtSenhaFuncionario.setText("");
                cboPerfilFuncionario.setSelectedItem(null);

                JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CRUDFuncionario(Alterar): " + erro);
        }
    }

    private void deletarUsuario() {

        int confirmarDelete = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirmarDelete == JOptionPane.YES_NO_OPTION) {

            String sql = "DELETE FROM Funcionario where codigo_funcionario=?";

            try {
                pstm = conn.prepareStatement(sql);

                pstm.setString(1, txtCodigoFuncionario.getText());

                pstm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Dados do usuário deletados com sucesso");

                txtNomeFuncionario.setText("");
                txtUsernameFuncionario.setText("");
                txtSenhaFuncionario.setText("");
                cboPerfilFuncionario.setSelectedItem(null);

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "CRUDFuncionario(Deletar): " + erro);
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

        bntCRUDCliente = new javax.swing.JButton();
        bntDataGrid = new javax.swing.JButton();
        bntFechar = new javax.swing.JButton();
        bntAcessarCaixa = new javax.swing.JButton();
        bntCRUDCliente1 = new javax.swing.JButton();
        bntCRUDProdutos = new javax.swing.JButton();
        bntPesquisa = new javax.swing.JButton();
        bntInserir = new javax.swing.JButton();
        bntAlterar = new javax.swing.JButton();
        bntExcluir = new javax.swing.JButton();
        bntLimpar = new javax.swing.JButton();
        txtCodigoFuncionario = new javax.swing.JTextField();
        txtNomeFuncionario = new javax.swing.JTextField();
        txtUsernameFuncionario = new javax.swing.JTextField();
        txtSenhaFuncionario = new javax.swing.JTextField();
        cboPerfilFuncionario = new javax.swing.JComboBox<>();
        lblBemVindo = new javax.swing.JLabel();
        imgFundo = new javax.swing.JLabel();
        lblNivelCRUDFuncionario = new javax.swing.JLabel();
        lblUsuarioCRUDFuncionario = new javax.swing.JLabel();

        bntCRUDCliente.setContentAreaFilled(false);
        bntCRUDCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCRUDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCRUDClienteActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bntDataGrid.setContentAreaFilled(false);
        bntDataGrid.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntDataGrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDataGridActionPerformed(evt);
            }
        });
        getContentPane().add(bntDataGrid, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 80, 40));

        bntFechar.setContentAreaFilled(false);
        bntFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFecharActionPerformed(evt);
            }
        });
        getContentPane().add(bntFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 190, 50));

        bntAcessarCaixa.setContentAreaFilled(false);
        bntAcessarCaixa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntAcessarCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAcessarCaixaActionPerformed(evt);
            }
        });
        getContentPane().add(bntAcessarCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 260, 70));

        bntCRUDCliente1.setContentAreaFilled(false);
        bntCRUDCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCRUDCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCRUDCliente1ActionPerformed(evt);
            }
        });
        getContentPane().add(bntCRUDCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 260, 70));

        bntCRUDProdutos.setContentAreaFilled(false);
        bntCRUDProdutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCRUDProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCRUDProdutosActionPerformed(evt);
            }
        });
        getContentPane().add(bntCRUDProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 260, 70));

        bntPesquisa.setContentAreaFilled(false);
        bntPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPesquisaActionPerformed(evt);
            }
        });
        getContentPane().add(bntPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 82, 140, 40));

        bntInserir.setContentAreaFilled(false);
        bntInserir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntInserirActionPerformed(evt);
            }
        });
        getContentPane().add(bntInserir, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, 90, 40));

        bntAlterar.setContentAreaFilled(false);
        bntAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(bntAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, 90, 40));

        bntExcluir.setContentAreaFilled(false);
        bntExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(bntExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, 90, 40));

        bntLimpar.setContentAreaFilled(false);
        bntLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLimparActionPerformed(evt);
            }
        });
        getContentPane().add(bntLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, 80, 40));

        txtCodigoFuncionario.setBackground(new java.awt.Color(240, 240, 240));
        txtCodigoFuncionario.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtCodigoFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtCodigoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 100, 40));

        txtNomeFuncionario.setBackground(new java.awt.Color(240, 240, 240));
        txtNomeFuncionario.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtNomeFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomeFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 350, 40));

        txtUsernameFuncionario.setBackground(new java.awt.Color(240, 240, 240));
        txtUsernameFuncionario.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtUsernameFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsernameFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 350, 40));

        txtSenhaFuncionario.setBackground(new java.awt.Color(240, 240, 240));
        txtSenhaFuncionario.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtSenhaFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenhaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 350, 40));

        cboPerfilFuncionario.setBackground(new java.awt.Color(102, 102, 102));
        cboPerfilFuncionario.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboPerfilFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcionario", "Admin" }));
        cboPerfilFuncionario.setSelectedIndex(-1);
        cboPerfilFuncionario.setToolTipText("");
        cboPerfilFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboPerfilFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPerfilFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(cboPerfilFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 120, 40));

        lblBemVindo.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        lblBemVindo.setForeground(new java.awt.Color(255, 255, 255));
        lblBemVindo.setText("Bem-Vindo");
        getContentPane().add(lblBemVindo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, -1, -1));

        imgFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Tela CRUD Funcionário.jpg"))); // NOI18N
        getContentPane().add(imgFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblNivelCRUDFuncionario.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        lblNivelCRUDFuncionario.setForeground(new java.awt.Color(255, 255, 255));
        lblNivelCRUDFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivelCRUDFuncionario.setText("Nivel");
        getContentPane().add(lblNivelCRUDFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        lblUsuarioCRUDFuncionario.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        lblUsuarioCRUDFuncionario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioCRUDFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsuarioCRUDFuncionario.setText("Usuario");
        lblUsuarioCRUDFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(lblUsuarioCRUDFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bntFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bntFecharActionPerformed

    private void bntAcessarCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAcessarCaixaActionPerformed
        frmCaixaVIEW acessarCaixa = new frmCaixaVIEW();
        acessarCaixa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntAcessarCaixaActionPerformed

    private void bntCRUDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCRUDClienteActionPerformed
        frmCRUDClienteVIEW CRUDCliente = new frmCRUDClienteVIEW();
        CRUDCliente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntCRUDClienteActionPerformed

    private void bntCRUDProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCRUDProdutosActionPerformed
        frmCRUDProdutosVIEW telaProdutos = new frmCRUDProdutosVIEW();
        telaProdutos.setVisible(true);
        this.dispose();

        /*
        frm_loginVIEW principal = new frm_loginVIEW();
    
        
    if (principal.autenticar()){       
        frmCRUDProdutosVIEW telaProdutos = new frmCRUDProdutosVIEW();
        telaProdutos.setVisible(true);
        this.dispose();
        }
        
    else {
        JOptionPane.showMessageDialog(null, "Você não tem permissão para acessar esse campo.");
        }
         */
    }//GEN-LAST:event_bntCRUDProdutosActionPerformed

    private void txtUsernameFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameFuncionarioActionPerformed

    private void txtSenhaFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaFuncionarioActionPerformed

    private void txtCodigoFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoFuncionarioActionPerformed

    private void txtNomeFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeFuncionarioActionPerformed

    private void bntInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInserirActionPerformed
        inserirUsuario();

        txtNomeFuncionario.setText("");
        txtUsernameFuncionario.setText("");
        txtSenhaFuncionario.setText("");
        cboPerfilFuncionario.setSelectedIndex(-1);
    }//GEN-LAST:event_bntInserirActionPerformed

    private void bntAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAlterarActionPerformed
        alterarUsuario();
    }//GEN-LAST:event_bntAlterarActionPerformed

    private void bntPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPesquisaActionPerformed
        consultarUsuario();
    }//GEN-LAST:event_bntPesquisaActionPerformed

    private void cboPerfilFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPerfilFuncionarioActionPerformed

    }//GEN-LAST:event_cboPerfilFuncionarioActionPerformed

    private void bntLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLimparActionPerformed
        txtCodigoFuncionario.setText("");
        txtNomeFuncionario.setText("");
        txtUsernameFuncionario.setText("");
        txtSenhaFuncionario.setText("");
        cboPerfilFuncionario.setSelectedItem(null);
    }//GEN-LAST:event_bntLimparActionPerformed

    private void bntExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExcluirActionPerformed
        deletarUsuario();
    }//GEN-LAST:event_bntExcluirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void bntCRUDCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCRUDCliente1ActionPerformed
        frmCRUDClienteVIEW telaCliente = new frmCRUDClienteVIEW();
        telaCliente.setVisible(true);
        this.dispose();

        /*
        frm_loginVIEW principal = new frm_loginVIEW();
        
    if (principal.autenticar()){       
        frmCRUDClienteVIEW telaCliente = new frmCRUDClienteVIEW();
        telaCliente.setVisible(true);
        this.dispose();
        }
        
    else {
        JOptionPane.showMessageDialog(null, "Você não tem permissão para acessar esse campo.");
        }
         */
    }//GEN-LAST:event_bntCRUDCliente1ActionPerformed

    private void bntDataGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDataGridActionPerformed
        databaseFuncionarioTest datagrid = new databaseFuncionarioTest();
        datagrid.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntDataGridActionPerformed

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
            java.util.logging.Logger.getLogger(frmCRUDFuncionarioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCRUDFuncionarioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCRUDFuncionarioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCRUDFuncionarioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCRUDFuncionarioVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAcessarCaixa;
    private javax.swing.JButton bntAlterar;
    private javax.swing.JButton bntCRUDCliente;
    private javax.swing.JButton bntCRUDCliente1;
    private javax.swing.JButton bntCRUDProdutos;
    private javax.swing.JButton bntDataGrid;
    private javax.swing.JButton bntExcluir;
    private javax.swing.JButton bntFechar;
    private javax.swing.JButton bntInserir;
    private javax.swing.JButton bntLimpar;
    private javax.swing.JButton bntPesquisa;
    private javax.swing.JComboBox<String> cboPerfilFuncionario;
    private javax.swing.JLabel imgFundo;
    private javax.swing.JLabel lblBemVindo;
    public javax.swing.JLabel lblNivelCRUDFuncionario;
    public javax.swing.JLabel lblUsuarioCRUDFuncionario;
    private javax.swing.JTextField txtCodigoFuncionario;
    private javax.swing.JTextField txtNomeFuncionario;
    private javax.swing.JTextField txtSenhaFuncionario;
    private javax.swing.JTextField txtUsernameFuncionario;
    // End of variables declaration//GEN-END:variables

}
