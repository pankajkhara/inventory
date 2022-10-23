package com.product.storage;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ItemRowMapper implements RowMapper<Item>{
	 @Override
	    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		 	Item item = new Item();
		 	item.setId(rs.getInt("id"));
		 	item.setBrandName(rs.getString("brandname"));
		 	item.setSize(rs.getString("size"));
		 	item.setQuantity(rs.getInt("quantity"));
		 	item.setPrice(rs.getDouble("price"));

	        return item;
	    }
}
