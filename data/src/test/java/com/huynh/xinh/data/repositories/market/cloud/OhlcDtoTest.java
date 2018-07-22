package com.huynh.xinh.data.repositories.market.cloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huynh.xinh.data.repositories.market.responses.OhlcResponse;

import org.junit.Test;

import static org.junit.Assert.*;

public class OhlcDtoTest {

    @Test
    public void testParseOhlc(){
        String json = mockJsonOhlc();

        OhlcResponse response = new Gson().fromJson(json, OhlcResponse.class);
    }

    private String mockJsonOhlc(){
        return "{\n" +
                "  \"result\": {\n" +
                "    \"60\": [\n" +
                "      [\n" +
                "        1506685380,\n" +
                "        27350,\n" +
                "        27350,\n" +
                "        27350,\n" +
                "        27350,\n" +
                "        0.0312,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506685440,\n" +
                "        27697.6,\n" +
                "        27697.6,\n" +
                "        27697.6,\n" +
                "        27697.6,\n" +
                "        0.699,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506685500,\n" +
                "        27699,\n" +
                "        27699,\n" +
                "        27689,\n" +
                "        27699,\n" +
                "        1.7090001,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506685560,\n" +
                "        27699,\n" +
                "        27777,\n" +
                "        27400,\n" +
                "        27776.99,\n" +
                "        0.0951,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506685620,\n" +
                "        27501,\n" +
                "        27501,\n" +
                "        27501,\n" +
                "        27501,\n" +
                "        0.0286,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506685800,\n" +
                "        27510,\n" +
                "        27777,\n" +
                "        27501,\n" +
                "        27777,\n" +
                "        1.7219999,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506685860,\n" +
                "        27777,\n" +
                "        27777,\n" +
                "        27650,\n" +
                "        27650,\n" +
                "        0.023,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506685920,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        0.0158,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506686100,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        0.015,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506686160,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        27511,\n" +
                "        0.0208,\n" +
                "        0\n" +
                "      ],\n" +
                "      [\n" +
                "        1506686220,\n" +
                "        27512,\n" +
                "        27512,\n" +
                "        27312,\n" +
                "        27312,\n" +
                "        5,\n" +
                "        0\n" +
                "      ]\n" +
                "    ]\n" +
                "  },\n" +
                "  \"allowance\": {\n" +
                "    \"cost\": 19271835,\n" +
                "    \"remaining\": 7563528087\n" +
                "  }\n" +
                "}";
    }
}