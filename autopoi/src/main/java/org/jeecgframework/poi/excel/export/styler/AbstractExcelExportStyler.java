/**
 * Copyright 2013-2015 JEECG (jeecgos@163.com)
 *   
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jeecgframework.poi.excel.export.styler;

import org.apache.poi.ss.usermodel.*;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.entity.params.ExcelForEachParams;

/**
 * 抽象接口提供两个公共方法
 * 
 * @author JEECG
 * @date 2015年1月9日 下午5:48:55
 */
public abstract class AbstractExcelExportStyler implements IExcelExportStyler {
	// 单行
	protected CellStyle stringNoneStyle;
	protected CellStyle stringNoneWrapStyle;
	// 间隔行
	protected CellStyle stringSeptailStyle;
	protected CellStyle stringSeptailWrapStyle;

	protected Workbook workbook;

	protected static final short STRING_FORMAT = (short) BuiltinFormats.getBuiltinFormat("TEXT");

	protected void createStyles(Workbook workbook) {
		this.stringNoneStyle = stringNoneStyle(workbook, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		this.stringNoneWrapStyle = stringNoneStyle(workbook, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		this.stringSeptailStyle = stringSeptailStyle(workbook, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		this.stringSeptailWrapStyle = stringSeptailStyle(workbook, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		this.workbook = workbook;
	}

	@Override
	public CellStyle getStyles(boolean noneStyler, ExcelExportEntity entity) {
		if (noneStyler && (entity == null || entity.isWrap())) {
			return stringNoneStyle(workbook, true, entity == null ? HorizontalAlignment.CENTER : entity.getHorizontalAlignment(), entity == null ? VerticalAlignment.CENTER : entity.getVerticalAlignment());
		}
		if (noneStyler) {
			return stringNoneStyle(workbook, false, entity.getHorizontalAlignment(), entity.getVerticalAlignment());
		}
		if (noneStyler == false && (entity == null || entity.isWrap())) {
			return stringSeptailStyle(workbook, true, entity == null ? HorizontalAlignment.CENTER : entity.getHorizontalAlignment(), entity == null ? VerticalAlignment.CENTER : entity.getVerticalAlignment());
		}

		return stringSeptailStyle(workbook, false, entity.getHorizontalAlignment(), entity.getVerticalAlignment());
	}

	public CellStyle stringNoneStyle(Workbook workbook, boolean isWarp, HorizontalAlignment alignment, VerticalAlignment verticalAlignment) {
		return null;
	}

	public CellStyle stringSeptailStyle(Workbook workbook, boolean isWarp, HorizontalAlignment alignment, VerticalAlignment verticalAlignment) {
		return null;
	}

	/**
	 * 获取模板样式（列循环时用到）
	 * @param isSingle
	 * @param excelForEachParams
	 * @return
	 */
	@Override
	public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams) {
		return null;
	}

}
