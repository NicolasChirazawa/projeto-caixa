/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import DAO.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class frmCRUDClienteVIEW extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public frmCRUDClienteVIEW() {
        initComponents();
    }

    private void inserirCliente() {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "INSERT INTO Cliente (nome_cliente, email_cliente, tel_cliente, endereco_cliente, numero_cliente, cep_cliente) VALUES (?, ?, ?, ?, ?, ?) ";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, txtNomeCliente.getText());
            pstm.setString(2, txtEmailCliente.getText());
            pstm.setString(3, txtTelefoneCliente.getText());
            pstm.setString(4, txtEnderecoCliente.getText());
            pstm.setString(5, txtNCliente.getText());
            pstm.setString(6, txtCEPCliente.getText());
            
            //Validação dos campos de dados
            if (txtNomeCliente.getText().isEmpty()
                    || txtEmailCliente.getText().isEmpty()
                    || txtTelefoneCliente.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha os campos de nome, email e telefone.");

            } else {

                pstm.executeUpdate();

                ResultSet rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                    txtCodigoCliente.setText(String.valueOf(rs.getInt(1)));
                }
                JOptionPane.showMessageDialog(null, "Cliente Cadastrado. Seu id é: " + rs.getInt(1));

                pstm.close();

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CRUDCliente(Inserir):  " + erro);
        }
    }

    private void consultarCliente() {
        conn = new ConexaoDAO().conectaBD();

        String sql = "SELECT * FROM Cliente where codigo_cliente=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtCodigoCliente.getText());
            rs = pstm.executeQuery();

            if (rs.next()) {
                txtNomeCliente.setText(rs.getString(2));
                txtEmailCliente.setText(rs.getString(3));
                txtTelefoneCliente.setText(rs.getString(4));
                txtEnderecoCliente.setText(rs.getString(5));
                txtNCliente.setText(rs.getString(6));
                txtCEPCliente.setText(rs.getString(7));
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não cadastrado");

                txtNomeCliente.setText("");
                txtEmailCliente.setText("");
                txtTelefoneCliente.setText("");
                txtEnderecoCliente.setText("");
                txtNCliente.setText("");
                txtCEPCliente.setText("");
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CRUDCliente(Consultar): " + erro);
        }
    }

    private void alterarCliente() {
        String sql = "UPDATE Cliente set nome_cliente=?, email_cliente=?, tel_cliente=?, endereco_cliente=?, numero_cliente=?, cep_cliente=? where codigo_cliente=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtNomeCliente.getText());
            pstm.setString(2, txtEmailCliente.getText());
            pstm.setString(3, txtTelefoneCliente.getText());
            pstm.setString(4, txtEnderecoCliente.getText());
            pstm.setString(5, txtNCliente.getText());
            pstm.setString(6, txtCEPCliente.getText());
            pstm.setString(7, txtCodigoCliente.getText());

            //Validação dos campos de dados
            if (txtNomeCliente.getText().isEmpty()
                    || txtEmailCliente.getText().isEmpty()
                    || txtTelefoneCliente.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Dados do cliente não alterados");

            } else {

                int adicionado = pstm.executeUpdate();

                txtNomeCliente.setText("");
                txtEmailCliente.setText("");
                txtTelefoneCliente.setText("");
                txtEnderecoCliente.setText("");
                txtNCliente.setText("");
                txtCEPCliente.setText("");

                JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CRUDFuncionario(Alterar): " + erro);
        }
    }

    private void deletarCliente() {

        int confirmarDelete = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirmarDelete == JOptionPane.YES_NO_OPTION) {

            String sql = "DELETE FROM Cliente where codigo_cliente=?";

            try {
                pstm = conn.prepareStatement(sql);

                pstm.setString(1, txtCodigoCliente.getText());

                pstm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Dados do cliente deletados com sucesso");

                txtNomeCliente.setText("");
                txtEmailCliente.setText("");
                txtTelefoneCliente.setText("");
                txtEnderecoCliente.setText("");
                txtNCliente.setText("");
                txtCEPCliente.setText("");

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "CRUDCliente(Deletar): " + erro);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuarioCRUDFuncionario = new javax.swing.JLabel();
        bntPesquisa = new javax.swing.JButton();
        bntFechar = new javax.swing.JButton();
        bntAcessarCaixa = new javax.swing.JButton();
        bntCRUDFuncionario = new javax.swing.JButton();
        bntCRUDProdutos = new javax.swing.JButton();
        bntInserir = new javax.swing.JButton();
        bntAlterar = new javax.swing.JButton();
        bntExcluir = new javax.swing.JButton();
        bntLimpar = new javax.swing.JButton();
        txtCodigoCliente = new javax.swing.JTextField();
        txtNomeCliente = new javax.swing.JTextField();
        txtEmailCliente = new javax.swing.JTextField();
        txtTelefoneCliente = new javax.swing.JTextField();
        txtEnderecoCliente = new javax.swing.JTextField();
        txtNCliente = new javax.swing.JTextField();
        txtCEPCliente = new javax.swing.JTextField();
        lblBemVindo = new javax.swing.JLabel();
        lblFundo = new javax.swing.JLabel();
        lblNivelCRUDCliente = new javax.swing.JLabel();
        lblUsuarioCRUDCliente = new javax.swing.JLabel();

        lblUsuarioCRUDFuncionario.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        lblUsuarioCRUDFuncionario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioCRUDFuncionario.setText("Usuario");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bntPesquisa.setContentAreaFilled(false);
        bntPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPesquisaActionPerformed(evt);
            }
        });
        getContentPane().add(bntPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 82, 140, 40));

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

        bntCRUDFuncionario.setContentAreaFilled(false);
        bntCRUDFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCRUDFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCRUDFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(bntCRUDFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 260, 70));

        bntCRUDProdutos.setContentAreaFilled(false);
        bntCRUDProdutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCRUDProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCRUDProdutosActionPerformed(evt);
            }
        });
        getContentPane().add(bntCRUDProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 260, 70));

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

        txtCodigoCliente.setBackground(new java.awt.Color(240, 240, 240));
        txtCodigoCliente.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtCodigoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 100, 40));

        txtNomeCliente.setBackground(new java.awt.Color(240, 240, 240));
        txtNomeCliente.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtNomeCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 230, 40));

        txtEmailCliente.setBackground(new java.awt.Color(240, 240, 240));
        txtEmailCliente.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtEmailCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtEmailCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtEmailCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 280, 40));

        txtTelefoneCliente.setBackground(new java.awt.Color(236, 240, 241));
        txtTelefoneCliente.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtTelefoneCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtTelefoneCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefoneCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 230, 40));

        txtEnderecoCliente.setBackground(new java.awt.Color(236, 240, 241));
        txtEnderecoCliente.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtEnderecoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtEnderecoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtEnderecoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 280, 40));

        txtNCliente.setBackground(new java.awt.Color(236, 240, 241));
        txtNCliente.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtNCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtNCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtNCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 230, 40));

        txtCEPCliente.setBackground(new java.awt.Color(236, 240, 241));
        txtCEPCliente.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtCEPCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCEPCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCEPClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtCEPCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 220, 40));

        lblBemVindo.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        lblBemVindo.setForeground(new java.awt.Color(255, 255, 255));
        lblBemVindo.setText("Bem-Vindo");
        getContentPane().add(lblBemVindo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, -1, -1));

        lblFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Tela CRUD Cliente.jpg"))); // NOI18N
        getContentPane().add(lblFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        lblNivelCRUDCliente.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        lblNivelCRUDCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblNivelCRUDCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivelCRUDCliente.setText("Nivel");
        getContentPane().add(lblNivelCRUDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        lblUsuarioCRUDCliente.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        lblUsuarioCRUDCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioCRUDCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsuarioCRUDCliente.setText("Usuario");
        lblUsuarioCRUDCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(lblUsuarioCRUDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

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

    private void bntCRUDFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCRUDFuncionarioActionPerformed
        frmCRUDFuncionarioVIEW CRUDFuncionario = new frmCRUDFuncionarioVIEW();
        CRUDFuncionario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntCRUDFuncionarioActionPerformed

    private void bntCRUDProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCRUDProdutosActionPerformed
        frmCRUDProdutosVIEW CRUDProdutos = new frmCRUDProdutosVIEW();
        CRUDProdutos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntCRUDProdutosActionPerformed

    private void txtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClienteActionPerformed

    private void txtNClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNClienteActionPerformed

    private void txtEmailClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailClienteActionPerformed

    private void txtEnderecoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoClienteActionPerformed

    private void txtCEPClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCEPClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCEPClienteActionPerformed

    private void txtTelefoneClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneClienteActionPerformed

    private void txtCodigoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoClienteActionPerformed

    private void bntInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInserirActionPerformed
        inserirCliente();
    }//GEN-LAST:event_bntInserirActionPerformed

    private void bntAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAlterarActionPerformed
        alterarCliente();
    }//GEN-LAST:event_bntAlterarActionPerformed

    private void bntExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExcluirActionPerformed
        deletarCliente();
    }//GEN-LAST:event_bntExcluirActionPerformed

    private void bntLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLimparActionPerformed
        txtCodigoCliente.setText("");
        txtNomeCliente.setText("");
        txtEmailCliente.setText("");
        txtTelefoneCliente.setText("");
        txtEnderecoCliente.setText("");
        txtNCliente.setText("");
        txtCEPCliente.setText("");
    }//GEN-LAST:event_bntLimparActionPerformed

    private void bntPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPesquisaActionPerformed
        consultarCliente();
    }//GEN-LAST:event_bntPesquisaActionPerformed

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
            java.util.logging.Logger.getLogger(frmCRUDClienteVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCRUDClienteVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCRUDClienteVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCRUDClienteVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCRUDClienteVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAcessarCaixa;
    private javax.swing.JButton bntAlterar;
    private javax.swing.JButton bntCRUDFuncionario;
    private javax.swing.JButton bntCRUDProdutos;
    private javax.swing.JButton bntExcluir;
    private javax.swing.JButton bntFechar;
    private javax.swing.JButton bntInserir;
    private javax.swing.JButton bntLimpar;
    private javax.swing.JButton bntPesquisa;
    private javax.swing.JLabel lblBemVindo;
    private javax.swing.JLabel lblFundo;
    public javax.swing.JLabel lblNivelCRUDCliente;
    public javax.swing.JLabel lblUsuarioCRUDCliente;
    private javax.swing.JLabel lblUsuarioCRUDFuncionario;
    private javax.swing.JTextField txtCEPCliente;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtEmailCliente;
    private javax.swing.JTextField txtEnderecoCliente;
    private javax.swing.JTextField txtNCliente;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtTelefoneCliente;
    // End of variables declaration//GEN-END:variables
}
