package cnpm.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cnpm.entity.Shoe;
import cnpm.repository.ShoeRepository;
import cnpm.repository.ShoeSpecification;
import cnpm.service.ShoeService;

@Service
@Transactional
public class ShoeServiceImpl implements ShoeService {

	@Autowired
	private ShoeRepository shoeRepository;

	@Override
	public List<Shoe> findAllShoes() {
		return (List<Shoe>) shoeRepository.findAllEagerBy();
	}

	@Override
	public Page<Shoe> findShoesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands) {
		Page<Shoe> page = shoeRepository
				.findAll(ShoeSpecification.filterBy(priceLow, priceHigh, sizes, categories, brands), pageable);
		return page;
	}

	@Override
	public Shoe findShoeById(Long id) {
		Optional<Shoe> opt = shoeRepository.findById(id);
		return opt.get();
	}

	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public Shoe saveShoe(Shoe shoe) {
		return shoeRepository.save(shoe);
	}

	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public void deleteShoeById(Long id) {
		shoeRepository.deleteById(id);
	}

	@Override
	@Cacheable("sizes")
	public List<String> getAllSizes() {
		return shoeRepository.findAllSizes();
	}

	@Override
	@Cacheable("categories")
	public List<String> getAllCategories() {
		return shoeRepository.findAllCategories();
	}

	@Override
	@Cacheable("brands")
	public List<String> getAllBrands() {
		return shoeRepository.findAllBrands();
	}

	@Override
	public List<Shoe> findByTopPrice() {
		return shoeRepository.findByTopPrice();
	}

	@Override
	public List<String> findName(String name) {
		return shoeRepository.findName(name);

	}

	@Override
	public Shoe findShoeByName(String name) {
		return shoeRepository.findShoeByName(name);
	}

	@Override
	public Page<Shoe> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.shoeRepository.findAll(pageable);
	}

	@Override
	public List<Shoe> findByRandom() {
		return shoeRepository.findRandom();
	}
}
