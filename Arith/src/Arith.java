import java.math.BigDecimal;


public class Arith 
{
	private static final int DEF_DIV_SCALE= 10;
	private Arith() {}
	/**
	 * 提供精确的加法运算。
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1,double v2)
	{
		BigDecimal b1= BigDecimal.valueOf(v1);
		BigDecimal b2= BigDecimal.valueOf(v2);
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确减法运算
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1,double v2)
	{
		BigDecimal b1= BigDecimal.valueOf(v1);
		BigDecimal b2= BigDecimal.valueOf(v2);
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确地乘法运算
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1,double v2)
	{
		BigDecimal b1= BigDecimal.valueOf(v1);
		BigDecimal b2= BigDecimal.valueOf(v2);
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 提供（相对）精确地除法运算，当发生除不尽情况时，
	 * 精确到小数点后DEF_DIV_SCALE位的数字四舍五入
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1,double v2)
	{
		BigDecimal b1= BigDecimal.valueOf(v1);
		BigDecimal b2= BigDecimal.valueOf(v2);
		return b1.divide(b2, DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args)
	{
		System.out.println("0.05+0.01="+Arith.add(0.05, 0.01));
		System.out.println("1.0-0.42="+Arith.sub(1.0, 0.42));
		System.out.println("4.015*100="+Arith.mul(4.015, 100));
		System.out.println("123.3/100="+Arith.div(123.3, 100));
	}

}
