package com.getir.intw.books.order;

import com.getir.intw.books.book.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderRepository orderRepository;

    @Test
    public void newOrder_WhenPostNewOrder_ThenReturnSuccessWithOrderInfo() throws Exception {
        when(orderRepository.insertNewOrder(any())).thenReturn(new Order());
        mockMvc.perform(post("/order/new-order").contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                        "    \"email\": \"Emre\",\n" +
                        "    \"orderedBooks\": [\n" +
                        "        {\n" +
                        "            \"name\": \"Cien Años de Soledad\",\n" +
                        "            \"price\": 21.87\n" +
                        "            }\n" +
                        "    ]\n" +
                        "}")
                        .header("AUTHORIZATION", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    public void getOrderById_WhenGetOrderId_ThenReturnSuccessWithOrderInfo() throws Exception {
        when(orderRepository.findById(any())).thenReturn(Optional.of(new Order()));
        mockMvc.perform(get("/order/123").contentType(MediaType.APPLICATION_JSON)
                        .header("AUTHORIZATION", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    public void getOrderById_WhenOrderNotExist_ThenReturnErrorNoSuchElement() throws Exception {
        when(orderRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(get("/order/123").contentType(MediaType.APPLICATION_JSON)
                        .header("AUTHORIZATION", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ERROR"));
    }
}
