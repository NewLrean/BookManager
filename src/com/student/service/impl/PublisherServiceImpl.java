package com.student.service.impl;

import java.util.List;

import com.student.dao.PublisherDao;
import com.student.dao.impl.PublisherDaoImpl;
import com.student.entiy.PageEntiy;
import com.student.entiy.Publisher;
import com.student.service.PublisherService;

public class PublisherServiceImpl implements PublisherService {

	PublisherDao publisherDao=new PublisherDaoImpl();
	@Override
	public List<Publisher> findAllPublisher(PageEntiy entiy) {
		// TODO Auto-generated method stub
		return publisherDao.findAllPublisher(entiy);
	}

	@Override
	public boolean deleteByPublisherId(String id) {
		// TODO Auto-generated method stub
		return publisherDao.deleteByPublisherId(id);
	}

	@Override
	public boolean addPublisher(Publisher publisher) {
		// TODO Auto-generated method stub
		return publisherDao.addPublisher(publisher);
	}

	@Override
	public boolean updateByPublisherId(Publisher publisher) {
		// TODO Auto-generated method stub
		return publisherDao.updateByPublisherId(publisher);
	}

	@Override
	public int findtotalPublishers(String id) {
		// TODO Auto-generated method stub
		return publisherDao.findtotalPublishers(id);
	}

	@Override
	public Publisher findOnePublisher(String id, PageEntiy entiy) {
		// TODO Auto-generated method stub
		return publisherDao.findOnePublisher(id, entiy);
	}

}
