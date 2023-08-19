package com.example.api.controller

import com.example.api.dto.CreateMenuRequest
import com.example.api.dto.EmptyResponse
import com.example.api.service.MenuService
import com.example.domain.entity.Menu
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "메뉴 API", description = "메뉴")
@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/menus")
class MenuController(
    val menuService: MenuService,
) {
    @Operation(description = "메뉴 추가")
    @PostMapping
    fun createMenu(@RequestBody request: CreateMenuRequest): Menu {
        return menuService.createMenu(request)
    }

    @Operation(description = "메뉴 수정")
    @PutMapping("/{menuId}")
    fun updateMenu(@RequestBody request: CreateMenuRequest, @PathVariable menuId: Long): Menu {
        return menuService.updateMenu(request, menuId)
    }

    @Operation(description = "메뉴 삭제")
    @DeleteMapping("/{menuId}")
    fun deleteMenu(@PathVariable menuId: Long): EmptyResponse {
        menuService.deleteMenu(menuId)
        return EmptyResponse
    }

    @Operation(description = "메뉴 전체 조회")
    @GetMapping
    fun getAllMenu(): List<Menu> {
        return menuService.getAllMenu()
    }
}
