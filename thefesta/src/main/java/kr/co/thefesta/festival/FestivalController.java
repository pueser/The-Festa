package kr.co.thefesta.festival;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
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

@RestController
@RequestMapping("/Standard")
@CrossOrigin(origins = "*")
public class FestivalController {
	@Autowired
	private IFestivalService service;
	
	private String API_KEY = "2CQIk%2BsA5VR%2Bxo%2BB%2FYaxP2uKEoNjx1QUnlxo0zZ0GTXgbiBzgX%2FKmndkj27rvGsy6TZhWQkNl5ewBnR1qaCspg%3D%3D";
	
	@GetMapping("/festivalAPI")
	public void festivalInfoGET() throws Exception {
		try {
			System.out.println("festivalAPI------------------------------");
			
			String EVENT_START_DATE = "20220101";
			String EVENT_END_DATE = "20221231";
			
			int numOfRows = 10;
			int pageNo = 1;
			
			while (true) {
				// API 호출 URL 구성 (searchFestival1)
				StringBuilder sfUrlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/searchFestival1");
				sfUrlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + API_KEY);
				sfUrlBuilder.append("&" + URLEncoder.encode("eventStartDate", "UTF-8") + "=" + EVENT_START_DATE);
				sfUrlBuilder.append("&" + URLEncoder.encode("eventEndDate", "UTF-8") + "=" + EVENT_END_DATE);
				sfUrlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "=Y");
				sfUrlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
				sfUrlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=TheFesta");
				sfUrlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=C");
				sfUrlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=100");
				sfUrlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo);
				sfUrlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
				
				URL sfUrl = new URL(sfUrlBuilder.toString());
				HttpURLConnection sfStream = (HttpURLConnection) sfUrl.openConnection();
				BufferedReader sfReader = new BufferedReader(new InputStreamReader(sfStream.getInputStream()));
				
				StringBuilder sfResultBuilder = new StringBuilder();
                String str;
                while ((str = sfReader.readLine()) != null) {
                	System.out.println("str--------" + str);
                	sfResultBuilder.append(str);
                }
                
                sfReader.close();
                sfStream.disconnect();

                String sfResult = sfResultBuilder.toString();
                
                if (sfResult.contains("\"items\": \"\"")) {
					System.out.println("items = null----------------------");
					System.out.println("sfUrlBuilder--------" + sfUrlBuilder.toString());
					System.out.println("완료-----------");
					break;
				} else {
					Gson gson = new Gson();
					SearchFestivalDTO sfResponse = gson.fromJson(sfResult, SearchFestivalDTO.class);
					
					List<SearchFestivalItemDTO> sfItems = sfResponse.getResponse().getBody().getItems().getItem();
					
					FestivalDTO fDto = new FestivalDTO();
					FestivalImageDTO fiDto = new FestivalImageDTO();
					
					for (SearchFestivalItemDTO searchFestivalItemDTO : sfItems) {
						if (searchFestivalItemDTO.getCat2().equals("A0207")) {
							System.out.println("searchFestivalItemDTO-----");
							fDto.setContentid(searchFestivalItemDTO.getContentid());
							fDto.setTitle(searchFestivalItemDTO.getTitle());
							fDto.setEventstartdate(searchFestivalItemDTO.getEventstartdate());
							fDto.setEventenddate(searchFestivalItemDTO.getEventenddate());
							fDto.setAddr1(searchFestivalItemDTO.getAddr1());
							fDto.setFirstimage(searchFestivalItemDTO.getFirstimage());
							fDto.setFirstimage2(searchFestivalItemDTO.getFirstimage2());
							fDto.setAcode(Integer.parseInt(searchFestivalItemDTO.getAreacode()));
							fDto.setScode(Integer.parseInt(searchFestivalItemDTO.getSigungucode()));
							fDto.setMlevel(Integer.parseInt(searchFestivalItemDTO.getMlevel()));
							fDto.setMapx(Double.parseDouble(searchFestivalItemDTO.getMapx()));
							fDto.setMapy(Double.parseDouble(searchFestivalItemDTO.getMapy()));
							
							fiDto.setContentid(searchFestivalItemDTO.getContentid());
							
							// API 호출 URL 구성 (detailCommon1)
							StringBuilder dcUrlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailCommon1");
							dcUrlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + API_KEY);
							dcUrlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
							dcUrlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=TheFesta");
							dcUrlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=100");
							dcUrlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
							dcUrlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
							dcUrlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + searchFestivalItemDTO.getContentid());
							dcUrlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=Y");
							dcUrlBuilder.append("&" + URLEncoder.encode("firstImageYN", "UTF-8") + "=Y");
							dcUrlBuilder.append("&" + URLEncoder.encode("areacodeYN", "UTF-8") + "=Y");
							dcUrlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=Y");
							dcUrlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=Y");
							dcUrlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=Y");
							
							URL dcUrl = new URL(dcUrlBuilder.toString());
							HttpURLConnection dcStream = (HttpURLConnection) dcUrl.openConnection();
							BufferedReader dcReader = new BufferedReader(new InputStreamReader(dcStream.getInputStream()));
							
							StringBuilder dcResultBuilder = new StringBuilder();
							String str2;
							while ((str2 = dcReader.readLine()) != null) {
								dcResultBuilder.append(str2);
							}
							
							dcReader.close();
							dcStream.disconnect();
							
							String dcResult = dcResultBuilder.toString();
							
							if (dcResult.contains("\"items\": \"\"")) {
								fDto.setHomepage(null);
							} else {
								DetailCommonDTO dcResponse = gson.fromJson(dcResult, DetailCommonDTO.class);
								
								List<DetailCommonItemDTO> dcItems = dcResponse.getResponse().getBody().getItems().getItem();
								
								for (DetailCommonItemDTO detailCommonItemDTO : dcItems) {
									System.out.println("detailCommonItemDTO-------");
									fDto.setHomepage(detailCommonItemDTO.getHomepage());
								}
							}
							
							// API 호출 URL 구성 (detailIntro1)
							StringBuilder diUrlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailIntro1");
							diUrlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + API_KEY);
							diUrlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
							diUrlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=TheFesta");
							diUrlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=100");
							diUrlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
							diUrlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
							diUrlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + searchFestivalItemDTO.getContentid());
							diUrlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=15");
							
							URL diUrl = new URL(diUrlBuilder.toString());
							HttpURLConnection diStream = (HttpURLConnection) diUrl.openConnection();
							BufferedReader diReader = new BufferedReader(new InputStreamReader(diStream.getInputStream()));
							
							StringBuilder diResultBuilder = new StringBuilder();
							String str3;
							while ((str3 = diReader.readLine()) != null) {
								diResultBuilder.append(str3);
							}
							
							diReader.close();
							diStream.disconnect();
							
							String diResult = diResultBuilder.toString();
							
							if (diResult.contains("\"items\": \"\"")) {
								fDto.setAgelimit(null);
								fDto.setSponsor1(null);
								fDto.setSponsor1tel(null);
								fDto.setSponsor2(null);
								fDto.setSponsor2tel(null);
								fDto.setUsetimefestival(null);
								fDto.setPlaytime(null);
							} else {
								DetailIntroDTO diResponse = gson.fromJson(diResult, DetailIntroDTO.class);
								
								List<DetailIntroItemDTO> diItems = diResponse.getResponse().getBody().getItems().getItem();
								
								for (DetailIntroItemDTO detailIntroItemDTO : diItems) {
									System.out.println("detailIntroItemDTO------");
									fDto.setAgelimit(detailIntroItemDTO.getAgelimit());
									fDto.setSponsor1(detailIntroItemDTO.getSponsor1());
									fDto.setSponsor1tel(detailIntroItemDTO.getSponsor1tel());
									fDto.setSponsor2(detailIntroItemDTO.getSponsor2());
									fDto.setSponsor2tel(detailIntroItemDTO.getSponsor2tel());
									fDto.setUsetimefestival(detailIntroItemDTO.getUsetimefestival());
									fDto.setPlaytime(detailIntroItemDTO.getPlaytime());
								}
							}
							
							// API 호출 URL 구성 (detailInfo1)
							StringBuilder difUrlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailInfo1");
							difUrlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + API_KEY);
							difUrlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
							difUrlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=TheFesta");
							difUrlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=100");
							difUrlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
							difUrlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
							difUrlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + searchFestivalItemDTO.getContentid());
							difUrlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=15");
							
							URL difUrl = new URL(difUrlBuilder.toString());
							HttpURLConnection difStream = (HttpURLConnection) difUrl.openConnection();
							BufferedReader difReader = new BufferedReader(new InputStreamReader(difStream.getInputStream()));
							
							StringBuilder difResultBuilder = new StringBuilder();
							String str4;
							while ((str4 = difReader.readLine()) != null) {
								difResultBuilder.append(str4);
							}
							
							difReader.close();
							difStream.disconnect();
							
							String difResult = difResultBuilder.toString();
							
							if (difResult.contains("\"items\": \"\"")) {
								fDto.setEventintro(null);
								fDto.setEventtext(null);
							} else {
								DetailInfoDTO difResponse = gson.fromJson(difResult, DetailInfoDTO.class);
								
								List<DetailInfoItemDTO> difItems = difResponse.getResponse().getBody().getItems().getItem();
								
								for (DetailInfoItemDTO detailInfoItemDTO : difItems) {
									if (detailInfoItemDTO.getInfoname().equals("행사소개")) {
										System.out.println("detailInfoItemDTO.getInfoname------");
										fDto.setEventintro(detailInfoItemDTO.getInfotext());
									} 
									if (detailInfoItemDTO.getInfoname().equals("행사내용")) {
										System.out.println("detailInfoItemDTO.getInfoname------");
										fDto.setEventtext(detailInfoItemDTO.getInfotext());
									} else {
										fDto.setEventtext(null);
									}
								}
							}
							
							service.insertApi(fDto);
							
							// API 호출 URL 구성 (detailImage1)
							StringBuilder dimgUrlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailImage1");
							dimgUrlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + API_KEY);
							dimgUrlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
							dimgUrlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=TheFesta");
							dimgUrlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=100");
							dimgUrlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
							dimgUrlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
							dimgUrlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + searchFestivalItemDTO.getContentid());
							dimgUrlBuilder.append("&" + URLEncoder.encode("imageYN", "UTF-8") + "=Y");
							dimgUrlBuilder.append("&" + URLEncoder.encode("subImageYN", "UTF-8") + "=Y");
							
							URL dimgUrl = new URL(dimgUrlBuilder.toString());
							HttpURLConnection dimgStream = (HttpURLConnection) dimgUrl.openConnection();
							BufferedReader dimgReader = new BufferedReader(new InputStreamReader(dimgStream.getInputStream()));
							
							StringBuilder dimgResultBuilder = new StringBuilder();
							String str5;
							while ((str5 = dimgReader.readLine()) != null) {
								dimgResultBuilder.append(str5);
							}
							
							dimgReader.close();
							dimgStream.disconnect();
							
							String dimgResult = dimgResultBuilder.toString();
							
							if (dimgResult.contains("\"items\": \"\"")) {
								fiDto.setOriginimgurl(null);
								fiDto.setSmallimageurl(null);
							} else {
								DetailImageDTO dimgResponse = gson.fromJson(dimgResult, DetailImageDTO.class);
								
								List<DetailImageItemDTO> dimgItems = dimgResponse.getResponse().getBody().getItems().getItem();
								
								for (DetailImageItemDTO detailImageItemDTO : dimgItems) {
									System.out.println("detailImageItemDTO.get------");
									fiDto.setOriginimgurl(detailImageItemDTO.getOriginimgurl());
									fiDto.setSmallimageurl(detailImageItemDTO.getSmallimageurl());
									service.insertImg(fiDto);
								}
							}
							
						}
					}
					
					pageNo++;
					
					if (sfItems.isEmpty() || sfItems.size() < numOfRows) {
						System.out.println("완료-----------");
						break;
					}
				}
                
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/getApi")
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
	
	@GetMapping("/test")
	public List<String> Test() {
		return Arrays.asList("서버 9090", "리액트 3000");
	}
}
