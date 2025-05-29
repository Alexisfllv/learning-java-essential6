package edu.com.javaesencial07salesapi.dto.sale;

import java.math.BigDecimal;

public record SellerStatsDTO(
        String nameUser,
        BigDecimal totalSales,
        Integer salesCount
) {}
