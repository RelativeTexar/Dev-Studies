// AcessoSistema.java
package Login; // Certifique-se de que o pacote corresponde ao seu projeto

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe AcessoSistema
 * Programa principal que cria a interface gráfica de login.
 * Utiliza Swing para a criação da tela e gerencia a interação do usuário.
 * Aplica tratamento de exceções para lidar com possíveis erros.
 *
 * @author [Seu Nome]
 */
public class AcessoSistema extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginService loginService; //  // Uso da interface para aplicar polimorfismo

    public AcessoSistema() {
        super("Acesso ao Sistema"); // 

        //  // INTERFACE: A variável 'loginService' é do tipo LoginService (interface).
        //  // POLIMORFISMO: Ela é instanciada com uma classe que implementa essa interface
        //  // (AuthenticationService). Isso permite que, no futuro, possamos facilmente
        //  // trocar a implementação do serviço de login sem alterar o código da interface
        //  // gráfica, promovendo um alto nível de desacoplamento.
        //  // Justificativa: Facilita a manutenção, o teste e a evolução do sistema,
        //  // pois a lógica de autenticação pode ser substituída sem impactar a UI.
        loginService = new AuthenticationService();

        // Configurações da janela 
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela 
        setResizable(false);

        // Layout
        setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Componentes da interface
        JLabel userLabel = new JLabel("Usuário:"); // 
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Senha:"); // 
        passwordField = new JPasswordField(20);

        JButton enterButton = new JButton("Entrar"); // 
        JButton cancelButton = new JButton("Cancelar"); // 
        JButton newuserButton = new JButton("Novo Usuário"); // 

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(enterButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(newuserButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ações dos botões
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { //  // Tratamento de exceção: Try-Catch-Finally
                    handleLogin();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AcessoSistema.this,
                            "Ocorreu um erro inesperado: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Para depuração
                } finally { // 
                    // Bloco finally é executado independentemente de ocorrer uma exceção.
                    // Pode ser usado para limpeza de recursos, por exemplo.
                    // Para este caso, não há uma ação específica no finally, mas é bom ter o exemplo.
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  // Clicando no botão "Cancelar" deverá encerrar o sistema.
                System.exit(0);
            }
        });

        newuserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  // Clicando no botão "Novo Usuário" deverá apresentar a mensagem "Em desenvolvimento."
                JOptionPane.showMessageDialog(AcessoSistema.this,
                        "Em desenvolvimento.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (loginService.login(username, password)) {
            //  // Acesso bem-sucedido
            User currentUser = UserSession.getCurrentUser();
            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + currentUser.getUsername() + "!",
                    "Login Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Aqui você poderia abrir a próxima janela do sistema
            // Exemplo: new TelaPrincipal().setVisible(true);
            dispose(); // Fecha a janela de login
        } else {
            //  // Login inválido
            JOptionPane.showMessageDialog(this,
                    "Login inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Garante que a GUI seja criada na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AcessoSistema().setVisible(true);
            }
        });
    }
}