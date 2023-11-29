package kr.co.thefesta.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.thefesta.food.domain.ItemDTO;
import kr.co.thefesta.food.service.IFoodService;
import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/food")
@Log4j
public class FoodController {
	
	@Autowired
	IFoodService service;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAll(ModelAndView mav) throws Exception {
		log.info("food list connect.....");
		
		List<ItemDTO> itemDto = service.listAll();
		log.info("list's itemDto : " + itemDto);
		
		mav = new ModelAndView("/food/list");
		mav.addObject("itemDto", itemDto);

		return mav;
		
	}
	
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail() {
		
		log.info("food detail connect.....");
		ModelAndView mav = new ModelAndView("/food/detail");
		
		return mav;
		
	}
}
