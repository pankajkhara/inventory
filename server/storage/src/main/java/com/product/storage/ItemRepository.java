package com.product.storage;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;


@Repository
public class ItemRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ItemRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Item> getAllItems() {

		String sql = "select id, brandname, size , quantity, price from items ";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new Item(rs.getInt("id"), rs.getString("brandname"),
				rs.getString("size"), rs.getInt("quantity"), rs.getDouble("price")));
	}

	public Integer getQuantity(String brandName, String size) {

		Integer quantity = 0;
		try {
			String sql = "select quantity from items WHERE brandName = ? AND size = ?";
			quantity = jdbcTemplate.queryForObject(sql, Integer.class, brandName, size);
		} catch (EmptyResultDataAccessException ex) {
			System.out.println("Exception has been caught");
		}

		return quantity;
	}

	public Item getItemById(Integer id) {

		Item item = null;
		try {
			String query = "select * FROM items WHERE Id = ?";
			item = jdbcTemplate.queryForObject(query, new ItemRowMapper(), new Object[] { id });
		} catch (org.springframework.dao.EmptyResultDataAccessException ex) {
			throw new NoSuchElementException();
		}
		return item;
	}

	public Integer getItemId(String brandName, String size) {

		try {
			String sql = "select id from items WHERE brandName = ? AND size = ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, brandName, size);
		} catch (Exception ex) {
			return -1;
		}

	}

	public void addItem(Item item) {

		String insertQuery = "insert into items(brandname, size , quantity, price) VALUES(?,?,?,?)";
		Integer id = jdbcTemplate.update(insertQuery, item.getBrandName(), item.getSize(), item.getQuantity(),
				item.getPrice());
		System.out.println("One item added " + id);
	}

	public void updateItem(Integer id, Integer quantity, Double price) {
		try {
			String updateQuery = "update items set quantity = quantity + ?, price = ? where id = ?";
			Integer updated = jdbcTemplate.update(updateQuery, quantity, price, id);
			System.out.println("One item updated " + updated);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new NoSuchElementException(id + " was not updated");
		}
	}

	public void deleteItem(Integer id) {

		try {
			String deleteQuery = "delete from items where id = ?";
			jdbcTemplate.update(deleteQuery, id);
		} catch (Exception ex) {
			throw new NoSuchElementException(id + " was not deleted");
		}
	}
}
