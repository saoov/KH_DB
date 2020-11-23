package kr.co.kh.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HaksaProject {

	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String protocol = null;
		Connection conn = null;
		Statement stmt = null;
		int cnt = 0;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		while (true) {
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			System.out.println("======학사관리======");
			System.out.println("1. 등록");
			System.out.println("2. 찾기");
			System.out.println("3. 삭제");
			System.out.println("4. 전체출력");
			System.out.println("============");
			System.out.println("0. 종료");
			System.out.println("번호를 선택해 주세요..");
			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (protocol.equals("1")) {// 등록
				try {
					protocol = HaksaFunction.register(protocol, input, stmt, conn, cnt);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				if (protocol.equals("4")) {//이전메뉴
					continue;
				} //이전메뉴
				System.out.println("계속하시려면 1, 종료 하시려면 0을 입력해 주세요..");
				try {
					String bunho = input.readLine();
					if (bunho.equals("1")) {
						continue;
					} else {
						System.exit(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // 등록종료
			else if (protocol.equals("2")) {// 찾기
				System.out.println("찾기");
				try {
					HaksaFunction.search(input, stmt, conn);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // 찾기종료
			else if (protocol.equals("3")) {// 삭제
				try {
					System.out.println("삭제");
					HaksaFunction.delete(input, stmt, conn, cnt);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} // 삭제종료
			else if (protocol.equals("4")) {// 전체출력
				try {
					System.out.println("전체 출력");
					HaksaFunction.list(stmt, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // 전체출력종료
			else if (protocol.equals("0")) {// 검색
				System.out.println("학사프로그램을 종료합니다.");
				System.exit(0);
			} // 검색
		} // while종료
	}
}
