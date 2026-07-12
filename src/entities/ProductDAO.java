package entities;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {


    public List<Product> findAll() {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        List<Product> products = new ArrayList<>();
        try {
            conn = DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select * from produtos");

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id_produto"));
                product.setName(rs.getString("nome_produto"));
                product.setPrice(rs.getDouble("preco_produto"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
        return products;
    }

    public void addProduct(String nome, Double preco) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "INSERT INTO produtos"
                            + " (nome_produto, preco_produto)"
                            + " VALUES"
                            + " (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);


            ps.setString(1, nome);
            ps.setDouble(2, preco);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closePreparedStatement(ps);
            DB.closeConnection();
        }
    }

    public int addOrder(LocalDate datePedido, Double valorFinal, int idUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;

        int idGerado = 0;
        try {

            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "INSERT INTO pedido"
                            + " (data_pedido, valor_final, idusuario)"
                            + " VALUES"
                            + " (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, java.sql.Date.valueOf(datePedido));
            ps.setDouble(2, valorFinal);
            ps.setInt(3, idUsuario);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closePreparedStatement(ps);
            DB.closeConnection();
        }
        return idGerado;
    }

    public void orderProducts(int idPedido, int idProduto, int quantidade){
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    " INSERT INTO itens_pedido"
                    + " (id_pedido, id_produto, quantidade) "
                    + "VALUES"
                    + " (?, ?, ?)");

            ps.setInt(1, idPedido);
            ps.setInt(2, idProduto);
            ps.setInt(3, quantidade);

            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(ps);
            DB.closeConnection();
        }
    }
}

