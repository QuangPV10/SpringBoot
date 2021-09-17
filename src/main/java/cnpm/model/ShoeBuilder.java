package cnpm.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cnpm.entity.Brand;
import cnpm.entity.Category;
import cnpm.entity.Shoe;
import cnpm.entity.Size;

public class ShoeBuilder {

	private String name;
	private String des;
	private int stock;
	private double price;
	private String picture;
	private List<String> sizes;
	private List<String> categories;
	private List<String> brands;

	public ShoeBuilder() {
	}

	public ShoeBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ShoeBuilder withDes(String des) {
		this.des = des;
		return this;
	}

	public ShoeBuilder stockAvailable(int stock) {
		this.stock = stock;
		return this;
	}

	public ShoeBuilder withPrice(double price) {
		this.price = price;
		return this;
	}

	public ShoeBuilder imageLink(String picture) {
		this.picture = picture;
		return this;
	}

	public ShoeBuilder sizesAvailable(List<String> sizes) {
		this.sizes = sizes;
		return this;
	}

	public ShoeBuilder ofCategories(List<String> categories) {
		this.categories = categories;
		return this;
	}

	public ShoeBuilder ofBrand(List<String> brands) {
		this.brands = brands;
		return this;
	}

	public Shoe build() {
		Shoe shoe = new Shoe();
		shoe.setName(this.name);
		shoe.setDes(this.des);
		shoe.setPrice(this.price);
		shoe.setStock(this.stock);
		shoe.setPicture(this.picture);

		if (this.sizes != null && !this.sizes.isEmpty()) {
			Set<Size> sizeElements = new HashSet<>();
			for (String val : this.sizes) {
				sizeElements.add(new Size(val, shoe));
			}
			shoe.setSizes(sizeElements);
		}

		if (this.categories != null && !this.categories.isEmpty()) {
			Set<Category> catElements = new HashSet<>();
			for (String val : this.categories) {
				catElements.add(new Category(val, shoe));
			}
			shoe.setCategories(catElements);
		}
		if (this.brands != null && !this.brands.isEmpty()) {
			Set<Brand> brandlements = new HashSet<>();
			for (String val : this.brands) {
				brandlements.add(new Brand(val, shoe));
			}
			shoe.setBrands(brandlements);
		}

		return shoe;
	}

}