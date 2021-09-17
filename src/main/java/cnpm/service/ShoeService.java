package cnpm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cnpm.entity.Shoe;

public interface ShoeService {

	List<Shoe> findAllShoes();

//	
	Page<Shoe> findShoesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands);

//	List<Article> findFirstArticles();

	Page<Shoe> findPaginated(int pageNo, int pageSize);

	Shoe findShoeById(Long id);

	Shoe saveShoe(Shoe shoe);

	void deleteShoeById(Long id);

	List<String> getAllSizes();

	List<String> getAllCategories();

	List<String> getAllBrands();

	List<Shoe> findByTopPrice();

	List<Shoe> findByRandom();

	List<String> findName(String name);

	Shoe findShoeByName(String name);
}
