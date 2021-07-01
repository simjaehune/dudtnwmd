package dus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class dudtn {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		int inputNo = 0;
		int price = 0;
		int total =0;
		int no = 0;
		int money = 0;
		String name = null;
		ArrayList<OrderListArray> orderList=new ArrayList<OrderListArray>();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mysql","root","my1234");
			    	    
			    System.out.print("상품 번호를 입력하세요 : " );
			    inputNo = scan.nextInt();	    
			    
			    Statement stmt = conn.createStatement(); 
			    ResultSet rset = stmt.executeQuery("SELECT * FROM goods WHERE no=" + inputNo);
			    
			    if (rset.next()) { 
			    	System.out.printf("%d %s %d원 %s\n", rset.getInt(1), rset.getString(2), 
			    									rset.getInt(3), rset.getString(4));
			    	no = rset.getInt(1);
			    	name = rset.getString(2);
			    	price = rset.getInt(3);		
			    	
			    } else {
			    	System.out.println("해당하는 상품이 없습니다.");
			    }
			    rset.close(); 
			    stmt.close(); 
			    conn.close(); 
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.print("구매 갯수 : " );
		    count = scan.nextInt();	   
		    total = price * count; 
		    
		    orderList.add(new OrderListArray(name, no, price, count, total));
		    
		    int type;
		    System.out.print("1. 추가구매, 2. 구매종료 : " );
		    type = scan.nextInt();
		    
			if(type == 2) {
				break;
			}
		}
		System.out.println("결제금액을 입력하세요");
		money = scan.nextInt();
		scan.close();
		
		System.out.println("카드결제는 30일(12월28일)이내");
 		System.out.println("카드와 영수증 지참 시 가능합니다");
 		System.out.println("--------------------------------");
 		System.out.printf("%s       %s       %s \n", "제품명", "수량", "가격");
 		
 		int sum=0;

		for(int index=0; index<orderList.size();index++) {
	    sum+=(orderList.get(index).price*orderList.get(index).count);
		
 		System.out.printf("%s      %d        %d \n", orderList.get(index).name, orderList.get(index).count, orderList.get(index).price*orderList.get(index).count);
		}
 		System.out.println("--------------------------------");
 		System.out.printf("%s                   %d \n", "과세 매출", 6908);
 		int tax = sum * 10/100;
 		System.out.printf("%s                    %d \n", "부가세", tax);
 		
		System.out.printf("%s            %d \n", "합        계", sum);
 		System.out.println("--------------------------------");
 		System.out.printf("           %s \n", "[결제내역]");
 		System.out.printf("%s   :   %s        \n", "결제방법", "GS25 모바일상품권");
 		System.out.printf("%s :     %s        \n", "결제코드", "9676*********8224");
 		System.out.printf("%s   :              %d \n", "결제금액", money);
 		System.out.printf("%s   :              %d \n", "잔    액", money-sum);
 		System.out.println("--------------------------------");
 		System.out.printf("      %s \n", "현금영수증(소득공제)");
 		System.out.printf("%s            %s \n", "거래자번호", "010000****");
 		System.out.printf("%s            %s \n", "승인  번호", "G666388255");
 		System.out.println("국세청문의http://현금영수증.kr에");
        System.out.println("================================");
        System.out.println("================================");
        System.out.println();
        System.out.println();
	}
	}


