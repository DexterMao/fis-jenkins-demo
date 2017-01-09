
package com.kakasure.bqf.util;

import java.math.BigDecimal;

import com.kakasure.common.enums.IsDeleted;

/**
 * 
 * 卖家时间常量类
 * 
 * @author kakasure
 *
 */
public class PoundageUtil {

	public static Double getPoundage(String payType, String isShanghai, String isTrafficBank, String isCompany, BigDecimal amount) {
		Double poundage = new Double(0);
		if (payType.equals("00")) {// 支付宝
			if (amount.compareTo(new BigDecimal(200)) != 1) {// 200（含） 1
				poundage = new Double(1);
			} else if (amount.compareTo(new BigDecimal(200)) == 1
													&& amount.compareTo(new BigDecimal(5000)) == -1) { // 200
																										// －5000
																										// 5%
				poundage = amount.multiply(new BigDecimal(0.005)).doubleValue();
			} else {// 25
				poundage = new Double(25);
			}
		}
		if (payType.equals("01")) {// 银联
			if (isCompany.equals(IsDeleted.YES.value)) {// 是否 企业（对公） y
				if (isShanghai.equals(IsDeleted.YES.value)) {// 是否上海 y
					if (isTrafficBank.equals(IsDeleted.YES.value)) {// 是否交行 y
						poundage = new Double(1);
					} else { // n
						poundage = new Double(1.2);
					}
				} else {// n
					poundage = new Double(0.5);
					if (amount.compareTo(new BigDecimal(10000)) != 1) {
						poundage = poundage + new Double(5);
					} else if (amount.compareTo(new BigDecimal(10000)) == 1
															&& amount.compareTo(new BigDecimal(100000)) != 1) {
						poundage = poundage + new Double(10);

					} else if (amount.compareTo(new BigDecimal(100000)) == 1
															&& amount.compareTo(new BigDecimal(500000)) != 1) {
						poundage = poundage + new Double(15);

					} else if (amount.compareTo(new BigDecimal(500000)) == 1
															&& amount.compareTo(new BigDecimal(1000000)) != 1) {
						poundage = poundage + new Double(20);

					} else {
						poundage = poundage + amount.multiply(new BigDecimal(0.0002)).doubleValue();
					}
					if (poundage > 200) {
						poundage = new Double(200);
					}
				}
			} else { // n
				if (isTrafficBank.equals(IsDeleted.YES.value)) {// 是否交行 y
					poundage = new Double(0.7);
				} else { // n
					if (isShanghai.equals(IsDeleted.YES.value)) {// 是否上海 y
						poundage = new Double(1.2);
					} else {// n
						poundage = new Double(2);

					}
				}
			}
		}
		return poundage;
	}
}
