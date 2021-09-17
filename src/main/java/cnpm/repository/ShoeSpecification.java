package cnpm.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cnpm.entity.Shoe;
import cnpm.entity.Brand;
import cnpm.entity.Category;
import cnpm.entity.Size;

public class ShoeSpecification {

	private ShoeSpecification() {
	}

	@SuppressWarnings("serial")
	public static Specification<Shoe> filterBy(Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands) {
		return new Specification<Shoe>() {
			@Override
			public Predicate toPredicate(Root<Shoe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				query.distinct(true);
				if (sizes != null && !sizes.isEmpty()) {
					Join<Shoe, Size> joinSize = root.join("sizes");
					predicates.add(criteriaBuilder.and(joinSize.get("value").in(sizes)));
				}
				if (categories != null && !categories.isEmpty()) {
					Join<Shoe, Category> joinSize = root.join("categories");
					predicates.add(criteriaBuilder.and(joinSize.get("name").in(categories)));
				}
				if (brands != null && !brands.isEmpty()) {
					Join<Shoe, Brand> joinSize = root.join("brands");
					predicates.add(criteriaBuilder.and(joinSize.get("name").in(brands)));
				}

				if (priceLow != null && priceLow >= 0) {
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceLow)));
				}
				if (priceHigh != null && priceHigh >= 0) {
					predicates
							.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceHigh)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
