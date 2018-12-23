package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.TruequeApp;

import io.github.jhipster.application.domain.Trueque;
import io.github.jhipster.application.repository.TruequeRepository;
import io.github.jhipster.application.service.TruequeService;
import io.github.jhipster.application.service.dto.TruequeDTO;
import io.github.jhipster.application.service.mapper.TruequeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Estado;
/**
 * Test class for the TruequeResource REST controller.
 *
 * @see TruequeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TruequeApp.class)
public class TruequeResourceIntTest {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final Estado DEFAULT_ESTADO = Estado.OFERTA;
    private static final Estado UPDATED_ESTADO = Estado.CONCRETADO;

    @Autowired
    private TruequeRepository truequeRepository;

    @Autowired
    private TruequeMapper truequeMapper;

    @Autowired
    private TruequeService truequeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTruequeMockMvc;

    private Trueque trueque;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TruequeResource truequeResource = new TruequeResource(truequeService);
        this.restTruequeMockMvc = MockMvcBuilders.standaloneSetup(truequeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trueque createEntity(EntityManager em) {
        Trueque trueque = new Trueque()
            .fecha(DEFAULT_FECHA)
            .estado(DEFAULT_ESTADO);
        return trueque;
    }

    @Before
    public void initTest() {
        trueque = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrueque() throws Exception {
        int databaseSizeBeforeCreate = truequeRepository.findAll().size();

        // Create the Trueque
        TruequeDTO truequeDTO = truequeMapper.toDto(trueque);
        restTruequeMockMvc.perform(post("/api/trueques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(truequeDTO)))
            .andExpect(status().isCreated());

        // Validate the Trueque in the database
        List<Trueque> truequeList = truequeRepository.findAll();
        assertThat(truequeList).hasSize(databaseSizeBeforeCreate + 1);
        Trueque testTrueque = truequeList.get(truequeList.size() - 1);
        assertThat(testTrueque.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testTrueque.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createTruequeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = truequeRepository.findAll().size();

        // Create the Trueque with an existing ID
        trueque.setId(1L);
        TruequeDTO truequeDTO = truequeMapper.toDto(trueque);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTruequeMockMvc.perform(post("/api/trueques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(truequeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trueque in the database
        List<Trueque> truequeList = truequeRepository.findAll();
        assertThat(truequeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = truequeRepository.findAll().size();
        // set the field null
        trueque.setFecha(null);

        // Create the Trueque, which fails.
        TruequeDTO truequeDTO = truequeMapper.toDto(trueque);

        restTruequeMockMvc.perform(post("/api/trueques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(truequeDTO)))
            .andExpect(status().isBadRequest());

        List<Trueque> truequeList = truequeRepository.findAll();
        assertThat(truequeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTrueques() throws Exception {
        // Initialize the database
        truequeRepository.saveAndFlush(trueque);

        // Get all the truequeList
        restTruequeMockMvc.perform(get("/api/trueques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trueque.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getTrueque() throws Exception {
        // Initialize the database
        truequeRepository.saveAndFlush(trueque);

        // Get the trueque
        restTruequeMockMvc.perform(get("/api/trueques/{id}", trueque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trueque.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrueque() throws Exception {
        // Get the trueque
        restTruequeMockMvc.perform(get("/api/trueques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrueque() throws Exception {
        // Initialize the database
        truequeRepository.saveAndFlush(trueque);

        int databaseSizeBeforeUpdate = truequeRepository.findAll().size();

        // Update the trueque
        Trueque updatedTrueque = truequeRepository.findById(trueque.getId()).get();
        // Disconnect from session so that the updates on updatedTrueque are not directly saved in db
        em.detach(updatedTrueque);
        updatedTrueque
            .fecha(UPDATED_FECHA)
            .estado(UPDATED_ESTADO);
        TruequeDTO truequeDTO = truequeMapper.toDto(updatedTrueque);

        restTruequeMockMvc.perform(put("/api/trueques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(truequeDTO)))
            .andExpect(status().isOk());

        // Validate the Trueque in the database
        List<Trueque> truequeList = truequeRepository.findAll();
        assertThat(truequeList).hasSize(databaseSizeBeforeUpdate);
        Trueque testTrueque = truequeList.get(truequeList.size() - 1);
        assertThat(testTrueque.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testTrueque.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingTrueque() throws Exception {
        int databaseSizeBeforeUpdate = truequeRepository.findAll().size();

        // Create the Trueque
        TruequeDTO truequeDTO = truequeMapper.toDto(trueque);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTruequeMockMvc.perform(put("/api/trueques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(truequeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trueque in the database
        List<Trueque> truequeList = truequeRepository.findAll();
        assertThat(truequeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrueque() throws Exception {
        // Initialize the database
        truequeRepository.saveAndFlush(trueque);

        int databaseSizeBeforeDelete = truequeRepository.findAll().size();

        // Get the trueque
        restTruequeMockMvc.perform(delete("/api/trueques/{id}", trueque.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Trueque> truequeList = truequeRepository.findAll();
        assertThat(truequeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trueque.class);
        Trueque trueque1 = new Trueque();
        trueque1.setId(1L);
        Trueque trueque2 = new Trueque();
        trueque2.setId(trueque1.getId());
        assertThat(trueque1).isEqualTo(trueque2);
        trueque2.setId(2L);
        assertThat(trueque1).isNotEqualTo(trueque2);
        trueque1.setId(null);
        assertThat(trueque1).isNotEqualTo(trueque2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TruequeDTO.class);
        TruequeDTO truequeDTO1 = new TruequeDTO();
        truequeDTO1.setId(1L);
        TruequeDTO truequeDTO2 = new TruequeDTO();
        assertThat(truequeDTO1).isNotEqualTo(truequeDTO2);
        truequeDTO2.setId(truequeDTO1.getId());
        assertThat(truequeDTO1).isEqualTo(truequeDTO2);
        truequeDTO2.setId(2L);
        assertThat(truequeDTO1).isNotEqualTo(truequeDTO2);
        truequeDTO1.setId(null);
        assertThat(truequeDTO1).isNotEqualTo(truequeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(truequeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(truequeMapper.fromId(null)).isNull();
    }
}
