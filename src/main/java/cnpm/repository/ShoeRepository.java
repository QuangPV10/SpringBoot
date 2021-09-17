package cnpm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import cnpm.entity.Shoe;

public interface ShoeRepository extends JpaRepository<Shoe, Long>, JpaSpecificationExecutor<Shoe> {

	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	List<Shoe> findAllEagerBy();

	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	Optional<Shoe> findById(Long id);

	@Query("SELECT DISTINCT s.value FROM Size s")
	List<String> findAllSizes();

	@Query("SELECT DISTINCT c.name FROM Category c")
	List<String> findAllCategories();

	@Query("SELECT DISTINCT b.name FROM Brand b")
	List<String> findAllBrands();

	@Query(value = "SELECT * FROM shoe order by price desc LIMIT 4", nativeQuery = true)
	List<Shoe> findByTopPrice();
	
	@Query(value = "SELECT * FROM shoe order by rand() LIMIT 4", nativeQuery = true)
	List<Shoe> findRandom();
	
	
	
	@Query(value = "SELECT name from shoe where name LIKE CONCAT('%',?1,'%')", nativeQuery = true)
	List<String> findName(String name);
	
	@Query(value = "SELECT * from shoe where name = ?1", nativeQuery = true)
	Shoe findShoeByName(String name);
	
	


}
