package com.example.inventory.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.envers.Audited;

@Entity
@Audited
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "groupId", nullable = false)
	private StockGroup group;
	private Long count;
	private Integer lowerLimit;
	private Boolean isActive;

	protected Stock() {
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StockGroup getGroup() {
		return group;
	}

	public void setGroup(StockGroup group) {
		this.group = group;
	}

	public Integer getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}



	public static class Builder {
		private String name;
		private StockGroup group;
		private Integer lowerLimit;
		private Long count;
		private Boolean isActive;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder group(StockGroup group) {
			this.group = group;
			return this;
		}

		public Builder lowerLimit(Integer lowerLimit) {
			this.lowerLimit = lowerLimit;
			return this;
		}
		
		public Builder count(Long count) {
			this.count = count;
			return this;
		}

		public Builder isActive(Boolean isActive) {
			this.isActive = isActive;
			return this;
		}

		public Stock build() {
			return new Stock(this);
		}
	}

	private Stock(Builder builder) {
		this.name = builder.name;
		this.group = builder.group;
		this.lowerLimit = builder.lowerLimit;
		this.isActive = builder.isActive;
		this.count = builder.count;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", group=" + group + ", count=" + count + ", lowerLimit="
				+ lowerLimit + ", isActive=" + isActive + "]";
	}
	
	
}
