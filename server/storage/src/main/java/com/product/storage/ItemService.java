package com.product.storage;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	private final ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	public Item getItemById(Integer id) {
		return itemRepository.getItemById(id);
	}

	public List<Item> getItems() {
		return itemRepository.getAllItems();
	}

	public void addItem(Item item) throws Exception {

		int id = -1;
		try {
			id = itemRepository.getItemId(item.getBrandName(), item.getSize());
			if (id == -1)
				itemRepository.addItem(item);
			else {
				itemRepository.updateItem(id, item.getQuantity(), item.getPrice());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
			// log in future
		}
	}

	public void updateItem(Integer id, Item item) {
		itemRepository.updateItem(id, item.getQuantity(), item.getPrice());
	}

	public void deleteItem(Integer id) {
		itemRepository.deleteItem(id);
	}
}
