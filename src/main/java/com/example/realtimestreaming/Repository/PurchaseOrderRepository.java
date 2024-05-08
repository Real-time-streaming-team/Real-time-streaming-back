package com.example.realtimestreaming.Repository;

import com.example.realtimestreaming.Domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}

