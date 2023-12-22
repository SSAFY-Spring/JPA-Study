package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속관계 싱글테이블 전략
@Inheritance(strategy = InheritanceType.JOINED) //상속관계 싱글테이블 전략
@DiscriminatorColumn
public abstract class Item { //Item 만 단독으로 저장할 일이 없다고 가정 => 추상 클래스로 생성.

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    //다대다 양방향
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
