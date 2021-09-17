package cnpm.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cnpm.entity.Shoe;
import cnpm.repository.ShoeRepository;
import cnpm.service.ShoeService;

@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminProductController {
	@Autowired
	private ShoeService shoeService;

	@Autowired
	private ShoeRepository shoeRepository;

	@GetMapping("/products-management")
	public String showProduct(Model model) {
		return findPaginatedAdmin(1, model);
	}

	@GetMapping(value = "/products-management/{pageNo}")
	public String findPaginatedAdmin(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 10;

		Page<Shoe> page = shoeService.findPaginated(pageNo, pageSize);
		List<Shoe> lstShoe = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("shoes", lstShoe);
		return "admin/productsmanagement";
	}

	@GetMapping("/delete-product/{id}")
	public String deleteProduct(@PathVariable Long id) {

		shoeService.deleteShoeById(id);
		return "redirect:/admin/products-management";
	}

	@PostMapping("/creat-product")
	public String creatProduct(HttpServletRequest request, @RequestParam("image") MultipartFile multipartFile)
			throws ParseException, IOException {
		Shoe shoe = new Shoe();
		shoe.setName(request.getParameter("name"));
		shoe.setPrice(Double.parseDouble(request.getParameter("price")));
		shoe.setDes(request.getParameter("des"));

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		shoe.setPicture(fileName);

		Path path = Paths.get("src/main/resources/static/images/" + fileName);
		try {
			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		shoeRepository.save(shoe);
		return "redirect:/admin/products-management";
	}

	@PostMapping("/update-product")
	public String updateProduct(HttpServletRequest request, @RequestParam("imageUpdate") MultipartFile multipartFile)
			throws ParseException {
		long id = Long.parseLong(request.getParameter("id"));
		Optional<Shoe> shoe = shoeRepository.findById(id);
		shoe.get().setName(request.getParameter("nameUpdate"));
		shoe.get().setPrice(Double.parseDouble(request.getParameter("priceUpdate")));
		shoe.get().setDes(request.getParameter("desUpdate"));

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		shoe.get().setPicture(fileName);

		Path path = Paths.get("src/main/resources/static/images/" + fileName);
		try {
			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		shoeRepository.save(shoe.get());
		return "redirect:/admin/products-management";
	}
}
