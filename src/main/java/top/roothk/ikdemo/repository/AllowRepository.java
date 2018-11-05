package top.roothk.ikdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.roothk.ikdemo.entity.Allow;

@Repository
public interface AllowRepository extends JpaRepository<Allow, Long> {

}
