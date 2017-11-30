package com.elv.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.elv.models.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long>{

	public Student findBySname(String sname);
}
