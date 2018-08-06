package com.ruixun.sincfin.mobile.api.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Excel 相关操作类(小数据量写入<=65536)
 */
public class Excel2003Utils {

	private static final int DEFAULT_COLUMN_SIZE = 20;

	public static void writeExcel(HttpServletResponse response, String fileName, int totalRow,
			ReportListExcel reportListExcel) throws IOException {

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xls");

		Workbook workBook = new HSSFWorkbook();
		try {

			Sheet sheet = workBook.createSheet();
			// 设置表格默认列宽度
			sheet.setDefaultColumnWidth(DEFAULT_COLUMN_SIZE);
			int lastRowIndex = 0;
			// 表头
			List<String> columnNames = reportListExcel.getColumnNames();
			Row headRow = sheet.createRow(lastRowIndex++);
			for (int i = 0; i < columnNames.size(); i++) {
				Cell cell = headRow.createCell(i);
				RichTextString text = new HSSFRichTextString(columnNames.get(i));
				cell.setCellValue(text);
			}
			CellStyle bigDecimalMoneyCellStyle = createBigDecimalMoneyCellStyle(workBook);
			while (lastRowIndex <= totalRow) {
				Row row = sheet.createRow(lastRowIndex);
				List<Object> rowDataList = reportListExcel.getRowData(lastRowIndex - 1);
				lastRowIndex++;
				int columnSize = rowDataList.size();
				for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
					Cell contentCell = row.createCell(columnIndex);
					Object dataObject = rowDataList.get(columnIndex);
					if (dataObject != null) {
						if (dataObject instanceof Long) {
							contentCell.setCellValue(Long.parseLong(dataObject.toString()));
						} else if (dataObject instanceof BigDecimal) {
							contentCell.setCellStyle(bigDecimalMoneyCellStyle);
							contentCell.setCellValue(((BigDecimal) dataObject).toPlainString());
						} else {
							contentCell.setCellValue(dataObject.toString());
						}
					} else {
						contentCell.setCellValue("");
					}
				}
			}

			workBook.write(response.getOutputStream());
		} finally {
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}

	}

	private static CellStyle createBigDecimalMoneyCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));// 保留两位小数点
		return style;
	}

}