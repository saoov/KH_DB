package kr.co.kh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberExecuterMe {
	public static String session;
	static {
		session = null;
	}

	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = null;
		String protocol = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsa", "dkdlxl");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		while (true) {
			System.out.println("R:ȸ������ L:ȸ����� S:IDã�� D:ȸ��Ż�� E:ȸ������ I:�α��� O:�α׾ƿ�");
			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (protocol.equalsIgnoreCase("r")) {// ȸ������
				if(session==null) {
				try {
					System.out.println("���̵��Է� : ");
					String id = input.readLine();
					System.out.println("�н������Է� : ");
					String pw = input.readLine();
					System.out.println("�ּ� �Է�");
					String addr = input.readLine();
					System.out.println("��ȭ��ȣ�Է� : ");
					String tel = input.readLine();
					Statement stmt = conn.createStatement();
					String sql = "INSERT INTO member (id,pw,addr,tel) VALUES ('" + id + "','" + pw + "','" + addr
							+ "','" + tel + "')";
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "�� ȸ���� ��ϵǾ����ϴ�.");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				} else {
					System.out.println("�α��εǾ��ֽ��ϴ�.");
					continue;
				}
			} // ȸ����������
			else if (protocol.equalsIgnoreCase("l")) {// ȸ�����
				System.out.print("ȸ�����̵�\t\t" + "ȸ���н�����\t" + "ȸ���ּ�\t" + "ȸ����ȭ��ȣ\t\n");
				ResultSet rs = null;
				try {
					Statement stmt = conn.createStatement();
					String sql = "SELECT id,pw,addr,tel from member";
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					while (rs.next()) {
						String listId = rs.getString("id");
						String listPw = rs.getString("pw");
						String listAddr = rs.getString("addr");
						String listTel = rs.getString("tel");
						System.out.print(listId + "\t" + listPw + "\t" + listAddr + "\t" + listTel + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} // ȸ���������
			else if (protocol.equalsIgnoreCase("s")) {// IDã��
				System.out.println("ã�� ID�� �Է� : ");
				ResultSet rs = null;
				try {
					String searchInputID = input.readLine();
					Statement stmt = conn.createStatement();
					String sql = "SELECT ID,PW,ADDR,TEL FROM MEMBER WHERE ID='" + searchInputID + "'";
					rs = stmt.executeQuery(sql);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					System.out.print("ȸ�����̵�\t\t" + "ȸ���н�����\t" + "ȸ���ּ�\t" + "ȸ����ȭ��ȣ\t\n");
					while (rs.next()) {
						String searchId = rs.getString("id");
						String searchPw = rs.getString("pw");
						String searchAddr = rs.getString("addr");
						String searchTel = rs.getString("tel");
						System.out.println(searchId + "\t" + searchPw + "\t" + searchAddr + "\t" + searchTel + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} // IDã������
			else if (protocol.equalsIgnoreCase("d")) {// ȸ��Ż��
				Statement stmt = null;
				if (session != null) {
					try {
						stmt = conn.createStatement();
						String sql = "DELETE FROM MEMBER WHERE ID='" + session + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println("ȸ�� Ż��Ǿ����ϴ�.");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("�α��� �Ǿ����� �ʽ��ϴ�.");
				}

			} // ȸ��Ż������
			else if (protocol.equalsIgnoreCase("e")) {// ȸ������
				Statement stmt = null;
				String sql;
				try {
					stmt = conn.createStatement();
					sql = "SELECT id,pw,addr,tel from member where id='" + session + "'";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						String updateId = rs.getString("id");
						String updatePw = rs.getString("pw");
						String updateAddr = rs.getString("addr");
						String updateTel = rs.getString("tel");
						System.out.println(updateId + "\t" + updatePw + "\t" + updateAddr + "\t" + updateTel + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (session != null) {
					try {
						System.out.println("���̵��Է� : ");
						String id = input.readLine();
						System.out.println("�н������Է� : ");
						String pw = input.readLine();
						System.out.println("�ּ� �Է�");
						String addr = input.readLine();
						System.out.println("��ȭ��ȣ�Է� : ");
						String tel = input.readLine();
						sql = "UPDATE MEMBER SET ID='" + id + "', pw='" + pw + "', addr='" + addr + "', tel='" + tel
								+ "' where id = '" + session + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "���� ȸ���� �����Ǿ����ϴ�.");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("�α��εǾ����� �ʽ��ϴ�.");
				}

			} // ȸ����������
			else if (protocol.equalsIgnoreCase("i")) {// �α���
				
				try {
					System.out.println("���̵��Է� : ");
					String loginId = input.readLine();
					System.out.println("�н������Է� : ");
					String loginPw = input.readLine();
					Statement stmt = conn.createStatement();
					String sql = "SELECT id,pw,addr,tel FROM MEMBER WHERE (ID,PW) IN (SELECT ID, PW FROM MEMBER WHERE ID='"
							+ loginId + "' AND pw='" + loginPw + "')";
					int cnt = stmt.executeUpdate(sql);
					if (cnt != 0) {
						System.out.println("�α��εǾ����ϴ�.");
						session = loginId;
					} else {
						System.out.println("ȸ�������� �ùٸ��� �ʽ��ϴ�.");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // �α�������
			else if (protocol.equalsIgnoreCase("o")) {// �α׾ƿ�
				if (session != null) {
					System.out.println("�α׾ƿ��Ǿ����ϴ�.");
					session = null;
				} else {
					System.out.println("�α��� �Ǿ����� �ʽ��ϴ�.");
				}
			} // �α׾ƿ�����
		} // while
	}// main

}
