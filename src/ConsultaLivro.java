
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConsultaLivro {


    private String NomLivro;
    private static final String SELECT_LIVRO_SQL = "select nm_titulo from livro where nm_titulo like ?  ;";


    private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
    private final String user = "postgres";
    private final String password = "123456";
    Connection conn = null;


    public static void main(String[] argv) throws SQLException {
        ConsultaLivro ConsultarStatementExample = new ConsultaLivro();
        ConsultarStatementExample.selectRecord();
    }


    public void selectRecord() throws SQLException {
        System.out.println(SELECT_LIVRO_SQL);

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVRO_SQL);) {
            preparedStatement.setString(1, JOptionPane.showInputDialog("Digite o Inicio do titulo do livro") + "%");

            ResultSet rs = preparedStatement.executeQuery();



        }
        public void getAllUsers() {

            try {
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_LIVRO_SQL);
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {

                    String name_autor = rs.getString("nm_autor");
                    System.out.println(name_autor + " - " + name_autor);
                }
            } catch (SQLException e) {

            }
        }
    }
}
