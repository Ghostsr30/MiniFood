package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public List<Product> findAll() {

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
            DB.closeConnection();
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return products;
    }
}

