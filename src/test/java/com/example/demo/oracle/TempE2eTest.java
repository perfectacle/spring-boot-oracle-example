package com.example.demo.oracle;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TempE2eTest extends AbstractE2eTest {
    @Test
    public void 이미_db에_저장된_데이터는_조회할_수_있다() throws Exception {
        final long id = 1L;

        final ResultActions result = whenFind(id);

        thenIsOk(result);
        thenHasId(result, id);
    }

    private ResultActions whenFind(final long id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/temps/" + id))
                      .andDo(MockMvcResultHandlers.print());
    }

    private void thenIsOk(final ResultActions result) throws Exception {
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void thenHasId(final ResultActions result, final long id) throws Exception {
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    public void temp를_저장한_후에_조회할_수_있다() throws Exception {
        final long id = 99L;

        whenSave(id);
        System.out.println("---------------------------");
        final ResultActions result = whenFind(id);

        thenIsOk(result);
        thenHasId(result, id);
    }

    private void whenSave(final long id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/temps/" + id)).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 존재하지_않는_temp는_조회_불가능하다() throws Exception {
        final long id = 77L;

        final ResultActions result = whenFind(id);

        thenIsOk(result);
        thenHasNotId(result);
    }

    private void thenHasNotId(final ResultActions result) throws Exception {
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());
    }
}
