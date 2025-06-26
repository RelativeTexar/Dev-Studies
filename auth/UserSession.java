// UserSession.java
package Login; // Certifique-se de que o pacote corresponde ao seu projeto

/**
 * Classe UserSession
 * Gerencia a sessão do usuário atual na aplicação.
 * Utiliza um atributo estático para manter o usuário logado globalmente.
 *
 * @author [Seu Nome]
 */
public class UserSession {
    private static User currentUser; //  // O usuário atualmente logado.

    /**
     * Define o usuário atualmente logado.
     *
     * @param user O objeto User a ser definido como usuário atual.
     */
    public static void setCurrentUser(User user) { // 
        UserSession.currentUser = user;
    }

    /**
     * Retorna o usuário atualmente logado.
     *
     * @return O objeto User do usuário atual, ou null se ninguém estiver logado.
     */
    public static User getCurrentUser() { // 
        return currentUser;
    }
}