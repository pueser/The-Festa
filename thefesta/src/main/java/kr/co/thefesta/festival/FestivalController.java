package kr.co.thefesta.festival;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.detailCommon.DetailCommonDTO;
import kr.co.thefesta.festival.domain.detailCommon.DetailCommonItemDTO;
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
	
	@GetMapping("/festivalAPI")
	public void festivalInfoGET() throws Exception {
		try {
			System.out.println("festivalAPI------------------------------");
			String API_KEY = "2CQIk%2BsA5VR%2Bxo%2BB%2FYaxP2uKEoNjx1QUnlxo0zZ0GTXgbiBzgX%2FKmndkj27rvGsy6TZhWQkNl5ewBnR1qaCspg%3D%3D";
			String EVENT_START_DATE = "20220101";
			String EVENT_END_DATE = "20221231";
			
			int numOfRows = 100;
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
                	sfResultBuilder.append(str);
                }
                
                sfReader.close();
                sfStream.disconnect();

                String sfResult = sfResultBuilder.toString();

                Gson gson = new Gson();
                SearchFestivalDTO sfResponse = gson.fromJson(sfResult, SearchFestivalDTO.class);

                List<SearchFestivalItemDTO> sfItems = sfResponse.getResponse().getBody().getItems().getItem();
                
                FestivalDTO fDto = new FestivalDTO();
                
                for (SearchFestivalItemDTO searchFestivalItemDTO : sfItems) {
                    if (searchFestivalItemDTO.getCat2().equals("A0207")) {
                    	System.out.println("searchFestivalItemDTO-----");
                    	fDto.setContentid(searchFestivalItemDTO.getContentid());
                    	fDto.setTitle(searchFestivalItemDTO.getTitle());
                    	fDto.setEventstartdate(searchFestivalItemDTO.getEventstartdate());
                    	fDto.setEventenddate(searchFestivalItemDTO.getEventenddate());
                    	fDto.setAddr1(searchFestivalItemDTO.getAddr1());
                    	fDto.setAcode(Integer.parseInt(searchFestivalItemDTO.getAreacode()));
                    	fDto.setScode(Integer.parseInt(searchFestivalItemDTO.getSigungucode()));
                    	fDto.setMlevel(Integer.parseInt(searchFestivalItemDTO.getMlevel()));
                    	fDto.setMapx(Double.parseDouble(searchFestivalItemDTO.getMapx()));
                    	fDto.setMapy(Double.parseDouble(searchFestivalItemDTO.getMapy()));
                    	
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
                    	
                        DetailCommonDTO dcResponse = gson.fromJson(dcResult, DetailCommonDTO.class);
                    	
                        List<DetailCommonItemDTO> dcItems = dcResponse.getResponse().getBody().getItems().getItem();
                        
                        for (DetailCommonItemDTO detailCommonItemDTO : dcItems) {
                        	System.out.println("detailCommonItemDTO-------");
							fDto.setHomepage(detailCommonItemDTO.getHomepage());
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
                        
                        DetailInfoDTO difResponse = gson.fromJson(difResult, DetailInfoDTO.class);
                    	
                        List<DetailInfoItemDTO> difItems = difResponse.getResponse().getBody().getItems().getItem();
                        
                        for (DetailInfoItemDTO detailInfoItemDTO : difItems) {
                        	if (detailInfoItemDTO.getInfoname().equals("행사소개")) {
                        		System.out.println("detailInfoItemDTO.getInfoname------");
                        		fDto.setEventintro(detailInfoItemDTO.getInfotext());
							} else if (detailInfoItemDTO.getInfoname().equals("행사내용")) {
								System.out.println("detailInfoItemDTO.getInfoname------");
                        		fDto.setEventtext(detailInfoItemDTO.getInfotext());
							}
						}
                        
//                    	System.out.println(fDto);
                    	service.insert(fDto);
                    }
                }
                
                pageNo++;
                
                if (sfItems.isEmpty() || sfItems.size() < numOfRows) {
                	System.out.println("완료-----------");
                	break;
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
