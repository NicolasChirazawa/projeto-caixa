
package VIEW;

import DAO.ConexaoDAO;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class databaseFuncionarioTest extends javax.swing.JFrame {

    public databaseFuncionarioTest() {
        initComponents();
        showUser();
    }
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    public ArrayList<User> userList(){
        ArrayList<User> usersList = new ArrayList<>();

        try
        {
            String url = "jdbc:mysql://localhost:3306/ProjetoLojaOfc?user=root&password=Carrinhos12";
            conn = DriverManager.getConnection(url);
            String query1;
            if (txtNome.getText().equals(""))
            {
                query1  = "select * from Funcionario";
            }
            else
            {
                query1  = "select * from Funcionario where nome_Funcionario like '%" + txtNome.getText() + "%'";
            }
           // String query1 = "SELECT * FROM Funcionario";
            Statement st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            User user;

            while(rs.next())
            {
                user = new User (rs.getInt("codigo_funcionario"), rs.getString("nome_funcionario"), rs.getString("username_funcionario"), rs.getString("senha_funcionario"), rs.getString("perfil_funcionario"));
                usersList.add(user);

            }
        }
        catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "User(Database Funcionario): " + erro);
        }
        return usersList;
    }

        public void showUser()
        {
            ArrayList<User> list = userList();
            DefaultTableModel model = (DefaultTableModel) jTable_Display_User.getModel();
            Object[] row = new Object[5];

            for (int i = 0; i<list.size(); i++)
            {
                row[0]=list.get(i).getCodigo();
                row[1]=list.get(i).getNome();
                row[2]=list.get(i).getUsername();
                row[3]=list.get(i).getSenha();
                row[4]=list.get(i).getPerfil();
                model.addRow(row);

            }
        
        }
        


        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_User = new javax.swing.JTable();
        txtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jTable_Display_User.setBackground(new java.awt.Color(240, 240, 240));
        jTable_Display_User.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nome", "username", "senha", "perfil"
            }
        ));
        jTable_Display_User.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable_Display_UserKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_User);

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });

        jLabel1.setText("Pesquisa:");

        jButton1.setText("Voltar");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap(277, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        showUser();
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
    
    }//GEN-LAST:event_txtNomeKeyPressed

    private void jTable_Display_UserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_Display_UserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_Display_UserKeyPressed

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
    DefaultTableModel model = (DefaultTableModel) jTable_Display_User.getModel();
    model.setRowCount(0);
    showUser();
    }//GEN-LAST:event_txtNomeKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frmCRUDFuncionarioVIEW CRUDFuncionario = new frmCRUDFuncionarioVIEW();
        CRUDFuncionario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new databaseFuncionarioTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_User;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
