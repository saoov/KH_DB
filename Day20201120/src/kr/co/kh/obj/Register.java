package kr.co.kh.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {

	private String protocol;
	private BufferedReader input;
	private Statement stmt;
	private Connection conn;
	private int cnt;
	
	public void showMenu() throws IOException {
		System.out.println("======�޴�����======");
		System.out.println("1. �л�");
		System.out.println("2. ����");
		System.out.println("3. ������");
		System.out.println("4. �����޴�");
		System.out.println("��ȣ�� ������ �ּ���..");
			protocol = input.readLine();
	}
	
	public void registerStudent() throws IOException, SQLException {
		System.out.println("�л����");
		System.out.println("���� : ");
		String age1 = input.readLine();
		System.out.println("�̸� : ");
		String irum = input.readLine();
		System.out.println("�й� : ");
		String hakbun1 = input.readLine();
		stmt = conn.createStatement();
		int age = Integer.parseInt(age1);
		int hakbun = Integer.parseInt(hakbun1);
		String sql = "insert into student2(no, age,irum,hakbun)values(student2_no.nextval," + age + ",'" + irum+ "'," + hakbun + ")";
		cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "�� �л��� ��ϵǾ����ϴ�.");
	}
	
	public void registerProfessor() throws IOException, SQLException {
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
	}
	
	public void registerManager() throws IOException, SQLException {
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
	
	
}

/*

	
	if (protocol.equals("1")) {// �л����
		

	} else if (protocol.equals("2")) {
		

	} else if (protocol.equals("3")) {
		

	}
	return protocol;
}
*/