package entities;

import java.sql.*;

public class UsuarioDAO {

    public void addUsuario(String nome, String email, String senha){

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = DB.getConnection();
            ps = conn.prepareStatement("INSERT INTO usuarios"
                                          + " (nome, gmail, senha_hash)"
                                          + " VALUES"
                                          + " (?, ?, ?)",
                                          Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, senha);

            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closePreparedStatement(ps);
            DB.closeConnection();
        }
    }

    public User loginUsuario(String email, String senha) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DB.getConnection();


            ps = conn.prepareStatement("SELECT * FROM usuarios"
                    + " WHERE gmail = ?"
                    + " AND senha_hash = ?");

            ps.setString(1, email);
            ps.setString(2, senha);

            rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId_usuario(rs.getInt("id"));
                user.setNome_usuario(rs.getString("nome"));
                user.setEmail_usuario(rs.getString("gmail"));
                user.setSenha_usuario(rs.getString("senha_hash"));
                return user;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closePreparedStatement(ps);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
        return null;
    }
}
