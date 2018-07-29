package com.huynh.xinh.trader.ui.detail;

import com.huynh.xinh.domain.models.EnumPeriod;
import com.huynh.xinh.trader.ui.detail.model.ItemTabPeriodTimeViewModel;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.huynh.xinh.domain.models.EnumPeriod._12_H;
import static com.huynh.xinh.domain.models.EnumPeriod._15_M;
import static com.huynh.xinh.domain.models.EnumPeriod._1_D;
import static com.huynh.xinh.domain.models.EnumPeriod._1_H;
import static com.huynh.xinh.domain.models.EnumPeriod._1_M;
import static com.huynh.xinh.domain.models.EnumPeriod._1_W;
import static com.huynh.xinh.domain.models.EnumPeriod._2_H;
import static com.huynh.xinh.domain.models.EnumPeriod._30_M;
import static com.huynh.xinh.domain.models.EnumPeriod._3_D;
import static com.huynh.xinh.domain.models.EnumPeriod._3_M;
import static com.huynh.xinh.domain.models.EnumPeriod._4_H;
import static com.huynh.xinh.domain.models.EnumPeriod._5_M;
import static com.huynh.xinh.domain.models.EnumPeriod._6_H;
import static org.junit.Assert.assertEquals;

public class DetailPairMapperTest {

    @Test
    public void toItemTabPeriodTimeViewModel() {
        List<EnumPeriod> periodTimeEnums = Arrays.asList(_1_M, _3_M, _5_M, _15_M, _30_M, _1_H, _2_H, _4_H, _6_H, _12_H, _1_D, _3_D, _1_W);
        List<ItemTabPeriodTimeViewModel> itemTabPeriodTimeViewModels = DetailPairMapper.INSTANCE.toItemTabPeriodTimeViewModels(periodTimeEnums);

        assertEquals(13, itemTabPeriodTimeViewModels.size());

        assertEquals("_1_M", itemTabPeriodTimeViewModels.get(0).getName());
        assertEquals(60, itemTabPeriodTimeViewModels.get(0).getValue());

        assertEquals("_3_M", itemTabPeriodTimeViewModels.get(1).getName());
        assertEquals(180, itemTabPeriodTimeViewModels.get(1).getValue());

        assertEquals("_5_M", itemTabPeriodTimeViewModels.get(2).getName());
        assertEquals(300, itemTabPeriodTimeViewModels.get(2).getValue());

        assertEquals("_15_M", itemTabPeriodTimeViewModels.get(3).getName());
        assertEquals(900, itemTabPeriodTimeViewModels.get(3).getValue());

        assertEquals("_30_M", itemTabPeriodTimeViewModels.get(4).getName());
        assertEquals(1800, itemTabPeriodTimeViewModels.get(4).getValue());

        assertEquals("_1_H", itemTabPeriodTimeViewModels.get(5).getName());
        assertEquals(3600, itemTabPeriodTimeViewModels.get(5).getValue());

        assertEquals("_2_H", itemTabPeriodTimeViewModels.get(6).getName());
        assertEquals(7200, itemTabPeriodTimeViewModels.get(6).getValue());

        assertEquals("_4_H", itemTabPeriodTimeViewModels.get(7).getName());
        assertEquals(14400, itemTabPeriodTimeViewModels.get(7).getValue());

        assertEquals("_6_H", itemTabPeriodTimeViewModels.get(8).getName());
        assertEquals(21600, itemTabPeriodTimeViewModels.get(8).getValue());

        assertEquals("_12_H", itemTabPeriodTimeViewModels.get(9).getName());
        assertEquals(43200, itemTabPeriodTimeViewModels.get(9).getValue());

        assertEquals("_1_D", itemTabPeriodTimeViewModels.get(10).getName());
        assertEquals(86400, itemTabPeriodTimeViewModels.get(10).getValue());

        assertEquals("_3_D", itemTabPeriodTimeViewModels.get(11).getName());
        assertEquals(259200, itemTabPeriodTimeViewModels.get(11).getValue());

        assertEquals("_1_W", itemTabPeriodTimeViewModels.get(12).getName());
        assertEquals(604800, itemTabPeriodTimeViewModels.get(12).getValue());
    }
}