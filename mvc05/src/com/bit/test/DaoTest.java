package com.bit.test;

import java.sql.Connection;
import java.util.ArrayList;

import com.bit.model.Guest02Dao;
import com.bit.model.Guest02Dto;

public class DaoTest {
	// 테스트용 나중에 삭제함.
	public static void main(String[] args) {
		Guest02Dao dao = new Guest02Dao();
		Connection conn = dao.getConnection();
		System.out.println("Connection: " + conn);
		System.out.println(conn != null);

		ArrayList<Guest02Dto> list = dao.getList();
		int last = 0;
		for (Guest02Dto bean : list) {
			int num = bean.getNum();
			if (last < num)
				last = num;
			// System.out.println(bean.getNum() + ", " + bean.getSub());
			// System.out.println(bean); /*toString 메소드값을 불러옴*
			// 많은 양의 dummy가 있을 때 일일이 다 호출 할 필요 없으니 아래 방법으로
		}
		System.out.println("list? " + (list != null));
		int size = list.size();
		System.out.println("list size>0 :" + (size > 0));
		// /////////////////////////////////////////////////////
		Guest02Dto target = new Guest02Dto();
		target.setNum(last + 1);
		target.setSub("new test1");
		target.setUnum(1);
		target.setPay(1111);
		// /////////////////////////////////////////////////////
		dao = new Guest02Dao();
		dao.addList(target.getSub(), target.getUnum(), target.getPay());
		dao = new Guest02Dao();
		System.out.println("insert? " + (size + 1 == dao.getList().size()));

		dao = new Guest02Dao();
		Guest02Dto bean = dao.getListOne(last + 1);
		System.out.println("detail? " + bean.equals(target));
		System.out.println(bean);
		/*
		 * System.out .println("detail? " + (bean.getSub().equals("new test1")
		 * && 1 == bean.getUnum() && 1111 == bean.getPay()));
		 */
		dao = new Guest02Dao();
		target.setSub("edit01");
		target.setPay(22222);
		System.out.println("update? "
				+ (dao.editOne(target.getSub(), target.getPay(),
						target.getNum()) > 0));
	}

}
