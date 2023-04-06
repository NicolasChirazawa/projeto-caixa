package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs; 
    
    /*
    
    String nomeLoginUsuario;
    int categoria_item, categoria_id;
    
    public String getNomeLoginUsuario() {
        return nomeLoginUsuario;
    }

    public void setNomeLoginUsuario(String nomeLoginUsuario) {
        this.nomeLoginUsuario = nomeLoginUsuario;
    }

    public int getCategoria_item() {
        return categoria_item;
    }

    public void setCategoria_item(int categoria_item) {
        this.categoria_item = categoria_item;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
    
    */
    
    public ResultSet listComboBox(){
    
    conn = new ConexaoDAO().conectaBD();
        
        try{
        String sql = "select codigo_categoria, nome_categoria from Categoria";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        return pstm.executeQuery();
        
        //ResultSet rs=pstm.executeQuery();     
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "UsuarioDAO: (Combobox: CategoriaCRUDProduto): " + e);
            return null;
        }
    }
    
    public ResultSet ComboBoxClienteCaixa(){
    
    conn = new ConexaoDAO().conectaBD();
        
        try{
        String sql = "select codigo_cliente, nome_cliente from Cliente";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        return pstm.executeQuery();
        
        //ResultSet rs=pstm.executeQuery();     
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "UsuarioDAO: (Combobox: ClienteCaixa): " + e);
            return null;
        }
    }
    
    public ResultSet ComboBoxFuncionarioCaixa(){
    
    conn = new ConexaoDAO().conectaBD();
        
        try{
        String sql = "select codigo_funcionario, nome_funcionario from Funcionario";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        return pstm.executeQuery();
        
        //ResultSet rs=pstm.executeQuery();     
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "UsuarioDAO: (Combobox: FuncionarioCaixa): " + e);
            return null;
        }
    }
    
    public ResultSet ComboBoxProdutoCaixa(){
    
    conn = new ConexaoDAO().conectaBD();
        
        try{
        String sql = "select codigo_produto, nome_produto from Produto";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        return pstm.executeQuery();
        
        //ResultSet rs=pstm.executeQuery();     
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "UsuarioDAO: (Combobox: ProdutoCaixa): " + e);
            return null;
        }
    }
   
/*
    public ResultSet autenticacaoUsuario(UsuarioDTO objusuarioDTO) {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "select * from Funcionario usuario where nome_funcionario = ? and senha_funcionario = ?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objusuarioDTO.getNome_usuario());
            pstm.setString(2, objusuarioDTO.getSenha_usuario());
            
            ResultSet rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO(AutenticarUsuario): " + erro);
            return null;
        }
    } 
        
    //Resolver no futuro
    /*
    

    public void cadastrarUsuario (UsuarioDTO objusuarioDTO) {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "INSERT INTO Funcionario (nome_funcionario, username_funcionario, senha_funcionario, perfil_funcionario) VALUES (?, ?, ?, ?) ";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objusuarioDTO.getNome_usuario());
            pstm.setString(2, objusuarioDTO.getUsername_usuario());
            pstm.setString(3, objusuarioDTO.getSenha_usuario());
            pstm.setString(4, objusuarioDTO.getPerfil_usuario());
            
            //Validação dos campos de dados
            
            
            
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Usuario Cadastrado");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO(CadastrarUsuario):  " + erro);  
        }
    } 
    
        public void  pesquisarUsuario (UsuarioDTO objUsuarioDTO) {
        conn = new ConexaoDAO().conectaBD();

        frmCRUDFuncionarioVIEW lista = new frmCRUDFuncionarioVIEW();
        
        try {
            String sql = "SELECT * FROM Funcionario where codigo_funcionario = ? VALUE () ";
            
            pstm = conn.prepareStatement(sql);
            
            String recebeValor;
            recebeValor = lista.get
            
            pstm.setString(1, lista);
                
                
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO(PesquisarUsuario): " + erro);  
        }
    }
    
    */
    
}


