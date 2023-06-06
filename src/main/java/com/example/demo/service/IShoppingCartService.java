package com.example.demo.service;

import com.example.demo.entity.CartItem;

import java.math.BigDecimal;
import java.util.Collection;

public interface IShoppingCartService {
    // add cart
    void add(CartItem item);

    void remove(int maSanPham);

    // update cart
    CartItem update(Integer maSanPham, int soLuongCapNhat);

    // clear cart
    void clear();

    // get cart
    Collection<CartItem> getAll();

    //     count cart items
    int getCount();

    //     get total money
    BigDecimal getAmount();

}
