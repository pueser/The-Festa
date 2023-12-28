package kr.co.thefesta.food;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import kr.co.thefesta.food.domain.AreacodeDTO;
import kr.co.thefesta.food.domain.Criteria;
import kr.co.thefesta.food.domain.LikeDTO;
import kr.co.thefesta.food.domain.NonUserDTO;
import kr.co.thefesta.food.domain.PageDTO;
import kr.co.thefesta.food.domain.RecommendDTO;
import kr.co.thefesta.food.domain.UserDTO;
import kr.co.thefesta.food.domain.api.ItemDTO;
import kr.co.thefesta.food.domain.api.ResultDTO;
import kr.co.thefesta.food.service.IFoodService;
import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/food")
@Log4j
public class FoodController {

	@Autowired
	IFoodService service;
	
	// 음식점 추천 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getFoodList(
			@RequestParam("contentid") String contentid,
			@RequestParam(name ="id", required = false) String id,
			@RequestParam(name = "pageNum", required = false) Integer pageNum,
	        @RequestParam(name = "amount", required = false) Integer amount) throws Exception {
		log.info("food list connect.....");
		log.info("contentid: " + contentid);
		log.info("Id: " + id);
		log.info("pageNum: " + pageNum);
		log.info("amount: " + amount);
		
		 // 페이징 정보 추출
		Criteria cri = new Criteria(pageNum != null ? pageNum : 1, amount != null ? amount : service.listCnt(contentid));
	    log.info("cri: " + (cri != null ? cri.toString() : "null"));
		
		Map<String, Object> responseMap = new HashMap<>();
		List<RecommendDTO> recDto;
		AreacodeDTO areaCodeDto;
		int total = 0;
		
		if (id != null) { //회원(로그인)
			
			UserDTO userDto = new UserDTO(contentid, id, cri);
			recDto = service.listAllUser(userDto);
			areaCodeDto = service.selectArea(userDto.getContentid());
			
		} else { //비회원(비로그인)
			NonUserDTO nonUserDto = new NonUserDTO(contentid, cri);
			recDto = service.listAll(nonUserDto);
			areaCodeDto = service.selectArea(nonUserDto.getContentid());
			
		}
		
		total = service.listCnt(contentid);
		log.info("total : " + total);
		
		responseMap.put("recommendDTOList", recDto);
		responseMap.put("areacodeDTO", areaCodeDto);
		responseMap.put("pageMaker", new PageDTO(cri, total));
		log.info("recDto: " + recDto);
		log.info("areaDto : " + areaCodeDto);
		log.info("responseMap: " + responseMap);
		
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	// 음식점 상세 페이지
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity<ItemDTO> detailFood(@RequestParam("contentid") String contentid) throws Exception {
		log.info("food detail connect.....");

		ItemDTO itemDto = service.read(contentid);
		log.info(itemDto);

		return new ResponseEntity<>(itemDto, HttpStatus.OK);

	}

	// 좋아요
	@RequestMapping(value = "/likefood", method = RequestMethod.POST)
	public ResponseEntity<Void> likeFood(@RequestBody LikeDTO likeDto) throws Exception {
		log.info("food like connect......");
		log.info("likeDto : " + likeDto);
		try {
			service.insertLike(likeDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 좋아요 취소
	@RequestMapping(value = "/unlikefood", method = RequestMethod.POST)
	public ResponseEntity<Void> unLikeFood(@RequestBody LikeDTO likeDto) throws Exception {
		log.info("food unlike connect......");
		log.info("likeDto : " + likeDto);
		try {
			service.deleteLike(likeDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// 회원이 좋아요한 음식점 리스트
	@RequestMapping(value = "/userlikelist", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getUserLikeList(@RequestParam("id") String id) throws Exception {
		log.info("userId : " + id);
		
		Map<String, Object> responseMap = new HashMap<>();
		
		List<LikeDTO> likeDto = service.userLikeList(id);
		responseMap.put("likeDTOList", likeDto);
		log.info("likeDTOList : " + likeDto);
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	// 음식점 Api 호출 및 DB 저장
	@RequestMapping(value = "/callapi", method = RequestMethod.GET)
	public void callApi() throws Exception {
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
			String decodeServiceKey = "2MithscUucMQWsH4BixwE4Y39fJl+Te/9AJQrbYYBgw+VRrDAn5XNSBD/j5qb/YCu5YA5o7aSlkegFxvF71uBQ==";
			String decodeServiceKey1 = "sxM6XuFTCETUKfUia3EOsqJj2UPNmebC5DENhavYsj7KsSdf+tC6t+i+PtSLoiy+1mfRsDqOQo0+FibnTAKUqQ==";
			String decodeServiceKey2 = "FUZ9ccIzOa33akBnPSF3ULPUdvCk/Cfj7x8/1hXBaStY+b8nVOmDepdDMLhQBp5qMvBSzdGCv/mOFMFAFTKMhA==";
			String decodeServiceKey3 = "Pl6K060b4TyB9IpUXHlnOQONaTgkdSlRW8yGTnUDedutQ5Y915/K84w7UW4BOae8X7S8FSmZlXrbtQeeaT5Dsw==";
			String decodeServiceKey4 = "syUAggWKlsXU0flFJw7DH8pKOHZWwazzpJY3fvseFQGLf3IEXKPyBE4/xUHvTZNzBtyaT3ApTkR1HvDEgS5I0A==";
			String encodeServiceKey = URLEncoder.encode(decodeServiceKey4, "UTF-8");

			// API 호출을 위한 파라미터 설정
			int pageNo = 1;
			int numOfRows = 100;

			// URI 및 파라미터 설정
			URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
					.queryParam("serviceKey", encodeServiceKey)
					.queryParam("pageNo", pageNo)
					.queryParam("numOfRows", numOfRows)
					.queryParam("MobileApp", "Thefesta")
					.queryParam("MobileOS", "ETC")
					.queryParam("contentTypeId", "39")
					.queryParam("arrange", "R")   // O=제목순, Q=수정일순, R=생성일순
					.queryParam("_type", "json")
					.build(true)
					.toUri();

//			log.info("uri address : " + uri);

			// HttpHeaders 설정
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "*/*;q=0.9"); // HTTP_ERROR 방지

			// API 호출
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
//			log.info("API Response : " + response.getBody());

			// 호출한 값 ResultDto 객체에 담기
			ResultDTO resultDto = gson.fromJson(response.getBody(), ResultDTO.class);
//			log.info("resultDto : " + resultDto.toString());

			// 음식점 총 개수 구하기
			int totalCount = resultDto.getResponse().getBody().getTotalCount();
			log.info("totalCount : " + totalCount);

			// pageNo 구하기
			int divNum = Math.floorDiv(totalCount, numOfRows);
			int modNum = Math.floorMod(totalCount, numOfRows);
			if (modNum == 0) {
				pageNo = divNum;
			} else {
				pageNo = divNum + 1;
			}
			log.info(pageNo);

			//page로 데이터 가져오기
			for (int i = 112; i <= pageNo; i++) {

				URI uri1 = UriComponentsBuilder.fromHttpUrl(baseUrl)
						.queryParam("serviceKey", encodeServiceKey)
						.queryParam("pageNo", i)
						.queryParam("numOfRows", numOfRows)
						.queryParam("MobileApp", "Thefesta")
						.queryParam("MobileOS", "ETC")
						.queryParam("contentTypeId", "39")
						.queryParam("arrange", "R")
						.queryParam("_type", "json")
						.build(true)
						.toUri();

				log.info("uri1 address : " + uri1);

				// API 호출
				ResponseEntity<String> response1 = restTemplate.exchange(uri1, HttpMethod.GET, null, String.class);
//				log.info("API Response1 : " + response1.getBody());

				// 호출한 값 ResultDto 객체에 담기
				ResultDTO resultDto1 = gson.fromJson(response1.getBody(), ResultDTO.class);
//				log.info("resultDto1 : " + resultDto1.toString());

				List<ItemDTO> itemDto = resultDto1.getResponse().getBody().getItems().getItem();

				//contentid로 데이터 가져오기
				for (ItemDTO item : itemDto) {
					String contentid = item.getContentid();
					item.setContentid(contentid);
					log.info("contentid: " + contentid);

					String baseUrl2 = "http://apis.data.go.kr/B551011/KorService1/detailCommon1";
					String baseUrl3 = "http://apis.data.go.kr/B551011/KorService1/detailIntro1";

					URI uri2 = UriComponentsBuilder.fromHttpUrl(baseUrl2)
							.queryParam("serviceKey", encodeServiceKey)
							.queryParam("MobileApp", "Thefesta")
							.queryParam("MobileOS", "ETC")
							.queryParam("contentId", contentid)
							.queryParam("defaultYN", "Y")
							.queryParam("firstImageYN", "Y")
							.queryParam("areacodeYN", "Y")
							.queryParam("catcodeYN", "Y")
							.queryParam("addrinfoYN", "Y")
							.queryParam("mapinfoYN", "Y")
							.queryParam("overviewYN", "Y")
							.queryParam("_type", "json")
							.build(true).toUri();

					URI uri3 = UriComponentsBuilder.fromHttpUrl(baseUrl3)
							.queryParam("serviceKey", encodeServiceKey)
							.queryParam("MobileApp", "Thefesta")
							.queryParam("MobileOS", "ETC")
							.queryParam("contentId", contentid)
							.queryParam("contentTypeId", "39")
							.queryParam("_type", "json")
							.build(true)
							.toUri();

					log.info("uri2 address : " + uri2);
					log.info("uri3 address : " + uri3);

					// uri2 API 호출
					ResponseEntity<String> response2 = restTemplate.exchange(uri2, HttpMethod.GET, null, String.class);
//					log.info("API Response2 : " + response2.getBody());

					// uri3 API 호출
					ResponseEntity<String> response3 = restTemplate.exchange(uri3, HttpMethod.GET, null, String.class);
//					log.info("API Response3 : " + response3.getBody());

					// uri2 데이터를 ResultDTO에 담기
					ResultDTO resultDto2 = gson.fromJson(response2.getBody().toString(), ResultDTO.class);
					log.info("resultDto2 : " + resultDto2.getResponse().getBody().getItems());

					// ResultDTO에서 값 가져오기
					String title = resultDto2.getResponse().getBody().getItems().getItem().get(0).getTitle();
					String addr1 = resultDto2.getResponse().getBody().getItems().getItem().get(0).getAddr1();
					String overview = resultDto2.getResponse().getBody().getItems().getItem().get(0).getOverview();
					String firstimage = resultDto2.getResponse().getBody().getItems().getItem().get(0).getFirstimage();
					String firstimage2 = resultDto2.getResponse().getBody().getItems().getItem().get(0)
							.getFirstimage2();
					String mapx = resultDto2.getResponse().getBody().getItems().getItem().get(0).getMapx();
					String mapy = resultDto2.getResponse().getBody().getItems().getItem().get(0).getMapy();
					String areacode = resultDto2.getResponse().getBody().getItems().getItem().get(0).getAreacode();
					String sigungucode = resultDto2.getResponse().getBody().getItems().getItem().get(0)
							.getSigungucode();
					String cat3 = resultDto2.getResponse().getBody().getItems().getItem().get(0).getCat3();

					// ItemDTO에 값 담기
					item.setTitle(title);
					item.setAddr1(addr1);
					item.setOverview(overview);
					item.setFirstimage(firstimage);
					item.setFirstimage2(firstimage2);
					item.setMapx(mapx);
					item.setMapy(mapy);
					item.setAreacode(areacode);
					item.setSigungucode(sigungucode);
					item.setCat3(cat3);

					if (response3.getBody().contains("\"items\": \"\"")) {
						item.setFirstmenu("(null)");
						item.setTreatmenu("(null)");
						item.setInfocenterfood("(null)");
						item.setParkingfood("(null)");
						item.setOpentimefood("(null)");
						item.setRestdatefood("(null)");
					} else {
						// uri3 데이터를 ResultDTO에 담기
						ResultDTO resultDto3 = gson.fromJson(response3.getBody(), ResultDTO.class);
						log.info("resultDto3 : " + resultDto3.getResponse().getBody().getItems());

						// ResultDTO에서 값 가져오기
						String firstmenu = resultDto3.getResponse().getBody().getItems().getItem().get(0)
								.getFirstmenu();
						String treatmenu = resultDto3.getResponse().getBody().getItems().getItem().get(0)
								.getTreatmenu();
						String infocenterfood = resultDto3.getResponse().getBody().getItems().getItem().get(0)
								.getInfocenterfood();
						String parkingfood = resultDto3.getResponse().getBody().getItems().getItem().get(0)
								.getParkingfood();
						String opentimefood = resultDto3.getResponse().getBody().getItems().getItem().get(0)
								.getOpentimefood();
						String restdatefood = resultDto3.getResponse().getBody().getItems().getItem().get(0)
								.getRestdatefood();

						// ItemDTO에 값 담기
						item.setFirstmenu(firstmenu);
						item.setTreatmenu(treatmenu);
						item.setInfocenterfood(infocenterfood);
						item.setParkingfood(parkingfood);
						item.setOpentimefood(opentimefood);
						item.setRestdatefood(restdatefood);
					}
				}
//				saveDataAsync(itemDto);
				
				//DB 저장
				for (ItemDTO item : itemDto) {
					log.info("itemDto : " + item.toString());
					service.create(item);
				}
				log.info("...............restaurant DB save complete!");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("Error encoding service key", e);
		} catch (Exception e) {
			log.error("Error making API call", e);
		}
	}

	@Async
	@Transactional
	public CompletableFuture<Void> saveDataAsync(List<ItemDTO> itemDto) {
		for (ItemDTO item : itemDto) {
			log.info("itemDto : " + item.toString());
			try {
				service.create(item);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("...............restaurant DB save complete!");
		return CompletableFuture.completedFuture(null);
	}

}