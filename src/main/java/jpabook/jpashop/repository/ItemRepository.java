package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.ItemDeleteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;
    private EntityManager em1;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public void delete(Long id){
        Item findItem = findOne(id);
        if (findItem.getOrderedCount() != 0){
            throw new ItemDeleteException("주문 상태의 상품입니다.");
        }
        else em.remove(findItem);
        }
    }
