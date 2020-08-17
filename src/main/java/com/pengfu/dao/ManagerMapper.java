package com.pengfu.dao;

import com.pengfu.entity.Manager;

public interface ManagerMapper {

	Manager selectByUsername(String username);

}
