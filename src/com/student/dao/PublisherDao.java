package com.student.dao;

import java.util.List;


import com.student.entiy.PageEntiy;
import com.student.entiy.Publisher;

public interface PublisherDao {
	public List<Publisher> findAllPublisher(PageEntiy entiy);
	
	public boolean deleteByPublisherId(String id);

	public boolean addPublisher(Publisher publisher);

	public boolean updateByPublisherId(Publisher publisher);

	public int findtotalPublishers(String id);
	
	public Publisher findOnePublisher(String id,PageEntiy entiy);
}
