package com.example.api.service

import com.example.api.dto.CreateMenuRequest
import com.example.domain.entity.Menu
import com.example.domain.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class MenuService(
    val menuRepository: MenuRepository,
) {
    fun createMenu(request: CreateMenuRequest): Menu {
        val menu = Menu(
            menuName = request.menuName,
            menuPrice = request.menuPrice,
            description = request.description,
            imageUrl = request.imageUrl,
            requiredTime = request.requiredTime,
        )
        return menuRepository.save(menu)
    }

    fun updateMenu(request: CreateMenuRequest, menuId: Long): Menu {
        val menu = Menu(
            id = menuId,
            menuName = request.menuName,
            menuPrice = request.menuPrice,
            description = request.description,
            imageUrl = request.imageUrl,
            requiredTime = request.requiredTime,
        )
        return menuRepository.save(menu)
    }

    fun deleteMenu(menuId: Long) {
        menuRepository.deleteById(menuId)
    }

    fun getAllMenu(): List<Menu> {
        return menuRepository.findAll().toList()
    }
}
