// AuthenticationService.java
package Login; // Certifique-se de que o pacote corresponde ao seu projeto

/**
 * Classe AuthenticationService
 * Implementa a interface LoginService, fornecendo a lógica de autenticação.
 * Aqui, o polimorfismo é aplicado, pois esta é uma implementação concreta
 * do serviço de login.
 *
 * @author [Seu Nome]
 */
public class AuthenticationService implements LoginService { // 
    private User currentUser; // 

    /**
     * Tenta autenticar um usuário com o nome de usuário e senha fornecidos.
     * Sobrescrita do método da interface LoginService.
     * Para este problema, o usuário de teste é "denys" e a senha "poo123456". 
     *
     * @param username O nome de usuário.
     * @param password A senha.
     * @return true se o login for bem-sucedido, false caso contrário.
     */
    @Override // 
    public boolean login(String username, String password) {
        //  // POLIMORFISMO: Este método é uma implementação concreta do método 'login'
        //  // definido na interface LoginService. A capacidade de ter múltiplas
        //  // implementações de um método de interface é uma forma de polimorfismo
        //  // (sobrescrita, neste caso).
        //  // Justificativa: Permite que diferentes estratégias de autenticação
        //  // sejam usadas (ex: autenticação por banco de dados, LDAP, etc.)
        //  // mantendo a mesma interface de chamada, tornando o sistema flexível.

        // Usuário de teste: "denys" e "poo123456" 
        if ("denys".equals(username) && "poo123456".equals(password)) {
            currentUser = new User(username, password);
            UserSession.setCurrentUser(currentUser);
            return true;
        } else {
            currentUser = null;
            UserSession.setCurrentUser(null);
            return false;
        }
    }

    /**
     * Realiza o logout do usuário atual.
     * Sobrescrita do método da interface LoginService.
     */
    @Override // 
    public void logout() {
        //  // POLIMORFISMO: Implementação concreta do método 'logout' da interface.
        //  // Justificativa: Garante que qualquer serviço de login saiba como
        //  // finalizar a sessão, independentemente de como a autenticação foi feita.
        currentUser = null;
        UserSession.setCurrentUser(null);
    }

    /**
     * Retorna o usuário atualmente logado.
     * Sobrescrita do método da interface LoginService.
     *
     * @return O objeto User do usuário atual.
     */
    @Override // 
    public User getCurrentUser() {
        //  // POLIMORFISMO: Implementação concreta do método 'getCurrentUser' da interface.
        //  // Justificativa: Fornece uma maneira padronizada de obter o usuário logado.
        return currentUser;
    }
}