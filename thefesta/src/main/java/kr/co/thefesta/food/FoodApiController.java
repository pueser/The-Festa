package kr.co.thefesta.food;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import kr.co.thefesta.food.domain.ItemDTO;
import kr.co.thefesta.food.domain.ResultDTO;
import kr.co.thefesta.food.service.IFoodService;
import lombok.extern.log4j.Log4j;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/call")
@Log4j
public class FoodApiController {

	@Autowired
	private IFoodService service;
	

	@GetMapping("/api")
	public void apiCall(){

		log.info("api call success.........");
		
		// RestTemplate 인스턴스 생성
		RestTemplate restTemplate = new RestTemplate();
	    restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
	    
	    // Gson 인스턴스 생성
	    Gson gson = new Gson();
		
		try {
			// baseURL
			String baseUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";

			// service Key
			String decodeServiceKey = "sxM6XuFTCETUKfUia3EOsqJj2UPNmebC5DENhavYsj7KsSdf+tC6t+i+PtSLoiy+1mfRsDqOQo0+FibnTAKUqQ==";
			String encodeServiceKey = URLEncoder.encode(decodeServiceKey, "UTF-8");

			// API 호출을 위한 파라미터 설정
			String pageNo = "5";
			String numOfRows = "50";
			String mobileApp = "AppTest";
			String mobileOS = "ETC";
			String contentTypeId = "39";
			String arrange = "O";
			String type = "json";

			// URI 및 파라미터 설정
			URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
					.queryParam("serviceKey", encodeServiceKey)
					.queryParam("pageNo", pageNo)
					.queryParam("numOfRows", numOfRows)
					.queryParam("MobileApp", mobileApp)
					.queryParam("MobileOS", mobileOS)
					.queryParam("contentTypeId", contentTypeId)
					.queryParam("arrange", arrange)
					.queryParam("_type", type)
					.build(true)
					.toUri();

//			log.info("uri address : " + uri);

			// HttpHeaders 설정
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			// API 호출
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
			log.info("API Response : " + response.getBody());
			
			ResultDTO resultDto = gson.fromJson(response.getBody(), ResultDTO.class);
//			log.info("resultDto : " + resultDto.toString());
			
			ArrayList<ItemDTO> itemDto = resultDto.getResponse().getBody().getItems().getItem();
//			log.info("itemDto : " + itemDto.toString());
			
//			for (ItemDTO item : itemDto) {
//				log.info(item.getContentid());
//			}
			String contentid = "";
			
			String baseUrl1 = "http://apis.data.go.kr/B551011/KorService1/detailCommon1";
			String baseUrl2 = "http://apis.data.go.kr/B551011/KorService1/detailIntro1";
			
			
			for (ItemDTO item : itemDto) {
				contentid = item.getContentid();
				
				URI uri1 = UriComponentsBuilder.fromHttpUrl(baseUrl1)
						.queryParam("serviceKey", encodeServiceKey)
						.queryParam("MobileApp", mobileApp)
						.queryParam("MobileOS", mobileOS)
						.queryParam("contentId", contentid)
						.queryParam("overviewYN", "Y")
						.queryParam("_type", type)
						.build(true)
						.toUri();
				
				URI uri2 = UriComponentsBuilder.fromHttpUrl(baseUrl2)
						.queryParam("serviceKey", encodeServiceKey)
						.queryParam("MobileApp", mobileApp)
						.queryParam("MobileOS", mobileOS)
						.queryParam("contentId", contentid)
						.queryParam("contentTypeId", contentTypeId)
						.queryParam("_type", type)
						.build(true)
						.toUri();
				
				log.info("uri1 address : " + uri1);
				log.info("uri2 address : " + uri2);
				
				// uri1 API 호출
				ResponseEntity<String> response1 = restTemplate.exchange(uri1, HttpMethod.GET, null, String.class);
//				log.info("API Response1 : " + response1.getBody());
				
				// uri2 API 호출
				ResponseEntity<String> response2 = restTemplate.exchange(uri2,  HttpMethod.GET, null, String.class);
//				log.info("API Response2 : " + response2.getBody());
				
				
				//uri1 json 값을 ResultDTO에 담기
				ResultDTO resultDto1 = gson.fromJson(response1.getBody(), ResultDTO.class);
				log.info("resultDto1 : " + resultDto1.getResponse().getBody().getItems().getItem().get(0).getOverview());
				
				//uri2 json 값을 ResultDTO에 담기
				ResultDTO resultDto2 = gson.fromJson(response2.getBody(), ResultDTO.class);
				log.info("resultDto2 : " + resultDto2.toString());
				
				
				// ResultDTO에서 값 가져오기
				String overview = resultDto1.getResponse().getBody().getItems().getItem().get(0).getOverview();
				String firstmenu = resultDto2.getResponse().getBody().getItems().getItem().get(0).getFirstmenu();
				String treatmenu = resultDto2.getResponse().getBody().getItems().getItem().get(0).getTreatmenu();
				String infocenterfood = resultDto2.getResponse().getBody().getItems().getItem().get(0).getInfocenterfood();
				String parkingfood = resultDto2.getResponse().getBody().getItems().getItem().get(0).getParkingfood();
				String opentimefood = resultDto2.getResponse().getBody().getItems().getItem().get(0).getOpentimefood();
				String restdatefood = resultDto2.getResponse().getBody().getItems().getItem().get(0).getRestdatefood();
				
				// ItemDTO에 값 담기
				item.setOverview(overview);
				item.setFirstmenu(firstmenu);
				item.setTreatmenu(treatmenu);
				item.setInfocenterfood(infocenterfood);
				item.setParkingfood(parkingfood);
				item.setOpentimefood(opentimefood);
				item.setRestdatefood(restdatefood);
				
			}
//			log.info("ItemDTO : " + itemDto.toString());
			
			//DB 저장
			for (ItemDTO item : itemDto) {
				service.create(item);
			}
			log.info("DB 저장 완료");

		} catch (UnsupportedEncodingException e) {
			log.error("Error encoding service key", e);
//			return "Error encoding service key";
		} catch (Exception e) {
			log.error("Error making API call", e);
//			return "Error making API call";
		}
	}
}
