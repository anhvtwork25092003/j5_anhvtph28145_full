package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.viewmodels.HoaDonViewModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = """
          	SELECT [Order].OrderId, [Order].CreatedDate, [account].fullname, SUM(OrderDetail.DonGia * OrderDetail.SoLuong) AS TongTien
              	FROM [Order]
              	JOIN OrderDetail ON [Order].OrderId = OrderDetail.order_id
              	JOIN DaQuy ON OrderDetail.daquy_id = DaQuy.id
              	JOIN account ON [account].id = [Order].userid
              	GROUP BY [Order].OrderId, [Order].CreatedDate, [account].fullname;
                        """
            , nativeQuery = true)
    List<Object[]> getHoaDonData();

    default List<HoaDonViewModels> getHoaDon() {
        List<Object[]> hoaDonData = getHoaDonData();
        List<HoaDonViewModels> hoaDonViewModelsList = new ArrayList<>();

        for (Object[] row : hoaDonData) {
            HoaDonViewModels hoaDonViewModel = new HoaDonViewModels();
            hoaDonViewModel.setId((Integer) row[0]);
            hoaDonViewModel.setCreatedDate((Date) row[1]);
            hoaDonViewModel.setFullname((String) row[2]);
            hoaDonViewModel.setTongTien((BigDecimal) row[3]);
            hoaDonViewModelsList.add(hoaDonViewModel);
        }

        return hoaDonViewModelsList;
    }

}
