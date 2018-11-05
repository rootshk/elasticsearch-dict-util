package top.roothk.ikdemo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 允许的字典
 */
@Data
@Entity
@Table(name = "dict_allow")
public class Allow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字段
     */
    private String name;

}
