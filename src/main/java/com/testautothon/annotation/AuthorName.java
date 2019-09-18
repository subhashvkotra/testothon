package com.testautothon.annotation;

public enum AuthorName {

	Subhash("Subhash Kotra"),
	
	Harish("Harish Rokkam"),
	
	Thrinadh("Thrinadh Kota");

	private String authorName;

	AuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String toString() {
		return this.authorName;
	}
}
