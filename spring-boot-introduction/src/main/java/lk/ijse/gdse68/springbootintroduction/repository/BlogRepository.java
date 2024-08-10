package lk.ijse.gdse68.springbootintroduction.repository;

import lk.ijse.gdse68.springbootintroduction.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
}

