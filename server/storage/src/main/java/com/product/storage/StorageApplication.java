package com.product.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StorageApplication {

	public StorageApplication(com.product.storage.ItemRepository itemRepository) {
		super();
	}

	public static void main(String[] args) {
		SpringApplication.run(StorageApplication.class, args);

	}
}
