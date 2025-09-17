package com.erp.users.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@EntityListeners(com.erp.users.audit.AuditAwareImpl.class)
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "business_id")
	private Long businessId;
	
	@Column(name = "user_type")
	private String userType;
	
	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private String createdBy;
	@LastModifiedBy
	@Column(name = "updated_by", insertable = false)
	private String updatedBy;
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	@LastModifiedDate
	@Column(name = "updated_at", insertable = false)
	private LocalDateTime updatedAt;
	
	
}
