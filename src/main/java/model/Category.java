package web.test.model;

import javax.persistence.*;

import java.io.Serializable;



@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {


    @Id
    private Long id;

    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO toDTO(){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(this.name);
        categoryDTO.setId(this.id);
        return categoryDTO;
    }
}
