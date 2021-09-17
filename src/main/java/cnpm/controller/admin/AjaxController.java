package cnpm.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cnpm.model.BrowserMarketResult;
import cnpm.model.BrowserResult;
import cnpm.model.BrowserResultSeries;
import cnpm.model.SalesResult;
import cnpm.model.WorldwideSalesResult;
import cnpm.repository.OrderRepository;
import cnpm.repository.UserRepository;

@RestController
public class AjaxController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping("/ajax/fuck1")
	public WorldwideSalesResult worldwideSalesResult1() {
		List<String> saleYears = new ArrayList<String>();
		saleYears.add("Doanh thu");

		List<SalesResult> salesResults = new ArrayList<SalesResult>();

		for (String saleYear : saleYears) {
			SalesResult salesResult = new SalesResult();
			salesResult.setName(saleYear);
			salesResult.setData(buildSalesData1());

			salesResults.add(salesResult);
		}

		WorldwideSalesResult result = new WorldwideSalesResult();
		result.setTitle("Sales Statistics By Year ");
		result.setCategories(buildSalesCategories1());
		result.setSeries(salesResults);

		return result;
	}

	// lấy năm trong db
	private List<String> buildSalesCategories1() {
		List<String> categories = orderRepository.getYear();

		return categories;
	}

	// gám value cho năm
	private List<Double> buildSalesData1() {

		List<Double> salesData = new ArrayList<Double>();
		List<String> categories = orderRepository.getYear();

		for (int i = 0; i < categories.size(); i++) {
			if (orderRepository.getTotalYear(Integer.parseInt(categories.get(i))) == null) {

				salesData.add(0.0);

			} else {
				salesData.add(Double.parseDouble(orderRepository.getTotalYear(Integer.parseInt(categories.get(i)))));
			}

		}

		return salesData;
	}

	@RequestMapping("/ajax/fuck")
	public WorldwideSalesResult worldwideSalesResult() {
		List<String> saleYears = new ArrayList<String>();
		saleYears.add("Amount Product");

		List<SalesResult> salesResults = new ArrayList<SalesResult>();

		for (String saleYear : saleYears) {
			SalesResult salesResult = new SalesResult();
			salesResult.setName(saleYear);
			salesResult.setData(buildSalesData());

			salesResults.add(salesResult);
		}

		WorldwideSalesResult result = new WorldwideSalesResult();
		result.setTitle("Statistics Of Products Sold By Year ");
		result.setCategories(buildSalesCategories());
		result.setSeries(salesResults);

		return result;
	}

	// lấy năm trong db
	private List<String> buildSalesCategories() {
		List<String> categories = orderRepository.getYear();

		return categories;
	}

	// gám value cho năm
	private List<Double> buildSalesData() {

		List<Double> salesData = new ArrayList<Double>();
		List<String> categories = orderRepository.getYear();

		for (int i = 0; i < categories.size(); i++) {
			if (orderRepository.getQtyYear(Integer.parseInt(categories.get(i))) == null) {

				salesData.add(0.0);

			} else {
				salesData.add(Double.parseDouble(orderRepository.getQtyYear(Integer.parseInt(categories.get(i)))));
			}

		}

		return salesData;
	}

	@RequestMapping("/ajax/browser_market_result")
	public BrowserMarketResult browserMarketResult() {

		BrowserMarketResult result = new BrowserMarketResult();
		result.setTitle("Gender Chart");
		result.setSeries(buildBrowserMarketData());

		return result;
	}

	private List<BrowserResultSeries> buildBrowserMarketData() {
		List<BrowserResultSeries> results = new ArrayList<BrowserResultSeries>();

		BrowserResultSeries result = new BrowserResultSeries();
		result.setName("Brands");

		List<BrowserResult> browserResults = new ArrayList<BrowserResult>();

		List<String> list = userRepository.test();

		double another = userRepository.test1("another");
		double male = userRepository.test1("male");
		double female = userRepository.test1("female");

		double sum = another + male + female;

		double[] arr = { another, male, female };

		for (int i = 0; i < list.size(); i++) {
			BrowserResult browserResult = new BrowserResult();
			browserResult.setName(list.get(i));
			browserResult.setY(arr[i] / sum * 100);

			browserResults.add(browserResult);

		}

		result.setData(browserResults);
		results.add(result);
		return results;
	}

	@RequestMapping("/ajax/browser_market_result1")
	public BrowserMarketResult browserMarketResult1() {

		BrowserMarketResult result = new BrowserMarketResult();
		result.setTitle("Total By Month");
		result.setSeries(buildBrowserMarketData1());

		return result;
	}

	private List<BrowserResultSeries> buildBrowserMarketData1() {
		List<BrowserResultSeries> results = new ArrayList<BrowserResultSeries>();

		BrowserResultSeries result = new BrowserResultSeries();
		result.setName("Price");

		List<BrowserResult> browserResults = new ArrayList<BrowserResult>();

		for (int i = 0; i < 13; i++) {
			BrowserResult browserResult = new BrowserResult();
			browserResult.setName("Month " + i);
			if (orderRepository.getTotal(i) == null) {
				browserResult.setY(0.0);
			} else {
				browserResult.setY(Double.parseDouble(orderRepository.getTotal(i)));
			}

			browserResults.add(browserResult);

		}

		result.setData(browserResults);
		results.add(result);
		return results;
	}

}
