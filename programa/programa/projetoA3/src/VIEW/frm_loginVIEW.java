/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import DAO.ConexaoDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class frm_loginVIEW extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    public String nome = new String();
    
    public void logar(){
        conn = new ConexaoDAO().conectaBD();
        
    String sql = "SELECT * FROM Funcionario where nome_funcionario=? and senha_funcionario=?";
    
        try{
    //As linhas abaixo preparam a consulta ao banco de dados. Referente aos campos escritos.
    
    pstm = conn.prepareStatement(sql);
    pstm.setString(1, txtNomeUsuario.getText());
    pstm.setString(2, txtSenhaUsuario.getText());
    
    rs = pstm.executeQuery();
    
    //Senha e usuário correspondente.
    if(rs.next()){
        //Obtém nível de acesso do perfil
  
        frmPrincipalVIEW telaPrincipal = new frmPrincipalVIEW();
        telaPrincipal.setVisible(true);
        
        //Trocando os nomes das labels na esquerda pelo usuário e nível de acesso
        //-----------------------------------------------------------------------
        //Sistema de nome e autenticidade
        
        /*
        telaPrincipal.lblUsuarioPrincipal.setText(txtNomeUsuario.getText());
        telaPrincipal.lblNivelPrincipal.setText(rs.getString("perfil_funcionario"));
        */
        
        /*
        
        frmCRUDFuncionarioVIEW telaCRUDFuncionario = new frmCRUDFuncionarioVIEW();
        
        telaCRUDFuncionario.lblUsuarioCRUDFuncionario.setText(txtNomeUsuario.getText());
        telaCRUDFuncionario.lblNivelCRUDFuncionario.setText(rs.getString("perfil_funcionario"));        
        
        */
        
        this.dispose();
        
    //Senha e/ou usuário não correspondente.  
    }else 
        JOptionPane.showMessageDialog(null, "Usuário ou/e senha inválida");
        txtNomeUsuario.setText("");
        txtSenhaUsuario.setText("");
    
    //Erro de conexão ao banco de dados.   
    }catch (Exception e){
            
        JOptionPane.showMessageDialog(null,"Login(Logar): " + e);   
        }
    }
        
    public frm_loginVIEW() {
        initComponents();
        conn = new ConexaoDAO().conectaBD();
        
        if(conn != null){
            //lblBancoOn.setText("Conectado");
            lblBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/databaseAcerto.png")));
        }
        else {
            //lblBancoOff.setText("Não conectado");
            lblBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/databaseErro.png")));
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

        txtNomeUsuario = new javax.swing.JTextField();
        txtSenhaUsuario = new javax.swing.JTextField();
        btnEntrarSistema = new javax.swing.JButton();
        bntSair = new javax.swing.JButton();
        lblBanco = new javax.swing.JLabel();
        lblFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Login");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNomeUsuario.setBackground(new java.awt.Color(240, 240, 240));
        txtNomeUsuario.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtNomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 290, 40));

        txtSenhaUsuario.setBackground(new java.awt.Color(240, 240, 240));
        txtSenhaUsuario.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtSenhaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenhaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, 290, 40));

        btnEntrarSistema.setContentAreaFilled(false);
        btnEntrarSistema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrarSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarSistemaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrarSistema, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 460, 230, 60));

        bntSair.setContentAreaFilled(false);
        bntSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSairActionPerformed(evt);
            }
        });
        getContentPane().add(bntSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 200, 60));
        getContentPane().add(lblBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 510, 510));

        lblFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Login.jpg"))); // NOI18N
        getContentPane().add(lblFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeUsuarioActionPerformed

    private void btnEntrarSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarSistemaActionPerformed
        logar();
        
    }//GEN-LAST:event_btnEntrarSistemaActionPerformed

    private void bntSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bntSairActionPerformed

    private void txtSenhaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(frm_loginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_loginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_loginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_loginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_loginVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSair;
    private javax.swing.JButton btnEntrarSistema;
    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblFundo;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JTextField txtSenhaUsuario;
    // End of variables declaration//GEN-END:variables
}
