package com.ssafy.ws.model;

import java.util.List;

public class BookDto {
	private String isbn;
	private String title;
	private String author;
	private int price;
	private String content;
	private List<FileInfoDto> fileInfoDtos;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<FileInfoDto> getFileInfoDtos() {
		return fileInfoDtos;
	}

	public void setFileInfoDtos(List<FileInfoDto> fileInfoDtos) {
		this.fileInfoDtos = fileInfoDtos;
	}

}
