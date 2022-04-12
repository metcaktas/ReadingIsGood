package com.getir.intw.books.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.regex.Matcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void register_WhenCustomerName_ThenReturnSuccessWithCustomerInfo() throws Exception {
        when(customerRepository.findByEmail(any())).thenReturn(Optional.empty());
        mockMvc.perform(post("/customer/register?email=cust").contentType(MediaType.APPLICATION_JSON)
                        .header("AUTHORIZATION", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    public void register_WhenCustomerNameEmpty_ThenReturnFailEmptyName() throws Exception {
        mockMvc.perform(post("/customer/register?email=").contentType(MediaType.APPLICATION_JSON)
                        .header("AUTHORIZATION", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ERROR"));
    }

    @Test
    public void orders_WhenCustomerHasOrders_ThenReturnOrdersList() throws Exception {
        mockMvc.perform(get("/customer/orders?email=Emre&page=0&size=10").contentType(MediaType.APPLICATION_JSON)
                        .header("AUTHORIZATION", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

}
