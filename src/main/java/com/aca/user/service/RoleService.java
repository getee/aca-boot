package com.aca.user.service;

import com.aca.user.entity.Role;

public interface RoleService {

	Role create(Role role);

	Role update(Role role);

	void delete(Long id);

	void deleteAll();
}
