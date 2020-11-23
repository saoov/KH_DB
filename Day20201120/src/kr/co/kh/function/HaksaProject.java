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
			System.out.println("======�л����======");
			System.out.println("1. ���");
			System.out.println("2. ã��");
			System.out.println("3. ����");
			System.out.println("4. ��ü���");
			System.out.println("============");
			System.out.println("0. ����");
			System.out.println("��ȣ�� ������ �ּ���..");
			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (protocol.equals("1")) {// ���
				try {
					protocol = HaksaFunction.register(protocol, input, stmt, conn, cnt);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				if (protocol.equals("4")) {//�����޴�
					continue;
				} //�����޴�
				System.out.println("����Ͻ÷��� 1, ���� �Ͻ÷��� 0�� �Է��� �ּ���..");
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
			} // �������
			else if (protocol.equals("2")) {// ã��
				System.out.println("ã��");
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
			} // ã������
			else if (protocol.equals("3")) {// ����
				try {
					System.out.println("����");
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

			} // ��������
			else if (protocol.equals("4")) {// ��ü���
				try {
					System.out.println("��ü ���");
					HaksaFunction.list(stmt, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // ��ü�������
			else if (protocol.equals("0")) {// �˻�
				System.out.println("�л����α׷��� �����մϴ�.");
				System.exit(0);
			} // �˻�
		} // while����
	}
}
