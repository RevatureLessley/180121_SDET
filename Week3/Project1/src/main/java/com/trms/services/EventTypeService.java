package com.trms.services;

import java.util.List;

import com.trms.beans.EventType;
import com.trms.beans.EventTypeDaoImpl;
import com.trms.daos.EventTypeDao;

public class EventTypeService {
	public static List<EventType> getEventTypes() {
		EventTypeDao dao = new EventTypeDaoImpl();
		
		return dao.getEventTypes();
	}
}
