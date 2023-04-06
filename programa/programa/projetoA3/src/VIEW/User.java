package VIEW;

class User 
{
    private int codigo;
    private String nome, username, senha, perfil;
    
        public User (int codigo, String nome, String username, String senha, String perfil)
        {
            this.codigo = codigo;
            this.nome = nome;
            this.username = username;
            this.senha = senha;
            this.perfil = perfil;           
        }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
    
    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public String getPerfil() {
        return perfil;
    }
    
}
