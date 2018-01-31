package com.taotao.service.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.junit.Test;

public class SimpleTest {
	public static void main(String[] args) throws Exception {
		 //获取十天后的日期
		 /*Calendar c1= Calendar.getInstance();
		 Calendar c2= Calendar.getInstance();
		 c2.add(Calendar.DAY_OF_YEAR, 1);
		 System.out.println(c2.getTime());
		 System.out.println(c2.getTimeInMillis() - c1.getTimeInMillis());
		 
		 System.out.println((c2.getTimeInMillis() - c1.getTimeInMillis())/(1000*60*60*24));*/
		 
		System.out.println(getSum(1,-1));
		 
		 
		 /*calendar.add(Calendar.DAY_OF_YEAR,10);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		 System.out.println(format.format(calendar.getTime()));*/
		
	}
	
	/*public static void main(String[] args) {
		    System.out.print("输入n：");
		    int n = new Scanner(System.in).nextInt();
		    double sum = 0;
		    int i =1;
		    
		    if(n%2 == 0){
		    	while(i<=n){
		    		i = i*2;
		    		sum += 1.0/i;
		    	}
		    }else{
		    	while(i<=n){
		    		sum += 1.0/i;
		    		i = i*2+1;
		    	}
		    }
		    System.out.println(sum);
	}*/
	
	public static String getSum(int x,int n){
		int result = 1;
		if(n ==0 ){
			return "1";
		}else if(n < 0){
			n = -n; 
			for(int i = 1;i<=n;i++){
				result = result*x;
			}
			return ""+((1.0)/result);
		}else{
			for(int i = 1;i<=n;i++){
				result = result*x;
			}
			return result+"";
		}
		
	}
}
