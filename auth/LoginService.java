// LoginService.java
package Login; // Certifique-se de que o pacote corresponde ao seu projeto

/**
 * <<interface>> LoginService
 * Define o contrato para serviços de login, permitindo polimorfismo.
 * Classes que implementam esta interface devem fornecer a lógica para
 * autenticação, logout e recuperação do usuário atual.
 *
 * @author [Seu Nome]
 */
public interface LoginService {
    /**
     * Tenta autenticar um usuário com o nome de usuário e senha fornecidos.
     *
     * @param username O nome de usuário.
     * @param password A senha.
     * @return true se o login for bem-sucedido, false caso contrário.
     */
    boolean login(String username, String password); // 

    /**
     * Realiza o logout do usuário atual.
     */
    void logout(); // 

    /**
     * Retorna o usuário atualmente logado.
     *
     * @return O objeto User do usuário atual, ou null se ninguém estiver logado.
     */
    User getCurrentUser(); // 
}