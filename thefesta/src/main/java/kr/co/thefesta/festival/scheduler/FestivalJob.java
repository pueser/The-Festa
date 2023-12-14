package kr.co.thefesta.festival.scheduler;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.api.ItemDTO;
import kr.co.thefesta.festival.domain.api.ResultDTO;
import kr.co.thefesta.festival.service.IFestivalService;
import lombok.extern.log4j.Log4j;

@Log4j
public class FestivalJob extends QuartzJobBean{
	
	private IFestivalService festivalService;
	
	@Autowired
    public void setFestivalService(IFestivalService festivalService) {
        this.festivalService = festivalService;
    }
	
	// 현재 날짜(년월일)
	private LocalDate today = LocalDate.now();
	// 현재 날짜 + 1년
	private String oneYearAfterDate = today.plusYears(1).format(DateTimeFormatter.ofPattern("YYYYMMdd"));
	// 현재 날짜 - 3년
	private String threeYearsBeforeDate = today.minusYears(1).format(DateTimeFormatter.ofPattern("YYYYMMdd"));
	
	private String EVENT_START_DATE = threeYearsBeforeDate;
	private String EVENT_END_DATE = oneYearAfterDate;
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			log.info("스케줄러 정상 작동------------------");
			callApi();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void callApi() throws Exception {
		log.info("api call success.........");

		// RestTemplate 인스턴스 생성
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		// Gson 인스턴스 생성
		Gson gson = new Gson();

		try {
			// baseURL
			String baseUrl = "http://apis.data.go.kr/B551011/KorService1/searchFestival1";

			// service Key
			String decodeServiceKey = "ry91Z60eoTHfLc9NI16ATVi+0k0SmnSdKalV1FXzqhL9Icp5Hv1b8uqkCyplKkQBi63yeRK0c43fRGAipcOFWg==";
			String encodeServiceKey = URLEncoder.encode(decodeServiceKey, "UTF-8");

			// API 호출을 위한 파라미터 설정
				int numOfRows = 100;
				int pageNo = 1;
				
				while (true) {
					try {
						URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
								.queryParam("serviceKey", encodeServiceKey)
								.queryParam("MobileOS", "ETC")
								.queryParam("MobileApp", "Thefesta")
								.queryParam("numOfRows", numOfRows)
								.queryParam("pageNo", pageNo)
								.queryParam("listYN", "Y")
								.queryParam("arrange", "C")
								.queryParam("eventStartDate", EVENT_START_DATE)
								.queryParam("eventEndDate", EVENT_END_DATE)
								.queryParam("_type", "json")
								.build(true)
								.toUri();

						log.info("uri1 address : " + uri);
						
						// HttpHeaders 설정
						HttpHeaders headers = new HttpHeaders();
						headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
						headers.set("Accept", "*/*;q=0.9"); // HTTP_ERROR 방지
						
						// API 호출
						ResponseEntity<String> response1 = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

						// 호출한 값 ResultDto 객체에 담기
						ResultDTO resultDto1 = gson.fromJson(response1.getBody(), ResultDTO.class);

						List<ItemDTO> itemDto = resultDto1.getResponse().getBody().getItems().getItem();
						FestivalDTO fDto = new FestivalDTO();

						//contentid로 데이터 가져오기
						for (ItemDTO item : itemDto) {
							if (item.getCat2().equals("A0207")) {
								String contentid = item.getContentid();
								
								fDto.setContentid(contentid);
								fDto.setEventstartdate(item.getEventstartdate());
								fDto.setEventenddate(item.getEventenddate());
								fDto.setFirstimage(item.getFirstimage());
								fDto.setFirstimage2(item.getFirstimage2());
								fDto.setMapx(item.getMapx() != null ? Double.parseDouble(item.getMapx()) : 0.0);
								fDto.setMapy(item.getMapy() != null ? Double.parseDouble(item.getMapy()) : 0.0);
								fDto.setTitle(item.getTitle());
								fDto.setAddr1(item.getAddr1());
								fDto.setAcode(item.getAreacode() != null ? Integer.parseInt(item.getAreacode()) : 0);
								fDto.setScode(item.getSigungucode() != null ? Integer.parseInt(item.getSigungucode()) : 0);
								
								log.info("contentid: " + contentid);
				
								String detailCommon = "http://apis.data.go.kr/B551011/KorService1/detailCommon1";
								String detailIntro = "http://apis.data.go.kr/B551011/KorService1/detailIntro1";
								String detailInfo = "http://apis.data.go.kr/B551011/KorService1/detailInfo1";
								String detailImage = "http://apis.data.go.kr/B551011/KorService1/detailImage1";
				
								URI detailCommonUri = UriComponentsBuilder.fromHttpUrl(detailCommon)
										.queryParam("serviceKey", encodeServiceKey)
										.queryParam("MobileOS", "ETC")
										.queryParam("MobileApp", "Thefesta")
										.queryParam("numOfRows", numOfRows)
										.queryParam("pageNo", "1")
										.queryParam("_type", "json")
										.queryParam("contentId", contentid)
										.queryParam("defaultYN", "Y")
										.queryParam("firstImageYN", "Y")
										.queryParam("areacodeYN", "Y")
										.queryParam("addrinfoYN", "Y")
										.queryParam("mapinfoYN", "Y")
										.queryParam("overviewYN", "Y")
										.build(true).toUri();
				
								URI detailIntroUri = UriComponentsBuilder.fromHttpUrl(detailIntro)
										.queryParam("serviceKey", encodeServiceKey)
										.queryParam("MobileOS", "ETC")
										.queryParam("MobileApp", "Thefesta")
										.queryParam("numOfRows", numOfRows)
										.queryParam("pageNo", "1")
										.queryParam("_type", "json")
										.queryParam("contentId", contentid)
										.queryParam("contentTypeId", "15")
										.build(true)
										.toUri();
								
								URI detailInfoUri = UriComponentsBuilder.fromHttpUrl(detailInfo)
										.queryParam("serviceKey", encodeServiceKey)
										.queryParam("MobileOS", "ETC")
										.queryParam("MobileApp", "Thefesta")
										.queryParam("numOfRows", numOfRows)
										.queryParam("pageNo", "1")
										.queryParam("_type", "json")
										.queryParam("contentId", contentid)
										.queryParam("contentTypeId", "15")
										.build(true)
										.toUri();
								
								URI detailImageUri = UriComponentsBuilder.fromHttpUrl(detailImage)
										.queryParam("serviceKey", encodeServiceKey)
										.queryParam("MobileOS", "ETC")
										.queryParam("MobileApp", "Thefesta")
										.queryParam("numOfRows", numOfRows)
										.queryParam("pageNo", "1")
										.queryParam("_type", "json")
										.queryParam("contentId", contentid)
										.queryParam("imageYN", "Y")
										.queryParam("subImageYN", "Y")
										.build(true)
										.toUri();
				
								log.info("detailCommonUri address : " + detailCommonUri);
								log.info("detailIntroUri address : " + detailIntroUri);
								log.info("detailInfoUri address : " + detailInfoUri);
								log.info("detailImageUri address : " + detailImageUri);
				
								// detailCommonUri API 호출
								ResponseEntity<String> detailCommonResponse = restTemplate.exchange(detailCommonUri, HttpMethod.GET, null, String.class);
				
								// detailIntroUri API 호출
								ResponseEntity<String> detailIntroUriResponse = restTemplate.exchange(detailIntroUri, HttpMethod.GET, null, String.class);
								
								// detailInfoUri API 호출
								ResponseEntity<String> detailInfoUriResponse = restTemplate.exchange(detailInfoUri, HttpMethod.GET, null, String.class);
								
								// detailImageUri API 호출
								ResponseEntity<String> detailImageUriResponse = restTemplate.exchange(detailImageUri, HttpMethod.GET, null, String.class);
								
								// detailCommonResponse
								if (detailCommonResponse.getBody().contains("\"items\": \"\"")) {
									item.setHomepage("(null)");
								} else {

									// detailCommonUri 데이터를 ResultDTO에 담기
									ResultDTO detailCommonResponseResultDto = gson.fromJson(detailCommonResponse.getBody().toString(), ResultDTO.class);
									log.info("detailCommonResponseResultDto : " + detailCommonResponseResultDto.getResponse().getBody().getItems());
									
									// ResultDTO에서 값 가져오기
									String homepage = detailCommonResponseResultDto.getResponse().getBody().getItems().getItem().get(0).getHomepage();
									
									// ItemDTO에 값 담기
									fDto.setHomepage(homepage);
								}
								
								// detailIntroUriResponse
								if (detailIntroUriResponse.getBody().contains("\"items\": \"\"")) {
									fDto.setAgelimit("(null)");
									fDto.setSponsor1("(null)");
									fDto.setSponsor1tel("(null)");
									fDto.setSponsor2("(null)");
									fDto.setSponsor2tel("(null)");
									fDto.setUsetimefestival("(null)");
									fDto.setPlaytime("(null)");
								} else {
									// uri3 데이터를 ResultDTO에 담기
									ResultDTO detailIntroResultDto = gson.fromJson(detailIntroUriResponse.getBody(), ResultDTO.class);
									log.info("detailIntroResultDto : " + detailIntroResultDto.getResponse().getBody().getItems());
				
									// ResultDTO에서 값 가져오기
									String agelimit = detailIntroResultDto.getResponse().getBody().getItems().getItem().get(0)
											.getAgelimit();
									String sponsor1 = detailIntroResultDto.getResponse().getBody().getItems().getItem().get(0)
											.getSponsor1();
									String sponsor1tel = detailIntroResultDto.getResponse().getBody().getItems().getItem().get(0)
											.getSponsor1tel();
									String sponsor2 = detailIntroResultDto.getResponse().getBody().getItems().getItem().get(0)
											.getSponsor2();
									String sponsor2tel = detailIntroResultDto.getResponse().getBody().getItems().getItem().get(0)
											.getSponsor2tel();
									String usetimefestival = detailIntroResultDto.getResponse().getBody().getItems().getItem().get(0)
											.getUsetimefestival();
									String playtime = detailIntroResultDto.getResponse().getBody().getItems().getItem().get(0)
											.getPlaytime();
				
									// ItemDTO에 값 담기
									fDto.setAgelimit(agelimit);
									fDto.setSponsor1(sponsor1);
									fDto.setSponsor1tel(sponsor1tel);
									fDto.setSponsor2(sponsor2);
									fDto.setSponsor2tel(sponsor2tel);
									fDto.setUsetimefestival(usetimefestival);
									fDto.setPlaytime(playtime);
								}
								
								// detailInfoUriResponse
								if (detailInfoUriResponse.getBody().contains("\"items\": \"\"")) {
									fDto.setEventintro("(null)");
									fDto.setEventtext("(null)");
								} else {
									// ResultDTO에서 값 가져오기
									ResultDTO detailInfoResultDto = gson.fromJson(detailInfoUriResponse.getBody(), ResultDTO.class);
									log.info("detailInfoResultDto : " + detailInfoResultDto.getResponse().getBody().getItems());
							       
									List<ItemDTO> difItems = detailInfoResultDto.getResponse().getBody().getItems().getItem();

							        for (ItemDTO itemDTO : difItems) {
							        	if (itemDTO.getInfoname().equals("행사소개")) {
							        		fDto.setEventintro(itemDTO.getInfotext());
							        	} else if (itemDTO.getInfoname().equals("행사내용")) {
							        		fDto.setEventtext(itemDTO.getInfotext());
							        	} else {
							        		fDto.setEventtext(null);
							        	}
							        }
									
								}
								
								boolean isUpdate = festivalService.updateFestival(fDto);
								log.info("isUpdate----" + isUpdate);
			                    
			                    if (isUpdate) {
			                    	log.info("isUpdate true----" + isUpdate);
			                    	
			                    	// detailImageUriResponse
			    					if (detailImageUriResponse.getBody().contains("\"items\": \"\"")) {
			    						log.info("이미지 없음----------");
			    					} else {

			    						// uri3 데이터를 ResultDTO에 담기
			    						FestivalImageDTO fiDto = new FestivalImageDTO();
			    						ResultDTO detailImageResultDto = gson.fromJson(detailImageUriResponse.getBody(), ResultDTO.class);
			    						log.info("detailImageResultDto : " + detailImageResultDto.getResponse().getBody().getItems());
			    						
			    						List<ItemDTO> imgItems = detailImageResultDto.getResponse().getBody().getItems().getItem();
			    						
			    						for (ItemDTO itemDTO : imgItems) {
			    							fiDto.setContentid(contentid);
			    				        	fiDto.setSerialnum(itemDTO.getSerialnum());
			    				            fiDto.setOriginimgurl(itemDTO.getOriginimgurl());
			    				            fiDto.setSmallimageurl(itemDTO.getSmallimageurl());
			    				            
			    				            int result = festivalService.searchImg(fiDto.getSerialnum());
			    				            if (result == 0) {
			    				            	log.info("insertImg---------");
			    				            	festivalService.insertImg(fiDto);
			    							} else {
			    								log.info("업데이트된 이미지 없음------------");
			    							}
			    				        }
			    					}
			                    	
			                    	log.info("단건 업데이트 완료--------------------");
								} else {
									log.info("isUpdate false----" + isUpdate);
									festivalService.insertApi(fDto);
									
			                    	// detailImageUriResponse
			    					if (detailImageUriResponse.getBody().contains("\"items\": \"\"")) {
			    						log.info("이미지 없음----------");
			    					} else {
			    						// uri3 데이터를 ResultDTO에 담기
			    						FestivalImageDTO fiDto = new FestivalImageDTO();
			    						ResultDTO detailImageResultDto = gson.fromJson(detailImageUriResponse.getBody(), ResultDTO.class);
			    						log.info("detailImageResultDto : " + detailImageResultDto.getResponse().getBody().getItems());
			    						
			    						List<ItemDTO> imgItems = detailImageResultDto.getResponse().getBody().getItems().getItem();
			    						
			    						for (ItemDTO itemDTO : imgItems) {
			    							fiDto.setContentid(contentid);
			    				        	fiDto.setSerialnum(itemDTO.getSerialnum());
			    				            fiDto.setOriginimgurl(itemDTO.getOriginimgurl());
			    				            fiDto.setSmallimageurl(itemDTO.getSmallimageurl());
			    				            
			    				            int result = festivalService.searchImg(fiDto.getSerialnum());
			    				            log.info("result searchImg----" + result);
			    				            if (result == 0) {
			    				            	log.info("insertImg---------");
			    				            	festivalService.insertImg(fiDto);
			    							} else {
			    								log.info("업데이트된 이미지 없음------------");
			    							}
			    				        }
			    					}
									log.info("단건 등록 완료--------------------");	
								}
							}
					}
					
					pageNo++;
					
					if (itemDto.isEmpty() || itemDto.size() < numOfRows) {
						log.info("itemDto :" + itemDto);
						log.info("완료-----------");
						break;
					}
				} catch (JsonSyntaxException e) {
					log.info("items = null----------------------");
					log.info("완료-----------");
					e.printStackTrace();
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			log.error("Error encoding service key", e);
		} catch (Exception e) {
			log.error("Error making API call", e);
		}
	}
}
