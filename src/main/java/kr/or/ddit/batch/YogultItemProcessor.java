package kr.or.ddit.batch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.yogult.model.CycleVO;
import kr.or.ddit.yogult.model.DailyVO;

public class YogultItemProcessor implements ItemProcessor<CycleVO, List<DailyVO>>{
	private static final Logger logger = LoggerFactory.getLogger(YogultItemProcessor.class);
	
	@Value("#{jobParameters[ym]}")
	private String ym;
	
	@Override
	public List<DailyVO> process(CycleVO cycleVO) throws Exception {
		// ym : 201907
		// input : cid : 1, pid : 100, day : 2, cnt : 1
		// output : cid : 1, pid : 100, 20190701, cnt : 1
		// 			cid : 1, pid : 100, 20190708, cnt : 1
		// 			cid : 1, pid : 100, 20190715, cnt : 1
		// 			cid : 1, pid : 100, 20190722, cnt : 1
		// 			cid : 1, pid : 100, 20190729, cnt : 1
		
		SimpleDateFormat ym_fomat = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		
		//cal:20190701
		cal.setTime(ym_fomat.parse(ym)); 
		
		//cal:20190731
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date ed_dt = cal.getTime();
		
		//cal:20190701
		cal.setTime(ym_fomat.parse(ym));
		
		SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
		
		List<DailyVO> dailyVOList = new ArrayList<DailyVO>();
		
		// cal : 1-31 순차적으로 증가, ed_dt 클때까지
		while(ed_dt.getTime() >= cal.getTimeInMillis()) {
			// 요일이 같을 때만 dailyVO로 생성
			// ex : cycleVO.getDay() : 2(월) ==> 20190701, 20190708, 20190722, 20190729
			if(cal.get(Calendar.DAY_OF_WEEK) == cycleVO.getDay()) {
				DailyVO vo = new DailyVO();
				vo.setCid(cycleVO.getCid());
				vo.setPid(cycleVO.getPid());
				vo.setDt(ymd.format(cal.getTime()));
				vo.setCnt(cycleVO.getCnt());
				dailyVOList.add(vo);
			}
			
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		logger.debug("dailyVOList : {}", dailyVOList);
		
		return dailyVOList;
	}
}
