/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import DAO.ConexaoDAO;
import DAO.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class frmCRUDProdutosVIEW extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public frmCRUDProdutosVIEW() {
        initComponents();
        restaurarDadosComboBox();
    }

    private void inserirProduto() {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "INSERT INTO Produto (codigo_categoria_produto, qtde_produto, tamanho_produto, marca_produto, preco_produto, nome_produto, status_produto) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, (String) cboIDCategoriaProduto.getSelectedItem());
            pstm.setString(2, txtQuantidadeProduto.getText());
            pstm.setString(3, txtTamanhoProduto.getText());
            pstm.setString(4, txtMarcaProduto.getText());
            pstm.setString(5, txtValorProduto.getText());
            pstm.setString(6, txtNomeProduto.getText());
            pstm.setString(7, txtStatusProduto.getText());

            //Validação dos campos de dados
            if (txtQuantidadeProduto.getText().isEmpty()
                    || txtTamanhoProduto.getText().isEmpty()
                    || txtMarcaProduto.getText().isEmpty()
                    || txtValorProduto.getText().isEmpty()
                    || txtNomeProduto.getText().isEmpty()
                    || txtStatusProduto.getText().isEmpty()
                    || //Resolver problema combo box mensagem
                    cboCategoriaProduto.getSelectedItem().equals(-1)) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos");

            } else {

                pstm.executeUpdate();

                ResultSet rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                    txtCodigoProduto.setText(String.valueOf(rs.getInt(1)));
                }
                JOptionPane.showMessageDialog(null, "Produto Cadastrado. Seu id é: " + rs.getInt(1));

                pstm.close();

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "frmCRUDProdutoVIEW(Inserir):  " + erro);
        }
    }

    private void consultarProduto() {

        conn = new ConexaoDAO().conectaBD();

        String sql = "SELECT * FROM Produto where codigo_produto=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtCodigoProduto.getText());
            rs = pstm.executeQuery();

            if (rs.next()) {

                cboIDCategoriaProduto.setSelectedItem(rs.getString(2));
                txtQuantidadeProduto.setText(rs.getString(3));
                txtTamanhoProduto.setText(rs.getString(4));
                txtMarcaProduto.setText(rs.getString(5));
                txtValorProduto.setText(rs.getString(6));
                txtNomeProduto.setText(rs.getString(7));
                txtStatusProduto.setText(rs.getString(8));

                //Um valor anterior ao do ID
                int name = cboIDCategoriaProduto.getSelectedIndex();

                cboCategoriaProduto.setSelectedIndex(name);
            } else {
                JOptionPane.showMessageDialog(null, "Produto não cadastrado");

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CRUDProdutos(Consulta): " + erro);
        }
    }

    private void alterarUsuario() {
        String sql = "UPDATE Produto set codigo_categoria_produto=?, qtde_produto=?, tamanho_produto=?, marca_produto=?, preco_produto=?, nome_produto=?, status_produto=? WHERE codigo_produto=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, (String) cboIDCategoriaProduto.getSelectedItem().toString());
            pstm.setString(2, txtQuantidadeProduto.getText());
            pstm.setString(3, txtTamanhoProduto.getText());
            pstm.setString(4, txtMarcaProduto.getText());
            pstm.setString(5, txtValorProduto.getText());
            pstm.setString(6, txtNomeProduto.getText());
            pstm.setString(7, txtStatusProduto.getText());
            pstm.setString(8, txtCodigoProduto.getText());

            //Validação dos campos de dados
            if (txtQuantidadeProduto.getText().isEmpty()
                    || txtTamanhoProduto.getText().isEmpty()
                    || txtMarcaProduto.getText().isEmpty()
                    || txtValorProduto.getText().isEmpty()
                    || txtNomeProduto.getText().isEmpty()
                    || txtStatusProduto.getText().isEmpty()
                    || //Resolver problema combo box mensagem{
                    cboCategoriaProduto.getSelectedItem().equals(-1)) {
                JOptionPane.showMessageDialog(null, "Dados do usuário não alterados");

            } else {

                int adicionado = pstm.executeUpdate();

                cboCategoriaProduto.setSelectedItem("");
                txtQuantidadeProduto.setText("");
                txtTamanhoProduto.setText("");
                txtMarcaProduto.setText("");
                txtValorProduto.setText("");
                txtNomeProduto.setText("");
                txtStatusProduto.setText("");

                JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CRUDAlterar(Alterar): " + erro);
        }
    }

    private void deletarUsuario() {

        int confirmarDelete = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirmarDelete == JOptionPane.YES_NO_OPTION) {

            String sql = "DELETE FROM Produto where codigo_produto=?";

            try {
                pstm = conn.prepareStatement(sql);

                pstm.setString(1, txtCodigoProduto.getText());

                pstm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Dados do usuário deletados com sucesso");

                cboCategoriaProduto.setSelectedItem("");
                txtQuantidadeProduto.setText("");
                txtTamanhoProduto.setText("");
                txtMarcaProduto.setText("");
                txtValorProduto.setText("");
                txtNomeProduto.setText("");
                txtStatusProduto.setText("");

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

        bntAcessarCaixa = new javax.swing.JButton();
        bntCRUDCliente = new javax.swing.JButton();
        bntCRUDFuncionario = new javax.swing.JButton();
        bntFechar = new javax.swing.JButton();
        bntInserir = new javax.swing.JButton();
        bntAlterar = new javax.swing.JButton();
        bntExcluir = new javax.swing.JButton();
        bntLimpar = new javax.swing.JButton();
        bntRead6 = new javax.swing.JButton();
        bntPesquisa = new javax.swing.JButton();
        txtCodigoProduto = new javax.swing.JTextField();
        txtNomeProduto = new javax.swing.JTextField();
        txtQuantidadeProduto = new javax.swing.JTextField();
        txtTamanhoProduto = new javax.swing.JTextField();
        txtMarcaProduto = new javax.swing.JTextField();
        txtValorProduto = new javax.swing.JTextField();
        txtStatusProduto = new javax.swing.JTextField();
        cboCategoriaProduto = new javax.swing.JComboBox<>();
        cboIDCategoriaProduto = new javax.swing.JComboBox<>();
        lblBemVindo = new javax.swing.JLabel();
        lblFundo = new javax.swing.JLabel();
        lblNivelCRUDProduto = new javax.swing.JLabel();
        lblUsuarioCRUDProduto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bntAcessarCaixa.setContentAreaFilled(false);
        bntAcessarCaixa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntAcessarCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAcessarCaixaActionPerformed(evt);
            }
        });
        getContentPane().add(bntAcessarCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 260, 70));

        bntCRUDCliente.setContentAreaFilled(false);
        bntCRUDCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCRUDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCRUDClienteActionPerformed(evt);
            }
        });
        getContentPane().add(bntCRUDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 260, 70));

        bntCRUDFuncionario.setContentAreaFilled(false);
        bntCRUDFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCRUDFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCRUDFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(bntCRUDFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 260, 70));

        bntFechar.setContentAreaFilled(false);
        bntFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFecharActionPerformed(evt);
            }
        });
        getContentPane().add(bntFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 190, 50));

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

        bntRead6.setContentAreaFilled(false);
        bntRead6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntRead6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntRead6ActionPerformed(evt);
            }
        });
        getContentPane().add(bntRead6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 130, 40));

        bntPesquisa.setContentAreaFilled(false);
        getContentPane().add(bntPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 130, 40));

        txtCodigoProduto.setBackground(new java.awt.Color(240, 240, 240));
        txtCodigoProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtCodigoProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCodigoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(txtCodigoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 100, 40));

        txtNomeProduto.setBackground(new java.awt.Color(240, 240, 240));
        txtNomeProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtNomeProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtNomeProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomeProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 460, 40));

        txtQuantidadeProduto.setBackground(new java.awt.Color(240, 240, 240));
        txtQuantidadeProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtQuantidadeProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtQuantidadeProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(txtQuantidadeProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 140, 40));

        txtTamanhoProduto.setBackground(new java.awt.Color(240, 240, 240));
        txtTamanhoProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtTamanhoProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtTamanhoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTamanhoProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTamanhoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 120, 40));

        txtMarcaProduto.setBackground(new java.awt.Color(236, 240, 241));
        txtMarcaProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtMarcaProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtMarcaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(txtMarcaProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 120, 40));

        txtValorProduto.setBackground(new java.awt.Color(240, 240, 240));
        txtValorProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtValorProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtValorProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(txtValorProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 210, 40));

        txtStatusProduto.setBackground(new java.awt.Color(236, 240, 241));
        txtStatusProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtStatusProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtStatusProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(txtStatusProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 120, 40));

        cboCategoriaProduto.setBackground(new java.awt.Color(102, 102, 102));
        cboCategoriaProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboCategoriaProduto.setToolTipText("");
        cboCategoriaProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboCategoriaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboCategoriaProdutoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboCategoriaProdutoMouseReleased(evt);
            }
        });
        cboCategoriaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCategoriaProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(cboCategoriaProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, 120, 40));

        cboIDCategoriaProduto.setBackground(new java.awt.Color(102, 102, 102));
        cboIDCategoriaProduto.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboIDCategoriaProduto.setToolTipText("");
        cboIDCategoriaProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboIDCategoriaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIDCategoriaProdutoActionPerformed(evt);
            }
        });
        getContentPane().add(cboIDCategoriaProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, 140, 40));

        lblBemVindo.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        lblBemVindo.setForeground(new java.awt.Color(255, 255, 255));
        lblBemVindo.setText("Bem-Vindo");
        getContentPane().add(lblBemVindo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, -1, -1));

        lblFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Tela CRUD Produto.jpg"))); // NOI18N
        getContentPane().add(lblFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        lblNivelCRUDProduto.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        lblNivelCRUDProduto.setForeground(new java.awt.Color(255, 255, 255));
        lblNivelCRUDProduto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivelCRUDProduto.setText("Nivel");
        getContentPane().add(lblNivelCRUDProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        lblUsuarioCRUDProduto.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        lblUsuarioCRUDProduto.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioCRUDProduto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsuarioCRUDProduto.setText("Usuario");
        lblUsuarioCRUDProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(lblUsuarioCRUDProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bntFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bntFecharActionPerformed

    private void bntAcessarCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAcessarCaixaActionPerformed
        frmCaixaVIEW acessarCaixa = new frmCaixaVIEW();
        this.dispose();
        acessarCaixa.setVisible(true);
    }//GEN-LAST:event_bntAcessarCaixaActionPerformed

    private void bntCRUDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCRUDClienteActionPerformed
        frmCRUDClienteVIEW CRUDCliente = new frmCRUDClienteVIEW();
        this.dispose();
        CRUDCliente.setVisible(true);
    }//GEN-LAST:event_bntCRUDClienteActionPerformed

    private void bntCRUDFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCRUDFuncionarioActionPerformed
        frmCRUDFuncionarioVIEW CRUDFuncionario = new frmCRUDFuncionarioVIEW();
        this.dispose();
        CRUDFuncionario.setVisible(true);
    }//GEN-LAST:event_bntCRUDFuncionarioActionPerformed

    private void txtTamanhoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTamanhoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTamanhoProdutoActionPerformed

    private void txtCodigoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProdutoActionPerformed

    private void txtMarcaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaProdutoActionPerformed

    private void txtStatusProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusProdutoActionPerformed

    private void txtValorProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorProdutoActionPerformed

    private void txtNomeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeProdutoActionPerformed

    private void txtQuantidadeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeProdutoActionPerformed

    private void bntLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLimparActionPerformed
        txtCodigoProduto.setText("");
        txtNomeProduto.setText("");
        txtQuantidadeProduto.setText("");
        txtTamanhoProduto.setText("");
        txtMarcaProduto.setText("");
        txtValorProduto.setText("");
        txtStatusProduto.setText("");
        cboCategoriaProduto.setSelectedIndex(-1);
    }//GEN-LAST:event_bntLimparActionPerformed

    private void cboCategoriaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCategoriaProdutoActionPerformed
        Connection conn;
        PreparedStatement pstm;
        ResultSet rs = null;

        conn = new ConexaoDAO().conectaBD();

        //String sql = "SELECT * FROM Categoria where nome_categoria=" + cboCategoriaProduto.getSelectedItem().toString();
        String sql = "SELECT * FROM Categoria where nome_categoria=?";

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cboCategoriaProduto.getSelectedItem().toString());
            rs = pstm.executeQuery();

            if (rs.next()) {
                cboIDCategoriaProduto.setSelectedItem(rs.getString(1));
                //Referente ao readOnly da IdCategoria
                cboIDCategoriaProduto.setEnabled(false);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CRUDProduto(ID-Produto): " + erro);
        }
    }//GEN-LAST:event_cboCategoriaProdutoActionPerformed

    private void bntInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInserirActionPerformed
        inserirProduto();
        
        txtCodigoProduto.setText("");
        txtNomeProduto.setText("");
        txtQuantidadeProduto.setText("");
        txtTamanhoProduto.setText("");
        txtMarcaProduto.setText("");
        txtValorProduto.setText("");
        txtStatusProduto.setText("");
        cboCategoriaProduto.setSelectedIndex(-1);
    }//GEN-LAST:event_bntInserirActionPerformed

    private void cboIDCategoriaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIDCategoriaProdutoActionPerformed

    }//GEN-LAST:event_cboIDCategoriaProdutoActionPerformed

    private void cboCategoriaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCategoriaProdutoMouseClicked

    }//GEN-LAST:event_cboCategoriaProdutoMouseClicked

    private void cboCategoriaProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCategoriaProdutoMouseReleased

    }//GEN-LAST:event_cboCategoriaProdutoMouseReleased

    private void bntRead6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntRead6ActionPerformed
        consultarProduto();
    }//GEN-LAST:event_bntRead6ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void bntAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAlterarActionPerformed
        alterarUsuario();
    }//GEN-LAST:event_bntAlterarActionPerformed

    private void bntExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExcluirActionPerformed
        deletarUsuario();
    }//GEN-LAST:event_bntExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(frmCRUDProdutosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCRUDProdutosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCRUDProdutosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCRUDProdutosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCRUDProdutosVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAcessarCaixa;
    private javax.swing.JButton bntAlterar;
    private javax.swing.JButton bntCRUDCliente;
    private javax.swing.JButton bntCRUDFuncionario;
    private javax.swing.JButton bntExcluir;
    private javax.swing.JButton bntFechar;
    private javax.swing.JButton bntInserir;
    private javax.swing.JButton bntLimpar;
    private javax.swing.JButton bntPesquisa;
    private javax.swing.JButton bntRead6;
    private javax.swing.JComboBox<String> cboCategoriaProduto;
    private javax.swing.JComboBox<String> cboIDCategoriaProduto;
    private javax.swing.JLabel lblBemVindo;
    private javax.swing.JLabel lblFundo;
    public static javax.swing.JLabel lblNivelCRUDProduto;
    public static javax.swing.JLabel lblUsuarioCRUDProduto;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtMarcaProduto;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtQuantidadeProduto;
    private javax.swing.JTextField txtStatusProduto;
    private javax.swing.JTextField txtTamanhoProduto;
    private javax.swing.JTextField txtValorProduto;
    // End of variables declaration//GEN-END:variables

    Vector<Integer> categoria_item = new Vector<Integer>();
    Vector<Integer> categoria_id = new Vector<Integer>();

    public void restaurarDadosComboBox() {

        try {

            UsuarioDAO objUsuarioDAO = new UsuarioDAO();
            ResultSet rs = objUsuarioDAO.listComboBox();

            while (rs.next()) {
                categoria_item.addElement(rs.getInt(1));
                cboCategoriaProduto.addItem(rs.getString(2));
                cboIDCategoriaProduto.addItem(rs.getString(1));
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "CRUDProduto(restaurarDadosComboBox): " + e);
        }
    }
}
