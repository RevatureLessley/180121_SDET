package com.trms.services;

import java.util.List;

import com.trms.beans.EventType;
import com.trms.daos.EventTypeDao;
import com.trms.daos.EventTypeDaoImpl;

public class EventTypeService {
	public static List<EventType> getEventTypes() {
		EventTypeDao dao = new EventTypeDaoImpl();
		
		return dao.getEventTypes();
	}
}
