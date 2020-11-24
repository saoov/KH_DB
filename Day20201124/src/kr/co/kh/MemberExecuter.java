package kr.co.kh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberExecuter {
	public static String session;
	static {
		session = null;
	}

	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Connection conn=null;
		String protocol = null;
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		String idlogin = null;

		while (true) {
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsa", "dkdlxl");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			System.out.println("R:회원가입 L:회원목록 S:ID찾기 D:회원탈퇴 E:회원수정 I:로그인 O:로그아웃");
			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (protocol.equalsIgnoreCase("r")) {

				try {
					stmt = conn.createStatement();
					sql = "select id from member";
					rs = stmt.executeQuery(sql);

					while (rs.next()) {
						idlogin = rs.getString("id");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (idlogin.equals(session)) {
					System.out.println("로그인중입니다.");
					continue;
				}
				try {
					System.out.println("아이디입력 : ");
					String id = input.readLine();
					System.out.println("패스워드입력 : ");
					String pw = input.readLine();
					System.out.println("주소 입력");
					String addr = input.readLine();
					System.out.println("전화번호입력 : ");
					String tel = input.readLine();
					stmt = conn.createStatement();
					sql = "insert into member(id,pw,tel.addr) values('" + id + "','" + pw + "','" + addr + "','" + tel+ "')";
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "건 회원이 등록되었습니다.");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				

			} else if (protocol.equalsIgnoreCase("l")) {

			} else if (protocol.equalsIgnoreCase("s")) {

			} else if (protocol.equalsIgnoreCase("d")) {

			} else if (protocol.equalsIgnoreCase("e")) {

			} else if (protocol.equalsIgnoreCase("i")) {//로그인
				String loginId=null;
				String loginPw=null;
				try {
					System.out.println("아이디입력 : ");
					loginId = input.readLine();
					System.out.println("패스워드입력 : ");
					loginPw = input.readLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				String id = null;
				String pw = null;
				try {
					stmt = conn.createStatement();
					sql = "select id,pw from member";
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						id = rs.getString("id");
						pw = rs.getString("pw");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(!(loginId.equals(id))) {
					System.out.println("아이디가 틀렸습니다.");
					continue;
				} else if(!(loginPw.equals(pw))) {
					System.out.println("패스워드가 틀렸습니다.");
					continue;
				}
				System.out.println("환영합니다. 로그인되었습니다.");
				session = loginId;
			} //로그인
			else if (protocol.equalsIgnoreCase("o")) {

			}
		}
	}

}
