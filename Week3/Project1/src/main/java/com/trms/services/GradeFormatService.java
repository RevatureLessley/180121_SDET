package com.trms.services;

import java.util.List;

import com.trms.beans.GradeFormat;
import com.trms.daos.GradeFormatDao;
import com.trms.daos.GradeFormatDaoImpl;

public class GradeFormatService {
	public static List<GradeFormat> getGradeFormats() {
		GradeFormatDao dao = new GradeFormatDaoImpl();
		
		return dao.getGradeFormats();
	}
}
