package com.zly.java.tools;

import java.math.BigDecimal;

/**  
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入.
 * 
 * 注意：此代码将加减乘除四舍五入方法已实现，但本项目中并未全部使用.
 * @author 赵林洋
 */ 
public class Arith {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 2;
	private Arith(){}
	
	/**  
	* 提供精确的加法运算。  
	* @param v1 被加数  
	* @param v2 加数  
	* @return 两个参数的和  
	*/ 
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**  
	* 提供精确的减法运算。  
	* @param v1 被减数  
	* @param v2 减数  
	* @return 两个参数的差  
	*/ 
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**  
	* 提供精确的乘法运算。  
	* @param v1 被乘数  
	* @param v2 乘数  
	* @return 两个参数的积  
	*/ 
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
}
