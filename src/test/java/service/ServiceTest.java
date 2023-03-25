package service;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ServiceTest  {

    @Mock
    private StudentXMLRepo studentXMLRepository;

    @Mock
    private TemaXMLRepo temaXMLRepository;

    @Mock
    private NotaXMLRepo notaXMLRepository;

    @InjectMocks
    private Service victim;

    @BeforeEach
    void setUp() {
        initMocks(this);
        victim = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    void shouldAddStudent() {
        // given
        var id = "id";
        var nume = "nume";
        var grupa = 1;
        Student student = new Student(id, nume, grupa);

        // when
        when(studentXMLRepository.save(any())).thenReturn(student);

        // actual
        int result = victim.saveStudent(id, nume, grupa);

        assertEquals(0, result);
        verify(studentXMLRepository).save(student);
    }

    @Test
    void shouldFailToAddStudent() {
        // given
        var id = "id";
        var nume = "nume";
        var grupa = 1;
        Student student = new Student(id, nume, grupa);

        // when
        when(studentXMLRepository.save(any())).thenReturn(null);

        // actual
        int result = victim.saveStudent(id, nume, grupa);

        assertEquals(1, result);
        verify(studentXMLRepository).save(student);
    }
}