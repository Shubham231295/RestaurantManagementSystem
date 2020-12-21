package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.dto.MenuCartDTO;
import com.app.pojos.Cart;

public interface ICartDao extends JpaRepository<Cart, Integer>{
	
	@Query("select new com.app.dto.MenuCartDTO(menu,cart) from Menu menu, Cart cart where cart.menu_id=menu.menu_id")
	List<MenuCartDTO> getMenuCartDetails();
	
	@Query("select new com.app.dto.MenuCartDTO(menu,cart) from Menu menu, Cart cart where cart.menu_id=menu.menu_id and menu.menu_id=:id")
	MenuCartDTO getMenuCartDetailsById(int id);
}
