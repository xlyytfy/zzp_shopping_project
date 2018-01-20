package com.taotao.service.test;

import java.util.Scanner;

public class SimpleTest {
	/*public static void main(String[] args) throws Exception {
		 //获取十天后的日期
		 Calendar calendar= Calendar.getInstance();
		 calendar.add(Calendar.DAY_OF_YEAR,10);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		 System.out.println(format.format(calendar.getTime()));
	}*/
	
	public static void main(String[] args) {
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
	}
	
	
}
