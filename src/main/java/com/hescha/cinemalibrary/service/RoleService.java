package com.hescha.cinemalibrary.service;

import com.hescha.cinemalibrary.model.Role;
import com.hescha.cinemalibrary.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService extends CrudService<Role> {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Role> findByRole(String role) {
        return repository.findByRole(role);
    }

    public List<Role> findByRoleContains(String role) {
        return repository.findByRoleContains(role);
    }

    public Role update(Long id, Role entity) {
        Role read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Role not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Role entity, Role read) {
        read.setRole(entity.getRole());
    }
}
