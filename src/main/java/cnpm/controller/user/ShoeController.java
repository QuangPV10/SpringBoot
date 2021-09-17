package cnpm.controller.user;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cnpm.entity.Shoe;
import cnpm.model.ShoeFilterForm;
import cnpm.model.SortFilter;
import cnpm.service.ShoeService;

@Controller
public class ShoeController {

	@Autowired
	private ShoeService shoeService;

	@RequestMapping("/san-pham")
	public String shoeList(@ModelAttribute("filters") ShoeFilterForm filters, Model model) {
		List<Shoe> shoes = shoeService.findAllShoes();
		model.addAttribute("shoes", shoes);
		model.addAttribute("allCategories", shoeService.getAllCategories());
		model.addAttribute("allBrands", shoeService.getAllBrands());

		model.addAttribute("allSizes", shoeService.getAllSizes());
		Integer page = filters.getPage();
		int pagenumber = (page == null || page <= 0) ? 0 : page - 1;
		SortFilter sortFilter = new SortFilter(filters.getSort());
		Page<Shoe> pageresult = shoeService.findShoesByCriteria(PageRequest.of(pagenumber, 8, sortFilter.getSortType()),
				filters.getPricelow(), filters.getPricehigh(), filters.getSize(), filters.getCategory(),
				filters.getBrand());

		model.addAttribute("shoes", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 8);

		return "product";
	}

	@GetMapping("/san-pham")
	public String showProduct(@ModelAttribute("filters") ShoeFilterForm filters, Model model) {
		return findPaginatedAdmin(filters, 1, model);
	}

//
	@GetMapping(value = "/san-pham/{pageNo}")
	public String findPaginatedAdmin(@ModelAttribute("filters") ShoeFilterForm filters,
			@PathVariable(value = "pageNo") int pageNo, Model model) {

		List<Shoe> shoes = shoeService.findAllShoes();
		model.addAttribute("shoes", shoes);
		model.addAttribute("allCategories", shoeService.getAllCategories());
		model.addAttribute("allBrands", shoeService.getAllBrands());

		model.addAttribute("allSizes", shoeService.getAllSizes());
		int pageSize = 8;

		SortFilter sortFilter = new SortFilter(filters.getSort());
		Page<Shoe> pageresult = shoeService.findShoesByCriteria(
				PageRequest.of(pageNo, pageSize, sortFilter.getSortType()), filters.getPricelow(),
				filters.getPricehigh(), filters.getSize(), filters.getCategory(), filters.getBrand());

//		pageresult = shoeService.findPaginated(pageNo, pageSize);
		List<Shoe> lstShoe = pageresult.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageresult.getTotalPages());
		model.addAttribute("totalItems", pageresult.getTotalElements());
		model.addAttribute("shoes", lstShoe);

		return "product";
	}

	@RequestMapping("/chi-tiet-san-pham")
	public String shoeDetail(@PathParam("id") Long id, Model model) {
		Shoe shoe = shoeService.findShoeById(id);
		model.addAttribute("shoe", shoe);
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addShoeSuccess", model.asMap().get("addShoeSuccess"));
		return "productdetail";
	}

	@RequestMapping("/tim-kiem/{name}")
	public String shoeDetailSearch(@PathVariable("name") String name, Model model) {
		model.addAttribute("shoe", shoeService.findShoeByName(name));
		System.out.println(shoeService.findShoeByName(name));
		return "searchdetail";
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	public List<String> findItem(@RequestParam("term") String name) {

		return shoeService.findName(name);

	}
}
