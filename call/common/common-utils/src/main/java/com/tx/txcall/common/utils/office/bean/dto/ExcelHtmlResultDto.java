package com.tx.txcall.common.utils.office.bean.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExcelHtmlResultDto extends DocHtmlDto {

	private List<ExcelSheetDto> sheetList;
	private Integer activeSheetIndex;
}
