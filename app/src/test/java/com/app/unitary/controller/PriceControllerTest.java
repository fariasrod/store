package com.app.unitary.controller;

import com.app.core.domain.PriceDomainRequest;
import com.app.core.service.PriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.app.factory.PriceFactory.priceDomain;
import static com.app.factory.PriceFactory.priceResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    public static final String URL = "/prices";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceService service;


    @Test
    @DisplayName("WHEN trying to retrieve a price THEN return a price [200] | unitary test")
    public void WHEN_trying_to_retrieve_a_price_THEN_ok() throws Exception {
        given(service.getPricesByDateAndBrandIdAndProductId(any(PriceDomainRequest.class))).willReturn(priceDomain());

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\": \"2030-06-14T16:00:00Z\","
                                + "\"brandId\": 1,"
                                + "\"productId\": 35455}"))
                .andExpect(jsonPath("$.startDate", is("2020-06-14T15:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-06-14T18:30:00")))
                .andExpect(jsonPath("$.curr", is(priceResponse().getCurr())))
                .andExpect(jsonPath("$.price", is(priceResponse().getPrice())))
                .andExpect(jsonPath("$.brandId", is(priceResponse().getBrandId())))
                .andExpect(jsonPath("$.productId", is(priceResponse().getProductId())))
                .andExpect(status().isOk());
        verify(service, times(1)).getPricesByDateAndBrandIdAndProductId(any(PriceDomainRequest.class));
    }
}
