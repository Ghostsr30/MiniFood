package entities;

import java.sql.*;
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

    public void addProduct(String nome, Double preco){
        Connection conn = null;
        PreparedStatement ps = null;

        try{
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
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closePreparedStatement(ps);
            DB.closeConnection();
        }
    }
}

