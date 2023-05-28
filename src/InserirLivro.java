import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class InserirLivro {
    private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
    private final String user = "postgres";
    private final String password = "123456";
    private String id_isbn,nm_titulo;
    private int id_categoria;
    private int id_editora;


    private static final String INSERT_USERS_SQL = "INSERT INTO Livro" +
            "  (id_isbn, id_categoria, id_editora, nm_titulo) VALUES (?, ?, ?, ?)";

    public static void main(String[] argv) throws SQLException {
        InserirLivro createTableExample = new InserirLivro();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString (1, id_isbn = JOptionPane.showInputDialog("Digite o id_isbn"));
            preparedStatement.setInt(2, id_categoria = Integer.parseInt(JOptionPane.showInputDialog("Digite o id_categiria")));
            preparedStatement.setInt(3,id_editora = Integer.parseInt(JOptionPane.showInputDialog("Digite o id_editora")));
            preparedStatement.setString(4,nm_titulo = JOptionPane.showInputDialog("Digite o nm_titulo"));


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
