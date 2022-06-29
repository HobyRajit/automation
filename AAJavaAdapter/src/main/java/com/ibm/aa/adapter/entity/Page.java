/**
 * 
 */
package com.ibm.aa.adapter.entity;

/**
 * Placeholder for Page
 * 
 * @author Rakesh R Pai
 *
 */
public class Page {

	private int offset;
	private int total;
	private int totalFilter;
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the totalFilter
	 */
	public int getTotalFilter() {
		return totalFilter;
	}
	/**
	 * @param totalFilter the totalFilter to set
	 */
	public void setTotalFilter(int totalFilter) {
		this.totalFilter = totalFilter;
	}
}
