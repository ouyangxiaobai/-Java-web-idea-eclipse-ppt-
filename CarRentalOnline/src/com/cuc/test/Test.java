package com.cuc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.cuc.dao.IBusinessStoreDAO;
import com.cuc.dao.ICarDAO;
import com.cuc.dao.IDayCarRentalDAO;
import com.cuc.dao.IDayStoreProfitDAO;
import com.cuc.dao.IMemberDAO;
import com.cuc.dao.IMessageDAO;
import com.cuc.dao.IMonthCarRentalDAO;
import com.cuc.dao.IMonthRegisterDAO;
import com.cuc.dao.IMonthStoreProfitDAO;
import com.cuc.dao.IOrderDAO;
import com.cuc.dao.ISysAdminDAO;
import com.cuc.dao.imp.BusinessStoreDAO;
import com.cuc.dao.imp.CarDAO;
import com.cuc.dao.imp.DayCarRentalDAO;
import com.cuc.dao.imp.DayStoreProfitDAO;
import com.cuc.dao.imp.MemberDAO;
import com.cuc.dao.imp.MessageDAO;
import com.cuc.dao.imp.MonthCarRentalDAO;
import com.cuc.dao.imp.MonthRegisterDAO;
import com.cuc.dao.imp.MonthStoreProfitDAO;
import com.cuc.dao.imp.OrderDAO;
import com.cuc.dao.imp.SysAdminDAO;
import com.cuc.model.Car;
import com.cuc.model.DayCarRental;
import com.cuc.model.DayStoreProfit;
import com.cuc.model.Message;
import com.cuc.model.MonthCarRental;
import com.cuc.model.MonthRegister;
import com.cuc.model.MonthStoreProfit;
import com.cuc.util.CommonUtil;
import com.cuc.util.SQLUtil;

public class Test {
	
	public static void main(String[] args) throws ParseException {
		ICarDAO carDAO = new CarDAO();
		IOrderDAO orderDAO = new OrderDAO();
//		IBusinessStoreDAO storeDAO = new  BusinessStoreDAO();
//		ArrayList<String[]> cityList = storeDAO.getAllBusinessCity();
//		if (cityList.size() != 0 && cityList != null) {
//			for (int i = 0; i < cityList.size(); i++) {
//				String[] pc = cityList.get(i);
//				System.out.println(pc[0]+"#"+pc[1]);
//			}
//		}
		
//		IBusinessStoreDAO storeDAO = new  BusinessStoreDAO();
//		ArrayList<String[]> nameList = storeDAO.getBusinessNameByCity("'福建省'", "'福州市'");
//		if (nameList.size() != 0 && nameList != null) {
//			for (int i = 0; i < nameList.size(); i++) {
//				String[] pc = nameList.get(i);
//				System.out.println(pc[0]);
//			}
//		}
		
		/*测试营业门店编号下的获取分页车辆信息*/
//		ICarDAO carDAO = new CarDAO();
//		System.out.println(carDAO.getRSCountByStoreId(1)[0]);
//		ArrayList<String[]> nameList = carDAO.pageSearchByStoreId(1, 2, 1);
//		if (nameList.size() != 0 && nameList != null) {
//			for (int i = 0; i < nameList.size(); i++) {
//				String[] pc = nameList.get(i);
//				System.out.println(pc[3]);
//			}
//		}
		
		//根据车辆编号获取车辆信息
//		ICarDAO carDAO = new CarDAO();
//		System.out.println(carDAO.searchByCarId(0));
		
		//获取所有企业管理员信息
//		ISysAdminDAO sysAdminDAO =  new SysAdminDAO();
//		ArrayList<String[]> nameList = sysAdminDAO.searchAllSysAdmin();
//		if (nameList.size() != 0 && nameList != null) {
//			for (int i = 0; i < nameList.size(); i++) {
//				String[] pc = nameList.get(i);
//				System.out.println(pc[3]);
//			}
//		}
		
		//分页获取会员用户信息
//		IMemberDAO memberDAO = new MemberDAO();
//		ArrayList<String[]> nameList = memberDAO.pageSearchAllMember(2, 1);
//		if (nameList.size() != 0 && nameList != null) {
//			for (int i = 0; i < nameList.size(); i++) {
//				String[] pc = nameList.get(i);
//				System.out.println(pc[0]);
//			}
//		}
		
		//获取会员信息条数
//		IMemberDAO memberDAO = new MemberDAO();
//		System.out.println(memberDAO.getMemberCount()[0]);
		
		//获取所有营业门店信息
//		IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
//		ArrayList<String[]> storeList = storeDAO.searchAllBusinessStore();
//		for(int i = 0 ; i < storeList.size();i++){
//			System.out.println(storeList.get(i)[1]);
//		}
		
		//测试改造的SQLUtil中改造后的 update【成功】
//		Object[] paramArray = new Object[2];
//		paramArray[0] = 3274;
//		paramArray[1]=1;
//		SQLUtil.getInstance().update("t_member", "memberPassword=?", "memberId=?", paramArray);
		
//		ICarDAO carDAO = new CarDAO();
//		ArrayList<String[]> carList = carDAO.pageSearchAllCar(5, 1);
//		System.err.println(carList.get(0)[2]);
		
		//测试租客会员查询某门店下某时间段可租赁车辆信息
		
		//获得2010年9月13日22点36分01秒 的Date对象
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		try {
//			Date myDate1 = dateFormat1.parse("2016-05-01 10:00:00");
//			System.out.println(myDate1+"$"+myDate1.getTime());
//			
//			Date myDate2 = dateFormat1.parse("2016-05-05 10:00:00");
//			System.out.println(myDate1+"$"+myDate2.getTime());
			
//			ArrayList<String[]> carList = carDAO.SearchByStoreNameAndTime("福建师范大学西门店", "2016-05-01 10:00","2016-05-05 10:00");
//			for(int i = 0 ; i < carList.size();i++){
//				System.out.println(carList.get(i)[0]+"#"+carList.get(i)[1]+"#"+carList.get(i)[2]+"#"+carList.get(i)[3]+"#"+carList.get(i)[4]+"#"+carList.get(i)[5]+"#"+carList.get(i)[6]+"#"+carList.get(i)[7]+"#");
//			}
			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		Date d = new Date();
//		System.out.println(d);
//		System.out.println(d.getTime());
		
		//测试查询某门店下按条件查询下的可租赁车辆信息sql
//	System.out.println(carDAO.pageSearchCarByCondition(1,new String[]{"大众","别克"},new float[]{0,500,10001,10000}, new String[]{"自动","手动"},new int[]{5},"2016-05-01 10:00","2016-05-05 10:00",1,2).size());
//	System.out.println(Integer.parseInt(carDAO.getRSCountBySearchCarCondition(1,new String[]{"大众","别克"},new float[]{0,500,10001,10000}, new String[]{"自动","手动"},new int[]{5},"2016-05-01 10:00","2016-05-05 10:00")[0].toString()));
		
		//测试时间差
		//System.out.println("->"+CommonUtil.differentDays("2016-10-04 23:42:00", "2016-10-05 23:41:00"));
		
		//测试超时订单  2016-10-07 22:00:00
		//ArrayList<String[]> orderList = orderDAO.searchTimeOutOrder("2010-10-07 22:30:00");
		
//		Date now = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
//		System.out.println(df.format(now));
//		System.out.println(df.format(now.getTime()-30 * 60 * 1000));// new Date()为获取当前系统时间
		
	//	System.out.println("-->"+CommonUtil.calMin("2016-10-16 14:24:29", "2016-10-16 14:32:00"));
		
//		Date d = new Date();//获取当前日期
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(d);
//		calendar.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
//		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		
//		System.out.println(sdf.format(d));
	
		
//		System.out.println("do CalMonthRegisterCountJob");
//
//		Date d = new Date();// 获取当前日期
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(d);
//		calendar.add(Calendar.MONTH, -1);// 把月期往后增加一天.整数往后推,负数往前移动
//		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//		String calDate = sdf.format(d);
//
//		String year = calDate.split("-")[0];
//		String month = calDate.split("-")[1];
//
//		IMemberDAO memberDAO = new MemberDAO();
//		int regCount = Integer.parseInt(memberDAO.getMonthRegCount(year, month)[0]
//				.toString());
//
//		MonthRegister register = new MonthRegister();
//		register.setYear(year);
//		register.setMonth(month);
//		register.setRegCount(regCount);
//
//		IMonthRegisterDAO registerDAO = new MonthRegisterDAO();
//		System.out.println(registerDAO.insert(register));
		
		
		
		
//		System.out.println("do CalDayCarRentalJob");
//
////		ICarDAO carDAO = new CarDAO();
//
//		Date d = new Date();// 获取当前日期
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(d);
//		calendar.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
//		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//		String calDate = sdf.format(d);// 前一天的日期
//
//		int year = Integer.parseInt(calDate.split("-")[0]);
//		int month = Integer.parseInt(calDate.split("-")[1]);
//		int day = Integer.parseInt(calDate.split("-")[2]);
//
//		ArrayList<String[]> calRentalNumList = carDAO
//				.getDayCarRentalNum(calDate);
//
//		ArrayList<DayCarRental> dayCarRentalList = new ArrayList<DayCarRental>();
//
//		DayCarRental carRental = null;
//
//		for (String[] calRentalNum : calRentalNumList) {
//
//			carRental = new DayCarRental();
//			carRental.setCarId(Integer.parseInt(calRentalNum[0]));
//			carRental.setRentalNum(Integer.parseInt(calRentalNum[1]));
//			carRental.setYear(year);
//			carRental.setMonth(month);
//			carRental.setDay(day);
//
//			dayCarRentalList.add(carRental);
//
//		}
//
//		IDayCarRentalDAO dayCarRentalDAO = new DayCarRentalDAO();
//		
//		System.out.println("1");
//		System.out.println(dayCarRentalDAO.insert(dayCarRentalList));
//		System.out.println("2");
		
		
//		
//		System.out.println("do CalMonthCarRentalJob");
//
//		IDayCarRentalDAO carRentalDAO = new DayCarRentalDAO();
//
//		Date d = new Date();// 获取当前日期
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(d);
//		calendar.add(Calendar.MONTH, -1);// 把日期往后增加一天.整数往后推,负数往前移动
//		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//		String calDate = sdf.format(d);
//
//		int year = Integer.parseInt(calDate.split("-")[0]);
//		int month = Integer.parseInt(calDate.split("-")[1]);
//
//		ArrayList<String[]> calRentalNumList = carRentalDAO
//				.getMonthCarRentalNum("" + year, "" + month);
//
//		ArrayList<MonthCarRental> monthCarRentalList = new ArrayList<MonthCarRental>();
//
//		MonthCarRental carRental = null;
//
//		System.out.println("size"+calRentalNumList.size());
//		
//		for (String[] calRentalNum : calRentalNumList) {
//			carRental = new MonthCarRental();
//			carRental.setCarId(Integer.parseInt(calRentalNum[0]));
//			carRental.setRentalNum(Integer.parseInt(calRentalNum[1]));
//			carRental.setYear(year);
//			carRental.setMonth(month);
//
//			monthCarRentalList.add(carRental);
//
//		}
//		
//		IMonthCarRentalDAO monthCarRentalDAO = new MonthCarRentalDAO();
//		
//		monthCarRentalDAO.insert(monthCarRentalList);
		
//		orderDAO.updateIsCancelByDaiChuCheTimeOut(1, 1);
		
		//////////每日统计营业门店收益////////////////////////
//		System.out.println("do CalDayStoreProfitJob");
//
//		Date d = new Date();// 获取当前日期
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(d);
//		calendar.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
//		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//		String calDate = sdf.format(d);// 前一天的日期
//
//		int year = Integer.parseInt(calDate.split("-")[0]);
//		int month = Integer.parseInt(calDate.split("-")[1]);
//		int day = Integer.parseInt(calDate.split("-")[2]);
//
//		
//		ArrayList<String[]> calStoreProfitList = orderDAO.getDayStoreProfit(calDate);
//		
//		ArrayList<DayStoreProfit> dayStoreProfitList = new ArrayList<DayStoreProfit>();
//
//		DayStoreProfit storeProfit = null;
//
//		for (String[] calStoreProfit : calStoreProfitList) {
//System.out.println(Integer.parseInt(calStoreProfit[0]));
//			storeProfit = new DayStoreProfit();
//			storeProfit.setStoreId(Integer.parseInt(calStoreProfit[0]));
//			storeProfit.setSumMoney(Float.parseFloat(calStoreProfit[1]));
//			storeProfit.setYear(year);
//			storeProfit.setMonth(month);
//			storeProfit.setDay(day);
//
//			dayStoreProfitList.add(storeProfit);
//
//		}
//
//		IDayStoreProfitDAO dayStoreProfitDAO = new DayStoreProfitDAO();
//
//		System.out.println("1");
//		System.out.println(dayStoreProfitDAO.insert(dayStoreProfitList));
//		System.out.println("2");
		
		
		
		System.out.println("do MonthStoreProfitJob");

		// IDayCarRentalDAO carRentalDAO = new DayCarRentalDAO();
		IDayStoreProfitDAO storeProfitDAO = new DayStoreProfitDAO();

		Date d = new Date();// 获取当前日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String calDate = sdf.format(d);

		int year = Integer.parseInt(calDate.split("-")[0]);
		int month = Integer.parseInt(calDate.split("-")[1]);

		ArrayList<String[]> calStoreProfitList = storeProfitDAO
				.getMonthStoreProfitByDate("" + year + "" + month);

		ArrayList<MonthStoreProfit> monthStoreProfitList = new ArrayList<MonthStoreProfit>();

		MonthStoreProfit storeProfit = null;

		System.out.println("size" + calStoreProfitList.size());

		for (String[] calStoreProfit : calStoreProfitList) {
			storeProfit = new MonthStoreProfit();
			storeProfit.setStoreId(Integer.parseInt(calStoreProfit[0]));
			storeProfit.setSumMoney(Float.parseFloat(calStoreProfit[1]));
			storeProfit.setYear(year);
			storeProfit.setMonth(month);

			monthStoreProfitList.add(storeProfit);

		}

		IMonthStoreProfitDAO monthStoreProfitDAO = new MonthStoreProfitDAO();

		monthStoreProfitDAO.insert(monthStoreProfitList);
	}
}
