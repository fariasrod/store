package com.app.integrated;

import com.spec.model.PriceRequest;
import com.spec.model.PriceResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static com.app.factory.PriceFactory.priceRequest;
import static com.app.factory.PriceFactory.priceResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceTest {

    public static final String URL = "/prices";

    @Autowired
    private TestRestTemplate template;

    PriceResponse priceResponse;

    @BeforeAll
    public void init() {
        priceResponse = priceResponse();
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price THEN return a price [200] | integrated test")
    public void WHEN_trying_to_retrieve_a_price_THEN_ok() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 1, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), priceResponse.getId());
        assertEquals(response.getBody().getStartDate(), priceResponse.getStartDate());
        assertEquals(response.getBody().getEndDate(), priceResponse.getEndDate());
        assertEquals(response.getBody().getCurr(), priceResponse.getCurr());
        assertEquals(response.getBody().getPrice(), priceResponse.getPrice());
        assertEquals(response.getBody().getBrandId(), priceResponse.getBrandId());
        assertEquals(response.getBody().getProductId(), priceResponse.getProductId());
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price THEN no content [204] | integrated test")
    public void WHEN_trying_to_retrieve_a_price_THEN_no_content() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2030, 6, 14, 16, 0, 0), 1, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price with a non existent brand THEN not found [404] | integrated test")
    public void WHEN_trying_to_retrieve_a_price_with_non_existent_brand_THEN_not_found() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 100, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price with a non existent product THEN not found [404] | integrated test")
    public void WHEN_trying_to_retrieve_a_price_with_non_existent_product_THEN_not_found() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 1, 100));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price THEN return a price [200] | integrated test case 1")
    public void WHEN_trying_to_retrieve_a_price_case_1_THEN_ok() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 1, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 1);
        assertEquals(response.getBody().getStartDate(), LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        assertEquals(response.getBody().getEndDate(), LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        assertEquals(response.getBody().getCurr(), "EUR");
        assertEquals(response.getBody().getPrice(), "35.50");
        assertEquals(response.getBody().getBrandId(), 1);
        assertEquals(response.getBody().getProductId(), 35455);
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price THEN return a price [200] | integrated test case 2")
    public void WHEN_trying_to_retrieve_a_price_case_2_THEN_ok() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 1, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 2);
        assertEquals(response.getBody().getStartDate(), LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        assertEquals(response.getBody().getEndDate(), LocalDateTime.of(2020, 6, 14, 18, 30, 0));
        assertEquals(response.getBody().getCurr(), "EUR");
        assertEquals(response.getBody().getPrice(), "25.45");
        assertEquals(response.getBody().getBrandId(), 1);
        assertEquals(response.getBody().getProductId(), 35455);
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price THEN return a price [200] | integrated test case 3")
    public void WHEN_trying_to_retrieve_a_price_case_3_THEN_ok() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 14, 21, 0, 0), 1, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 1);
        assertEquals(response.getBody().getStartDate(), LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        assertEquals(response.getBody().getEndDate(), LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        assertEquals(response.getBody().getCurr(), "EUR");
        assertEquals(response.getBody().getPrice(), "35.50");
        assertEquals(response.getBody().getBrandId(), 1);
        assertEquals(response.getBody().getProductId(), 35455);
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price THEN return a price [200] | integrated test case 4")
    public void WHEN_trying_to_retrieve_a_price_case_4_THEN_ok() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 15, 10, 0, 0), 1, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 3);
        assertEquals(response.getBody().getStartDate(), LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        assertEquals(response.getBody().getEndDate(), LocalDateTime.of(2020, 6, 15, 11, 0, 0));
        assertEquals(response.getBody().getCurr(), "EUR");
        assertEquals(response.getBody().getPrice(), "30.50");
        assertEquals(response.getBody().getBrandId(), 1);
        assertEquals(response.getBody().getProductId(), 35455);
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price THEN return a price [200] | integrated test case 5")
    public void WHEN_trying_to_retrieve_a_price_case_5_THEN_ok() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 15, 10, 0, 0), 1, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 3);
        assertEquals(response.getBody().getStartDate(), LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        assertEquals(response.getBody().getEndDate(), LocalDateTime.of(2020, 6, 15, 11, 0, 0));
        assertEquals(response.getBody().getCurr(), "EUR");
        assertEquals(response.getBody().getPrice(), "30.50");
        assertEquals(response.getBody().getBrandId(), 1);
        assertEquals(response.getBody().getProductId(), 35455);
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price THEN return a price [200] | integrated test case 6")
    public void WHEN_trying_to_retrieve_a_price_case_6_THEN_ok() {
        HttpEntity<PriceRequest> httpEntity = new HttpEntity<>(priceRequest(LocalDateTime.of(2020, 6, 16, 21, 0, 0), 1, 35455));

        ResponseEntity<PriceResponse> response = this.template
                .exchange(URL, HttpMethod.POST, httpEntity, PriceResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 4);
        assertEquals(response.getBody().getStartDate(), LocalDateTime.of(2020, 6, 15, 16, 0, 0));
        assertEquals(response.getBody().getEndDate(), LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        assertEquals(response.getBody().getCurr(), "EUR");
        assertEquals(response.getBody().getPrice(), "38.95");
        assertEquals(response.getBody().getBrandId(), 1);
        assertEquals(response.getBody().getProductId(), 35455);
    }
}
