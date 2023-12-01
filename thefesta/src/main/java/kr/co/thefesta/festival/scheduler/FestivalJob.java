package kr.co.thefesta.festival.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
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
public class FestivalJob extends QuartzJobBean{
	
	private IFestivalService festivalService;
	
	@Autowired
    public void setFestivalService(IFestivalService festivalService) {
        this.festivalService = festivalService;
    }
	
	private String API_KEY = "2CQIk%2BsA5VR%2Bxo%2BB%2FYaxP2uKEoNjx1QUnlxo0zZ0GTXgbiBzgX%2FKmndkj27rvGsy6TZhWQkNl5ewBnR1qaCspg%3D%3D";
	
	// 현재 날짜(년월일)
	private LocalDate today = LocalDate.now();
	// 현재 날짜 + 1년
	private String oneYearAfterDate = today.plusYears(1).format(DateTimeFormatter.ofPattern("YYYYMMdd"));
	// 현재 날짜 - 3년
	private String threeYearsBeforeDate = today.minusYears(3).format(DateTimeFormatter.ofPattern("YYYYMMdd"));
	
	private String EVENT_START_DATE = threeYearsBeforeDate;
	private String EVENT_END_DATE = oneYearAfterDate;
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("스케줄러 정상 작동------------------");
		festivalInfoGET();
	}
	
	private void festivalInfoGET() {
	    try {
	    	int numOfRows = 100;
	        int pageNo = 1;

	        while (true) {
	            String sfUrlBuilder = buildUrl("searchFestival1", pageNo);

	            String sfResult = callAPI(sfUrlBuilder);
	            
	            try {
	            	Gson gson = new Gson();
	                SearchFestivalDTO sfResponse = gson.fromJson(sfResult, SearchFestivalDTO.class);

	                List<SearchFestivalItemDTO> sfItems = sfResponse.getResponse().getBody().getItems().getItem();
	                
	                FestivalDTO fDto = new FestivalDTO();
	                
	                for (SearchFestivalItemDTO sfItemDTO : sfItems) {
	                    if (sfItemDTO.getCat2().equals("A0207")) {
	                    	fDto = setSearchFestival(sfItemDTO);
	                        
	                        FestivalImageDTO fiDto = new FestivalImageDTO();
	                        
	                        String contentid = sfItemDTO.getContentid(); 
	                        
	                        fiDto.setContentid(contentid);
	                        
	                        String dcUrlBuilder = buildUrl("detailCommon1", contentid);
	                        String dcResult = callAPI(dcUrlBuilder);
	                        setDetailCommon(fDto, gson, dcResult);

	                        String diUrlBuilder = buildUrl("detailIntro1", contentid);
	                        String diResult = callAPI(diUrlBuilder);
	                        setDetailIntro(fDto, gson, diResult);

	                        String difUrlBuilder = buildUrl("detailInfo1", contentid);
	                        String difResult = callAPI(difUrlBuilder);
	                        setDetailInfo(fDto, gson, difResult);
	                        
	                        boolean result = festivalService.updateFestival(fDto);
	                        
	                        if (result) {
	                        	String dimgUrlBuilder = buildUrl("detailImage1", contentid);
	                        	String dimgResult = callAPI(dimgUrlBuilder);
	                        	setDetailImage(fiDto, gson, dimgResult);
	                        	
	                        	log.info("단건 업데이트 완료--------------------");
							} else {
								festivalService.insertApi(fDto);
								
								String dimgUrlBuilder = buildUrl("detailImage1", contentid);
								String dimgResult = callAPI(dimgUrlBuilder);
								setDetailImage(fiDto, gson, dimgResult);
								
								log.info("단건 등록 완료--------------------");
							}
	                    }
	                }

	                pageNo++;

	                if (sfItems.isEmpty() || sfItems.size() < numOfRows) {
	                	log.info("완료-----------");
	                    break;
	                }
				} catch (JsonSyntaxException e) {
					log.info("items = null----------------------");
					log.info("sfUrlBuilder--------" + sfUrlBuilder);
					log.info("완료-----------");
					e.printStackTrace();
					break;
				}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private String buildUrl(String apiName, Object... params) throws UnsupportedEncodingException {
	    StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/");
	    urlBuilder.append(apiName);
	    urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + API_KEY);
	    urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
	    urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=100");
	    urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=TheFesta");

	    switch (apiName) {
	        case "searchFestival1":
	            urlBuilder.append("&" + URLEncoder.encode("eventStartDate", "UTF-8") + "=" + EVENT_START_DATE);
	            urlBuilder.append("&" + URLEncoder.encode("eventEndDate", "UTF-8") + "=" + EVENT_END_DATE);
	            urlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "=Y");
	            urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=C");
	            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + params[0]);
	            break;
	        case "detailCommon1":
	        	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
	        	urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + params[0]);
	        	urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=Y");
	        	urlBuilder.append("&" + URLEncoder.encode("firstImageYN", "UTF-8") + "=Y");
	        	urlBuilder.append("&" + URLEncoder.encode("areacodeYN", "UTF-8") + "=Y");
	        	urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=Y");
	        	urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=Y");
	        	urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=Y");
	        	break;
	        case "detailIntro1":
	        case "detailInfo1":
	        	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
	        	urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + params[0]);
	        	urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=15");
	        	break;
	        case "detailImage1":
	            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
	            urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + params[0]);
	            urlBuilder.append("&" + URLEncoder.encode("imageYN", "UTF-8") + "=Y");
	            urlBuilder.append("&" + URLEncoder.encode("subImageYN", "UTF-8") + "=Y");
	            break;
	    }

	    return urlBuilder.toString();
	}

	private String callAPI(String url) throws IOException {
	    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
	        StringBuilder resultBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            resultBuilder.append(line);
	        }
	        return resultBuilder.toString();
	    } finally {
	        connection.disconnect();
	    }
	}
	
	private FestivalDTO setSearchFestival(SearchFestivalItemDTO itemDTO) {
		FestivalDTO fDto = new FestivalDTO();
	    fDto.setContentid(itemDTO.getContentid());
	    fDto.setTitle(itemDTO.getTitle());
	    fDto.setEventstartdate(itemDTO.getEventstartdate());
	    fDto.setEventenddate(itemDTO.getEventenddate());
	    fDto.setAddr1(itemDTO.getAddr1());
	    fDto.setFirstimage(itemDTO.getFirstimage());
	    fDto.setFirstimage2(itemDTO.getFirstimage2());
	    fDto.setAcode(Integer.parseInt(itemDTO.getAreacode()));
	    fDto.setScode(Integer.parseInt(itemDTO.getSigungucode()));
	    fDto.setMapx(Double.parseDouble(itemDTO.getMapx()));
	    fDto.setMapy(Double.parseDouble(itemDTO.getMapy()));
	    return fDto;
	}

	private void setDetailCommon(FestivalDTO fDto, Gson gson, String dcResult) {
	    try {
	        DetailCommonDTO dcResponse = gson.fromJson(dcResult, DetailCommonDTO.class);
	        List<DetailCommonItemDTO> dcItems = dcResponse.getResponse().getBody().getItems().getItem();
	        
	        for (DetailCommonItemDTO detailCommonItemDTO : dcItems) {
	            fDto.setHomepage(detailCommonItemDTO.getHomepage());
	        }
	    } catch (JsonSyntaxException e) {
	        // JSON 파싱 오류가 발생하면 빈 객체로 유지
	        fDto.setHomepage(null);
	        e.printStackTrace();
	    }
	}

	private void setDetailIntro(FestivalDTO fDto, Gson gson, String diResult) {
	    try {
	        DetailIntroDTO diResponse = gson.fromJson(diResult, DetailIntroDTO.class);
	        List<DetailIntroItemDTO> diItems = diResponse.getResponse().getBody().getItems().getItem();

	        for (DetailIntroItemDTO detailIntroItemDTO : diItems) {
	            fDto.setAgelimit(detailIntroItemDTO.getAgelimit());
	            fDto.setSponsor1(detailIntroItemDTO.getSponsor1());
	            fDto.setSponsor1tel(detailIntroItemDTO.getSponsor1tel());
	            fDto.setSponsor2(detailIntroItemDTO.getSponsor2());
	            fDto.setSponsor2tel(detailIntroItemDTO.getSponsor2tel());
	            fDto.setUsetimefestival(detailIntroItemDTO.getUsetimefestival());
	            fDto.setPlaytime(detailIntroItemDTO.getPlaytime());
	        }
	    } catch (JsonSyntaxException e) {
	        // JSON 파싱 오류가 발생하면 빈 객체로 유지
	        fDto.setAgelimit(null);
	        fDto.setSponsor1(null);
	        fDto.setSponsor1tel(null);
	        fDto.setSponsor2(null);
	        fDto.setSponsor2tel(null);
	        fDto.setUsetimefestival(null);
	        fDto.setPlaytime(null);
	        e.printStackTrace();
	    }
	}

	private void setDetailInfo(FestivalDTO fDto, Gson gson, String difResult) {
	    try {
	        DetailInfoDTO difResponse = gson.fromJson(difResult, DetailInfoDTO.class);
	        List<DetailInfoItemDTO> difItems = difResponse.getResponse().getBody().getItems().getItem();

	        for (DetailInfoItemDTO detailInfoItemDTO : difItems) {
	            if (detailInfoItemDTO.getInfoname().equals("행사소개")) {
	                fDto.setEventintro(detailInfoItemDTO.getInfotext());
	            } else if (detailInfoItemDTO.getInfoname().equals("행사내용")) {
	                fDto.setEventtext(detailInfoItemDTO.getInfotext());
	            } else {
	                fDto.setEventtext(null);
	            }
	        }
	    } catch (JsonSyntaxException e) {
	        // JSON 파싱 오류가 발생하면 빈 객체로 유지
	        fDto.setEventintro(null);
	        fDto.setEventtext(null);
	        e.printStackTrace();
	    }
	}

	private void setDetailImage(FestivalImageDTO fiDto, Gson gson, String dimgResult) throws Exception {
	    try {
	        DetailImageDTO dimgResponse = gson.fromJson(dimgResult, DetailImageDTO.class);
	        List<DetailImageItemDTO> dimgItems = dimgResponse.getResponse().getBody().getItems().getItem();
	        
	        for (DetailImageItemDTO detailImageItemDTO : dimgItems) {
	        	fiDto.setSerialnum(detailImageItemDTO.getSerialnum());
	            fiDto.setOriginimgurl(detailImageItemDTO.getOriginimgurl());
	            fiDto.setSmallimageurl(detailImageItemDTO.getSmallimageurl());
	            
	            int result = festivalService.searchImg(fiDto.getSerialnum());
	            if (result == 0) {
	            	log.info("insertImg---------");
	            	festivalService.insertImg(fiDto);
				} else {
					log.info("업데이트된 이미지 없음------------");
				}
	        }
	    } catch (JsonSyntaxException e) {
	        // JSON 파싱 오류가 발생하면 빈 객체로 유지
	    	log.info("이미지 없음-------");
	        e.printStackTrace();
	    }
	}



	
}
