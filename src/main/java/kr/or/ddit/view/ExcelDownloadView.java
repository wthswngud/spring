package kr.or.ddit.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVO;

public class ExcelDownloadView implements View{

	@Override
	public String getContentType() {
		return "application/vnd.ms-excel";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + (String)model.get("filename") + ".xlsx"); 
		
		List<String> header = (List<String>) model.get("header");	//data 헤더정보(userId, name, alias ...)
		List<UserVO> userList = (List<UserVO>) model.get("data");		//data

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("data");
		
		Row row = null;
		int rowIdx = 0;
		int colIdx = 0;
		
		row = sheet.createRow(rowIdx++);

		//header 쓰기
		for(String head : header) 
			row.createCell(colIdx++).setCellValue(head);
		
		for(UserVO userVO : userList) {
			colIdx = 0;
			row = sheet.createRow(rowIdx++);
			row.createCell(colIdx++).setCellValue(userVO.getUserId());
			row.createCell(colIdx++).setCellValue(userVO.getName());
			row.createCell(colIdx++).setCellValue(userVO.getAlias());
			row.createCell(colIdx++).setCellValue(userVO.getAddr1());
			row.createCell(colIdx++).setCellValue(userVO.getAddr2());
			row.createCell(colIdx++).setCellValue(userVO.getZipcd());
			
			Date date = userVO.getBirth();
			String sdf = null;
			if(date != null) {
				sdf = new SimpleDateFormat("yyyy-MM-dd").format(date);
			}
			
			row.createCell(colIdx++).setCellValue(sdf);
		}
		
		//data쓰기
		ServletOutputStream sos = response.getOutputStream();
		workbook.write(sos);
		workbook.close();
		
		sos.flush();
		sos.close();
	}
}
