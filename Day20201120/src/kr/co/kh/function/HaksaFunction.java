package kr.co.kh.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HaksaFunction {

	public static String register(String protocol, BufferedReader input, Statement stmt, Connection conn, int cnt)
			throws IOException, SQLException {
		System.out.println("======�޴�����======");
		System.out.println("1. �л�");
		System.out.println("2. ����");
		System.out.println("3. ������");
		System.out.println("4. �����޴�");
		System.out.println("��ȣ�� ������ �ּ���..");
		try {
			protocol = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protocol.equals("1")) {// �л����
			System.out.println("�л����");
			System.out.println("���� : ");
			String age1 = input.readLine();
			System.out.println("�̸� : ");
			String irum = input.readLine();
			System.out.println("�й� : ");
			String hakbun1 = input.readLine();
			cnt = 0;
			stmt = conn.createStatement();
			int age = Integer.parseInt(age1);
			int hakbun = Integer.parseInt(hakbun1);
			String sql = "insert into student2(no, age,irum,hakbun)values(student2_no.nextval," + age + ",'" + irum
					+ "'," + hakbun + ")";
			cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "�� �л��� ��ϵǾ����ϴ�.");

		} else if (protocol.equals("2")) {
			System.out.println("�������");
			System.out.println("���� : ");
			String age1 = input.readLine();
			System.out.println("�̸� : ");
			String irum = input.readLine();
			System.out.println("���� : ");
			String subject = input.readLine();
			int age = Integer.parseInt(age1);
			cnt = 0;
			stmt = conn.createStatement();
			String sql = "insert into professor(no,age,irum,subject) values (professor_no.nextval," + age + ",'" + irum
					+ "','" + subject + "')";
			cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "�� �������� ��ϵǾ����ϴ�.");

		} else if (protocol.equals("3")) {
			System.out.println("�����ڵ��");
			System.out.println("���� : ");
			String age1 = input.readLine();
			System.out.println("�̸� : ");
			String irum = input.readLine();
			System.out.println("�μ� : ");
			String part = input.readLine();
			cnt = 0;
			stmt = conn.createStatement();
			int age = Integer.parseInt(age1);
			String sql = "insert into manager(no,age,irum,part) values (manager_no.nextval," + age + ",'" + irum + "','"
					+ part + "')";
			cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "���� �����ڰ� ��ϵǾ����ϴ�.");

		}
		return protocol;
	}

	public static void search(BufferedReader input, Statement stmt, Connection conn) throws IOException, SQLException {
		System.out.println("ã�� ��� : ");
		System.out.println("1. �л� 2.���� 3.������");
		String gubun = null;
			gubun = input.readLine();
		if (gubun.equals("1")) {// �л� ã��
				System.out.println("ã�� �л��̸��� �Է��ϼ���.");
				String searchName = input.readLine();
				stmt = conn.createStatement();
				String sql = "SELECT no,age,irum,hakbun from student2 where irum='" + searchName + "'";
				int op = stmt.executeUpdate(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if (op != 0) {
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						int hakbun = rs.getInt("hakbun");
						System.out.print("����:" + age + "\t" + "�̸�:" + irum + "\t" + "�й�:" + hakbun + "\n");
					}
				} else {
					System.out.println("ã�� �л��� �����ϴ�.");
				}
		} // �л� ã��
		else if (gubun.equals("2")) { // ���� ã��
				System.out.println("ã�� �����̸��� �Է��ϼ���.");
				String searchName = input.readLine();
				stmt = conn.createStatement();
				String sql = "SELECT no,age,irum,subject from professor where irum='" + searchName + "'";
				int op = stmt.executeUpdate(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if (op != 0) {
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						String subject = rs.getString("subject");
						System.out.print("����:" + age + "\t" + "�̸�:" + irum + "\t" + "����:" + subject + "\n");
					}
				} else {
					System.out.println("ã�� �������� �����ϴ�.");
				}

		} // ���� ã��
		else if (gubun.equals("3")) { // ������ ã��
				System.out.println("ã�� �������̸��� �Է��ϼ���.");
				String searchName = input.readLine();
				stmt = conn.createStatement();
				String sql = "SELECT no,age,irum,part from manager where irum='" + searchName + "'";
				int op = stmt.executeUpdate(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if (op != 0) {
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						String part = rs.getString("part");
						System.out.print("����:" + age + "\t" + "�̸�:" + irum + "\t" + "�μ�:" + part + "\n");
					}
				} else {
					System.out.println("ã�� �����ڰ� �����ϴ�.");
				}
		} // ������ ã��
	}

	public static void delete(BufferedReader input, Statement stmt, Connection conn, int cnt) throws SQLException, IOException {
		System.out.println("������ ��� : ");
		System.out.println("1. �л� 2.���� 3.������");
		String gubun = null;
			gubun = input.readLine();
		if (gubun.equals("1")) {// �л� ����
				System.out.println("������ �л� �̸��� �Է��ϼ���.");
				String deleteName = input.readLine();
				stmt = conn.createStatement();
				String sql = "DELETE FROM student2 where irum='" + deleteName + "'";
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "���� �л������� �����Ǿ����ϴ�.");

		} // �л�����
		else if (gubun.equals("2")) {// ���� ����
				System.out.println("������ ���� �̸��� �Է��ϼ���.");
				String deleteName = input.readLine();
				stmt = conn.createStatement();
				String sql = "DELETE FROM professor where irum='" + deleteName + "'";
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "���� ���������� �����Ǿ����ϴ�.");
		} // ��������
		else if (gubun.equals("3")) {// ������ ����
				System.out.println("������ ������ �̸��� �Է��ϼ���.");
				String deleteName = input.readLine();
				stmt = conn.createStatement();
				String sql = "DELETE FROM manager where irum='" + deleteName + "'";
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "���� ������������ �����Ǿ����ϴ�.");
			}

	}

	public static void list(Statement stmt, Connection conn) throws SQLException {
			stmt = conn.createStatement();
			String sql = "select no,age,irum,hakbun from student2";
			ResultSet rs = stmt.executeQuery(sql); // rs�� ���̺��� ����Ų��
			while (rs.next()) {
				int age = rs.getInt("age");
				String irum = rs.getString("irum");
				int hakbun = rs.getInt("hakbun");
				System.out.print("�̸� :");
				System.out.print(irum + "\t");
				System.out.print("���� :");
				System.out.print(age + "\t");
				System.out.print("�й� :");
				System.out.print(hakbun + "\n");
			} // �л� ��ü���
			stmt = conn.createStatement();
			sql = "select no,age,irum,subject from professor";
			rs = stmt.executeQuery(sql); // rs�� ���̺��� ����Ų��
			while (rs.next()) {
				int age = rs.getInt("age");
				String irum = rs.getString("irum");
				String subject = rs.getString("subject");
				System.out.print("�̸� :");
				System.out.print(irum + "\t");
				System.out.print("���� :");
				System.out.print(age + "\t");
				System.out.print("���� :");
				System.out.print(subject + "\n");
			} // ���� ��ü���
			stmt = conn.createStatement();
			sql = "select no,age,irum,part from manager";
			rs = stmt.executeQuery(sql); // rs�� ���̺��� ����Ų��
			while (rs.next()) {
				int age = rs.getInt("age");
				String irum = rs.getString("irum");
				String part = rs.getString("part");
				System.out.print("�̸� :");
				System.out.print(irum + "\t");
				System.out.print("���� :");
				System.out.print(age + "\t");
				System.out.print("�μ� :");
				System.out.print(part + "\n");
			} // ������ ��ü���
			stmt = conn.createStatement();
			sql = "select s.no as ��ȣ,s.age as ����,s.irum as �̸�,s.hakbun as �й�,p.age as ��������,p.irum as �����̸�,p.subject as ����,m.age as �����ڳ���,m.irum as �������̸�,m.part as �μ� from (student2 s full outer join professor p on s.no=p.no) full outer join manager m on s.no=m.no ORDER BY ��ȣ ASC";
			rs = stmt.executeQuery(sql); // rs�� ���̺��� ����Ų��
			System.out.println("======�л���ü���======");
			while (rs.next()) {
				int sage = rs.getInt("����");
				String sirum = rs.getString("�̸�");
				int shakbun = rs.getInt("�й�");
				int page = rs.getInt("��������");
				String pirum = rs.getString("�����̸�");
				String psubject = rs.getString("����");
				int mage = rs.getInt("�����ڳ���");
				String mirum = rs.getString("�������̸�");
				String mpart = rs.getString("�μ�");
				System.out.print(sage + "\t" + sirum + "\t" + shakbun + "\t" + page + "\t" + pirum + "\t"
						+ psubject + "\t" + mage + "\t" + mirum + "\t" + mpart + "\n");
			} // ��ü���
	}

}
