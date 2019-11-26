package baserelacionala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Alba
 */
public class BaseRelacionalA {

    public static Connection conn = null;
//    public PreparedStatement pstmt;
//    public ResultSet rs;

    //EN LA CONSOLA CONECTARSE COMO HR
    private Connection conexion() {
        final String driver = "jdbc:oracle:thin:";
        final String host = "localhost.localdomain";
        final String porto = "1521";
        final String sid = "orcl";
        final String usuario = "hr";
        final String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

//        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insireProduto(String codigo, String descricion, int prezo) {
        String sql = "INSERT INTO produtos VALUES('" + codigo + "','" + descricion + "'," + prezo + ")";

        try (Connection conn = this.conexion();
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizaPre(String codigo, int prezo) {

        String sql = "UPDATE produtos SET prezo =" + prezo + " WHERE codigo ='" + codigo + "'";

        try (Connection conn = this.conexion();
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarFila(String codigo) {

        String sql = "DELETE FROM produtos WHERE codigo = '" + codigo + "'";

        try (Connection conn = this.conexion();
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void amosarFila(String codigo) {
        String sql = "SELECT * from produtos where codigo ='" + codigo + "'";

        try (Connection conn = this.conexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("codigo") + "\t"
                        + rs.getString("descricion") + "\t"
                        + rs.getInt("prezo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) throws SQLException {
        BaseRelacionalA obx = new BaseRelacionalA();
//        obx.insireProduto("p1", "parafusos", 3);
//        obx.insireProduto("p2", "cravos", 4);
//        obx.insireProduto("p3", "tachas", 6);

//        obx.insireProduto("p5", "tornillos", 8);
//        obx.actualizaPre("p5", 9);
        obx.borrarFila("p5");
//        obx.amosarFila("p1");
        conn.close();
    }

}
