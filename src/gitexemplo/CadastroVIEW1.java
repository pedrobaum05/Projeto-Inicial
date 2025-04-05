import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CadastroVIEW1 extends JFrame {
    private JTextField textNome, textValor;
    private JComboBox<String> comboStatus;
    private JButton btnSalvar;

    public CadastroVIEW1() {
        // Configurações básicas do JFrame
        setTitle("Tela de Cadastro");
        setBounds(100, 100, 400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel para os campos de entrada
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));  // Layout para alinhar os componentes
        add(panel, BorderLayout.CENTER);

        // Adicionando componentes (campos de texto e botão)
        panel.add(new JLabel("Nome:"));
        textNome = new JTextField();
        panel.add(textNome);

        panel.add(new JLabel("Valor:"));
        textValor = new JTextField();
        panel.add(textValor);

        panel.add(new JLabel("Status:"));
        comboStatus = new JComboBox<>(new String[]{"Vendido", "A venda"});
        panel.add(comboStatus);

        btnSalvar = new JButton("Salvar");
        panel.add(btnSalvar);

        // Definir ação para o botão Salvar
        btnSalvar.addActionListener(e -> salvarCadastro());

        // Exibir a janela
        setVisible(true);
    }

    // Método para salvar dados no banco
    private void salvarCadastro() {
        String nome = textNome.getText();
        String valor = textValor.getText();
        String status = (String) comboStatus.getSelectedItem();

        try (Connection con = Conexao2.getConnection()) {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, nome);
                pst.setString(2, valor);
                pst.setString(3, status);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco de dados.");
        }
    }

    public static void main(String[] args) {
        // Inicia a tela de cadastro
        new CadastroVIEW1();
    }
}