package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.DayRegister;

public interface IDayRegisterDAO {

	public boolean insert(DayRegister register);

	public ArrayList<String[]> getAllDayRegisterList(int pageSize,
			int currentPage);

	public Object[] getDayRegisterCount();

	public ArrayList<String[]> getDayRegisterByContidion(String beginTime,
			String endTime, int pageSize, int currentPage);

	public Object[] getDayRegisterByConditionRsCount(String beginTime,
			String endTime);

	public ArrayList<String[]> getMonthRegisterByCondition(String beginDate,
			String endDate, int pageSize, int currentPage);

	public Object[] getMonthRegisterByConditionRsCount(String beginDate,
			String endDate);
}
