package top.roothk.ikdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.roothk.ikdemo.entity.NotAllowed;

@Repository
public interface NotAllowedRepository extends JpaRepository<NotAllowed, Long> {

}
