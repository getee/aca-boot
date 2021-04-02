package com.aca.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aca.user.entity.Role;
import com.aca.user.repository.RoleRepository;

@Component
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repos;

	@Override
	public Role create(Role role) {
		return repos.save(role);
	}

	@Override
	public Role update(Role role) {
		return repos.save(role);
	}

	@Override
	public void delete(Long id) {
		repos.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repos.deleteAll();
	}
}
