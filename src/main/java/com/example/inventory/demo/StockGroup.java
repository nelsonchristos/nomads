package com.example.inventory.demo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class StockGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long groupId;
	private String groupName;
	@OneToMany(mappedBy = "group",fetch=FetchType.LAZY)
	private Set<Stock> stocks;

	protected StockGroup() {
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public static class Builder {
		private String groupName;
		private Set<Stock> stocks;

		public Builder groupName(String groupName) {
			this.groupName = groupName;
			return this;
		}

		public Builder stocks(Set<Stock> stocks) {
			this.stocks = stocks;
			return this;
		}

		public StockGroup build() {
			return new StockGroup(this);
		}
	}

	private StockGroup(Builder builder) {
		this.groupName = builder.groupName;
		this.stocks = builder.stocks;
	}
}
