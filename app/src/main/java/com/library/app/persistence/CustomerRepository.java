
package com.library.app.persistence;

import java.util.List;

import com.library.domain.users.User;

public interface CustomerRepository {
	List<User> getUserList();

	User getUser(Long id);

	void deleteUser(Long id);

	void save(User user);
}
