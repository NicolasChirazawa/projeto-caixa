/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import DAO.ConexaoDAO;
import DAO.UsuarioDAO;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class frmCaixaVIEW extends javax.swing.JFrame {

    /**
     * Creates new form frmCaixaVIEW
     */
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public frmCaixaVIEW() {
        initComponents();
        restaurarComboboxCliente();
        restaurarComboboxFuncionario();
        restaurarComboboxProduto();
    }

    private void iniciarNovaVenda() {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "INSERT INTO Venda (codigo_funcionario_venda, codigo_cliente_venda) VALUES (?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, (String) cboIDClienteVenda.getSelectedItem());
            pstm.setString(2, (String) cboIDFuncionarioVenda.getSelectedItem());

            //pstm.setString(3, txtDataVenda.getText());
            //Validação dos campos de dados
            if ( //Resolver problema combo box mensagem
                    cboClienteVenda.getSelectedItem().equals(-1)
                    || cboIDFuncionarioVenda.getSelectedItem().equals(-1)) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            } else {

                pstm.executeUpdate();

                ResultSet rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                    //JOptionPane.showMessageDialog(null, rs.getInt(1));
                    txtCodigoVenda.setText(String.valueOf(rs.getInt(1)));
                }

                pstm.close();

                JOptionPane.showMessageDialog(null, "Venda Iniciada: Insira os dados do produto");

            }
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "frmCaixaView(Iniciar Nova Venda):  " + erro);
        }
    }

    double total;

    public void Calcular() {
        if ("".equals(txtQuantidadeVenda.getText())) {
            return;
        }

        if (cboFormaPagamentoVenda.getSelectedItem() == "") {
            return;
        }

        if ("".equals(txtValorVenda.getText())) {
            return;
        }

        if (cboFormaPagamentoVenda.getSelectedItem() == "Dinheiro") {
            total = total + (Double.parseDouble(txtValorVenda.getText())) * Double.parseDouble(txtQuantidadeVenda.getText()) * 0.95;

            lblTotal.setText(Double.toString(total));
            return;
        }

        if (cboFormaPagamentoVenda.getSelectedItem() == "Dinheiro") {
            total = total + (Double.parseDouble(txtValorVenda.getText())) * Double.parseDouble(txtQuantidadeVenda.getText()) * 0.95;

            lblTotal.setText(Double.toString(total));
            return;
        }

        if (cboFormaPagamentoVenda.getSelectedItem() == "PIX") {
            total = total + (Double.parseDouble(txtValorVenda.getText())) * Double.parseDouble(txtQuantidadeVenda.getText()) * 0.95;

            lblTotal.setText(Double.toString(total));
            return;
        }

        if (cboFormaPagamentoVenda.getSelectedItem() == "Parcelado") {
            total = total + (Double.parseDouble(txtValorVenda.getText())) * Double.parseDouble(txtQuantidadeVenda.getText()) * 0.95;

            lblTotal.setText(Double.toString(total));
            return;
        }
    }

    private void addProduto() {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "INSERT INTO ItemVenda (codigo_venda_itemvenda, codigo_produto_itemvenda, qtde_itemvenda, preco_UNI_itemvenda) VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtCodigoVenda.getText());
            pstm.setString(2, (String) cboIDProdutoVenda.getSelectedItem());
            pstm.setString(3, txtQuantidadeVenda.getText());
            pstm.setString(4, txtValorVenda.getText());

            //pstm.setString(4, lblTotal.getText());
            //Validação dos campos de dados
            if (cboIDProdutoVenda.getSelectedItem().equals(-1)
                    || cboFormaPagamentoVenda.getSelectedItem().equals(-1)
                    || txtQuantidadeVenda.getText().isEmpty()
                    || txtValorVenda.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos");

            } else {

                Calcular();

                pstm.executeUpdate();

                pstm.close();

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "frmCaixar(addProduto):  " + erro);
        }
    }

    public void InserirTabela() {

        DefaultTableModel modelo = (DefaultTableModel) tabelaCaixa.getModel();

        Object[] dados = {txtCodigoVenda.getText(), cboIDProdutoVenda.getSelectedItem().toString(), txtQuantidadeVenda.getText(), txtValorVenda.getText()};
        modelo.addRow(dados);
    }

    int quantidadeTotal;
    int quantidadeComprada;
    
    private void guardarProduto() {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "SELECT qtde_produto FROM Produto WHERE codigo_produto=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, (String) cboIDProdutoVenda.getSelectedItem().toString());
            rs = pstm.executeQuery();
            
            if(rs.next()){
            quantidadeTotal = rs.getInt(1);
            quantidadeComprada = Integer.parseInt(txtQuantidadeVenda.getText());
            }

            //Validação dos campos de dados
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "frmCaixa(guardarProduto):  " + erro);
        }
    }
    
    private void atualizarEstoque(){
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "UPDATE Produto set qtde_produto=? WHERE codigo_produto=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(2, (String) cboIDProdutoVenda.getSelectedItem().toString());
            
            quantidadeTotal -= quantidadeComprada;
            System.out.println(quantidadeTotal);
            pstm.setInt(1, quantidadeTotal);            
           
            pstm.executeUpdate();

            //Validação dos campos de dados
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "frmCaixa(atualizarEstoque):  " + erro);
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
        bntCRUDFuncionario = new javax.swing.JButton();
        bntCRUDProdutos = new javax.swing.JButton();
        bntFechar = new javax.swing.JButton();
        bntInserirVenda = new javax.swing.JButton();
        bntInserirVenda1 = new javax.swing.JButton();
        bntAdd = new javax.swing.JButton();
        cboClienteVenda = new javax.swing.JComboBox<>();
        cboIDClienteVenda = new javax.swing.JComboBox<>();
        cboFuncionarioVenda = new javax.swing.JComboBox<>();
        cboIDFuncionarioVenda = new javax.swing.JComboBox<>();
        cboIDProdutoVenda = new javax.swing.JComboBox<>();
        cboProdutoVenda = new javax.swing.JComboBox<>();
        cboFormaPagamentoVenda = new javax.swing.JComboBox<>();
        txtCodigoVenda = new javax.swing.JTextField();
        txtDataVenda = new javax.swing.JTextField();
        txtQuantidadeVenda = new javax.swing.JTextField();
        txtValorVenda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCaixa = new javax.swing.JTable();
        lbl = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        bntCRUDProdutos.setContentAreaFilled(false);
        bntCRUDProdutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCRUDProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCRUDProdutosActionPerformed(evt);
            }
        });
        getContentPane().add(bntCRUDProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 260, 70));

        bntFechar.setContentAreaFilled(false);
        bntFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFecharActionPerformed(evt);
            }
        });
        getContentPane().add(bntFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 190, 50));

        bntInserirVenda.setContentAreaFilled(false);
        bntInserirVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntInserirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntInserirVendaActionPerformed(evt);
            }
        });
        getContentPane().add(bntInserirVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, 90, 50));

        bntInserirVenda1.setContentAreaFilled(false);
        bntInserirVenda1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(bntInserirVenda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 540, 90, 50));

        bntAdd.setContentAreaFilled(false);
        bntAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAddActionPerformed(evt);
            }
        });
        getContentPane().add(bntAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 90, 40));

        cboClienteVenda.setBackground(new java.awt.Color(102, 102, 102));
        cboClienteVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboClienteVenda.setToolTipText("");
        cboClienteVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboClienteVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboClienteVendaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboClienteVendaMouseReleased(evt);
            }
        });
        cboClienteVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClienteVendaActionPerformed(evt);
            }
        });
        getContentPane().add(cboClienteVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 130, 30));

        cboIDClienteVenda.setBackground(new java.awt.Color(102, 102, 102));
        cboIDClienteVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboIDClienteVenda.setToolTipText("");
        cboIDClienteVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboIDClienteVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboIDClienteVendaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboIDClienteVendaMouseReleased(evt);
            }
        });
        cboIDClienteVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIDClienteVendaActionPerformed(evt);
            }
        });
        getContentPane().add(cboIDClienteVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 100, 30));

        cboFuncionarioVenda.setBackground(new java.awt.Color(102, 102, 102));
        cboFuncionarioVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboFuncionarioVenda.setToolTipText("");
        cboFuncionarioVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboFuncionarioVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboFuncionarioVendaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboFuncionarioVendaMouseReleased(evt);
            }
        });
        cboFuncionarioVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFuncionarioVendaActionPerformed(evt);
            }
        });
        getContentPane().add(cboFuncionarioVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 130, 30));

        cboIDFuncionarioVenda.setBackground(new java.awt.Color(102, 102, 102));
        cboIDFuncionarioVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboIDFuncionarioVenda.setToolTipText("");
        cboIDFuncionarioVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboIDFuncionarioVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboIDFuncionarioVendaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboIDFuncionarioVendaMouseReleased(evt);
            }
        });
        cboIDFuncionarioVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIDFuncionarioVendaActionPerformed(evt);
            }
        });
        getContentPane().add(cboIDFuncionarioVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 100, 30));

        cboIDProdutoVenda.setBackground(new java.awt.Color(102, 102, 102));
        cboIDProdutoVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboIDProdutoVenda.setToolTipText("");
        cboIDProdutoVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboIDProdutoVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboIDProdutoVendaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboIDProdutoVendaMouseReleased(evt);
            }
        });
        cboIDProdutoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIDProdutoVendaActionPerformed(evt);
            }
        });
        getContentPane().add(cboIDProdutoVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, 110, 30));

        cboProdutoVenda.setBackground(new java.awt.Color(102, 102, 102));
        cboProdutoVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboProdutoVenda.setToolTipText("");
        cboProdutoVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboProdutoVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboProdutoVendaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboProdutoVendaMouseReleased(evt);
            }
        });
        cboProdutoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProdutoVendaActionPerformed(evt);
            }
        });
        getContentPane().add(cboProdutoVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 370, 30));

        cboFormaPagamentoVenda.setBackground(new java.awt.Color(102, 102, 102));
        cboFormaPagamentoVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboFormaPagamentoVenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "PIX", "Cartao" }));
        cboFormaPagamentoVenda.setSelectedIndex(-1);
        cboFormaPagamentoVenda.setToolTipText("");
        cboFormaPagamentoVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboFormaPagamentoVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboFormaPagamentoVendaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboFormaPagamentoVendaMouseReleased(evt);
            }
        });
        cboFormaPagamentoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFormaPagamentoVendaActionPerformed(evt);
            }
        });
        getContentPane().add(cboFormaPagamentoVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 200, 30));

        txtCodigoVenda.setBackground(new java.awt.Color(240, 240, 240));
        txtCodigoVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtCodigoVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCodigoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoVendaActionPerformed(evt);
            }
        });
        getContentPane().add(txtCodigoVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 140, 30));

        txtDataVenda.setBackground(new java.awt.Color(240, 240, 240));
        txtDataVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtDataVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtDataVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataVendaActionPerformed(evt);
            }
        });
        getContentPane().add(txtDataVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 100, 30));

        txtQuantidadeVenda.setBackground(new java.awt.Color(240, 240, 240));
        txtQuantidadeVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtQuantidadeVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtQuantidadeVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeVendaActionPerformed(evt);
            }
        });
        getContentPane().add(txtQuantidadeVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, 110, 30));

        txtValorVenda.setBackground(new java.awt.Color(240, 240, 240));
        txtValorVenda.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtValorVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtValorVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorVendaActionPerformed(evt);
            }
        });
        getContentPane().add(txtValorVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 140, 30));

        tabelaCaixa.setBackground(new java.awt.Color(240, 240, 240));
        tabelaCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Venda", "Código Produto", "Quantidade", "Preço Unitário"
            }
        ));
        jScrollPane1.setViewportView(tabelaCaixa);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 620, 170));

        lbl.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        lbl.setText("Total:");
        getContentPane().add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 570, -1, -1));

        lblTotal.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        getContentPane().add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 570, 260, 20));

        lblFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Acessar Caixa.jpg"))); // NOI18N
        getContentPane().add(lblFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bntCRUDProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCRUDProdutosActionPerformed
        frmCRUDProdutosVIEW CRUDProdutos = new frmCRUDProdutosVIEW();
        this.dispose();
        CRUDProdutos.setVisible(true);
    }//GEN-LAST:event_bntCRUDProdutosActionPerformed

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

    private void bntFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bntFecharActionPerformed

    private void txtCodigoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoVendaActionPerformed

    private void cboClienteVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboClienteVendaMouseClicked

    }//GEN-LAST:event_cboClienteVendaMouseClicked

    private void cboClienteVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboClienteVendaMouseReleased

    }//GEN-LAST:event_cboClienteVendaMouseReleased

    private void cboClienteVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClienteVendaActionPerformed
        Connection conn;
        PreparedStatement pstm;
        ResultSet rs = null;

        conn = new ConexaoDAO().conectaBD();

        //String sql = "SELECT * FROM Categoria where nome_categoria=" + cboCategoriaProduto.getSelectedItem().toString();
        String sql = "SELECT * FROM Cliente where nome_cliente=?";

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cboClienteVenda.getSelectedItem().toString());
            rs = pstm.executeQuery();

            if (rs.next()) {
                cboIDClienteVenda.setSelectedItem(rs.getString(1));

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Caixa(ID cliente): " + erro);
        }
    }//GEN-LAST:event_cboClienteVendaActionPerformed

    private void cboFuncionarioVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboFuncionarioVendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFuncionarioVendaMouseClicked

    private void cboFuncionarioVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboFuncionarioVendaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFuncionarioVendaMouseReleased

    private void cboFuncionarioVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFuncionarioVendaActionPerformed
        Connection conn;
        PreparedStatement pstm;
        ResultSet rs = null;

        conn = new ConexaoDAO().conectaBD();

        //String sql = "SELECT * FROM Categoria where nome_categoria=" + cboCategoriaProduto.getSelectedItem().toString();
        String sql = "SELECT * FROM Funcionario where nome_funcionario=?";

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cboFuncionarioVenda.getSelectedItem().toString());
            rs = pstm.executeQuery();

            if (rs.next()) {
                cboIDFuncionarioVenda.setSelectedItem(rs.getString(1));

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Caixa(id funcionario): " + erro);
        }
    }//GEN-LAST:event_cboFuncionarioVendaActionPerformed

    private void txtValorVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVendaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Referente ao readOnly
        txtCodigoVenda.setEnabled(false);
        txtDataVenda.setEnabled(false);
        cboIDClienteVenda.setEnabled(false);
        cboIDFuncionarioVenda.setEnabled(false);
        cboIDProdutoVenda.setEnabled(false);

        //Os dados produtos serão desabilitados até dar o "Novo"
        cboProdutoVenda.setEnabled(false);
        cboIDProdutoVenda.setEnabled(false);
        cboFormaPagamentoVenda.setEnabled(false);
        txtQuantidadeVenda.setEnabled(false);
        txtValorVenda.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void cboIDFuncionarioVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboIDFuncionarioVendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDFuncionarioVendaMouseClicked

    private void cboIDFuncionarioVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboIDFuncionarioVendaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDFuncionarioVendaMouseReleased

    private void cboIDFuncionarioVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIDFuncionarioVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDFuncionarioVendaActionPerformed

    private void cboIDClienteVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboIDClienteVendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDClienteVendaMouseClicked

    private void cboIDClienteVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboIDClienteVendaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDClienteVendaMouseReleased

    private void cboIDClienteVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIDClienteVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDClienteVendaActionPerformed

    private void bntInserirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInserirVendaActionPerformed
        iniciarNovaVenda();

        cboProdutoVenda.setEnabled(true);
        cboFormaPagamentoVenda.setEnabled(true);
        txtQuantidadeVenda.setEnabled(true);
        cboClienteVenda.setEnabled(false);
        cboFuncionarioVenda.setEnabled(false);
    }//GEN-LAST:event_bntInserirVendaActionPerformed

    private void cboFormaPagamentoVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboFormaPagamentoVendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFormaPagamentoVendaMouseClicked

    private void cboFormaPagamentoVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboFormaPagamentoVendaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFormaPagamentoVendaMouseReleased

    private void cboFormaPagamentoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFormaPagamentoVendaActionPerformed
        Connection conn;
        PreparedStatement pstm;
        ResultSet rs = null;

        conn = new ConexaoDAO().conectaBD();

        String sql = "SELECT * FROM Produto where nome_produto=?";

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cboProdutoVenda.getSelectedItem().toString());
            rs = pstm.executeQuery();

            if (rs.next()) {
                cboIDProdutoVenda.setSelectedItem(rs.getString(1));
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Caixa(id funcionario): " + erro);
        }
    }//GEN-LAST:event_cboFormaPagamentoVendaActionPerformed

    private void txtDataVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataVendaActionPerformed

    private void txtQuantidadeVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeVendaActionPerformed

    }//GEN-LAST:event_txtQuantidadeVendaActionPerformed

    private void cboProdutoVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboProdutoVendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProdutoVendaMouseClicked

    private void cboProdutoVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboProdutoVendaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProdutoVendaMouseReleased

    private void cboProdutoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProdutoVendaActionPerformed
        Connection conn;
        PreparedStatement pstm;
        ResultSet rs = null;

        conn = new ConexaoDAO().conectaBD();

        //String sql = "SELECT * FROM Categoria where nome_categoria=" + cboCategoriaProduto.getSelectedItem().toString();
        String sql = "SELECT * FROM Produto where nome_produto=?";

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cboProdutoVenda.getSelectedItem().toString());
            rs = pstm.executeQuery();

            if (rs.next()) {
                cboIDProdutoVenda.setSelectedItem(rs.getString(1));
                txtValorVenda.setText(rs.getString(6));

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Caixa(ID produto): " + erro);
        }
    }//GEN-LAST:event_cboProdutoVendaActionPerformed

    private void cboIDProdutoVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboIDProdutoVendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDProdutoVendaMouseClicked

    private void cboIDProdutoVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboIDProdutoVendaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDProdutoVendaMouseReleased

    private void cboIDProdutoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIDProdutoVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDProdutoVendaActionPerformed

    private void bntAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAddActionPerformed
        addProduto();
        InserirTabela();
        guardarProduto();
        atualizarEstoque();
    }//GEN-LAST:event_bntAddActionPerformed

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
            java.util.logging.Logger.getLogger(frmCaixaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCaixaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCaixaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCaixaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCaixaVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAdd;
    private javax.swing.JButton bntCRUDCliente;
    private javax.swing.JButton bntCRUDFuncionario;
    private javax.swing.JButton bntCRUDProdutos;
    private javax.swing.JButton bntFechar;
    private javax.swing.JButton bntInserirVenda;
    private javax.swing.JButton bntInserirVenda1;
    private javax.swing.JComboBox<String> cboClienteVenda;
    private javax.swing.JComboBox<String> cboFormaPagamentoVenda;
    private javax.swing.JComboBox<String> cboFuncionarioVenda;
    private javax.swing.JComboBox<String> cboIDClienteVenda;
    private javax.swing.JComboBox<String> cboIDFuncionarioVenda;
    private javax.swing.JComboBox<String> cboIDProdutoVenda;
    private javax.swing.JComboBox<String> cboProdutoVenda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblFundo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tabelaCaixa;
    private javax.swing.JTextField txtCodigoVenda;
    private javax.swing.JTextField txtDataVenda;
    private javax.swing.JTextField txtQuantidadeVenda;
    private javax.swing.JTextField txtValorVenda;
    // End of variables declaration//GEN-END:variables

    Vector<Integer> cliente_nome = new Vector<Integer>();
    Vector<Integer> cliente_id = new Vector<Integer>();

    public void restaurarComboboxCliente() {

        try {

            UsuarioDAO objUsuarioDAO = new UsuarioDAO();
            ResultSet rs = objUsuarioDAO.ComboBoxClienteCaixa();

            while (rs.next()) {
                cliente_nome.addElement(rs.getInt(1));
                cboClienteVenda.addItem(rs.getString(2));
                cboIDClienteVenda.addItem(rs.getString(1));
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "CRUDProduto(restaurarDadosComboBox: Cliente): " + e);
        }
    }

    Vector<Integer> funcionario_nome = new Vector<Integer>();

    public void restaurarComboboxFuncionario() {

        try {

            UsuarioDAO objUsuarioDAO = new UsuarioDAO();
            ResultSet rs = objUsuarioDAO.ComboBoxFuncionarioCaixa();

            while (rs.next()) {
                funcionario_nome.addElement(rs.getInt(1));
                cboFuncionarioVenda.addItem(rs.getString(2));
                cboIDFuncionarioVenda.addItem(rs.getString(1));
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "CRUDProduto(restaurarDadosComboBox: Funcionario): " + e);
        }
    }

    Vector<Integer> produto_nome = new Vector<Integer>();

    public void restaurarComboboxProduto() {

        try {

            UsuarioDAO objUsuarioDAO = new UsuarioDAO();
            ResultSet rs = objUsuarioDAO.ComboBoxProdutoCaixa();

            while (rs.next()) {
                produto_nome.addElement(rs.getInt(1));
                cboProdutoVenda.addItem(rs.getString(2));
                cboIDProdutoVenda.addItem(rs.getString(1));
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "CRUDProduto(restaurarDadosComboBox: Produto): " + e);
        }
    }
}
