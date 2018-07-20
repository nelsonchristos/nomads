package com.example.inventory.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/demo")
public class MainController {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired 
	private StockRepository stockRepository;
	@Autowired 
	private StockGroupRepository stockGrpRepository;
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewStock (@RequestParam String name) {
		StockGroup grp = new StockGroup.Builder()
				.groupName("Bit")
				.build();
		stockGrpRepository.save(grp);
		
		Stock stk = new Stock.Builder()
					.name(name)
					.group(grp)
					.isActive(true)
					.build();
		
		stockRepository.save(stk);
		return "Saved";
	}
	
	@GetMapping(path="/update") // Map ONLY GET Requests
	public @ResponseBody String editStock (@RequestParam String name,@RequestParam Long count) {
		Stock stk = stockRepository.findByName(name).get(0);
		stk.setCount(count);
		stockRepository.save(stk);
		return "Updated";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Stock> getAllStock() {
	/*	stockRepository
				.findAll()
				.forEach(stock -> log.info(stock.getGroup().getGroupName()));*/
		
		return stockRepository.findAll();
	}
}
