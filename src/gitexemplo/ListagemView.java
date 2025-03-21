import conexao2.java.Conexao2;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ListagemVIEW1 {
    private JFrame frame;
    private JTable table;

    public static void main(String[] args) {
        ListagemVIEW1 window = new ListagemVIEW1();
        window.frame.setVisible(true);
    }

    public ListagemVIEW1() {
        // Configura a janela de listagem
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Adiciona a tabela
        table = new JTable();
        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        // Preenche a tabela com dados
        atualizarTabela();
    }

    // Atualiza a tabela com dados do banco
    public void atualizarTabela() {
        List<Object[]> produtos = getProdutos();
        String[] colunas = {"ID", "Nome", "Valor", "Status"};
        Object[][] dados = new Object[produtos.size()][4];

        for (int i = 0; i < produtos.size(); i++) {
            dados[i] = produtos.get(i);
        }

        table.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }

    // Recupera os produtos do banco
    public List<Object[]> getProdutos() {
        List<Object[]> produtos = new ArrayList<>();
        try (Connection con = Conexao2.getConnection()) {
            String sql = "SELECT * FROM produtos";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String valor = rs.getString("valor");
                String status = rs.getString("status");
                produtos.add(new Object[]{id, nome, valor, status});
            }
        } catch (SQLException e) {
        }
        return produtos;
    }
}
