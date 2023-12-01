package kr.co.thefesta.festival;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.PageDTO;
import kr.co.thefesta.festival.domain.areacode.AreaCodeDataDTO;
import kr.co.thefesta.festival.domain.areacode.AreaCodeItemDTO;
import kr.co.thefesta.festival.domain.detailCommon.DetailCommonDTO;
import kr.co.thefesta.festival.domain.detailCommon.DetailCommonItemDTO;
import kr.co.thefesta.festival.domain.detailImage.DetailImageDTO;
import kr.co.thefesta.festival.domain.detailImage.DetailImageItemDTO;
import kr.co.thefesta.festival.domain.detailInfo.DetailInfoDTO;
import kr.co.thefesta.festival.domain.detailInfo.DetailInfoItemDTO;
import kr.co.thefesta.festival.domain.detailIntro.DetailIntroDTO;
import kr.co.thefesta.festival.domain.detailIntro.DetailIntroItemDTO;
import kr.co.thefesta.festival.domain.searchFestival.SearchFestivalDTO;
import kr.co.thefesta.festival.domain.searchFestival.SearchFestivalItemDTO;
import kr.co.thefesta.festival.service.IFestivalService;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/getApi")
@CrossOrigin(origins = "*")
public class FestivalAPIController {
	@Autowired
	private IFestivalService service;
	
	private String API_KEY = "2CQIk%2BsA5VR%2Bxo%2BB%2FYaxP2uKEoNjx1QUnlxo0zZ0GTXgbiBzgX%2FKmndkj27rvGsy6TZhWQkNl5ewBnR1qaCspg%3D%3D";
	
	@GetMapping("/areaApi")
	public void festivalCodeGET() throws Exception {
	    System.out.println("/getApi-------------------------");

	    // 첫 번째 HTTP GET 요청
	    List<AreaCodeItemDTO> acItems = performAreaCodeRequest("");

	    // 두 번째 HTTP GET 요청 및 시군구 코드 처리
	    processSigunguCodes(acItems);
	}

	// 지역 코드 정보를 얻기 위한 공통 메소드
	private List<AreaCodeItemDTO> performAreaCodeRequest(String endpoint) throws Exception {
	    StringBuilder acodeUrlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/areaCode1");
	    acodeUrlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + API_KEY);
	    acodeUrlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
	    acodeUrlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=TheFesta");
	    acodeUrlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=100");
	    acodeUrlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
	    acodeUrlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
	    
	    if (!endpoint.isEmpty()) {
	    	acodeUrlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + endpoint);
		}
	    
	    URL acUrl = new URL(acodeUrlBuilder.toString());
	    HttpURLConnection acStream = (HttpURLConnection) acUrl.openConnection();
	    BufferedReader acReader = new BufferedReader(new InputStreamReader(acStream.getInputStream()));

	    StringBuilder acResultBuilder = new StringBuilder();
	    String str;
	    while ((str = acReader.readLine()) != null) {
	        acResultBuilder.append(str);
	    }

	    acReader.close();
	    acStream.disconnect();

	    String acResult = acResultBuilder.toString();
	    Gson gson = new Gson();
	    AreaCodeDataDTO acResponse = gson.fromJson(acResult, AreaCodeDataDTO.class);
	    return acResponse.getResponse().getBody().getItems().getItem();
	}

	// 시군구 코드 처리를 위한 메소드
	private void processSigunguCodes(List<AreaCodeItemDTO> acItems) throws Exception {
		System.out.println("processSigunguCodes-----");
		AreaCodeDTO aDto = new AreaCodeDTO();
	    
		for (AreaCodeItemDTO areaCodeItemDTO : acItems) {
	        System.out.println("areaCodeItemDTO------");

	        aDto.setAcode(areaCodeItemDTO.getCode());
	        aDto.setAname(areaCodeItemDTO.getName());
	        
	        switch (areaCodeItemDTO.getRnum()) {
	            case 1:
	                insertSigunguCodes("1", areaCodeItemDTO, aDto);
	                break;
	            case 2:
	            	insertSigunguCodes("2", areaCodeItemDTO, aDto);
	            	break;
	            case 3:
	            	insertSigunguCodes("3", areaCodeItemDTO, aDto);
	            	break;
	            case 4:
	            	insertSigunguCodes("4", areaCodeItemDTO, aDto);
	            	break;
	            case 5:
	            	insertSigunguCodes("5", areaCodeItemDTO, aDto);
	            	break;
	            case 6:
	            	insertSigunguCodes("6", areaCodeItemDTO, aDto);
	            	break;
	            case 7:
	            	insertSigunguCodes("7", areaCodeItemDTO, aDto);
	            	break;
	            case 8:
	            	insertSigunguCodes("8", areaCodeItemDTO, aDto);
	            	break;
	            case 9:
	            	insertSigunguCodes("31", areaCodeItemDTO, aDto);
	            	break;
	            case 10:
	            	insertSigunguCodes("32", areaCodeItemDTO, aDto);
	            	break;
	            case 11:
	            	insertSigunguCodes("33", areaCodeItemDTO, aDto);
	            	break;
	            case 12:
	            	insertSigunguCodes("34", areaCodeItemDTO, aDto);
	            	break;
	            case 13:
	            	insertSigunguCodes("35", areaCodeItemDTO, aDto);
	            	break;
	            case 14:
	            	insertSigunguCodes("36", areaCodeItemDTO, aDto);
	            	break;
	            case 15:
	            	insertSigunguCodes("37", areaCodeItemDTO, aDto);
	            	break;
	            case 16:
	            	insertSigunguCodes("38", areaCodeItemDTO, aDto);
	            	break;
	            case 17:
	            	insertSigunguCodes("39", areaCodeItemDTO, aDto);
	            	break;
	            default:
	                break;
	        }
	    }
	}
	
	private void insertSigunguCodes(String rnum, AreaCodeItemDTO areaCodeItemDTO, AreaCodeDTO aDto) throws Exception {
		System.out.println("insertSigunguCodes-----------------------------------");
		
		List<AreaCodeItemDTO> aList = null;
       
        aList = performAreaCodeRequest(rnum);
		for (AreaCodeItemDTO areaCodeItemDTO2 : aList) {
			aDto.setScode(areaCodeItemDTO2.getCode());
			aDto.setSname(areaCodeItemDTO2.getName());
			
			service.insertAreaCode(aDto);
		};
	}
}
