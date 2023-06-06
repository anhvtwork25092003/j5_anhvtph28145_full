package com.example.demo.service;


import com.example.demo.entity.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartService implements IShoppingCartService {
    Map<Integer, CartItem> maps = new HashMap<>();

    // add cart
    @Override
    public void add(CartItem item) {
        CartItem cartItem = maps.get(item.getMaSanPham());
        if (cartItem != null) {
            cartItem.setSoLuong(cartItem.getSoLuong() + 1);

        } else {
            maps.put(item.getMaSanPham(), item);

        }
    }

    @Override
    public void remove(int maSanPham) {
        maps.remove(maSanPham);
    }

    // update cart
    @Override
    public CartItem update(Integer maSanPham, int soLuongCapNhat) {
        CartItem cartItem = maps.get(maSanPham);
        cartItem.setSoLuong(soLuongCapNhat);
        return cartItem;
    }

    // clear cart
    @Override
    public void clear() {
        maps.clear();
    }


    // get cart
    @Override
    public Collection<CartItem> getAll() {
        return maps.values();
    }

    //     count cart items
    @Override
    public int getCount() {
        return maps.values().size();
    }

    //     get total money
    @Override
    public BigDecimal getAmount() {
        return maps.values().stream()
                .map(item -> new BigDecimal(item.getSoLuong()).multiply(item.getDonGia()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
