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
			System.out.println("R:ȸ������ L:ȸ����� S:IDã�� D:ȸ��Ż�� E:ȸ������ I:�α��� O:�α׾ƿ�");
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
					System.out.println("�α������Դϴ�.");
					continue;
				}
				try {
					System.out.println("���̵��Է� : ");
					String id = input.readLine();
					System.out.println("�н������Է� : ");
					String pw = input.readLine();
					System.out.println("�ּ� �Է�");
					String addr = input.readLine();
					System.out.println("��ȭ��ȣ�Է� : ");
					String tel = input.readLine();
					stmt = conn.createStatement();
					sql = "insert into member(id,pw,tel.addr) values('" + id + "','" + pw + "','" + addr + "','" + tel+ "')";
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "�� ȸ���� ��ϵǾ����ϴ�.");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				

			} else if (protocol.equalsIgnoreCase("l")) {

			} else if (protocol.equalsIgnoreCase("s")) {

			} else if (protocol.equalsIgnoreCase("d")) {

			} else if (protocol.equalsIgnoreCase("e")) {

			} else if (protocol.equalsIgnoreCase("i")) {//�α���
				String loginId=null;
				String loginPw=null;
				try {
					System.out.println("���̵��Է� : ");
					loginId = input.readLine();
					System.out.println("�н������Է� : ");
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
					System.out.println("���̵� Ʋ�Ƚ��ϴ�.");
					continue;
				} else if(!(loginPw.equals(pw))) {
					System.out.println("�н����尡 Ʋ�Ƚ��ϴ�.");
					continue;
				}
				System.out.println("ȯ���մϴ�. �α��εǾ����ϴ�.");
				session = loginId;
			} //�α���
			else if (protocol.equalsIgnoreCase("o")) {

			}
		}
	}

}
