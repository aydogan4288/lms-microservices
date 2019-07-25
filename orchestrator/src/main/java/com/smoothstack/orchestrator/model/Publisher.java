package com.smoothstack.orchestrator.model;

import java.util.List;

public class Publisher {

	private Integer publisherId;

	private String publisherName;

	private String publisherAddress;

	public Publisher() {
		super();
	}

	public Publisher(Integer publisherId, String publisherName, String publisherAddress, List<Book> books) {
		super();
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.publisherAddress = publisherAddress;
//		this.books = books;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
}