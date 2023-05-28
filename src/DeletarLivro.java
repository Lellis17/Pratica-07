import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarLivro {

    private static final String DELETE_Livro_SQL = "delete from Livro where id_isbn = ?;";

    private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
    private final String user = "postgres";
    private final String password = "123456";
    private String id_isbn;

    public static void main(String[] argv) throws SQLException {
        DeletarLivro deleteStatementExample = new DeletarLivro();
        deleteStatementExample.deleteRecord();
    }

    public void deleteRecord() throws SQLException {
        System.out.println(DELETE_Livro_SQL);


        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_Livro_SQL);) {
            preparedStatement.setString(1,id_isbn = JOptionPane.showInputDialog("Digite o id o livro que voce deseja deletar"));

            // Step 3: Execute the query or update query
            int result = preparedStatement.executeUpdate();
            System.out.println("Number of records affected :: " + result);
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