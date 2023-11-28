package kr.co.thefesta.food;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/food")
@Log4j
public class FoodController {

	@GetMapping("/suggest")
	public String foodSuggest() {
		log.info("food page connect.....");
		
		return "detail";
	}
	
	@GetMapping("/quartz")
	public String index() {
		return "Hello, This is food page....";
	}
}
