package com.example.java26.week3.rest.APIHW.repository.Impl;

import com.example.java26.week3.rest.APIHW.exception.ResourceNotFoundException;
import com.example.java26.week3.rest.APIHW.pojo.entity.Student;
import com.example.java26.week3.rest.APIHW.repository.StudentRepository;

import org.springframework.stereotype.Repository;

import org.hibernate.jpa.HibernatePersistenceProvider;

import org.postgresql.ds.PGSimpleDataSource;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private DataSource dataSource;
    private Properties properties;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    private DataSource getDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("7777");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mydb");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/example/java26/week3/rest.APIHW.pojo.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties);
        em.setPersistenceUnitName("student-unit");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public StudentRepositoryImpl() {
        dataSource = getDataSource();
        properties = getProperties();
        entityManagerFactory = entityManagerFactory(dataSource, properties);
        em = entityManagerFactory.createEntityManager();
    }

    @Override
    public Student getById(int id) {
        Query query = em.createQuery("select s from Student s where s.id = ?1");
        Student s = (Student) query.setParameter(1, id).getSingleResult();
        if (s == null)
            throw new ResourceNotFoundException ("not found");
        return s;
    }

    @Override
    public Collection<Student> getAll() {
        Query query = em.createQuery("select s from Student s");
        Collection<Student> studentList = query.getResultList();
        if (studentList == null)
            throw new ResourceNotFoundException ("not found");
        return studentList;

    }

    @Override
    public String insert(Student s) {
        // insert a student
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student stu = new Student();
        stu.setId(s.getId());
        stu.setName(s.getName());;
        em.merge(stu);
        tx.commit();
        return "success inserted!";
    }

    @Override
    public String update(Student s, int id) {
        // update student by id passed
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s where s.id = ?1");
        Student stu = (Student) query.setParameter(1, id).getSingleResult();
        stu.setName(s.getName());
        em.merge(stu);
        tx.commit();
        return "success updated!";
    }

    @Override
    public String deleteById(int id) {
        // delete student
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = em.find(Student.class, id);
        em.remove(s);
        tx.commit();
        return "success deleted!";
    }

}
