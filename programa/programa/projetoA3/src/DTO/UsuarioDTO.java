package DTO;

public class UsuarioDTO {
    
    private int id_usuario;
    private String nome_usuario, username_usuario, senha_usuario, perfil_usuario;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }
        
    public String getUsername_usuario() {
        return username_usuario;
    }

    public void setUsername_usuario(String username_usuario) {
        this.username_usuario = username_usuario;
    }
    
    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getPerfil_usuario() {
        return perfil_usuario;
    }

    public void setPerfil_usuario(String perfil_usuario) {
        this.perfil_usuario = perfil_usuario;
    }
    
    
}
