package com.tianyoukeji.parent.entity.template;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tianyoukeji.parent.entity.base.IBaseEntity;

@Entity
@Table(name = "timer_template", uniqueConstraints= {@UniqueConstraint(columnNames= {"source_state_template_id","code"})})
@EntityListeners(AuditingEntityListener.class)
public class TimerTemplate implements IBaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uuid")
	private Long uuid;
	
	@CreatedDate
	@Column(name = "created_at")
	private Date createdAt;

	@LastModifiedDate
	@Column(name = "updated_at")
	private Date updatedAt;

	@Version
	@Column(name = "version")
	private Long version;
	
	@Column(name = "entity")
	private String entity;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "source_state_template_id")
	private StateTemplate source;
	
	@Column(name = "action")
	private String action;

	@Column(name = "time_interval")
	private Integer timerInterval;
	
	@Column(name = "time_once")
	private Integer timerOnce;
	


	public Integer getTimerOnce() {
		return timerOnce;
	}

	public void setTimerOnce(Integer timerOnce) {
		this.timerOnce = timerOnce;
	}



	public StateTemplate getSource() {
		return source;
	}

	public void setSource(StateTemplate source) {
		this.source = source;
	}

	public Integer getTimerInterval() {
		return timerInterval;
	}

	public void setTimerInterval(Integer timerInterval) {
		this.timerInterval = timerInterval;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}
