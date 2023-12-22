package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
    /**
     * 값 타입
     */
    @Column(length = 10) // 장점 - 공통 룰을 관리 가능
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    // 장점  - 의미있는 비즈니스 메서드를 만들 수 있음.
    public String fullAddress(){
        return getCity() + " " + getStreet() + " " + getZipcode();
    }
    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    // setter는 만들어도 되고 안 만들어도 됨. 대신 만든다면 private.
    // equals와 hashCode 필수 -> 생상할 때 use getter 사용 권장.
    /* 사용 x : 필드에 직접 접근 -> proxy에서는 불가
    * getter를 통해 접근하면 proxy 객체가 진짜 객체에 접근 가능
    * */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
