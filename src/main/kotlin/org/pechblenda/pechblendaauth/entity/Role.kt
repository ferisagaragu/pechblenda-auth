package org.pechblenda.pechblendaauth.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.ManyToMany

import java.util.UUID

@Entity
@Table(name = "roles")
class Role(
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var uuid: UUID,
	var name: String,

	@ManyToMany(mappedBy = "roles")
	var users: MutableList<User>?
)
