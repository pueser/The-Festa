package kr.co.thefesta.food;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/food")
@Log4j
public class FoodController {

	@GetMapping("/suggest")
	public String foodSuggest() {
		log.info("food page connect.....");
		
		return "detail";
	}
}
