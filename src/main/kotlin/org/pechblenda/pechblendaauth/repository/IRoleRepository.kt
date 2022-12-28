package org.pechblenda.pechblendaauth.repository

import org.pechblenda.pechblendaauth.entity.Role

import org.springframework.data.jpa.repository.JpaRepository

import java.util.UUID
import java.util.Optional

interface IRoleRepository: JpaRepository<Role, UUID> {
	fun findRoleByName(name: String): Optional<Role>
}
