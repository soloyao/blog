package com.zmy.pojo;

/**
 * @ClassName:Archive
 * @Description:文章归档
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:22:08
 */
public class Archive {
	private int id;
	private String archiveName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArchiveName() {
		return archiveName;
	}
	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
	public Archive(int id, String archiveName) {
		super();
		this.id = id;
		this.archiveName = archiveName;
	}
	public Archive() {
		super();
	}
}
