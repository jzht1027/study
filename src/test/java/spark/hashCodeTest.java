package spark;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName hashCode
 * @Description
 * 7
 * @Author
 * @Date 2021/6/22 14:44
 * @Version 1.0
 **/
public class hashCodeTest {
    public static void main(String args[]){
        Map<String,String> map =new HashMap<String,String>();

        Double rate = Double.parseDouble("123132120");
        String rates = NumberFormat.getNumberInstance().format(rate);
        System.out.println("****"+rates);

        NumberFormat numb = NumberFormat.getPercentInstance();
        numb.setMaximumFractionDigits(2);
        numb.setRoundingMode(RoundingMode.HALF_UP);
        rates = numb.format(rate);
        System.out.println("****"+rates);

        DecimalFormat decimalFormat = new DecimalFormat("0.##%");
        rates = decimalFormat.format(rate);
        System.out.println(rates);

        rates = String.format("%.2f",rate);
        System.out.println(rates);

        BigDecimal b = new BigDecimal(rate);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);

        String sss = "交易账户ID".toUpperCase();
        System.out.println(sss);
        String seq = "{\"groupOp\":\"AND\",\"rules\":[{\"field\":\"name\",\"op\":\"eq\",\"data\":\"九坤私享33号私募证券投资基金-EQ-PB@CICC-11709  \"}]}";



        if (seq != null && !seq.isEmpty()){
            System.out.println("&&&&&"+seq.replaceAll("  +",""));
        }else{
            System.out.println("seq:"+seq);

        }

        DecimalFormat df = new DecimalFormat("0.############");
        double num = 100011677.12313212315645;
        String s = df.format(num);
//        s = String.valueOf(num);
        System.out.println(s);
        s = subZeroAndDot(s);
        System.out.println(s);

        System.out.println(StringUtils.substringBefore("pici:123","sdfsd"));
        System.out.println(StringUtils.substringAfter("pici:123",":"));
        System.out.println("pici:123".substring("pici:123".indexOf(":")+1));
        System.out.println("pici:123".substring(0,"pici:123".indexOf(":")));
        System.out.println("pici:123".indexOf(":"));

        if("pici".equals(StringUtils.substringBefore("pici:123",":"))){
            System.out.println("@@@@@@@@@@@@@@@@@");
        }else {
            System.out.println("#################");

        }
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            System.out.println(s.indexOf("."));
            System.out.println(s);
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        System.out.println(s);
        return s;
    }
}