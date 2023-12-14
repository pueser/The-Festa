package kr.co.thefesta.admin;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.thefesta.admin.service.IAdminService;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class AdminSchdulerSvc {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(AdminSchdulerSvc.class);



	@Autowired
	private IAdminService FestivalRepositoryDelete;

	

//	@Scheduled(fixedRate = 1000)
//	public void sampleTask() throws Exception {
//		log.info("SchdulerSvc is started");
//		
//		LocalDateTime time = LocalDateTime.now().minusYears(1);
//		log.info(time);
//
//	}

	
}
