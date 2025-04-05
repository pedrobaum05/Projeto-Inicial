import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao2 {

    // Defina as informações do banco de dados (ajuste conforme sua configuração)
    private static final String URL = "jdbc:mysql://localhost:3306/Leiloesupdate";  // URL do seu banco de dados
    private static final String USER = "root";  // Usuário do banco de dados
    private static final String PASSWORD = "04022002";  // Senha do banco de dados

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Criar e retornar a conexão
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver JDBC não encontrado", e);
        }
    }
}
