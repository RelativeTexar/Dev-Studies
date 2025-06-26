// User.java
package Login; // Certifique-se de que o pacote corresponde ao seu projeto

/**
 * Classe User
 * Representa um usuário com nome de usuário e senha.
 * Aplica o encapsulamento, mantendo os atributos privados e
 * fornecendo métodos públicos para acessá-los (getters) e modificá-los (setters).
 *
 * @author [Seu Nome]
 */
public class User {
    private String username; // 
    private String password; // 

    /**
     * Construtor da classe User.
     *
     * @param username O nome de usuário.
     * @param password A senha.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retorna o nome de usuário.
     *
     * @return O nome de usuário.
     */
    public String getUsername() { // 
        return username;
    }

    /**
     * Define o nome de usuário.
     *
     * @param username O novo nome de usuário.
     */
    public void setUsername(String username) { // 
        this.username = username;
    }

    /**
     * Retorna a senha.
     *
     * @return A senha.
     */
    public String getPassword() { // 
        return password;
    }

    /**
     * Define a senha.
     *
     * @param password A nova senha.
     */
    public void setPassword(String password) { // 
        this.password = password;
    }
}