package org.pechblenda.pechblendaauth.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping

import org.pechblenda.exception.HttpExceptionResponse
import org.pechblenda.service.Request
import org.pechblenda.doc.annotation.ApiDocumentation
import org.pechblenda.pechblendaauth.service.`interface`.IRoleService

import java.util.UUID

@CrossOrigin(methods = [
	RequestMethod.GET
])
@RestController
@RequestMapping(name = "Role", value = ["/rest/roles"])
class RoleController(
	private val roleService: IRoleService,
	private val httpExceptionResponse: HttpExceptionResponse
) {

	@GetMapping(value = ["/{roleUuid}", ""])
	@ApiDocumentation(path = "doc/role/find-all-roles-by-uuid.json")
	fun findAllRolesByUuid(
		@PathVariable roleUuid: UUID
	): ResponseEntity<Any> {
		return try {
			roleService.findAllRolesByUuid(roleUuid)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

}
